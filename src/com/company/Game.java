package com.company;

import java.util.Arrays;

public class Game {

    private int columns;
    private int rows;
    private String[][] matrixGame;//= new String[rows][columns];
    private String[][] oldMatrixGame;

    public Game(int rows, int columns) {
        matrixGame = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixGame[i][j] = ".";
            }
        }
        setColumns(columns);
        setRows(rows);
        oldMatrixGame = matrixGame;
    }

    public String getMatrixElem(int rows, int columns) {
        return matrixGame[rows][columns];
    }


    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getColumns() {
        return columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getRows() {
        return rows;
    }

    public String[][] getMatrixGame() {
        return matrixGame;
    }

    public void setStartPosition(int xPosition, int yPosition) {
        matrixGame[xPosition][yPosition] = "*";
    }


    public String matrixToString(String[][] matrixGame) {

        StringBuilder builder = new StringBuilder();

        for (String[] row : matrixGame) {

            for (String element : row) {
                builder.append(element).append(" ");
            }
            builder.append("\n");
        }

        return builder.toString();

    }

    public int howManyLiveNeighbors(int positionX, int positionY) {
        int neighbors = 0;

        for (int i = positionX - 1; i <= positionX + 1; i++) {
            for (int j = positionY - 1; j <= positionY + 1; j++) {
                if (i >= 0 && i < this.rows && j >= 0 && j < this.columns && (i != positionX || j != positionY)) {
                    if (matrixGame[i][j] == "*") {
                        neighbors++;
                    }
                }
            }
        }
        return neighbors;

    }

    public void lifeCycle() {
        String[][] newMatrixGame = new String[this.rows][this.columns];

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                int liveNeighbors = howManyLiveNeighbors(i, j);

                if (matrixGame[i][j] == "*" && (liveNeighbors == 2 || liveNeighbors == 3)) {
                    newMatrixGame[i][j] = "*";
                } else if (matrixGame[i][j] == "." && liveNeighbors == 3) {
                    newMatrixGame[i][j] = "*";
                } else {
                    newMatrixGame[i][j] = ".";
                }
            }
        }
        oldMatrixGame = matrixGame;
        matrixGame = newMatrixGame;

    }

    public boolean checkchecUniqueness(String[][] newMatrix){
       // boolean uniqueness = true;
//        System.out.println(matrixToString(oldMatrixGame));
//      //  if (newMatrix.equals(oldMatrixGame)){
//        if()){
//            return false;
//        }else {
//            return true;
//        }
        return !Arrays.deepEquals(newMatrix,oldMatrixGame);
    }

}
