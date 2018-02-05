package model;

import java.util.LinkedList;

/**
 * Insert class description here
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

    @Override
    public String toString() {
        String cadena = "";
        cadena = "Minerals S.A: "+getTotalMiners()+" "+getGoldMiners()+" "+getSilverMiners()+" "+getCopperMiners()+" Tiene "+getMines().size()+" minas";        
        return cadena;
    }

}
