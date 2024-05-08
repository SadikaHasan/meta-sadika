SUMMARY = "A console-only image that fully supports the target device \
hardware and shows temperature on a mini OLED I2C SSD1306 display."

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image

IMAGE_INSTALL:append = " \
	nano vim \
	connman-client \
	i2c-tools ssd1306 \
	displaytext \
"

EXTRA_IMAGE_FEATURES += "ssh-server-dropbear debug-tweaks tools-debug"
