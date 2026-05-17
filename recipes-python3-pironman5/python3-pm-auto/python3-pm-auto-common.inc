# Common metadata shared by versioned recipes for python3-pm-auto.
# Keep SRC_URI, PV, and SRCREV in versioned .bb files.

# Package metadata.
SUMMARY = "Library for monitoring Raspberry Pi system status"
HOMEPAGE = "https://github.com/sunfounder/pm_auto"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

# Build settings.
S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi3|raspberrypi5)"

# Runtime dependencies.
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
