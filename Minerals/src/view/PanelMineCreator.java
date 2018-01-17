/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Class.Mine;
import Class.Node;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.OverlayLayout;

/**
 *
 * @author unalman
 */
public class PanelMineCreator extends javax.swing.JPanel {

    /**
     * Creates new form PanelMineCreator
     */
    private int height;
    private int width;
    private int cellsize;
    private int optionselected;
    private boolean paint;
    private Node mapparttopaint;
    private Mine mine;

    public PanelMineCreator() {
        initComponents();
        cellsize = 5;
        optionselected = 0;
        paint = false;
        setLayout(new OverlayLayout(this));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mine != null) {
            Node current;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    Rectangle space = new Rectangle(j * cellsize, i * cellsize, cellsize, cellsize);
                    current = new Node(space, i, j);
                    mine.addMapPart(current);
                    g.setColor(Color.BLACK);
                    g.drawRect(space.x, space.y, space.width, space.height);
                }
            }

        }
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public int getWidth() {
        return width;
    }

    public void setRowsCol(int w, int h) {
        this.width = w;
        this.height = h;
        mine = new Mine(height, width);
        //this.matriz=new Matriz(ancho, alto);
    }

    public void setWidth(int w) {
        this.width = w;
    }

    private Node getClickedSpace(Point clickCoordinates) {
        Node n;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (mine.getMapNodeSpace(i, j).contains(clickCoordinates)) {
                    n = mine.getElementinPosition(i, j);
                    return n;
                }
            }
        }
        return null;

    }

    public int getOptionselected() {
        return optionselected;
    }

    public void setOptionselected(int optionselected) {
        this.optionselected = optionselected;
    }

    public boolean isPaint() {
        return paint;
    }

    public void setPaint(boolean paint) {
        this.paint = paint;
    }

    public void paintSpace(Node tobecolored) {
        if (tobecolored != null && paint) {
            JLabel label = new JLabel();
            label.setBounds(tobecolored.getSpace());
            if (getComponentAt(tobecolored.getSpace().x, tobecolored.getSpace().y) != null) {
                remove(getComponentAt(tobecolored.getSpace().x, tobecolored.getSpace().y));
            }

            switch (optionselected) {
                case 0:
                    repaint(tobecolored.getSpace());
                    return;
                case 1:
                    label.setBackground(new Color(0, 0, 255, 100));
                    break;
                case 2:
                    label.setBackground(new Color(0, 255, 0, 100));
                    break;
                case 3:
                    label.setBackground(new Color(255, 255, 0, 100));
                    break;
                default:
                    label.setBackground(Color.BLACK);
                    break;
            }

            label.setOpaque(true);
            add(label);

            repaint();

        }
    }

    public Node getNode(int i, int j) {

        return mine.getElementinPosition(i, j);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 350, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        if (paint) {
            mapparttopaint = getClickedSpace(evt.getPoint());
            if (mapparttopaint != null) {
                if (mapparttopaint.getCategory() == 0 && optionselected != 0) {

                    switch (optionselected) {
                        case 1: {
                            if (mine.getExit() == null) {
                                if (mapparttopaint.getX() == 0 || mapparttopaint.getY() == 0 || mapparttopaint.getX() == (mine.getMapX() - 1) || mapparttopaint.getY() == (mine.getMapY() - 1)) {
                                    mapparttopaint.setCategory(optionselected);
                                    paintSpace(mapparttopaint);
                                    mine.setExit(mapparttopaint);
                                    mine.addMapPart(mapparttopaint);
                                } else {
                                    System.out.println("La salida de la mina tiene que estar en sus extremos");
                                }
                            } else {
                                System.out.println("Una mina solo puede tener una salida");
                            }
                            break;

                        }
                        case 2: {
                            if (mine.getExit() != null) {
                                mapparttopaint.setCategory(optionselected);
                                //Oro metal por defecto temporalmente
                                mine.addDeposit(mapparttopaint, "Oro", 2);
                                paintSpace(mapparttopaint);
                                mine.addMapPart(mapparttopaint);
                            } else {
                                System.out.println("Para agregar depósitos debe existir una salida primero");
                            }
                            break;
                        }
                        case 3: {
                            mapparttopaint.setCategory(optionselected);
                            paintSpace(mapparttopaint);
                            mine.addMapPart(mapparttopaint);
                            break;

                        }
                    }
                }
            } else if (mapparttopaint.getCategory() != 0) {
                System.out.println("Ocupado");
            }

            System.out.println("" + mine.printMap());
        }

    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
