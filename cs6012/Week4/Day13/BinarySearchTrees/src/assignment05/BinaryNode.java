package assignment05;

import org.w3c.dom.Node;

public class BinaryNode<T>{
    T key;
    BinaryNode<T> left;
    BinaryNode<T> right;

    public BinaryNode(T item){
        key = item;
        left = null;
        right = null;

    }
}
