# Common metadata shared by versioned recipes for python3-pironman5.
# Keep SRC_URI, PV, and SRCREV in versioned .bb files.

# Package metadata.
SUMMARY = "Library for monitoring Raspberry Pi system status"
HOMEPAGE = "https://github.com/sunfounder/pironman5"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

# Build settings.
S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi3|raspberrypi5)"

# Runtime dependencies.
RDEPENDS:${PN} += " \
    python3-adafruit-blinka \
    rpi-gpio \
    python3-adafruit-circuitpython-neopixel-spi \
    python3-psutil \
    python3-smbus2 \
"

# Runtime support commands.
# lsblk in util-linux is needed to get disk information.
RDEPENDS:${PN} += " \
    bash \
    util-linux \
"

# Systemd service settings.
SYSTEMD_SERVICE:${PN} = "pironman5.service"
SYSTEMD_AUTO_ENABLE = "enable"

do_install:append(){
    # Install and enable systemd unit.
    # pironman5 service is automatically started by systemd
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    install -m 644 ${S}/bin/pironman5.service ${D}${sysconfdir}/systemd/system/
    ln -s ${sysconfdir}/systemd/system/pironman5.service \
        ${D}${sysconfdir}/systemd/system/multi-user.target.wants/pironman5.service

    # Load i2c-dev on boot via modules-load.d.
    install -d ${D}${sysconfdir}/modules-load.d
    echo "i2c-dev" >> ${D}${sysconfdir}/modules-load.d/i2c-dev.conf
}

FILES:${PN} += " /boot/firmware/overlays/${DT_OVERLAYS} "
