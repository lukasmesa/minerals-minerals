/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 *
 * @author unalman
 */
public class Mine implements Runnable {

    private LinkedList<Deposit> deposits;
    private Matrix map;
    private String mineral;
    private int minersCapacity;
    private int depositCapacity;
    private double timeExtraction;
    private String timeUnit;
    private int displacementSpeed;
    private String speedUnit;
    private LinkedList<Profit> profits;
    private Node exit;
    private LinkedList<Worker> workers;
    private Worker minero1 = new Worker();
    private Worker minero2 = new Worker();
    private Worker minero3 = new Worker();
    private Thread hilomina = new Thread();
    private PathCalculator bestpath;
    private PriorityQueue<Node> closestDeposits;
    private HeuristicNodes comparing;

    public PriorityQueue<Node> getClosestDeposits() {
        return closestDeposits;
    }

    public Mine(int rows, int columns) {
        this.map = new Matrix(rows, columns);
        this.deposits = new LinkedList<>();
        this.profits = new LinkedList<>();
        this.workers = new LinkedList<>();
        workers.add(minero1);
        workers.add(minero2);
        workers.add(minero3);
        this.hilomina = new Thread(this);
        this.comparing = new HeuristicNodes();
        this.closestDeposits = new PriorityQueue<>(comparing);

    }

    public Mine(String mineral, int minersCapacity, int depositCapacity, double timeExtraction, String timeUnit, int displacementSpeed, String speedUnit, int rows, int columns) {
        this.deposits = new LinkedList<>();
        this.profits = new LinkedList<>();
        this.workers = new LinkedList<>();
        this.map = new Matrix(rows, columns);
        this.mineral = mineral;
        this.minersCapacity = minersCapacity;
        this.depositCapacity = depositCapacity;
        this.timeExtraction = timeExtraction;
        this.timeUnit = timeUnit;
        this.displacementSpeed = displacementSpeed;
        this.speedUnit = speedUnit;
        Node currentNode;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Rectangle space = new Rectangle();
                currentNode = new Node(space, i, j);
                this.addMapSector(currentNode);
            }
        }
        this.workers = new LinkedList<>();
        workers.add(minero1);
        workers.add(minero2);
        workers.add(minero3);
        this.comparing = new HeuristicNodes();
        this.closestDeposits = new PriorityQueue<>(comparing);
    }

    public void paintWorkers(Graphics g) {
        for (Worker worker : workers) {
            worker.paintWorker(g);
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
        System.out.println("Position: X:" + poscolumn + " Y:" + posrow);
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
        if (deposit != null) {
            map.fillNeighbors();
            bestpath = new PathCalculator(exit, deposit, map);
            bestpath.FindPath();

        }
        return bestpath.getPath();
    }

    public void addDeposit(Node n, String mineral, int quantity) {
        System.out.println(n.getCategory() + " " + mineral + " " + quantity);
        getDeposits().add(new Deposit(n, mineral, quantity));
        if (exit != null) {
            n.setCost((int) Math.sqrt(Math.pow(n.getX() - exit.getX(), 2) + Math.pow(n.getY() - exit.getY(), 2)));
            closestDeposits.add(n);
        }
    }

    public int depositsPerType(int type) {
        int depositsType = 0;
        if (getDeposits().size() > 0) {
            for (Deposit deposit : deposits) {
                if (deposit.getNode().getCategory() == type) {
                    depositsType++;
                }
            }
            return depositsType;
        }
        return depositsType;
    }

    public void printDepositsPosition() {
        String c = "[";
        for (Deposit d : getDeposits()) {
            c += "Deposito en pos [" + d.getPosX() + ", " + d.getPosY() + ", categoría: " + d.getNode().getCategory() + "], ";
        }
        c += "]";
        System.out.println("Depositos: " + c);
    }

    public int QuantityDeposit(Node node) {
        for (Deposit o : deposits) {
            if (o.getNode().equals(node)) {
                return o.getQuantity();
            }
        }
        return 0;
    }

    public void Work(Node node) {
        for (Deposit o : deposits) {
            if (o.getNode().equals(node)) {
                if (o.getQuantity() > 0) {
                    o.setQuantity(o.getQuantity() - 1);
                    System.out.println("Saqué: " + o.getQuantity());
                }
            }
        }
    }

    public String printMap() {
        return getMap().printMatrixValues();
    }

    public int getMapX() {
        return getMap().getColumns();
    }

    public int getMapY() {
        return getMap().getRows();
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

    /**
     * @return the profits
     */
    public LinkedList<Profit> getProfits() {
        return profits;
    }

    /**
     * @param profits the profits to set
     */
    public void setProfits(LinkedList<Profit> profits) {
        this.profits = profits;
    }

    /**
     * @return the workers
     */
    public LinkedList<Worker> getWorkers() {
        return workers;
    }

    /**
     * @param workers the workers to set
     */
    public void setWorkers(LinkedList<Worker> workers) {
        this.workers = workers;
    }

    public String showProfits() {
        String cadena = "";
        System.out.println(getProfits().size());
        for (Profit profit : getProfits()) {
            cadena = cadena + profit.getQuantity() + " E: " + profit.getSpecialists() + " C: " + profit.getJokers() + " - ";
        }
        return cadena;
    }

    @Override
    public void run() {
        boolean continuar = true;
        while (continuar) {
            for (Worker minero : workers) {
                minero.avanzar(this);
            }
            continuar = false;
        }
    }

    public void start() {
        this.hilomina.start();
    }

    public Node obtainClosestDeposit() {
        if (!closestDeposits.isEmpty()) {
            return closestDeposits.poll();
        }
        return null;
    }
}
