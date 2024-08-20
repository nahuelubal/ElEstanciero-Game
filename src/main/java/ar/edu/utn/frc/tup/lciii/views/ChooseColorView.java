package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

import java.util.LinkedList;
@Data
public class ChooseColorView extends AbstractView {

        private DefineMoneyToWinView  defineMoneyToWinView ;

        public ChooseColorView(){
            super();
            defineMoneyToWinView = new DefineMoneyToWinView();
        }

    @Override
    public void render() {
        int colorQuantity = Color.values().length;
        int optionsQuantity = colorQuantity + 1;
        String colorQuantityString = Integer.toString(optionsQuantity);

        String selectedOption;
        ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
        ConsolePrinter.printlnBold("Now you must choose a color");
        ConsolePrinter.println("Which color do you want?");

        for (int i = 0; i < colorQuantity; i++) {
            String firstColor = Color.values()[i].name();
            String secondColor = Color.values()[i + 1].name();
            ConsolePrinter.println(ConsoleColor.CYAN.toString() + (i + 1) + ". " + ConsoleColor.RESET + firstColor + " ".repeat(12 - firstColor.length()) +
                    ConsoleColor.CYAN + (i + 2) + ". " + ConsoleColor.RESET + secondColor);
            i++;
        }

        ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + colorQuantityString + "." + ConsoleColor.RESET + " Back");

        System.out.print("✎... ");
        selectedOption = getScanner().nextLine();
        while (!InputValidator.isValidNumberInRange(selectedOption, 1, colorQuantity + 1)) {
            ConsolePrinter.println("Invalid number. Please enter a number between 1 and " + colorQuantityString + ".");
            System.out.print("✎... ");
            selectedOption = getScanner().nextLine();
        }

        if (!selectedOption.equals(colorQuantityString)) {
            Color selectedColor = Color.values()[Integer.parseInt(selectedOption) - 1];
            ConsolePrinter.println("Okay! You selected the color " + selectedColor.name());

            HumanPlayer humanPlayer = new HumanPlayer();
            humanPlayer.setColor(selectedColor);
            humanPlayer.setMoneyAvailable(35000);
            humanPlayer.setIsActive(true);
            humanPlayer.setPlayerOrder(0);
            humanPlayer.setPrisoner(false);

            Game.getInstance().setPlayers(new LinkedList<>());
            Game.getInstance().getPlayers().add(humanPlayer);
        }
        defineMoneyToWinView.render();
    }
}

