# Common metadata shared by versioned recipes for python3-pm-dashboard.
# Keep SRC_URI, PV, and SRCREV in versioned .bb files.

# Package metadata.
SUMMARY = "Library for Sunfounder Power Control"
HOMEPAGE = "https://github.com/sunfounder/pm_dashboard"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

# Build settings.
S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi3|raspberrypi5)"

# Runtime dependencies.
RDEPENDS:${PN} += " \
    python3-flask \
    python3-flask-cors \
    python3-influxdb \
    python3-pyudev \
"

RDEPENDS:${PN} += " \
    procps \
    lsof \
    coreutils \
    gawk \
"
