package com.maze.generator.impl;

import com.maze.generator.MazeGenerator;
import com.maze.model.Maze;
import com.maze.model.Node;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RecursiveBacktrackingMazeGenerator implements MazeGenerator {

    private Random random = new Random();

    @Override
    public Maze generate(int width, int height) {
        final Maze maze = new Maze(width, height, true);

        processNode(maze.getNode(0, 0));

        return maze;
    }

    private void processNode(final Node node) {
        node.setVisited(true);
        while (hasAnyUnvisitedNeighbours(node)) {
            final Node randomUnvisitedNeighbour = getRandomUnvisitedNeighbour(node);
            if (randomUnvisitedNeighbour != null) {
                removeEdgesBetweenNodes(node, randomUnvisitedNeighbour);
                processNode(randomUnvisitedNeighbour);
            }
        }
    }

    private boolean hasAnyUnvisitedNeighbours(final Node node) {
        final Maze maze = node.getMaze();
        final int x = node.getPositionX();
        final int y = node.getPositionY();

        return !checkIfVisited(maze, x, y - 1) || !checkIfVisited(maze, x + 1, y)
                || !checkIfVisited(maze, x, y + 1) || !checkIfVisited(maze, x - 1, y);
    }

    private boolean checkIfVisited(final Maze maze, final int x, final int y) {
        final Node node = maze.getNode(x, y);

        if (node == null) {
            return true;
        }

        return node.isVisited();
    }

    private Node getRandomUnvisitedNeighbour(final Node node) {
        List<Node> unvisitedNeighbours = node.getNeighbours().stream()
                .filter(Predicate.not(Node::isVisited))
                .collect(Collectors.toList());

        if (unvisitedNeighbours.size() == 0) {
            return null;
        }

        return unvisitedNeighbours.get(random.nextInt(unvisitedNeighbours.size()));
    }

    private void removeEdgesBetweenNodes(final Node firstNode, final Node secondNode) {
        if (firstNode.getPositionX() == secondNode.getPositionX()) {
            if (firstNode.getPositionY() > secondNode.getPositionY()) {
                firstNode.getEdges().setTop(Optional.empty());
                secondNode.getEdges().setBottom(Optional.empty());
            } else {
                firstNode.getEdges().setBottom(Optional.empty());
                secondNode.getEdges().setTop(Optional.empty());
            }
        }

        if (firstNode.getPositionY() == secondNode.getPositionY()) {
            if (firstNode.getPositionX() > secondNode.getPositionX()) {
                firstNode.getEdges().setLeft(Optional.empty());
                secondNode.getEdges().setRight(Optional.empty());
            } else {
                firstNode.getEdges().setRight(Optional.empty());
                secondNode.getEdges().setLeft(Optional.empty());
            }
        }
    }
}
