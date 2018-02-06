/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private BufferedImage image;
    private LinkedList<Node> currentPath;
    private int touchNodes;
    private boolean going;
    private Timer timer;
    private PanelMineCreator panel;

    public Worker(PanelMineCreator p) {
        PanelMineCreator panel = p;
        touchNodes = 0;
        going = true;
        try {
            image = ImageIO.read(new File("src/Resources/pordefecto.png"));
            Timer timer = new Timer(60, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    move();

                }
            });
            timer.setRepeats(true);
            timer.setCoalesce(true);
        } catch (IOException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
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

    public BufferedImage getImage() {
        return image;
    }

    public int getTouchNodes() {
        return touchNodes;
    }

    public void setCurrentPath(LinkedList<Node> currentPath) {
        this.currentPath = currentPath;
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

    private void move() {
        int moveX = finalX - currentX;
        int moveY = finalY - currentY;
        if (moveX < 0) {
            moveLeft();
        } else if (moveX > 0) {
            moveRight();
        }
        if (moveY < 0) {
            moveUp();
        } else if (moveY > 0) {
            moveDown();
        }
        panel.repaint();
        if (moveX == 0 && moveY == 0) {

            if (touchNodes < currentPath.size()) {
                if (currentPath.get(touchNodes).getCategory()== 3) {
                    System.out.println("In Deposit");
                    currentPath.get(touchNodes).setCategory(2);
                    panel.paintSpace(currentPath.get(touchNodes));
                }
                finalX = currentPath.get(touchNodes).getX();
                finalY = currentPath.get(touchNodes).getY();
                touchNodes++;

            } else {
                timer.stop();
            }

        }
    }

    private void moveLeft() {
        currentX -= 1;
    }

    private void moveRight() {
        currentX += 1;
    }

    private void moveUp() {
        currentY -= 1;
    }

    private void moveDown() {
        currentY += 1;
    }

    public void Animate() {
        timer.start();
    }

    public boolean isRunning() {
        return timer.isRunning();
    }

}
