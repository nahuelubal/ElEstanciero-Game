package ar.edu.utn.frc.tup.lciii.others.console_writer;

public class LetterByLetterPrinter {

    public static void println(String text) {
        try {
            for (int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
                Thread.sleep(1);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(String text) {
        try {
            for (int i = 0; i < text.length(); i++) {
                System.out.print(text.charAt(i));
                Thread.sleep(1);
            }
            System.out.print("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
