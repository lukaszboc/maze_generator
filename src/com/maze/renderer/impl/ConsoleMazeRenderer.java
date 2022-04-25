package com.maze.renderer.impl;

import com.maze.model.Maze;
import com.maze.model.Node;
import com.maze.renderer.MazeRenderer;

public class ConsoleMazeRenderer implements MazeRenderer {
    public ConsoleMazeRenderer() {
        setWallSign((char) 0x2588);
        setSpaceSign((char) 0x0020);
    }

    private char wallSign;
    private char spaceSign;

    @Override
    public void render(final Maze maze) {
        final char[][] printArray = createPrintArray(maze);

        draw(maze, printArray);
        printToConsole(printArray);
    }

    protected char[][] createPrintArray(final Maze maze) {
        char[][] printArray = new char[maze.getHeight() * 2 + 1][maze.getWidth() * 2 + 1];
        for (int y = 0; y <= maze.getHeight() * 2; y++) {
            for (int x = 0; x <= maze.getWidth() * 2; x++) {
                printArray[y][x] = (x % 2 == 0 || y % 2 == 0) ? getWallSign() : getSpaceSign();
            }
        }

        createEntryAndExitPoints(printArray);

        return printArray;
    }

    protected void draw(final Maze maze, final char[][] array) {
        for (int y = 0; y < maze.getHeight(); y++) {
            for (int x = 0; x < maze.getWidth(); x++) {
                final Node currentNode = maze.getNode(x, y);
                if (currentNode.getEdges().getBottom().isEmpty() && currentNode.getPositionY() < maze.getHeight() -1) {
                    array[(y * 2) + 2][x * 2 + 1] = spaceSign;
                }
                if (currentNode.getEdges().getRight().isEmpty() && currentNode.getPositionX() < maze.getWidth() - 1) {
                    array[y * 2 + 1][x * 2 + 2] = spaceSign;
                }
            }
        }
    }

    protected void createEntryAndExitPoints(final char[][] array) {
        array[0][1] = (char) 0x2193;
        array[array.length - 1][array[0].length - 2] = (char) 0x2193;
    }

    protected void printToConsole(final char[][] array) {
        for (int y = 0; y < array.length; y++) {
            System.out.println(array[y]);
        }
    }

    public char getWallSign() {
        return wallSign;
    }

    public void setWallSign(char wallSign) {
        this.wallSign = wallSign;
    }

    public char getSpaceSign() {
        return spaceSign;
    }

    public void setSpaceSign(char spaceSign) {
        this.spaceSign = spaceSign;
    }
}
