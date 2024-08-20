package ar.edu.utn.frc.tup.lciii.others.console_writer;

import java.util.ArrayList;
import java.util.List;

public class ConsolePrinter {
    private static final String ANSI_REGEX = "\\u001B\\[[;\\d]*m";
    private static final String BOLD = "\033[0;1m";
    private static final String RESET_BOLD = "\033[22m";
    private static final Integer DEFAULT_LINE_SIZE = 175;

    private static String removeAnsiCodes(String input) {
        return input.replaceAll(ANSI_REGEX, "");
    }

    public static List<Integer> findCharacterIndices(StringBuilder input, String character) {
        List<Integer> indices = new ArrayList<>();

        int firstIndex = input.indexOf(character);
        int lastIndex = input.lastIndexOf(character);

        indices.add(firstIndex);
        indices.add(lastIndex);

        return indices;
    }

    private static void applyString(StringBuilder stringBuilder, String modifier, String reset) {
        List<Integer> indices = findCharacterIndices(stringBuilder, "◊");

        int firstIndex = indices.get(0);
        int lastIndex = indices.get(1);

        stringBuilder.insert(firstIndex + 1, modifier);
        stringBuilder.insert(lastIndex + modifier.length(), reset);


    }

    private static void applyColor(StringBuilder stringBuilder, ConsoleColor consoleColor) {
        if (consoleColor != null) {
            applyString(stringBuilder, consoleColor.toString(), ConsoleColor.RESET.toString());
        }
    }

    private static void applyBold(StringBuilder stringBuilder, Boolean isBold) {
        if (isBold) {
            applyString(stringBuilder, BOLD, RESET_BOLD);
        }
    }

    private static StringBuilder getString(String stringToPrint, ConsoleConfig consoleConfig, ConsoleColor consoleColor) {
        String plainString = removeAnsiCodes(stringToPrint);
        Align align = consoleConfig.getAlign();

        StringBuilder stringBuilder = new StringBuilder();

        switch (align) {
            case LEFT:
                int leftCaseSpaces = (consoleConfig.getSize() - plainString.length()) - 5;
                stringBuilder.append("◊  ");
                stringBuilder.append(stringToPrint);
                stringBuilder.append(" ".repeat(Math.max(0, leftCaseSpaces)));
                stringBuilder.append(" ◊");
                break;
            case CENTER:
                int centerCaseSpaces = (consoleConfig.getSize() - plainString.length()) - 4;
                int centerCaseLeftSpaces = centerCaseSpaces / 2;
                int centerCaseRightSpaces = centerCaseSpaces - centerCaseLeftSpaces;
                stringBuilder.append("◊ ");
                stringBuilder.append(" ".repeat(Math.max(0, centerCaseLeftSpaces)));
                stringBuilder.append(stringToPrint);
                stringBuilder.append(" ".repeat(Math.max(0, centerCaseRightSpaces)));
                stringBuilder.append(" ◊");
        }

        applyBold(stringBuilder, consoleConfig.isBold());
        applyColor(stringBuilder, consoleColor);

        return stringBuilder;
    }

    public static void println(String stringToPrint, ConsoleConfig consoleConfig) {
        StringBuilder stringBuilder = getString(stringToPrint, consoleConfig, null);
        LetterByLetterPrinter.println(stringBuilder.toString());
    }

    public static void println(String stringToPrint) {
        StringBuilder stringBuilder = getString(stringToPrint, new ConsoleConfig(), null);
        LetterByLetterPrinter.println(stringBuilder.toString());
    }

    public static void print(String stringToPrint) {
        StringBuilder stringBuilder = getString(stringToPrint, new ConsoleConfig(), null);
        LetterByLetterPrinter.print(stringBuilder.toString());
    }

    public static void printlnCentered(String stringToPrint, int size) {
        StringBuilder stringBuilder = getString(stringToPrint, new ConsoleConfig(Align.CENTER, size), null);
        LetterByLetterPrinter.println(stringBuilder.toString());
    }

    public static void printlnBold(String stringToPrint) {
        StringBuilder stringBuilder = getString(stringToPrint, new ConsoleConfig(Align.LEFT, true), null);
        LetterByLetterPrinter.println(stringBuilder.toString());
    }

    public static void printlnColor(String stringToPrint, ConsoleColor consoleColor) {
        StringBuilder stringBuilder = getString(stringToPrint, new ConsoleConfig(), consoleColor);
        LetterByLetterPrinter.println(stringBuilder.toString());
    }

    public static void printlnColor(String stringToPrint, ConsoleColor consoleColor, ConsoleConfig consoleConfig) {
        StringBuilder stringBuilder = getString(stringToPrint, consoleConfig, consoleColor);
        LetterByLetterPrinter.println(stringBuilder.toString());
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String buildLine(char character, int characterLength) {
        StringBuilder lineBuilder = new StringBuilder();

        for (int i = 0; i < characterLength; i++) {
            lineBuilder.append(character);
        }

        return lineBuilder.toString();
    }

    public static String buildLine(char character) {
        StringBuilder lineBuilder = new StringBuilder();

        for (int i = 0; i < DEFAULT_LINE_SIZE; i++) {
            lineBuilder.append(character);
        }

        return lineBuilder.toString();
    }
}
