package com.maze.model;

public class Maze {
    public Maze(final int width, final int height, boolean drawWalls) {
        this.width = width;
        this.height = height;
        this.nodes = new Node[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.nodes[y][x] = new Node(this, x, y, drawWalls);
            }
        }
    }

    private int width;
    private int height;
    private Node[][] nodes;

    public Node getNode(final int x, final int y) {
        if (x < 0 || x >= this.getWidth() || y < 0 || y >= this.getHeight()) {
            return null;
        }

        return getNodes()[y][x];
    }

    public Node[][] getNodes() {
        return nodes;
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
