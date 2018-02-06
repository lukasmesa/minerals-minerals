/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author unalman
 */
public class Deposit {

    private Mineral mineral;
    private Node node;
    private int quantity;

    public Deposit(Node internalnode, String min, int value) {
        node = internalnode;
        mineral = new Mineral(min);
        quantity = value;
    }

    public int getPosX() {
        return getNode().getX();
    }

    public int getPosY() {
        return getNode().getY();
    }

    /**
     * @return the mineral
     */
    public Mineral getMineral() {
        return mineral;
    }

    /**
     * @param mineral the mineral to set
     */
    public void setMineral(Mineral mineral) {
        this.mineral = mineral;
    }

    /**
     * @return the node
     */
    public Node getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(Node node) {
        this.node = node;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
