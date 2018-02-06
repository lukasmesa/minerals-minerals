/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.util.LinkedList;

/**
 *
 * @author unalman
 */
public class Mine {

    private LinkedList<Deposit> deposits;
    private Matrix map;
    private String mineral;
    private int minersCapacity;
    private int depositCapacity;
    private double timeExtraction;
    private String timeUnit;
    private int displacementSpeed;
    private String speedUnit;
    private Node exit;
    private LinkedList<Worker> workers;
    
    
    public Mine(int width, int height) {
        map = new Matrix(width, height);
        deposits = new LinkedList<>();
    }

    public Mine(String mineral, int minersCapacity, int depositCapacity, double timeExtraction, String timeUnit, int displacementSpeed, String speedUnit, int width, int length) {
        this.deposits = new LinkedList<>();
        this.map = new Matrix(length, width);
        this.mineral = mineral;
        this.minersCapacity = minersCapacity;
        this.depositCapacity = depositCapacity;
        this.timeExtraction = timeExtraction;
        this.timeUnit = timeUnit;
        this.displacementSpeed = displacementSpeed;
        this.speedUnit = speedUnit;
        Node currentNode;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                Rectangle space = new Rectangle();
                currentNode = new Node(space, i, j);
                this.addMapSector(currentNode);
            }
        }
    }

    /**
     * @return the deposits
     */
    public LinkedList<Deposit> getDeposits() {
        return deposits;
    }

    /**
     * @param deposits the deposits to set
     */
    public void setDeposits(LinkedList<Deposit> deposits) {
        this.deposits = deposits;
    }

    /**
     * @return the map
     */
    public Matrix getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Matrix map) {
        this.map = map;
    }

    /**
     * @return the minersCapacity
     */
    public int getMinersCapacity() {
        return minersCapacity;
    }

    /**
     * @param minersCapacity the minersCapacity to set
     */
    public void setMinersCapacity(int minersCapacity) {
        this.minersCapacity = minersCapacity;
    }

    /**
     * @return the depositCapacity
     */
    public int getDepositCapacity() {
        return depositCapacity;
    }

    /**
     * @param depositCapacity the depositCapacity to set
     */
    public void setDepositCapacity(int depositCapacity) {
        this.depositCapacity = depositCapacity;
    }

    /**
     * @return the timeExtraction
     */
    public double getTimeExtraction() {
        return timeExtraction;
    }

    /**
     * @param timeExtraction the timeExtraction to set
     */
    public void setTimeExtraction(double timeExtraction) {
        this.timeExtraction = timeExtraction;
    }

    /**
     * @return the timeUnit
     */
    public String getTimeUnit() {
        return timeUnit;
    }

    /**
     * @param timeUnit the timeUnit to set
     */
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    /**
     * @return the displacementSpeed
     */
    public int getDisplacementSpeed() {
        return displacementSpeed;
    }

    /**
     * @param displacementSpeed the displacementSpeed to set
     */
    public void setDisplacementSpeed(int displacementSpeed) {
        this.displacementSpeed = displacementSpeed;
    }

    /**
     * @return the speedUnit
     */
    public String getSpeedUnit() {
        return speedUnit;
    }

    /**
     * @param speedUnit the speedUnit to set
     */
    public void setSpeedUnit(String speedUnit) {
        this.speedUnit = speedUnit;
    }

    public Node getExit() {
        return exit;
    }

    public void setExit(Node exit) {
        if (this.exit == null) {
            this.exit = exit;
        }
    }

    public void addMapPart(Node n) {
        getMap().addNode(n, n.getX(), n.getY());
    }

    private void addMapSector(Node n) {
        getMap().addNode(n, n.getX(), n.getY());
    }

    public Node getElementinPosition(int posrow, int poscolumn) {
        return getMap().getNode(posrow, poscolumn);
    }

    public void setExitWithPosition(int posrow, int poscolumn) {
        if (getMap().getNode(posrow, poscolumn) != null) {
            getMap().getNode(posrow, poscolumn).setCategory(1);
            setExit(getMap().getNode(posrow, poscolumn));
        }
    }

    public Rectangle getMapNodeSpace(int posrow, int poscolumn) {
        return getMap().getNode(posrow, poscolumn).getSpace();
    }

    public LinkedList<Node> findPath(Node deposit) {
        LinkedList<Node>path=new LinkedList<Node>();
        if (deposit != null) {
            map.fillNeighbors();
        }
        return path;
    }

    

    public void addDeposit(Node n, String mineral, int quantity) {
        getDeposits().add(new Deposit(n, mineral, quantity));
    }

    public void printDepositsPosition() {
        String c = "[";
        for (Deposit d : getDeposits()) {
            c += "Deposito en pos [" + d.getPosX() + ", " + d.getPosY() + "], ";
        }
        c += "]";
        System.out.println("Depositos: " + c);
    }

    public String printMap() {
        return getMap().printMatrixValues();
    }

    public int getMapX() {
        return getMap().getRows();
    }

    public int getMapY() {
        return getMap().getColumns();
    }

    /**
     * Descripción del método.
     *
     * @return mineral de la mina
     */
    public String getMineralType() {
        if (getDeposits() != null) {
            return getDeposits().get(0).getMineral().getName();
        }
        return "No hay mineral asignado a la mina";
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

}
