/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Mine;
import model.Node;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.OverlayLayout;
import model.Deposit;

/**
 *
 * @author unalman
 */
public class PanelMineCreator extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form PanelMineCreator
     */
    private int heightMine;
    private int widthMine;
    private int cellsize;
    private int optionselected;
    private boolean paintable;
    private Node mapparttopaint;
    private Mine mine;
    private JPopupMenu menuOpciones;//formularios con las multiples opciones para manipular la aplicacion
    private JMenuItem itemEntradaSalida;
    private JMenuItem itemDeposito;
    private JMenuItem itemCamino;
    public LinkedList<Node> caminotemporal;

    public PanelMineCreator() {
        initComponents();
        this.cellsize = 25;
        this.optionselected = 0;
        this.paintable = false;
        this.menuOpciones = new JPopupMenu("Opciones De Creación");
        this.optionsMenu();
        setLayout(new OverlayLayout(this));
        this.caminotemporal=new LinkedList<Node>();
    }
    
    

    public int getHeightMine() {
        return heightMine;
    }

    public void setHeightMine(int h) {
        this.heightMine = h;
    }

    public int getWidthMine() {
        return widthMine;
    }

    public void setWidthMine(int w) {
        this.widthMine = w;
    }

    public int getCellsize() {
        return cellsize;
    }

    public void setCellsize(int cellsize) {
        this.cellsize = cellsize;
    }

    public int getOptionselected() {
        return optionselected;
    }

    public void setOptionselected(int optionselected) {
        this.optionselected = optionselected;
    }

    public boolean isPaintable() {
        return paintable;
    }

    public void setPaintable(boolean paintable) {
        this.paintable = paintable;
    }

    public Node getMapparttopaint() {
        return mapparttopaint;
    }

    public void setMapparttopaint(Node mapparttopaint) {
        this.mapparttopaint = mapparttopaint;
    }

    public Mine getMine() {
        return mine;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public JPopupMenu getMenuOpciones() {
        return menuOpciones;
    }

    public void setMenuOpciones(JPopupMenu menuOpciones) {
        this.menuOpciones = menuOpciones;
    }

    /**
     * @return the itemEntradaSalida
     */
    public JMenuItem getItemEntradaSalida() {
        return itemEntradaSalida;
    }

    /**
     * @param itemEntradaSalida the itemEntradaSalida to set
     */
    public void setItemEntradaSalida(JMenuItem itemEntradaSalida) {
        this.itemEntradaSalida = itemEntradaSalida;
    }

    /**
     * @return the itemDeposito
     */
    public JMenuItem getItemDeposito() {
        return itemDeposito;
    }

    /**
     * @param itemDeposito the itemDeposito to set
     */
    public void setItemDeposito(JMenuItem itemDeposito) {
        this.itemDeposito = itemDeposito;
    }

    /**
     * @return the itemCamino
     */
    public JMenuItem getItemCamino() {
        return itemCamino;
    }

    /**
     * @param itemCamino the itemCamino to set
     */
    public void setItemCamino(JMenuItem itemCamino) {
        this.itemCamino = itemCamino;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getMine() != null) {
            Node current;
            for (int i = 0; i < getHeightMine(); i++) {
                for (int j = 0; j < getWidthMine(); j++) {
                    Rectangle space = new Rectangle(j * getCellsize(), i * getCellsize(), getCellsize(), getCellsize());
                    current = new Node(space, i, j);
                    getMine().addMapPart(current);
                    g.setColor(Color.BLACK);
                    g.drawRect(space.x, space.y, space.width, space.height);
                }
            }
            mine.paintWorkers(g);
        }
        this.repaint();
    }

    public void setRowsCol(int w, int h) {
        setHeightMine(h);
        setWidthMine(w);
        setMine(new Mine(getHeightMine(), getWidthMine()));
        //this.matriz=new Matriz(ancho, alto);
    }

    private Node getClickedSpace(Point clickCoordinates) {
        Node n;
        for (int i = 0; i < getWidthMine(); i++) {
            for (int j = 0; j < getHeightMine(); j++) {
                if (getMine().getMapNodeSpace(i, j).contains(clickCoordinates)) {
                    n = getMine().getElementinPosition(i, j);
                    return n;
                }
            }
        }
        return null;
    }

    public void paintSpace(Node tobecolored) {
        System.out.println("paintSpace");
        if (tobecolored != null && isPaintable()) {
            JLabel label = new JLabel();
            label.setBounds(tobecolored.getSpace());
            switch (getOptionselected()) {
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

        return getMine().getElementinPosition(i, j);
    }

    /**
     * Descripción del método.
     *
     */
    public void createMineEntry() {
        //your code
    }

    /**
     * Crea el menú con las opciones que se ofrecen al usuario para crear la mina
     *
     */
    private void optionsMenu() {
        System.out.println("OptionsMenu created");

        if (getMenuOpciones() != null) {
            getMenuOpciones().removeAll();
            setItemEntradaSalida(new JMenuItem("Agregar Entrada"));
            getItemEntradaSalida().addActionListener(this);
            setItemDeposito(new JMenuItem("Agregar Deposito"));
            getItemDeposito().addActionListener(this);
            setItemCamino(new JMenuItem("Agregar Camino"));
            getItemCamino().addActionListener(this);
            getMenuOpciones().add(itemEntradaSalida);
            getMenuOpciones().add(itemDeposito);
            getMenuOpciones().add(itemCamino);
        }
    }

    /**
     * Muestra al usuario las opciones disponible para crear la mina
     *
     * @param evt evento ocasionado por el cilck del mouse
     */
    public void showOptionsMenu(java.awt.event.MouseEvent evt) {
        System.out.println("getOptionsMenu");
        System.out.println("Mouse Button: " + evt.getButton());

        if (evt.getButton() == 3) {
            getMenuOpciones().show(this, evt.getX(), evt.getY());
            this.repaint();
        } else if (evt.getButton() != 3) {
            JOptionPane.showMessageDialog(this, "Debe usar el click derecho del mouse para editar la mina");
        }
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
        setMapparttopaint(getClickedSpace(evt.getPoint()));
        if (getMapparttopaint() != null) {
            setPaintable(true);
            if (isPaintable()) {
                showOptionsMenu(evt);
            }
        }
    }//GEN-LAST:event_formMouseClicked

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Action Performed");
        if (getMapparttopaint().getCategory() == 0) {
            if (evt.getSource() == getItemEntradaSalida()) {
                System.out.println("getItemEntradaSalida");
                setOptionselected(1);
                if (getMine().getExit() == null) {
                    if (getMapparttopaint().getX() == 0 || getMapparttopaint().getY() == 0 || getMapparttopaint().getX() == (getMine().getMapX() - 1) || getMapparttopaint().getY() == (getMine().getMapY() - 1)) {
                        getMapparttopaint().setCategory(getOptionselected());
                        paintSpace(getMapparttopaint());
                        getMine().setExit(getMapparttopaint());
                        getMine().addMapPart(getMapparttopaint());
                    } else {
                        JOptionPane.showMessageDialog(this, "La salida de la mina debe estar en los extremos");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Una mina solo puede tener una salida");
                }
            } else if (evt.getSource() == getItemDeposito()) {
                setOptionselected(2);
                if (getMine().getExit() != null) {
                    getMapparttopaint().setCategory(getOptionselected());
                    //Oro metal por defecto temporalmente
                    getMine().addDeposit(getMapparttopaint(), "Oro", 2);
                    paintSpace(getMapparttopaint());
                    getMine().addMapPart(getMapparttopaint());
                } else {
                    JOptionPane.showMessageDialog(this, "Para agregar depósitos debe existir una salida primero");
                }
            } else if (evt.getSource() == getItemCamino()) {
                setOptionselected(3);
                if (getMine().getExit() != null) {
                    getMapparttopaint().setCategory(getOptionselected());
                    paintSpace(getMapparttopaint());
                    getMine().addMapPart(getMapparttopaint());
                } else {
                    JOptionPane.showMessageDialog(this, "Para agregar caminos debe existir una salida primero");
                }
            }
        } else if (getMapparttopaint().getCategory() != 0) {
            JOptionPane.showMessageDialog(this, "Este espacio ya se encuentra ocupado");
        }
        System.out.println("" + getMine().printMap());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
