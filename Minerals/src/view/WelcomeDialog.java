/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Insert class description here
 *
 * @author Lukas
 * @version Jan 16, 2018
 */
public class WelcomeDialog extends JPanel {

    private JFrame mineFrame;
    private int opcionSelecionada;
    private int tipoMineral;

    public WelcomeDialog() {
        this.tipoMineral = -1;
        this.opcionSelecionada = -1;
        this.ventanaBienvenida();
    }

    /**
     * @return the mineFrame
     */
    public JFrame getMineFrame() {
        return mineFrame;
    }

    /**
     * @param mineFrame the mineFrame to set
     */
    public void setMineFrame(JFrame mineFrame) {
        this.mineFrame = mineFrame;
    }

    /**
     * @return the opcionSelecionada
     */
    public int getOpcionSelecionada() {
        return opcionSelecionada;
    }

    /**
     * @param opcionSelecionada the opcionSelecionada to set
     */
    public void setOpcionSelecionada(int opcionSelecionada) {
        this.opcionSelecionada = opcionSelecionada;
    }

    /**
     * @return the tipoMineral
     */
    public int getTipoMineral() {
        return tipoMineral;
    }

    /**
     * @param tipoMineral the tipoMineral to set
     */
    public void setTipoMineral(int tipoMineral) {
        this.tipoMineral = tipoMineral;
    }

    /**
     * Crea la ventana de bienvenida al programa con las opciones que puede
     * elegir el usuario
     *
     */
    private void ventanaBienvenida() {
        setOpcionSelecionada(JOptionPane.showOptionDialog(
                this,
                "Seleccione una opción",
                "Minerals & Minerals S.A",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                new Object[]{"Crear Mina", "Cargar Mina", "Predeterminado"}, // null para YES, NO y CANCEL
                "Cargar Mina"));
        System.out.println("Opción seleccionada: " + getOpcionSelecionada());
        switch (getOpcionSelecionada()) {
            case 0:
                seleccionMineral();
                break;
            case 1:
                seleccionArchivo();
                break;
            case 2:
                opcionPredeterminda();
                break;
            default:
                break;
        }
    }

    /**
     * Permite al usuario elegir el mineral con el cuál va crear la mina a
     * través de un elemento JOptionPane
     *
     */
    public void seleccionMineral() {
        Object[] minerales = {"Oro", "Plata", "Cobre"};
        int mineral = JOptionPane.showOptionDialog(
                this,
                "Crear Mina",
                "Seleccione el tipo de mineral",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null, // null para icono por defecto.
                minerales,
                minerales[1]
        );
        if (mineral != -1) {
            switch (mineral) {
                case 0:
                    System.out.println("Tipo Mineral: Oro");
                    break;
                case 1:
                    System.out.println("Tipo Mineral: Plata");
                    break;
                case 2:
                    System.out.println("Tipo Mineral: Cobre");
                    break;
                default:
                    System.out.println("Tipo Mineral: Error");
                    break;
            }
            setMineFrame(new MineFrame());
            getMineFrame().setVisible(true);
            getMineFrame().setTitle("Crear Mina");
//            mineFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    /**
     * Despliega un JFileChooser para que el usuario elija el archivo de
     * extensión json que desea cargar
     *
     */
    private void seleccionArchivo() {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON Documents", "json");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //System.out.println("You chose to open this file:" + chooser.getSelectedFile().getName());
            leerArchivo(chooser.getSelectedFile());
            setMineFrame(new IndexFrame());
            getMineFrame().setVisible(true);
        }
    }

    /**
     * Crea una mina por defecto
     *
     */
    private void opcionPredeterminda() {
        System.out.println("Opción predeterminada");
        setMineFrame(new IndexFrame());
        getMineFrame().setVisible(true);
    }

    private void leerArchivo(File selectedFile) {
        System.out.println("You chose to open this file:" + selectedFile.getName());
        System.out.println("Archivo leído");
    }

//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("Minerals & Minerals S.A");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        WelcomeDialog newContentPane = new WelcomeDialog(frame);
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WelcomeDialog bienvenida = new WelcomeDialog();
            }
        });
    }

}
