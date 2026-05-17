# Common metadata shared by versioned recipes for python3-pironman5.
# Keep SRC_URI, PV, and SRCREV in versioned .bb files.

# Package metadata.
SUMMARY = "Library for monitoring Raspberry Pi system status"
HOMEPAGE = "https://github.com/sunfounder/sf_rpi_status"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/sunfounder/sf_rpi_status.git;protocol=https;branch=main"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi3|raspberrypi5)"

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    psutil
#    pyudev
#    requests

RDEPENDS:${PN} += " \
    python3-psutil \
    python3-pyudev \
    python3-requests \
"
