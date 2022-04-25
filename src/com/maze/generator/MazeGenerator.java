package com.maze.generator;

import com.maze.model.Maze;

public interface MazeGenerator {
    Maze generate(int width, int height);
}
