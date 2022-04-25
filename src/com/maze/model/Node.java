package com.maze.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Node {
    public Node(final Maze maze, int x, int y, boolean drawWalls) {
        this.visited = false;
        this.edges = new Edges(drawWalls);
        this.positionX = x;
        this.positionY = y;
        this.maze = maze;
    }

    private boolean visited;
    private Edges edges;
    private int positionX;
    private int positionY;
    private final Maze maze;

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Edges getEdges() {
        return edges;
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public Maze getMaze() {
        return maze;
    }

    public List<Node> getNeighbours() {
        List<Node> neighbours = new ArrayList<>();
        addNeighbourToList(neighbours, this.getPositionX(), this.getPositionY() - 1);
        addNeighbourToList(neighbours, this.getPositionX() + 1, this.getPositionY());
        addNeighbourToList(neighbours, this.getPositionX(), this.getPositionY() + 1);
        addNeighbourToList(neighbours, this.getPositionX() - 1, this.getPositionY());
        return neighbours;
    }

    private void addNeighbourToList(final List<Node> neighbours, final int x, final int y) {
        final Node node = maze.getNode(x, y);

        if (node != null) {
            neighbours.add(node);
        }
    }

    public class Edges {
        Edges(boolean drawWalls) {
            this.top = drawWalls ? Optional.of(new Edge()) : Optional.empty();
            this.right = drawWalls ? Optional.of(new Edge()) : Optional.empty();
            this.bottom = drawWalls ? Optional.of(new Edge()) : Optional.empty();
            this.left = drawWalls ? Optional.of(new Edge()) : Optional.empty();
        }

        private Optional<Edge> top;
        private Optional<Edge> right;
        private Optional<Edge> bottom;
        private Optional<Edge> left;

        public Optional<Edge> getTop() {
            return top;
        }

        public void setTop(Optional<Edge> top) {
            this.top = top;
        }

        public Optional<Edge> getRight() {
            return right;
        }

        public void setRight(Optional<Edge> right) {
            this.right = right;
        }

        public Optional<Edge> getBottom() {
            return bottom;
        }

        public void setBottom(Optional<Edge> bottom) {
            this.bottom = bottom;
        }

        public Optional<Edge> getLeft() {
            return left;
        }

        public void setLeft(Optional<Edge> left) {
            this.left = left;
        }
    }
}
