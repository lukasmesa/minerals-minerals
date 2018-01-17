/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

/**
 *
 * @author unalman
 */
public class Deposit {

    private Mineral mineral;
    public Node node;
    private int quantity;

    public Deposit(Node internalnode, String min, int value) {
        node = internalnode;
        mineral = new Mineral(min);
        quantity = value;
    }

    public int getPosX() {
        return node.getX();
    }

    public int getPosY() {
        return node.getY();
    }

    public Mineral getMineral() {
        return mineral;
    }

    public Node getNodo() {
        return node;
    }

}
