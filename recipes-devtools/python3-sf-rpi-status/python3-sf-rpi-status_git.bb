# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Library for monitoring Raspberry Pi system status"
HOMEPAGE = "https://github.com/sunfounder/sf_rpi_status"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI = "git://github.com/sunfounder/sf_rpi_status.git;protocol=https;branch=main"

# Modify these as desired
PV = "1.1.0+git${SRCPV}"
SRCREV = "7f7fa9d42f2d62a6b5faf9efc6a01577fc2c38c1"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

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
