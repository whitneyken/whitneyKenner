package assignment07;

import java.util.ArrayList;

public class Node {
    private Character value;
    private boolean visited;
    private ArrayList<Node> adjacentNodes;
    private Node cameFrom;

    //constructor take in a character to be stored as the value
    Node(Character val){
        value = val;
        visited = false;
        adjacentNodes = new ArrayList<>();
        //set to 4
    }

    //getter for value, returns a character
    public Character getValue(){
        return value;
    }
    //setter for value, takes in a character to be set as the value
    public void setValue(Character val){
        value = val;
    }
    //getter for neighbors, returns an arraylist of nodes
    public ArrayList<Node> getNeighbors(){
        return adjacentNodes;
    }
    //setter for neighbors, takes in a nodes and adds it to this nodes list of neighbors
    public void setNeighbor(Node neighbor){
        adjacentNodes.add(neighbor);
    }
    //getter for visited, returns a boolean
    public boolean getVisited(){
        return visited;
    }
    //setter for visited, takes a boolean and sets it to the visited status
    public void setVisited(boolean status){
        visited = status;
    }
    //getter for previous node, returns a node
    public Node getCameFrom(){
        return cameFrom;
    }
    //setter for previous, takes in a node and sets this previous node to it
    public void setCameFrom(Node prevNode){
        cameFrom = prevNode;
    }
}
