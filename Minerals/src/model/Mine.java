/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * @author unalman
 */
public class Mine {

    private LinkedList<Deposit> deposits;
    private Matrix map;
    private Node exit;

    public Mine(int width, int height) {
        map = new Matrix(width, height);
        deposits = new LinkedList<>();
    }

    public void addMapPart(Node n) {
        map.addNode(n, n.getX(), n.getY());
    }

    public Node getElementinPosition(int posrow, int poscolumn) {
        return map.getNode(posrow, poscolumn);
    }
    

    public Rectangle getMapNodeSpace(int posrow, int poscolumn) {
        return map.getNode(posrow, poscolumn ).getSpace();
    }

//    public LinkedList<Nodo> encontrarCamino(Nodo dep) {
//        //Retornar aqui lista de nodos entre salida y el deposito mas cercano
//        //Nodo dep=obtenerDepositoMasCercano();
//        if (dep != null) {
//            matriz.llenarVecinos();
//            estrategia = new EstrategiaCaminoMasCorto(salida, dep, matriz);
//            estrategia.buscar();
//            //System.out.println(" "+estrategia.imprimirMatriz());
//            //System.out.println(""+estrategia.camino);
//        }
//        return estrategia.camino;
//    }

    public void addDeposit(Node n, String mineral, int quantity) {
        deposits.add(new Deposit(n, mineral, quantity));
    }
    

    public void printDepositsPosition() {
        String c = "[";
        for (Deposit d : deposits) {
            c += "Deposito en pos [" + d.getPosX() + ", " + d.getPosY() + "], ";
        }
        c += "]";
        System.out.println("Depositos: " + c);
    }

    public Node getExit() {
        return exit;
    }

    public void setExit(Node exit) {
        if (this.exit == null) {
            this.exit = exit;
        }
    }

    public String printMap() {
        return map.printMatrixValues();
    }

    public int getMapX() {
        return map.getRows();
    }

    public int getMapY() {
        return map.getColumns();
    }

}
