# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Library for Sunfounder Power Control"
HOMEPAGE = "https://github.com/sunfounder/pm_dashboard"
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "GPL-2.0-only & MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
                    file://pm_dashboard/www/static/js/main.4925da66.js.LICENSE.txt;md5=0a5f677f039c44d8bf39bea04dd61322"

SRC_URI = "git://github.com/sunfounder/pm_dashboard.git;protocol=https;branch=1.2.x"

# Modify these as desired
PV = "1.2.10+git${SRCPV}"
SRCREV = "6f9e40eaf742e8ca985b069fc0db9b0c885dacec"

S = "${WORKDIR}/git"

inherit python_setuptools_build_meta

COMPATIBLE_MACHINE = "(raspberrypi5)"

# WARNING: We were unable to map the following python package/module
# runtime dependencies to the bitbake packages which include them:
#    flask
#    flask_cors
#    influxdb
#    pyudev

RDEPENDS:${PN} += " \
    python3-flask \
    python3-flask-cors \
    python3-influxdb \
    python3-pyudev \
"

# extra commands needed by the pm_dashboard
# pkill      : procps
# lsof       : lsof
# grep, cut  : coreutils
# awk        : gawk
RDEPENDS:${PN} += " \
    procps \
    lsof \
    coreutils \
    gawk \
"