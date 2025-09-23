# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

SUMMARY = "Linux SBC GPIO module"
HOMEPAGE = "http://abyz.me.uk/lg/py_rgpio.html"
# NOTE: License in setup.py/PKGINFO is: unlicense.org
# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
# NOTE: Original package / source metadata indicates license is: unlicense.org
#
# NOTE: multiple licenses have been detected; they have been separated with &
# in the LICENSE value for now since it is a reasonable assumption that all
# of the licenses apply. If instead there is a choice between the multiple
# licenses then you should change the value to separate the licenses with |
# instead of &. If there is any doubt, check the accompanying documentation
# to determine which situation is applicable.
LICENSE = "Unlicense & unlicense.org"
LIC_FILES_CHKSUM = "file://LICENSE;md5=61287f92700ec1bdf13bc86d8228cd13"

SRC_URI[sha256sum] = "f0eab70749becff9f003bff6870bbfcb45483ae59edc6c043b6db80335a492ba"

inherit pypi setuptools3

# WARNING: the following rdepends are determined through basic analysis of the
# python sources, and might not be 100% accurate.
RDEPENDS:${PN} += "python3-core python3-crypt python3-io"

PYPI_PACKAGE = "rgpio"
