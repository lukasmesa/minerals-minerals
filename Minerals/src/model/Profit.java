/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Insert class description here
 *
 * @author Lukas
 * @version Feb 5, 2018
 */
public class Profit {

    private double quantity;
    private int specialists;
    private int jokers;

    public Profit() {
    }

    public Profit(double quantity, int specialists, int jokers) {
        this.quantity = quantity;
        this.specialists = specialists;
        this.jokers = jokers;
    }

    /**
     * @return the quantity
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the specialists
     */
    public int getSpecialists() {
        return specialists;
    }

    /**
     * @param specialists the specialists to set
     */
    public void setSpecialists(int specialists) {
        this.specialists = specialists;
    }

    /**
     * @return the jokers
     */
    public int getJokers() {
        return jokers;
    }

    /**
     * @param jokers the jokers to set
     */
    public void setJokers(int jokers) {
        this.jokers = jokers;
    }
}
