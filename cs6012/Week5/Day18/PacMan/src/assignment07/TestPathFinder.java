package assignment07;

import java.io.IOException;
import java.lang.annotation.Target;

public class TestPathFinder {

  public static void main(String[] args) throws IOException {

    /*
     * The below code assumes you have a file "tinyMaze.txt" in your project folder.
     * If PathFinder.solveMaze is implemented, it will create a file "tinyMazeOutput.txt" in your project folder.
     * 
     * REMEMBER - You have to refresh your project to see the output file in your package explorer. 
     * You are still required to make JUnit tests...just lookin' at text files ain't gonna fly. 
     */
    PathFinder.solveMaze("mazes/tinyMaze.txt", "tinyMazeOutput.txt");
    //Graph tinyMazeGraph = new Graph("tinyMazeOutput.txt");
//    System.out.println(tinyMazeGraph.getNumDots());
//    tinyMazeGraph.printMap();

  }






}

