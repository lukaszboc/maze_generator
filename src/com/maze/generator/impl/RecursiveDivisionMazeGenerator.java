package com.maze.generator.impl;

import com.maze.generator.MazeGenerator;
import com.maze.model.Edge;
import com.maze.model.Maze;
import com.maze.model.Node;

import java.util.*;

public class RecursiveDivisionMazeGenerator implements MazeGenerator {
    public RecursiveDivisionMazeGenerator() {
        this.random = new Random();
    }

    private Random random;

    @Override
    public Maze generate(int width, int height) {
        final Maze maze = new Maze(width, height, false);
        divideMazeFragment(maze, width, height, maze.getNode(0, 0));

        return maze;
    }

    private void divideMazeFragment(final Maze maze, int width, int height, final Node offset) {
        boolean isHorizontalDivision = random.nextInt(2) == 0;

        if (possibleToDrawWall(width, height)) {
            int divisionPosition = drawWall(maze, isHorizontalDivision, width, height, offset);

            if (isHorizontalDivision) {
                divideMazeFragment(maze, width, divisionPosition, offset);
                divideMazeFragment(maze, width, height - divisionPosition, maze.getNode(offset.getPositionX(), offset.getPositionY() + divisionPosition));
            } else {
                divideMazeFragment(maze, divisionPosition, height, offset);
                divideMazeFragment(maze, width - divisionPosition, height, maze.getNode(offset.getPositionX() + divisionPosition, offset.getPositionY()));
            }
        }
    }

    private int drawWall(final Maze maze, boolean isHorizontalDivision, int width, int height, final Node offset) {
        int wallPosition = random.nextInt(isHorizontalDivision ? height - 1 : width - 1);
        final Node wallStartNode = maze.getNode(offset.getPositionX() + (isHorizontalDivision ? 0 : wallPosition),
                offset.getPositionY() + (isHorizontalDivision ? wallPosition : 0));
        final Node wallEndNode = maze.getNode(offset.getPositionX() + (isHorizontalDivision ? width - 1 : wallPosition),
                offset.getPositionY() + (isHorizontalDivision ? wallPosition : height - 1));

        drawWallBetweenNodes(maze, wallStartNode, wallEndNode, isHorizontalDivision);

        return wallPosition + 1;
    }

    private void drawWallBetweenNodes(final Maze maze, final Node wallStartNode, final Node wallEndNode, boolean isHorizontalDivision) {
        if (isHorizontalDivision) {
            int y = wallStartNode.getPositionY();
            for (int x = wallStartNode.getPositionX(); x <= wallEndNode.getPositionX(); x++) {
                // for horizontal wall, set bottom edge
                maze.getNode(x, y).getEdges().setBottom(Optional.of(new Edge()));
            }
            // carve hole in random position within the wall
            int holePositionX = random.nextInt(wallEndNode.getPositionX() - wallStartNode.getPositionX()) + wallStartNode.getPositionX();
            maze.getNode(holePositionX, y).getEdges().setBottom(Optional.empty());
        } else {
            int x = wallStartNode.getPositionX();
            for (int y = wallStartNode.getPositionY(); y <= wallEndNode.getPositionY(); y++) {
                // for vertical wall, set right edge
                maze.getNode(x, y).getEdges().setRight(Optional.of(new Edge()));
            }
            // carve hole in random position within the wall
            int holePositionY = random.nextInt(wallEndNode.getPositionY() - wallStartNode.getPositionY()) + wallStartNode.getPositionY();
            maze.getNode(x, holePositionY).getEdges().setRight(Optional.empty());
        }
    }

    private boolean possibleToDrawWall(int width, int height) {
        return width >= 2 && height >= 2;
    }
}
