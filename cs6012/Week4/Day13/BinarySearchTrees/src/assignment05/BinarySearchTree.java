package assignment05;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> implements SortedSet<T> {
    private BinaryNode<T> root;
    private int size;

    //default constructor that takes no parameters and sets the root to null
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    //another constructor that takes in an item and sets it to be the root of the binary search tree
    public BinarySearchTree(T item) {
        root = new BinaryNode<>(item);
    }

    //add method which takes the item to be added as input and returns a boolean.
    //if the item is null, a null pointer exception is thrown
    @Override
    public boolean add(T item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        //if the root is null, we create a new root
        if (root == null) {
            root = new BinaryNode<>(item);
            size++;
            return true;
        } else {
            //otherwise we call recursive add which determines where the element is added (and IF it is added)
            return recursivelyAddItem(item, root);
        }
    }

    //recursive addItem, takes in the node to check and the item to add
    private boolean recursivelyAddItem(T item, BinaryNode<T> node) {
        //if the key of the node we are on is the item we are trying to add, return false because we cannot have duplicates
        if (node.key.equals(item)) {
            return false;
        }
        //if the item is less than the node key we are on, we check the left node
        if (item.compareTo(node.key) < 0) {
            //if the left node is null, we create a new BinaryNode and attach it and return true
            if (node.left == null) {
                node.left = new BinaryNode<>(item);
                size++;
                return true;
            } else {
                //otherwise recursively call this method with the left node
                return recursivelyAddItem(item, node.left);
            }
            //otherwise do the same thing on the right side if the item is greater than the node key
        } else {
            if (node.right == null) {
                node.right = new BinaryNode<>(item);
                size++;
                return true;
            } else {
                return recursivelyAddItem(item, node.right);
            }
        }
    }

    //addAll take a collection of items and will return true if the binary Search tree is modified as a result
    @Override
    public boolean addAll(Collection<? extends T> items) throws NullPointerException {
        //throws a null pointer exception if the collection is null
        if (items == null) {
            throw new NullPointerException();
        }
        //this boolean is used to check if the tree is modified
        boolean isChanged = false;
        //for each item
        for (T item : items) {
            //attempts to add each item
            if (add(item)) {
                //if an item is added and returns true, the boolean is changed to true
                isChanged = true;
            }
        }
        return isChanged;
    }

    //clears the tree
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    //this method checks if an item is found within the tree. it takes in the item as parameters and returns true
    //if the item is found, false if it is not, or throws an null pointer exception if the item passed in is null
    @Override
    public boolean contains(T item) throws NullPointerException {
        //throws exception if the item is null
        if (item == null) {
            throw new NullPointerException();
        }
        //if the root is null, the item is not contained
        if (root == null) {
            return false;
        } else {
            //otherwise recursively search the tree for the item
            return recursiveContains(item, root);
        }
    }

    //this is the recursive method for contains. takes in the item being searched for as well as the node being checked
    //returns a boolean on whether it is found
    private boolean recursiveContains(T item, BinaryNode<T> node) {
        //returns true if the key of the node passed in is the same as the item being searched for
        if (item.equals(node.key)) {
            return true;
        }
        // if the item is less than the current node key
        if (item.compareTo(node.key) < 0) {
            //if there is nothing in the left node of this node
            if (node.left == null) {
                //return false because we have reached the end
                return false;
            } else {
                //otherwise recursively call this on the left node
                return recursiveContains(item, node.left);
            }
            //otherwise try the same thing on the right side if the item is greater than the node
        } else {
            if (node.right == null) {
                return false;
            } else {
                return recursiveContains(item, node.right);
            }
        }
    }

    //containsAll takes in a collection of items and returns true if all items are contained, otherwise
    //returns false
    @Override
    public boolean containsAll(Collection<? extends T> items) throws NullPointerException {
        //if the collection passed in is null, a null pointer exception is thrown
        if (items == null) {
            throw new NullPointerException();
        }
        //for each item in the collection
        for (T item : items) {
            //if it is not contained
            if (!contains(item)) {
                return false;
            }
        }
        //this will only be reached if all elements are contained
        return true;
    }

    //this method returns the smallest element in a tree
    @Override
    public T first() throws NoSuchElementException {
        //if the root is null, throws a no such element exception
        if (root == null) {
            throw new NoSuchElementException();
        }
        //otherwise call recursive method to find the smallest
        return recursiveFindSmallest(root);
    }

    //this method recursively finds the smallest element in a tree and returns it
    private T recursiveFindSmallest(BinaryNode<T> node) {
        //if the left node is empty, this is our base case (the smallest element) and we return it
        if (node.left == null) {
            return node.key;
        } else {
            //otherwise continue down to the left
            return recursiveFindSmallest(node.left);
        }
    }

    //returns true if the boolean is empty, otherwise returns false
    @Override
    public boolean isEmpty() {
        return (root == null);
    }

    //returns the largest element in the tree
    @Override
    public T last() throws NoSuchElementException {
        //if the root is null, throw a no such element exception
        if (root == null) {
            throw new NoSuchElementException();
        } else {
            //otherwise recursively find the largest element
            return recursiveFindLargest(root);
        }
    }

    //this method recursively finds the largest element in this tree. it takes in a node as a parameter
    private T recursiveFindLargest(BinaryNode<T> node) {
        //the base case is if the node has no right node, the largest element has been reached and will be returned
        if (node.right == null) {
            return node.key;
        } else {
            //otherwise call this method passing in this nodes right node
            return recursiveFindLargest(node.right);
        }
    }

    //this method takes in an item to remove as a parameter and returns a boolean on whether the element
    //was removed or not
    @Override
    public boolean remove(T item) throws NullPointerException {
        //if the item is null, a null pointer exception is thrown
        if (item == null) {
            throw new NullPointerException();
        }
        //if the item is not contained, return false
        if (!contains(item)) {
            return false;
        } else {
            //otherwise call the recursive remove which returns a node that is stored as the root
            root = recursiveRemove(item, root);
            //decrement size
            size--;
            return true;
        }
    }

    //this is the recursive method call for remove, takes in the item to be removed as well as a node and returns a node
    private BinaryNode<T> recursiveRemove(T item, BinaryNode<T> node) {
        //base case: if the node is null, return the node
        if (node == null) {
            return node;
        }
        //if the item is less that the node's kay, recursively call this method moving to the left node
        if (item.compareTo(node.key) < 0) {
            node.left = recursiveRemove(item, node.left);
            //otherwise if the item is greater, recursively call this method moving down to the right
        } else if (item.compareTo(node.key) > 0) {
            node.right = recursiveRemove(item, node.right);
            //otherwise (this is reached once we have found the item)
        } else {
            //node with 1 or 0 children
            //if there is no left node, return the right
            if (node.left == null) {
                return node.right;
                //if there is no right node, return the left
            } else if (node.right == null) {
                return node.left;
            }
            //node with 2 children
            //finds the successor (smallest val in right tree)
            node.key = smallestVal(node.right);
            //deletes the OG version of the successor
            node.right = recursiveRemove(node.key, node.right);
        }
        return node;
    }

    //this method finds the successor to replace the element being deleted. a node is passed in and the
    // key of the item is returned
    private T smallestVal(BinaryNode<T> node) {
        //minVal is set to the first node's key
        T minVal = node.key;
        //while there is a left node to travel to
        while (node.left != null) {
            //replace minVal with the next smallest value
            minVal = node.left.key;
            //traverse to the left
            node = node.left;
        }
        //return the smallest value found
        return minVal;
    }

    //this method takes in a collection of items and returns a boolean. the method returns true if the tree is changed by
    //this method and returns false otherwise.
    @Override
    public boolean removeAll(Collection<? extends T> items) throws NullPointerException{
        boolean isChanged = false;
        //for each item
        for (T item : items) {
            //if the item is null, throws a null pointer exception
            if (item == null){
                throw new NullPointerException();
            }else {
                //otherwise calls remove on the item. if successfully removed, is changed is true
                if (remove(item)){
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    //returns the size of the tree
    @Override
    public int size() {
        return size;
    }
    public BinaryNode<T> root(){
        return root;
    }

    @Override
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayOfTree = new ArrayList<>(size);
        inOrderTraversal(root, arrayOfTree);
        return arrayOfTree;
    }

    private void inOrderTraversal(BinaryNode<T> node, ArrayList<T> array) {
        if (node == null){
            return;
        }
        inOrderTraversal(node.left, array);
        array.add(node.key);
        inOrderTraversal(node.right, array);




    }
}
