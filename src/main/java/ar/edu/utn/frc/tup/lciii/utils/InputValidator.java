package ar.edu.utn.frc.tup.lciii.utils;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;

import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {
	private static final String YES_NO_REGEX = "^[yYnN]$";
	private static final String NUMBER_REGEX = "\\d+";

	public static boolean isValidNumberInRange(String input, int min, int max) {
		String regex = buildRegexForRange(min, max);
		Pattern pattern = Pattern.compile(regex);

		return pattern.matcher(input).matches();
	}

	private static String buildRegexForRange(int min, int max) {
		String minStr = String.valueOf(min);
		String maxStr = String.valueOf(max);

		return "^[" + minStr + "-" + maxStr + "]$";
	}

	public static Boolean getYesNoAnswer() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("✎... ");
		String input = scanner.nextLine();
		Pattern pattern = Pattern.compile(YES_NO_REGEX);

		while (!pattern.matcher(input).matches()) {
			ConsolePrinter.printlnColor("✖ Invalid answer. Please enter 'y' or 'n'", ConsoleColor.BRIGHT_RED);
			System.out.print("✎... ");
			input = scanner.nextLine();
		}

		return input.equalsIgnoreCase("y");
	}

	public static Boolean isValidNumber(String input) {
		Pattern pattern = Pattern.compile(NUMBER_REGEX);
		return pattern.matcher(input).matches();
	}
}
