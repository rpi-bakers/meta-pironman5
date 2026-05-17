require python3-pironman5-common.inc

SRC_URI = " \
    git://github.com/sunfounder/pironman5.git;protocol=https;branch=pro-max \
"

PV = "1.3.7${SRCPV}"
SRCREV = "5f83b6a5a026d672a1bb20c61e7ee21d5c43bae7"

inherit useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-r pironman5"
USERADD_PARAM:${PN} = "-r -g pironman5 -d /opt/pironman5 -s /sbin/nologin pironman5"

do_install:append(){
    # Install variant-selected device tree overlay.
    install -d ${D}/boot/firmware/overlays
    install -m 0644 ${S}/overlays/${DT_OVERLAYS} ${D}/boot/firmware/overlays/

    # add default log dir and config dir.
    install -d ${D}/opt/pironman5
    install -d ${D}/var/log/pironman5
    touch ${D}/var/log/pironman5/pironman5.log

    sed -i  \
        -e "s#/usr/local/bin/pironman5#${bindir}/pironman5#g" \
        -e "s#/opt/pironman5/venv/bin/pironman5-service#${bindir}/pironman5-service#g" \
        ${D}${sysconfdir}/systemd/system/pironman5.service
}

FILES:${PN} += " \
    /usr/local/bin/pironman5 \
    /opt/pironman5 \
"
