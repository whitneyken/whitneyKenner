package assignment07;

public class Graph {
    private Node[][] maze;
    private int height_;
    private int width_;
    private Node startNode;

    //contractor takes in an int for height and width of the matrices
    Graph(int height, int width) {
        maze = new Node[height][width];
        height_ = height;
        width_ = width;
    }

    //this method creates a new node. it takes in an int for the row index, int for the
    //column index and the value to be stored
    public void createNode(int rowIndex, int columnIndex, int charVal) {
        //if the value is X, set a node to null and store it
        if ((char) charVal == 'X') {
            maze[rowIndex][columnIndex] = new Node(null);
        } else if ((char) charVal == 'S') {
            startNode = new Node('S');
            startNode.setCameFrom(null); //set previous node of start node to null so we know when we reach the end
            maze[rowIndex][columnIndex] = startNode;
        } else if ((char) charVal == 'G') {
            maze[rowIndex][columnIndex] = new Node('G');
        } else {
            maze[rowIndex][columnIndex] = new Node(' ');
        }

    }

    //helper method to print the map to the console
    void printMap() {
        for (int i = 0; i < height_; i++) {
            for (int j = 0; j < width_; j++) {
                if (maze[i][j].getValue() == null) {
                    System.out.print("X");
                } else if (maze[i][j].getValue() == ' ') {
                    System.out.print(" ");
                } else {
                    System.out.print(maze[i][j].getValue());
                }
            }
            System.out.println();
        }
    }

    //getter method returns the start node or throws a null ppointer exception if it's null
    public Node getStartNode() throws NullPointerException {
        if (startNode == null) {
            throw new NullPointerException();
        } else {
            return startNode;
        }
    }


    //method for finding and adding all nodes neighbors
    public void addNeighbors() {
        //starting and ending inside the boundaries of the maze
        for (int row = 1; row < height_ - 1; row++) {
            for (int column = 1; column < width_ - 1; column++) {
                if (maze[row][column].getValue() != null) { //if the value of this node isn't null check for neighbors
                    if (maze[row + 1][column].getValue() != null) { //if above neighbor isn't null
                        maze[row][column].setNeighbor(maze[row + 1][column]); //add neighbor
                    }
                    if (maze[row - 1][column].getValue() != null) {//if below neighbor isn't null
                        maze[row][column].setNeighbor(maze[row - 1][column]); //add neighbor
                    }
                    if (maze[row][column + 1].getValue() != null) { //if right neighbor isn't null
                        maze[row][column].setNeighbor(maze[row][column + 1]); //add neighbor
                    }
                    if (maze[row][column - 1].getValue() != null) { //if left neighbor isn't null
                        maze[row][column].setNeighbor(maze[row][column - 1]); //add neighbor
                    }
                }
            }
        }
    }

    //getter for not. takes in the indexes of the node we want to get and returns that node
    public Node get(int rowIndex, int columnIndex) {
        return maze[rowIndex][columnIndex];
    }

    //helper method for testing returns the number of dots (aka the solution to a maze)

}

//make node a child in the graph class

