package assignment07;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class PathFinderTest {
    //test broken files!!

    //tests that the number of dots of my programs solution is equal to the number of dots in the provided solution
    @Test
    void testDotNumberBigMaze() throws IOException {
        PathFinder.solveMaze("mazes/bigMaze.txt", "myBigMazeSol.txt");
        Assertions.assertEquals(getSolvedMazeNumDots("myBigMazeSol.txt"), getSolvedMazeNumDots("mazes/bigMazeSol.txt"));
    }

    //tests the number of dots on the random maze (which has multiple solutions
    @Test
    void testDotNumberRandomMaze() throws IOException {
        PathFinder.solveMaze("mazes/randomMaze.txt", "myRandomMazeSol.txt");
        Assertions.assertEquals(getSolvedMazeNumDots("myRandomMazeSol.txt"), getSolvedMazeNumDots("mazes/randomMazeSol.txt"));
    }
    //tests the number of dots on an unsolvable maze
    @Test
    void testDotNumberUnsolvableMaze() throws IOException {
        PathFinder.solveMaze("mazes/unsolvable.txt", "myUnsolvableSol.txt");
        Assertions.assertEquals(getSolvedMazeNumDots("myUnsolvableSol.txt"), getSolvedMazeNumDots("mazes/unsolvableSol.txt"));
    }

    @Test
    void testInvalidInputExceptions(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> PathFinder.solveMaze("", ""));
        Assertions.assertThrows(FileNotFoundException.class, () -> PathFinder.solveMaze("invalid.txt", "valid.txt"));
    }

    public static int getSolvedMazeNumDots(String inputFile) throws IOException {
        int numDots = 0;

        //try with resources statement means the resource (readers) must be closed after the
        // program is done with them
        try (BufferedReader buffRead = new BufferedReader(new FileReader(inputFile))) {
            //first line has the dimensions, read it in to array split by spaces
            String[] dimensions = buffRead.readLine().split(" ");
            //then pull out the height and width
            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            //great a new graph with the dimensions of the height and width
            Graph thisMaze = new Graph(height, width);
            //for each column
            for (int rowIn = 0; rowIn < height; rowIn++) {
                //read in the next line
                String nextLine = buffRead.readLine();
                //for each column
                for (int columnIn = 0; columnIn < width; columnIn++) {
                    //creat a new node passing in it's location on the graph and it's character value
                    if (nextLine.charAt(columnIn) == '.') {
                        numDots++;
                    }
                }
            }
        }
        return numDots;
    }
}
//
//}