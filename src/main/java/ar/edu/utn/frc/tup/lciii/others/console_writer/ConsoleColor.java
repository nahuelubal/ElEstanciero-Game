package ar.edu.utn.frc.tup.lciii.others.console_writer;

public enum ConsoleColor {
	// Colors
	RESET("\033[0m"),
	BLACK("\033[0;30m"),
	RED("\033[0;31m"),
	GREEN("\033[0;32m"),
	YELLOW("\033[0;33m"),
	BLUE("\033[0;34m"),
	VIOLET("\033[0;35m"),
	CYAN("\033[0;36m"),
	WHITE("\033[0;37m"),
	BRIGHT_BLACK("\033[0;90m"),
	BRIGHT_RED("\033[0;91m"),
	BRIGHT_GREEN("\033[0;92m"),
	BRIGHT_YELLOW("\033[0;93m"),
	BRIGHT_BLUE("\033[0;94m"),
	BRIGHT_MAGENTA("\033[0;95m"),
	BRIGHT_CYAN("\033[0;96m"),
	BRIGHT_WHITE("\033[0;97m"),

	// Background Colors
	BLACK_BACKGROUND("\033[40m"),
	RED_BACKGROUND("\033[41m"),
	GREEN_BACKGROUND("\033[42m"),
	YELLOW_BACKGROUND("\033[43m"),
	BLUE_BACKGROUND("\033[44m"),
	VIOLET_BACKGROUND("\033[45m"),
	CYAN_BACKGROUND("\033[46m"),
	WHITE_BACKGROUND("\033[47m"),

	// Custom Colors
	ORANGE("\033[38;2;255;165;0m"),
	ORANGE_BACKGROUND("\033[48;2;255;165;0m");

	private final String code;

	ConsoleColor(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}
}

