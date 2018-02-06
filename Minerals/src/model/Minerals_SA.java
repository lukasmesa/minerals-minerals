package model;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Insert class description here
 *
 * @author Lukas
 * @version Feb 4, 2018
 */
public class Minerals_SA {

    private final int totalMiners;
    private final int goldMiners;
    private final int silverMiners;
    private final int copperMiners;
    private final int jokerMiners;
    private final int capacityGold;
    private final int capacitySilver;
    private final int capacityCopper;
    private final String chargeUnit;
    private final int goldGain;
    private final int silverGain;
    private final int copperGain;
    private LinkedList<Mine> mines;

    public Minerals_SA() {
        this.totalMiners = 0;
        this.goldMiners = 0;
        this.silverMiners = 0;
        this.copperMiners = 0;
        this.jokerMiners = 0;
        this.capacityGold = 0;
        this.capacitySilver = 0;
        this.capacityCopper = 0;
        this.chargeUnit = "";
        this.goldGain = 0;
        this.silverGain = 0;
        this.copperGain = 0;
        this.mines = new LinkedList<>();
    }

    public Minerals_SA(int totalMiners, int goldMiners, int silverMiners, int copperMiners, int jokerMiners, int capacityGold, int capacitySilver, int capacityCopper, String chargeUnit, int goldGain, int silverGain, int copperGain) {
        this.totalMiners = totalMiners;
        this.goldMiners = goldMiners;
        this.silverMiners = silverMiners;
        this.copperMiners = copperMiners;
        this.jokerMiners = jokerMiners;
        this.capacityGold = capacityGold;
        this.capacitySilver = capacitySilver;
        this.capacityCopper = capacityCopper;
        this.chargeUnit = chargeUnit;
        this.goldGain = goldGain;
        this.silverGain = silverGain;
        this.copperGain = copperGain;
        this.mines = new LinkedList<>();
    }

    /**
     * @return the totalMiners
     */
    public int getTotalMiners() {
        return totalMiners;
    }

    /**
     * @return the goldMiners
     */
    public int getGoldMiners() {
        return goldMiners;
    }

    /**
     * @return the silverMiners
     */
    public int getSilverMiners() {
        return silverMiners;
    }

    /**
     * @return the copperMiners
     */
    public int getCopperMiners() {
        return copperMiners;
    }

    /**
     * @return the jokerMiners
     */
    public int getJokerMiners() {
        return jokerMiners;
    }

    /**
     * @return the capacityGold
     */
    public int getCapacityGold() {
        return capacityGold;
    }

    /**
     * @return the capacitySilver
     */
    public int getCapacitySilver() {
        return capacitySilver;
    }

    /**
     * @return the capacityCopper
     */
    public int getCapacityCopper() {
        return capacityCopper;
    }

    /**
     * @return the chargeUnit
     */
    public String getChargeUnit() {
        return chargeUnit;
    }

    /**
     * @return the goldGain
     */
    public int getGoldGain() {
        return goldGain;
    }

    /**
     * @return the silverGain
     */
    public int getSilverGain() {
        return silverGain;
    }

    /**
     * @return the copperGain
     */
    public int getCopperGain() {
        return copperGain;
    }

    /**
     * @return the mines
     */
    public LinkedList<Mine> getMines() {
        return mines;
    }

    /**
     * @param mines the mines to set
     */
    public void setMines(LinkedList<Mine> mines) {
        this.mines = mines;
    }

    /**
     * Calcula todas las posibles ganancias dependiendo del mineral
     *
     */
    public void allPossibleProfits() {
        if (getMines().size() > 0) {
            for (Mine mine : getMines()) {
                if (mine.getMineral().equalsIgnoreCase("oro")) {
                    allProfits(mine, getGoldGain());
                } else if (mine.getMineral().equalsIgnoreCase("plata")) {
                    allProfits(mine, getSilverGain());
                } else if (mine.getMineral().equalsIgnoreCase("cobre")) {
                    allProfits(mine, getCopperGain());
                }
            }
        }
    }

    /**
     * En base a los parámetros datos, calcula todas las ganancias para una mina
     * dada según las restricciones del problema
     *
     * @param mine
     * @param gain
     */
    public void allProfits(Mine mine, int gain) {
        double profit = 0;
        int mineros = 0;
        int depositsInMine = mine.depositsPerType(2);
        int baseProfit = depositsInMine * mine.getDepositCapacity() * gain;
        System.out.println("Depositos Mina: " + depositsInMine + " Base: " + baseProfit);
        for (int i = 1; i <= mine.getMinersCapacity(); i++) {
            mineros = i;
            //System.out.println(mineros);
            if (i < depositsInMine) {
                int j = 0;
                while (mineros >= 0) {
                    profit = baseProfit + (800 * mineros) + ((800 * 0.7) * j);
                    Profit currentProfit = new Profit(profit, mineros, j);
                    mine.getProfits().add(currentProfit);
                    j++;
                    mineros--;
                }
            } else if (i == depositsInMine) {
                int j = 0;
                while (mineros >= 0) {
                    profit = baseProfit + (950 * mineros) + ((950 * 0.7) * j);
                    Profit currentProfit = new Profit(profit, mineros, j);
                    mine.getProfits().add(currentProfit);
                    j++;
                    mineros--;
                }
            } else if (i > depositsInMine) {
                int j = 0;
                while (mineros >= 0) {
                    profit = baseProfit + ((250 * mineros) + ((250 * 0.7) * j)) - (130 *((mineros + j) - depositsInMine));
                    Profit currentProfit = new Profit(profit, mineros, j);
                    mine.getProfits().add(currentProfit);
                    j++;
                    mineros--;
                }
            }
        }
        Comparator<Profit> comparator = (profit1, profit2) -> {
            return new Double(profit1.getQuantity()).compareTo(profit2.getQuantity());
        };
        mine.getProfits().sort(comparator.reversed());
    }

    @Override
    public String toString() {
        String cadena = "";
        cadena = "Minerals S.A: " + getTotalMiners() + " " + getGoldMiners() + " " + getSilverMiners() + " " + getCopperMiners() + " Tiene " + getMines().size() + " minas" + "\t ganancias por mina: ";
        for (Mine mine : getMines()) {
            cadena = cadena + "\n" + getMines().indexOf(mine) + " " + mine.showProfits();
        }
        return cadena;
    }

}
