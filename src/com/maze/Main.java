package com.maze;

import com.maze.generator.MazeGenerator;
import com.maze.generator.impl.RecursiveBacktrackingMazeGenerator;
import com.maze.generator.impl.RecursiveDivisionMazeGenerator;
import com.maze.model.Maze;
import com.maze.renderer.MazeRenderer;
import com.maze.renderer.impl.ConsoleMazeRenderer;
import com.maze.renderer.impl.PrettyPrintConsoleMazeRenderer;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        int width = (args.length <= 1 || args[0] == null || "0".equals(args[0])) ? 10 : Integer.parseInt(args[0]);
        int height = (args.length <= 1 || args[1] == null || "0".equals(args[1])) ? 10 : Integer.parseInt(args[1]);
        final MazeGenerator generator = (args.length <= 1 || args[2] == null) ? new RecursiveDivisionMazeGenerator() : (MazeGenerator) Class.forName("com.maze.generator.impl." + args[2]).newInstance();

        // render maze
        final MazeRenderer renderer = new PrettyPrintConsoleMazeRenderer();

        final Maze maze = generator.generate(width, height);
        renderer.render(maze);
    }

}
