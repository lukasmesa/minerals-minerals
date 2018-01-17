/**
 *Esta clase hace posible la representación de una matriz de adyacencias,
 * Un nodo puede ser de varios tipos, para representar el tipo se usa un valor entero con el siguiente código:
 * 0 inaccesible,1 salida,2 deposito, 3 ruta
 * el entero se llama category
 */
package Class;

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

    //Constructor para dibujar
    public Node(Rectangle space, int x, int y) {
        this.space = space;
        this.x = x;
        this.y = y;
        this.category = 0;
        this.neighbors = new LinkedList<Node>();
        this.inRoute = false;
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

    public void addNeighbor(Node current) {
        if (current.getCategory() != 0) {
            neighbors.add(current);
        }
    }

    public boolean isInroute() {
        return inRoute;
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
    
    
    

}
