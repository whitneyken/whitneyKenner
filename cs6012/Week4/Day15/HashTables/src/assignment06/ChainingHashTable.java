package assignment06;

import java.util.Collection;
import java.util.LinkedList;

public class ChainingHashTable implements Set<String> {
    private LinkedList<String>[] storage;

    private HashFunctor functor_;
    private int capacity_;
    private int size_;
    private int totalCollisions;

    //this is the constructor, it takes in a hash functor and a capacity as input
    @SuppressWarnings("unchecked")
     ChainingHashTable(int capacity, HashFunctor functor) {
        //the array of linked lists is initialized based on the input capacity
        storage = (LinkedList<String>[]) new LinkedList[capacity];
        //store the functor
        if (functor == null) {
            throw new NullPointerException();
        } else {
            functor_ = functor;
        }
        //store the capacity
        capacity_ = capacity;
        //initialize each linkedList within the array
        for (int i = 0; i < capacity_; i++) {
            storage[i] = new LinkedList<>();
        }
        //initialize the size to 0
        size_ = 0;
    }

    //this method takes in a string as input and returns a boolean based on whether the string was added or not
    @Override
    public boolean add(String item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        //if the item is already contained or the item is null, it will not be added and we return false
        if (contains(item)) {
            return false;
            //otheriwise
        } else {
            //use the functor to get the index where we will add this in the array
            int index = Math.floorMod(functor_.hash(item), capacity_);
            //add the item to the linked list
            if (storage[index].size() > 0){
                totalCollisions++;
            }
            storage[index].add(item);
            //increase the size
            size_++;
            return true;
        }
    }

    //this method takes in a collection of strings and returns true if the storage is modified as a result of this
    @Override
    public boolean addAll(Collection<? extends String> items) throws NullPointerException {
        if (items == null) {
            throw new NullPointerException();
        }
        if (items.size() == 0) {
            return false;
        }
        //storage boolean to see if changes are made
        boolean isChanged = false;
        //for each string
        for (String item : items) {
            if (item == null) {
                throw new NullPointerException();
            }
            //if adding it returns true
            if (add(item)) {
                //storage boolean is changed!
                isChanged = true;
            }
        }
        return isChanged;
    }

    //this method clears the storage array of linked lists
    @Override
    public void clear() {
        //for each linked list in the storage array
        for (int i = 0; i < capacity_; i++) {
            storage[i].clear();
        }
        size_ = 0;

    }

    //this method takes in a string and returns a boolean based on whether the string is contained within the
    //storage array or not
    @Override
    public boolean contains(String item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        //if the item isn't null and the storage array is not empty
        if (isEmpty()) {
            return false;
        }
        //index of where an item should be
        int index = Math.floorMod(functor_.hash(item), capacity_);
        //for each linked list item
        return storage[index].contains(item);

        //makes it here only if it is not found or initial conditions are not met

    }

    //this method checks if all the strings passed in are contained within the storage array, if so, returns
    //true, else returns false
    @Override
    public boolean containsAll(Collection<? extends String> items) throws NullPointerException {
        //if the items is null or there are no items in it, return false before we even have to check
        if (items == null) {
            throw new NullPointerException();
        }
        if (items.size() == 0) {
            return false;
        }
        if (isEmpty()) {
            return false;
        }
        //for each string passed in
        for (String item : items) {
            if (item == null) {
                throw new NullPointerException();
            }
            //if the item is not contained or the item is null, return false
            if (!contains(item)) {
                return false;
            }
        }
        //only makes it here if everything is contained
        return true;
    }

    //this method returns a boolean whether the storage array is empty or not
    @Override
    public boolean isEmpty() {
        //only empty if the size is equal to 0
        return size_ == 0;
    }

    //this method takes in a string to be removed and returns a boolean on whether the storage array was modified as a
    //result of this method
    @Override
    public boolean remove(String item) throws NullPointerException {
        if (item == null) {
            throw new NullPointerException();
        }
        //first check if the item is contained, is not null and storage is not empty
        int index = Math.floorMod(functor_.hash(item), capacity_);
        if (storage[index].remove(item)) {
            size_--;
            return true;
        }
        return false;

    }

    //this method takes in a collection of strings a sa parameter and returns true only if the storage
    //array is modified as a result of removal
    @Override
    public boolean removeAll(Collection<? extends String> items) throws NullPointerException {
        //if the collection passed in is empty, we cannot modify anything anyway, so return false
        if (items == null) {
            throw new NullPointerException();
        }
        if (items.size() == 0) {
            return false;
        }
        boolean isChanged = false;
        //otherwise for each item
        for (String item : items) {
            if (item == null) {
                throw new NullPointerException();
            }
            //if remove returns true
            if (remove(item)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    //this method returns an int of the private member variable size
    @Override
    public int size() {
        return size_;
    }


    //this was a helper method for me to display the storage array
    public void display() {
        for (int i = 0; i < capacity_; i++) {
            if (storage[i] != null) {
                for (int j = 0; j < storage[i].size(); j++) {
                    System.out.println("The value at index " + i + " and linkedList index " + j + " is " + storage[i].get(j));
                }
            }
        }
    }

    public int numCollisions() {
        int totalCollisions = 0;
        int numSlots = 0;
        for (int i = 0; i < capacity_; i++) {
            if (storage[i] != null) {
                if (storage[i].size() > 1) {
                    totalCollisions += storage[i].size();
                    numSlots++;
                }

            }
        }
        totalCollisions -= numSlots;
        //System.out.println("The total number of collisions is: " + totalCollisions);
        return totalCollisions;
    }
}
