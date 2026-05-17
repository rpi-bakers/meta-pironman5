require python3-pironman5-common.inc

SRC_URI = "git://github.com/sunfounder/pironman5.git;protocol=https;branch=main \
            file://0001-Change-path-to-avoid-using-the-venv.patch \
            "

PV = "1.2.19${SRCPV}"
SRCREV = "cf1b40f11f06b4b83273bed097db589a0d099f9b"

do_install:append(){
    install -d ${D}${bindir}
    install -m 0755 ${S}/bin/pironman5 ${D}${bindir}

    # Install variant-selected device tree overlay.
    install -d ${D}/boot/firmware/overlays
    install -m 0644 ${S}/${DT_OVERLAYS} ${D}/boot/firmware/overlays/
}
