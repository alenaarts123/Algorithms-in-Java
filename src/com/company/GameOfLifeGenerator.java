package com.company;

import java.util.Arrays;
import java.util.Random;

public class GameOfLifeGenerator {
    private int[][] lifeMatrix;

    private final int ALIVE = 1;
    private final int DEAD = 0;

    private int calculateAliveNeighbours(int i, int j) {
        int gridWidth = lifeMatrix[0].length;
        int gridHeight = lifeMatrix.length;

        int aliveNeighbours = 0;

        // check cell upper row
        if ((i - 1) >= 0) {
            aliveNeighbours += lifeMatrix[i - 1][j];
            if ((j - 1) >= 0) {
                aliveNeighbours += lifeMatrix[i - 1][j - 1];
            }
            if ((j + 1) < gridWidth) {
                aliveNeighbours += lifeMatrix[i - 1][j + 1];
            }
        }

        //check cell below row
        if ((i + 1) < gridHeight) {
            aliveNeighbours += lifeMatrix[i + 1][j];

            if ((j - 1) >= 0) {
                aliveNeighbours += lifeMatrix[i + 1][j - 1];
            }
            if ((j + 1) < gridWidth) {
                aliveNeighbours += lifeMatrix[i + 1][j + 1];
            }
        }

        //check cell same row
        if ((j - 1) >= 0) {
            aliveNeighbours += lifeMatrix[i][j - 1];
        }
        if ((j + 1) < gridWidth) {
            aliveNeighbours += lifeMatrix[i][j + 1];
        }
        return aliveNeighbours;

    }

    public int[][] getNextGenerationMatrix() {
        int matrixHeight = lifeMatrix.length;
        int matrixWidth = lifeMatrix[0].length;
        int[][] nextGenerationMatrix = new int[matrixHeight][matrixWidth];
        int cellAliveNeighbours = 0;
        for (int i = 0; i < matrixHeight; i++) {
            nextGenerationMatrix[i] = Arrays.copyOf(lifeMatrix[i], lifeMatrix[i].length);
        }

        for (int i = 0; i < matrixHeight; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                cellAliveNeighbours = calculateAliveNeighbours(i, j);

                if (nextGenerationMatrix[i][j] == ALIVE) {

                    // if alive cell has 2 or 3 neighbours it remains alive

                    if ((cellAliveNeighbours == 2) || (cellAliveNeighbours == 3)) {
                        nextGenerationMatrix[i][j] = ALIVE;
                    }

                    // if cell has < 2 neighbours or more than 3 it will die
                    if ((cellAliveNeighbours < 2) || (cellAliveNeighbours > 3)) {
                        nextGenerationMatrix[i][j] = DEAD;
                    }

                } else if (nextGenerationMatrix[i][j] == DEAD) {
                    // if dead cell has 3 neighbours it becomes alive
                    if (cellAliveNeighbours == 3) {
                        nextGenerationMatrix[i][j] = ALIVE;
                    }

                }

            }

        }
        return nextGenerationMatrix;

    }

    public void generateLifeMatrix(int size) {
        int[][] lifeMatrix = new int[size][size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                lifeMatrix[i][j] = random.nextInt(2);
            }
        }

        this.lifeMatrix = lifeMatrix;

    }
}
