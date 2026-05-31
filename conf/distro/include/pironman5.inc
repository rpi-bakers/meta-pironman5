
require conf/distro/rpi-build.conf

BBMASK += "/meta-raspberrypi/dynamic-layers/multimedia-layer/recipes-multimedia/libcamera/libcamera_.*\.bbappend"

# pironman5 specific settings
ENABLE_UART = "1"
ENABLE_SPI_BUS = "1"
ENABLE_I2C = "1"
ENABLE_IR = "1"
GPIO_IR = "13"

IMAGE_INSTALL:append = " \
    python3-pironman5 \
    python3-pm-auto \
    python3-pm-dashboard \
    python3-sf-rpi-status \
    python3-gpiozero \
    lgpio \
    lirc \
"
