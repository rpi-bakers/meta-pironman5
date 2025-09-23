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
    oe_runmake
}


do_install() {
    export PYTHONPATH=${D}${PYTHON_SITEPACKAGES_DIR}:$PYTHONPATH
    oe_runmake install DESTDIR=${D} PYTHON_SITEPACKAGES_DIR=${D}${PYTHON_SITEPACKAGES_DIR}
}
