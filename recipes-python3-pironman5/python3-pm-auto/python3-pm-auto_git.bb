# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Library for monitoring Raspberry Pi system status"
HOMEPAGE = "https://github.com/sunfounder/pm_auto"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/sunfounder/pm_auto.git;protocol=https;branch=1.2.x"

# Modify these as desired
PV = "1.2.12+git${SRCPV}"
SRCREV = "dc0c9e368b07f1a83ef41c32a625a0efbccd591a"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi5)"

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    Pillow
#    adafruit-circuitpython-neopixel-spi
#    gpiod
#    gpiozero
#    influxdb
#    psutil
#    rpi.lgpio
#    smbus2
RDEPENDS:${PN} += " \
    python3-pillow \
    python3-adafruit-circuitpython-neopixel-spi \
    python3-gpiod \
    python3-gpiozero \
    python3-influxdb \
    python3-psutil \
    lgpio \
    python3-smbus2 \
"