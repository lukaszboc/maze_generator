package com.maze.renderer.impl;

public class PrettyPrintConsoleMazeRenderer extends ConsoleMazeRenderer {
    public PrettyPrintConsoleMazeRenderer() {
        this.widthMultiplier = 3;
        setWallSign((char) 0x258A);
    }

    private int widthMultiplier;

    @Override
    protected void printToConsole(final char[][] array) {
        for (int y = 0; y < array.length; y++) {
            final char[] stretchedRow = new char[array[y].length * widthMultiplier];

            for (int i = 0; i < array[y].length; i++) {
                for (int offset = 0; offset < widthMultiplier; offset++) {
                    stretchedRow[i * widthMultiplier + offset] = array[y][i];
                }
            }

            System.out.println(stretchedRow);
        }
    }
}
