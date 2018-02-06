/**
 *Esta clase hace posible la representación de una matriz de adyacencias,
 * Un nodo puede ser de varios tipos, para representar el tipo se usa un valor entero con el siguiente código:
 * 0 inaccesible,1 salida,2 deposito, 3 ruta
 * el entero se llama category
 */
package model;

import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author unalman
 */
public class Node {

    private int category;
    private int x;
    private int y;
    private Rectangle space;
    private LinkedList<Node> neighbors;
    private boolean inRoute;
    private int cost;
    private Node predecessor;

    public Node() {
        this.space = new Rectangle();
        this.x = 0;
        this.y = 0;
        this.category = 0;
        this.neighbors = new LinkedList<Node>();
        this.inRoute = false;
        this.predecessor = null;
    }

    //Constructor para dibujar
    public Node(Rectangle space, int x, int y) {
        this.space = space;
        this.x = x;
        this.y = y;
        this.category = 0;
        this.neighbors = new LinkedList<Node>();
        this.inRoute = false;
        this.predecessor = null;
    }

    public Rectangle getSpace() {
        return space;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public LinkedList<Node> getNeighbors() {
        return neighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isInroute() {
        return isInRoute();
    }

    public void setInRoute(boolean value) {
        this.inRoute = value;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param space the space to set
     */
    public void setSpace(Rectangle space) {
        this.space = space;
    }

    /**
     * @param neighbors the neighbors to set
     */
    public void setNeighbors(LinkedList<Node> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * @return the inRoute
     */
    public boolean isInRoute() {
        return inRoute;
    }

    /**
     * @return the predecessor
     */
    public Node getPredecessor() {
        return predecessor;
    }

    /**
     * @param predecessor the predecessor to set
     */
    public void setPredecessor(Node predecessor) {
        this.predecessor = predecessor;
    }
    
    public void addNeighbor(Node current) {
        if (current.getCategory() != 0) {
            getNeighbors().add(current);
        }
    }
}
