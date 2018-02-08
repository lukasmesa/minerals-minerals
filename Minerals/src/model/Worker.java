/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import view.PanelMineCreator;

/**
 *
 * @author unalman
 */
public class Worker {

    private int currentX;
    private int currentY;
    private int finalX;
    private int finalY;
    private Image image;
    private LinkedList<Node> currentPath;
    private int touchNodes;
    private boolean going;
    private Timer timer;
    private PanelMineCreator panel;
    private Node current;
    private LinkedList<LinkedList<Node>> possiblePaths;

    public Worker() {
        this.image = new ImageIcon(getClass().getResource("../Resources/pordefecto2.png")).getImage();
        this.possiblePaths = new LinkedList<LinkedList<Node>>();
    }

    public Worker(LinkedList<Node> currentPath) {
        this.currentPath = currentPath;
        this.image = new ImageIcon(getClass().getResource("../Resources/pordefecto2.png")).getImage();
        if (!this.currentPath.isEmpty()) {
            this.current = this.currentPath.getFirst();
        }
        this.possiblePaths = new LinkedList<LinkedList<Node>>();
    }

    public Node depositPath(LinkedList<Node> camino) {
        int i = 0;
        Node temporal = camino.get(i);
        while (!(temporal.getCategory() == 2)) {
            i++;
            temporal = camino.get(i);
        }
        return temporal;
    }

    public void avanzar(Mine mina) {
        for (LinkedList<Node> listica : possiblePaths) {
            Node temporal = depositPath(listica);
            if (mina.QuantityDeposit(temporal) > 0) {
                this.currentPath = listica;
                for (Node nodo : currentPath) {
                    try {
                        this.current = nodo;
                        if (this.current.getCategory() == 2) {
                            Thread.sleep(1000);
                            mina.Work(this.current);
                        }
                        Thread.sleep(250);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public LinkedList<LinkedList<Node>> getPossiblePaths() {
        return possiblePaths;
    }

    public void setPossiblePaths(LinkedList<LinkedList<Node>> possiblePaths) {
        this.possiblePaths = possiblePaths;
    }

    public void paintWorker(Graphics g) {
        if (current != null) {
            g.setColor(Color.red);
            g.drawImage(this.image, current.getY() * 25 + 5, current.getX() * 25 + 5, null);
        }

    }

    public LinkedList<Node> getCurrentPath() {
        return currentPath;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getFinalX() {
        return finalX;
    }

    public int getFinalY() {
        return finalY;
    }

    public Image getImage() {
        return image;
    }

    public int getTouchNodes() {
        return touchNodes;
    }

    public void setCurrentPath(LinkedList<Node> currentPath) {
        this.currentPath = currentPath;
        if (!this.currentPath.isEmpty()) {
            this.current = this.currentPath.getFirst();
        }
    }

    public boolean isGoing() {
        return going;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setFinalX(int finalX) {
        this.finalX = finalX;
    }

    public void setFinalY(int finalY) {
        this.finalY = finalY;
    }

    public void setGoing(boolean going) {
        this.going = going;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setTouchNodes(int touchNodes) {
        this.touchNodes = touchNodes;
    }

    public boolean isRunning() {
        return timer.isRunning();
    }
}
