SUMMARY = "LGPIO library for GPIO control"
DESCRIPTION = "LGPIO is a lightweight library for controlling GPIO pins on Raspberry Pi."
HOMEPAGE = "http://abyz.me.uk/lg/"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://UNLICENCE;md5=61287f92700ec1bdf13bc86d8228cd13"

SRC_URI = "http://abyz.me.uk/lg/lg.zip \
           file://0001-modify-for-bitbake-recipe.patch \
           "
SRC_URI[md5sum] = "ee8f96ea76b840022d67c0cbfd2382c6"
SRC_URI[sha256sum] = "bb31c6031b632911a4cbbd1d47ea326f1249b9f9efe1504eca83d1e0ef0394af"

S = "${WORKDIR}/lg"

DEPENDS = "python3 swig-native python3-setuptools-native python3-setuptools"

inherit python3native setuptools3

do_compile() {
    # Build C library + tools
    oe_runmake
    # Generate SWIG wrapper source for Python3 extension build
    cd ${S}/PY_LGPIO
    ${STAGING_BINDIR_NATIVE}/swig -python lgpio.i
}


do_install() {
    # Install C headers/libs/tools/manpages
    install -d ${D}${includedir} ${D}${libdir} ${D}${bindir} ${D}${mandir}/man1 ${D}${mandir}/man3
    install -m 0644 ${S}/lgpio.h ${D}${includedir}/
    install -m 0644 ${S}/rgpio.h ${D}${includedir}/
    install -m 0755 ${S}/liblgpio.so.1 ${D}${libdir}/
    install -m 0755 ${S}/librgpio.so.1 ${D}${libdir}/
    ln -sf liblgpio.so.1 ${D}${libdir}/liblgpio.so
    ln -sf librgpio.so.1 ${D}${libdir}/librgpio.so
    install -m 0755 ${S}/rgpiod ${D}${bindir}/
    install -m 0755 ${S}/rgs ${D}${bindir}/
    install -m 0644 ${S}/rgpiod.1 ${D}${mandir}/man1/
    install -m 0644 ${S}/rgs.1 ${D}${mandir}/man1/
    install -m 0644 ${S}/lgpio.3 ${D}${mandir}/man3/
    install -m 0644 ${S}/rgpio.3 ${D}${mandir}/man3/

    # Install Python modules without easy_install/.egg and without compiling .pyc
    export PYTHONDONTWRITEBYTECODE=1
    export PYTHONNOUSERSITE=1

    # Avoid embedding TMPDIR in debug symbols (e.g. via recorded compiler command lines)
    # and make sure any remaining paths are mapped away.
    export CFLAGS="${CFLAGS} -fno-record-gcc-switches -fdebug-prefix-map=${WORKDIR}=/usr/src/debug/${PN}/${PV} -fmacro-prefix-map=${WORKDIR}=/usr/src/debug/${PN}/${PV}"
    export CPPFLAGS="${CPPFLAGS} -fno-record-gcc-switches -fdebug-prefix-map=${WORKDIR}=/usr/src/debug/${PN}/${PV} -fmacro-prefix-map=${WORKDIR}=/usr/src/debug/${PN}/${PV}"
    export LDFLAGS="${LDFLAGS} -fno-record-gcc-switches"

    cd ${S}/PY_RGPIO
    ${PYTHON} setup.py -q install \
        --root=${D} \
        --prefix=${prefix} \
        --install-lib=${PYTHON_SITEPACKAGES_DIR} \
        --single-version-externally-managed \
        --record=${T}/rgpio-install-record.txt \
        --no-compile

    cd ${S}/PY_LGPIO
    ${PYTHON} setup.py -q build_ext \
        --include-dirs=${S} \
        --library-dirs=${S}
    ${PYTHON} setup.py -q install \
        --root=${D} \
        --prefix=${prefix} \
        --install-lib=${PYTHON_SITEPACKAGES_DIR} \
        --single-version-externally-managed \
        --record=${T}/lgpio-install-record.txt \
        --no-compile

    # Safety cleanup: remove any bytecode/egg artifacts that would trigger buildpaths QA
    find ${D}${PYTHON_SITEPACKAGES_DIR} -type d -name '__pycache__' -prune -exec rm -rf {} + || true
    find ${D}${PYTHON_SITEPACKAGES_DIR} -type f \( -name '*.pyc' -o -name '*.pyo' \) -delete || true
    find ${D}${PYTHON_SITEPACKAGES_DIR} -maxdepth 1 -type d -name '*.egg' -prune -exec rm -rf {} + || true
}
