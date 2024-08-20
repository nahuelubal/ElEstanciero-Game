package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.card.DeckOfCard;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {
    //Atributes
    private Integer idDashboard;
    private List<AbstractBox> boxes;
    private static Dashboard instance;
    private DeckOfCard luckyCards;
    private DeckOfCard destinyCards;

    private final Integer BOX_WIDTH = 25;
    private final Integer BOX_QUANTITY = 7;

    private static class Holder {
        private static Dashboard INSTANCE = new Dashboard();
    }

    public static Dashboard getInstance() {
        return Dashboard.Holder.INSTANCE;
    }

    public static void setInstance(Dashboard dashboard) {
        Dashboard.Holder.INSTANCE = dashboard;
    }

    //Methods
    public void paintBoxes() {
        List[] partitions = partition(boxes, BOX_QUANTITY);

        StringBuilder firstLine = new StringBuilder();
        StringBuilder secondLine = new StringBuilder();
        StringBuilder thirdLine = new StringBuilder();
        StringBuilder fourthLine = new StringBuilder();
        StringBuilder fifthLine = new StringBuilder();
        StringBuilder sixthLine = new StringBuilder();

        for (int i = 0; i < partitions.length; i++) {

            ArrayList<AbstractBox> boxesToDisplay = (ArrayList<AbstractBox>) partitions[i];

            for (AbstractBox box : boxesToDisplay) {
                sixthLine.append("-".repeat(BOX_WIDTH));
                firstLine.append("-".repeat(BOX_WIDTH));

                String boxNumber = box.getIdBox() - 1 < 1 ? "SALIDA" : (box.getIdBox() - 1) + "";

                secondLine.append(getStringBuilderLine(boxNumber));

                thirdLine.append(getStringBuilderLine(box.getDescription()));

                StringBuilder players = new StringBuilder();

                Integer playersQuantity = 0;
                for (AbstractPlayer p : box.getPlayers()) {
                    ConsoleColor color = ConsoleColor.valueOf(p.getColor().name());
                    players.append(color).append("◉ ").append(ConsoleColor.RESET);
                    playersQuantity += 2;
                }

                fourthLine.append(getStringBuilderLine(players.toString(), playersQuantity));

                if (box instanceof ProvinceProperty) {
                    StringBuilder upgrades = new StringBuilder();
                    Province province = (Province) ((ProvinceProperty) box).getDeed();
                    Integer upgradesQuantity = 0;
                    if (province.getHasRanch()) {
                        upgrades.append(ConsoleColor.YELLOW + "■ ").append(ConsoleColor.RESET);
                        upgradesQuantity = 2;
                    } else {
                        if (province.getFarmQuantity() != 0) {
                            for (int j = 0; j < province.getFarmQuantity(); j++) {
                                upgradesQuantity += 2;
                                upgrades.append(ConsoleColor.RED + "■ ").append(ConsoleColor.RESET);
                            }
                        }
                    }
                    fifthLine.append(getStringBuilderLine(upgrades.toString(), upgradesQuantity));

                } else {
                    fifthLine.append("| ")
                            .append(" ".repeat(BOX_WIDTH - 4))
                            .append(" |");
                }

            }

            System.out.println("◊  " + firstLine + "  ◊");
            System.out.println("◊  " + secondLine + "  ◊");
            System.out.println("◊  " + thirdLine + "  ◊");
            System.out.println("◊  " + fourthLine + "  ◊");
            System.out.println("◊  " + fifthLine + "  ◊");
            System.out.println("◊  " + sixthLine + "  ◊");

            firstLine.setLength(0);
            secondLine.setLength(0);
            thirdLine.setLength(0);
            fourthLine.setLength(0);
            fifthLine.setLength(0);
            sixthLine.setLength(0);
        }
    }

    private StringBuilder getStringBuilderLine(String text) {
        if (text == null) {
            text = "";
        }

        text = text.replaceAll("\\u001B\\[[;\\d]*m", "");

        int centerCaseSpaces = (BOX_WIDTH - text.length()) - 4;
        int centerCaseLeftSpaces = centerCaseSpaces / 2;
        int centerCaseRightSpaces = centerCaseSpaces - centerCaseLeftSpaces;

        StringBuilder line = new StringBuilder();
        return line.append("| ")
                .append(" ".repeat(Math.max(0, centerCaseLeftSpaces)))
                .append(text)
                .append(" ".repeat(Math.max(0, centerCaseRightSpaces)))
                .append(" |");
    }

    private StringBuilder getStringBuilderLine(String text, Integer quantity) {
        // este metodo es para cuando viene con color
        int centerCaseSpaces = (BOX_WIDTH - quantity) - 4;
        int centerCaseLeftSpaces = centerCaseSpaces / 2;
        int centerCaseRightSpaces = centerCaseSpaces - centerCaseLeftSpaces;

        StringBuilder line = new StringBuilder();
        return line.append("| ")
                .append(" ".repeat(Math.max(0, centerCaseLeftSpaces)))
                .append(text)
                .append(" ".repeat(Math.max(0, centerCaseRightSpaces)))
                .append(" |");
    }

    public static <T> List[] partition(List<T> list, int n) {
        // obtener el tamaño de la lista
        int size = list.size();

        // Calcular el número total de particiones `m` de tamaño `n` cada una
        int m = size / n;
        if (size % n != 0) {
            m++;
        }

        // crea `m` listas vacías e inicialízalas usando `List.subList()`
        ArrayList[] partition = new ArrayList[m];
        for (int i = 0; i < m; i++) {
            int fromIndex = i * n;
            int toIndex = Math.min(i * n + n, size);

            partition[i] = new ArrayList<>(list.subList(fromIndex, toIndex));
        }

        // devuelve las listas
        return partition;
    }

    public void movePlayerByBox(AbstractPlayer player, int idBox) {
        AbstractBox oldBox = findBoxByPlayer(player);
        if (oldBox != null) {
            oldBox.removePlayer(player);
            AbstractBox newBox = this.findBoxById(idBox);
            newBox.addPlayerToBox(player);
        }
    }

    /**
     * @param player
     * @param quantity cantidad de LUGARES que el player va a moverse, puede ser por rolldices o por la cantidad de lugares "n" que indique
     *                 una carta para que se mueva
     */
    public void movePlayerByQuantity(AbstractPlayer player, int quantity) {
        AbstractBox oldBox = findBoxByPlayer(player);
        if (oldBox != null) {
            int indexOldBox = oldBox.getIdBox();
            oldBox.removePlayer(player);
            int indexNewBox = quantity + indexOldBox;
            AbstractBox newBox;
            if (boxes.size() >= indexNewBox && indexNewBox >= 0) {
                newBox = this.findBoxById(indexNewBox);
                newBox.addPlayerToBox(player);
            } else if (indexNewBox < 0) {
                newBox = this.findBoxById(indexNewBox + boxes.size());
                newBox.addPlayerToBox(player);

            } else {
                newBox = this.findBoxById(indexNewBox - boxes.size());
                newBox.addPlayerToBox(player);
                player.receive(5000);
                ConsolePrinter.println("Player " +player.getColor() +" completed a lap and received $5000 from the bank");
                ConsolePrinter.println("Now the player " +player.getColor() +" has $" + player.getMoneyAvailable());
            }

        }
    }

    public AbstractBox findBoxByPlayer(AbstractPlayer player) {
        for (AbstractBox box : boxes) {
            if (box.getPlayers().contains(player)) {
                return box;
            }
        }

        return null;
    }

    public AbstractBox findBoxById(Integer id) {
        for (AbstractBox box : boxes) {
            if (box.getIdBox().equals(id)) {
                return box;
            }
        }

        return null;
    }

    /**
     * Este metodo se utiliza para instanciar una casilla en especifico, buscando dentro de un lista de
     * AbstractBox y devolviendo un boxType
     * Solo se utiliza para las casillas unicas como startBox, farmerPrizeBox o JailBOx
     *
     * @param boxType puede ser cualquier CLASE que extienda AbstractBox.
     * @return si encuentra una clase del tipo recibido como parametro, lo retorna.
     */
    public AbstractBox findBoxByType(Class<? extends AbstractBox> boxType) {
        for (AbstractBox box : boxes) {
            if (boxType.isInstance(box)) {
                return box;
            }
        }
        return null;
    }

}
