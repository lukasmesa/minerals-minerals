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

    private Double quantity;
    private int specialists;
    private int jokers;
    private String mineral;
    private boolean checked;

    public Profit() {
    }

    public Profit(Double quantity, int specialists, int jokers, String mineral) {
        this.quantity = quantity;
        this.specialists = specialists;
        this.jokers = jokers;
        this.mineral = mineral;
        this.checked = false;
    }

    /**
     * @return the quantity
     */
    public Double getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Double quantity) {
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

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * @return the mineral
     */
    public String getMineral() {
        return mineral;
    }

    /**
     * @param mineral the mineral to set
     */
    public void setMineral(String mineral) {
        this.mineral = mineral;
    }

    /**
     * Descripción del método.
     *
     * @return descripción de lo que retorna
     */
    public int totalMiners() {
        int total;
        total = getSpecialists() + getJokers();
        return total;
    }

    @Override
    public String toString() {
        String cadena;
        cadena = "Ganancia: " + getQuantity() + " Especializados: " + getSpecialists() + " Comodines: " + getJokers() + " Mineral: " + getMineral();
        return cadena;
    }
}
