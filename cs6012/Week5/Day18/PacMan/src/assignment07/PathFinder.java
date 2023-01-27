package assignment07;

import java.io.*;
import java.util.LinkedList;
import java.util.MissingFormatArgumentException;
import java.util.Queue;

public class PathFinder {

    //static primary method to solve maze. take a string of the input file name to read from and a string of the
    //output file name to write to. throws IOEException on failure to open either file
    public static void solveMaze(String inputFile, String outputFile) throws IOException {
        if (inputFile == null || outputFile == null || inputFile.equals("") || outputFile.equals("") ){
            throw new IllegalArgumentException();
        }
        //try with resources statement means the resource (readers) must be closed after the
        // program is done with them
        try (BufferedReader buffRead = new BufferedReader(new FileReader(inputFile))) {
            //first line has the dimensions, read it in to array split by spaces
            String[] dimensions = buffRead.readLine().split(" ");
            //then pull out the height and width
            if (dimensions.length <2 ){
                throw new IllegalArgumentException();
            }
            int height = Integer.parseInt(dimensions[0]);
            int width = Integer.parseInt(dimensions[1]);
            //great a new graph with the dimensions of the height and width
            Graph thisMaze = new Graph(height, width);
            //for each column

            for (int rowIn = 0; rowIn < height; rowIn++) {
                //read in the next line
                String nextLine = buffRead.readLine();
                if (nextLine == null || nextLine.equals("")){
                    throw new IllegalArgumentException();
                }
                //for each column
                for (int columnIn = 0; columnIn < width; columnIn++) {
                    //creat a new node passing in it's location on the graph and it's character value
                    thisMaze.createNode(rowIn, columnIn, nextLine.charAt(columnIn));
                }
            }
            //once the graph has been made, add neighbors to each node
            thisMaze.addNeighbors();
            //call to solve the maze, if it's not already solved???
            BFS(thisMaze.getStartNode());
            //another try with resources to open the output file and write to it. resources will be closed
            // when the program is done
            try (PrintWriter output = new PrintWriter(new FileWriter(outputFile))) {
                //prints the height and width on the first line
                output.println(height + " " + width);
                //for each row
                for (int rowOut = 0; rowOut < height; rowOut++) {
                    //for each column within thar row
                    for (int columnOut = 0; columnOut < width; columnOut++) {
                        //if the value stored in this node is null, print out X
                        if (thisMaze.get(rowOut, columnOut).getValue() == null) {
                            output.print("X");
                        } else {
                            //otherwise print out whatever value is stored in that node
                            output.print(thisMaze.get(rowOut, columnOut).getValue());
                        }
                    }
                    //new line for each row
                    output.println();
                }
            }
        }


    }

    //the actual solving method that finds a path from the start to end and updated the nodes with the path
    //takes the start node as input
    private static void BFS(Node startNode) {
        //new queue as a linked list for storing nodes to check for the end node
        Queue<Node> nodeQueue = new LinkedList<>();
        startNode.setVisited(true); //mark start node as visited
        nodeQueue.add(startNode); //add start node to queue
        while (!nodeQueue.isEmpty()) { //when the queue is empty we have looked at them all and not found the end
            Node currentNode = nodeQueue.poll(); //poll works like dequeue and takes the next node out (FIFO)
            if (currentNode.getValue() == 'G') { //if we find the end
                currentNode = currentNode.getCameFrom(); //get the final nodes previous so we don't overwrite the G
                //start previous is set to null, so while we haven't reached start
                while (currentNode.getCameFrom() != null) {
                    currentNode.setValue('.'); //set the value to a dot
                    currentNode = currentNode.getCameFrom(); //get the previous node to this node
                }
            } else { //otherwise
                for (Node neighbor : currentNode.getNeighbors()) { //for each neighbor node to the current node
                    if (!neighbor.getVisited()) { //if it hasn't been visited
                        neighbor.setVisited(true); //set visited to true
                        neighbor.setCameFrom(currentNode); //set the previous of the neighbor to this current node
                        nodeQueue.add(neighbor); //add neighbor to queue
                    }
                }
            }
        }
    }
}
