/*
 * Clase que controla los caminos más cortos
 * Implementación de la búsqueda A*
 * A star algorithm, para mas info http://www.cokeandcode.com/main/tutorials/path-finding/
 * https://www.geeksforgeeks.org/a-search-algorithm/
 */
package model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.LinkedList;

/**
 *
 * @author unalman
 */
public class PathCalculator {

    private Node start;
    private Node end;
    private Matrix map;
    private boolean pathFound;
    private PriorityQueue<Node> openList;
    private ArrayList<Node> closedList;
    private LinkedList<Node> path;
    private HeuristicNodes comparing;

    public PathCalculator(Node start, Node end, Matrix map) {
        this.start = start;
        this.end = end;
        this.map = map;
        this.pathFound = false;
        this.comparing=new HeuristicNodes();
        this.closedList = new ArrayList<Node>();
        this.openList = new PriorityQueue<Node>(comparing);
        this.path = new LinkedList<Node>();
        this.openList.add(start);
    }

    public void FindPath() {
        while (!openList.isEmpty()) {
            Node current = openList.poll();
            closedList.add(current);
            if (end.equals(current)) {
                pathFound = true;
                getPathsito(current);
            } else {
                addAdjacents(current);
            }
        }
    }

    //el algoritmo necesita encontrar sus adyacencias, esto se hace con la lista de vecinos
    // como estamos hablando de caminos, el tiene que saber de donde viene, por eso el padre.
    public void addAdjacents(Node current) {
        for (Node adjacent : current.getNeighbors()) {
            if (!openList.contains(adjacent) && !closedList.contains(adjacent)) {
                setHeuristic(adjacent);
                openList.add(adjacent);
                adjacent.setPredecessor(current);
            }
        }
    }

    //la heuristica se calcula para tomar la mejor decisión 
    public void setHeuristic(Node node) {
        node.setCost((int) Math.sqrt(Math.pow(node.getX() - end.getX(), 2) + Math.pow(node.getY() - end.getY(), 2)));
    }

    public void getPathsito(Node current) {
        Node n=current;
        while (n != start) {
            path.add(n);
            n.setInRoute(true);
            n = n.getPredecessor();
        }
    }

    public LinkedList<Node> getPath() {
        return path;
    }

    public void setPath(LinkedList<Node> path) {
        this.path = path;
    }
    
    

}
