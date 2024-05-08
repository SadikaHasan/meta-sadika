SUMMARY="Thermometer with HTU21D and SSD1306 OLED I2C display"
DESCRIPTION = "Show temperature and humidity on mini OLED I2C display"
HOMEPAGE = "https://github.com/SadikaHasan/DisplayText"
SECTION = "console/utils"
LICENSE = "GPL-2.0-or-later"

LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

SRC_URI = " \
	git://github.com/SadikaHasan/DisplayText.git;protocol=https;branch=main \
"
SRCREV = "d2395cf97dfbf0fe7029f10cd206b1a0b6d63356"

inherit systemd

S = "${WORKDIR}/git"

# coreutils provides fmt which is used in the Makefile
DEPENDS += "i2c-tools coreutils-native"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/displaytext ${D}${bindir}

	if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
		# Install systemd service
		install -d ${D}${systemd_unitdir}/system
		install -m 644 ${S}/displaytext.service ${D}${systemd_unitdir}/system
	fi
}

SYSTEMD_AUTO_ENABLE = "enable"
SYSTEMD_SERVICE:${PN} = "displaytext.service"
