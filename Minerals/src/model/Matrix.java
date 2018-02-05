/*
   Clase para representar la matriz de adyacencias, se compone de nodos
   *cada nodo tiene un tipo, a la hora de pintarlo, se va a ver reflejado
 */
package model;


/**
 *
 * @author unalman
 */
public class Matrix {

    private Node[][] matrix;
    private int columns;
    private int rows;

    public Matrix(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        matrix = new Node[rows][columns];
    }

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    public Node[][] getMatrix() {
        return matrix;
    }

    //Metodo para agregar nodos a la matriz, mira que la posición esté vacia antes de agregar
    //y que la posición que le ingresa se encuentre dentro del tamaño de la matriz(posiblemente removerlo a futuro)
    public void addNode(Node newest, int posrow, int poscolumn) {
        if (posrow < rows && poscolumn < columns) {
            if (matrix[posrow][poscolumn] == null) {
                matrix[posrow][poscolumn] = newest;
            }
        }
    }

    public Node getNode(int posrow, int poscolumn) {
        if (posrow < rows && poscolumn < columns) {
            return matrix[posrow][poscolumn];
        } else {
            return null;
        }
    }

    public String printMatrixValues() {
        String message = "";
        for (Node[] nodes : matrix) {
            for (Node current : nodes) {
                message += current.getCategory() + " ";
            }
            message += "\n";
        }
        return message;
    }

    public void fillNeighbors() {
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Node current = matrix[row][column];
                current.setInRoute(false);
                if (current.getCategory() != 0) {
                    if (row == 0) {
                        if (column == 0) {
                            current.addNeighbor(matrix[row + 1][column]);
                            current.addNeighbor(matrix[row][column + 1]);
                        } else if (column == columns - 1) {
                            current.addNeighbor(matrix[row - 1][column]);
                            current.addNeighbor(matrix[row][column + 1]);
                        } else {
                            current.addNeighbor(matrix[row + 1][column]);
                            current.addNeighbor(matrix[row - 1][column]);
                            current.addNeighbor(matrix[row][column + 1]);
                        }
                    }
                }
            }
        }

    }

    /**
     * @param matrix the matrix to set
     */
    public void setMatrix(Node[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * @param columns the columns to set
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

}
