FILESEXTRAPATHS:prepend := "${THISDIR}/files:"

SRC_URI += "file://pironman5"

# pironman5 pro max settings.
do_install:append() {
    if [ "${PIRONMAN5_VARIANT}" = "promax" ]; then
        install -d ${D}${sysconfdir}/sudoers.d
        install -m 0440 ${WORKDIR}/pironman5 ${D}${sysconfdir}/sudoers.d/pironman5
    fi
}

FILES:${PN}:append = " \
    ${@bb.utils.contains('PIRONMAN5_VARIANT', 'promax', ' ${sysconfdir}/sudoers.d/pironman5', '', d)} \
"