# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Library for monitoring Raspberry Pi system status"
HOMEPAGE = "https://github.com/sunfounder/pironman5"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/sunfounder/pironman5.git;protocol=https;branch=main \
           file://0001-Change-path-to-avoid-using-the-venv.patch \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "cf1b40f11f06b4b83273bed097db589a0d099f9b"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi5)"

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    Adafruit-Blinka
#    RPi.GPIO
#    adafruit-circuitpython-neopixel-spi
#    psutil
#    smbus2
RDEPENDS:${PN} += " \
    python3-adafruit-blinka \
    rpi-gpio \
    python3-adafruit-circuitpython-neopixel-spi \
    python3-psutil \
    python3-smbus2 \
"

# other dependencies
# lsblk in 'util-linux' is needed to get disk informations.
RDEPENDS:${PN} += " \
    bash \
    util-linux \
"

# Add install process.

SYSTEMD_SERVICE:${PN} = "pironman5.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install:append(){
    # pironman5 service is automatically started by systemd
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    install -m 644 ${S}/bin/pironman5.service ${D}${sysconfdir}/systemd/system/
    ln -s ${sysconfdir}/systemd/system/pironman5.service \
        ${D}${sysconfdir}/systemd/system/multi-user.target.wants/pironman5.service

    # program which is called by pironman5 service
    install -d ${D}${bindir}
    install -m 755 ${S}/bin/pironman5 ${D}${bindir}

    # device tree overlay
    install -d ${D}/boot/firmware/overlays
    install -m 644 ${S}/sunfounder-pironman5.dtbo ${D}/boot/firmware/overlays/

    # load i2c-dev on boot using modules-load.d instead of /etc/modules
    install -d ${D}${sysconfdir}/modules-load.d
    echo "i2c-dev" >> ${D}${sysconfdir}/modules-load.d/i2c-dev.conf
}

FILES:${PN} += " /boot/firmware/overlays/sunfounder-pironman5.dtbo "
