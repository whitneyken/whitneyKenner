package assignment03;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class BinarySearchSet<E> implements SortedSet<E>, Iterable<E> {
    private E[] mySet;
    private int capacity;
    private int size;
    private boolean hasComparator;

    private Comparator<E> comparator_;
    private boolean canRemove = false;

    /* Constructor 1*/
    public BinarySearchSet() {
        //My chosen initial capacity
        capacity = 10;
        size = 0;
        //Have to cast my set to an set of elements
        mySet = (E[]) new Object[capacity];
        hasComparator = false;
    }

    /* this constructor is called if a comparator is passed in*/
    public BinarySearchSet(Comparator<? super E> comparator) {
        //initial capacity
        capacity = 10;
        size = 0;
        mySet = (E[]) new Object[capacity];
        //This is the past in comparator
        comparator_ = (Comparator<E>) comparator;
        hasComparator = true;
    }

    //Will return the comparator if this has one, otherwise returns null
    @Override
    public Comparator<? super E> comparator() {
        if (hasComparator) {
            return comparator_;
        } else {
            return null;
        }
    }

    //returns the index 0 element in an set
    @Override
    public E first() throws NoSuchElementException {
        //Check if the set is empty
        if (this.isEmpty()) {
            //return null if empty
            throw new NoSuchElementException();
        }
        //return the first object
        return this.mySet[0];
    }

    //returns the last element in the set
    @Override
    public E last() throws NoSuchElementException {
        //check if the set is empty
        if (this.isEmpty()) {
            //if so, return null
            throw new NoSuchElementException();
        }
        //return the last object
        return this.mySet[size - 1];


    }

    //
    @Override
    public boolean add(E element) {
        if (this.contains(element)) {
            return false;
        }
        if (this.size == this.capacity) {
            doubleSetCapacity();
        }
        if (isEmpty()) {
            mySet[0] = (E) element;
            size++;
            return true;
        } else {
            //call binary search to find the index we want to place the element
            int index = getIndexForInsertion(element);
            //call insertion sort to shift elements to the right and insert the desired element
            insertElement(index, element);
            size++;
            return true;
        }
    }

    //This method inserts the given element at the proper index location, it directly modifies the set
    private void insertElement(int index, E element) {
        //This goes through all elements at or above the index of the location to insert the given element
        for (int i = this.size; i > index; i--) {
            //shifts the elements to the right by 1
            mySet[i] = mySet[i - 1];
        }
        //this sets the element at the desired index after having shifted the other elements
        mySet[index] = (E) element;
    }

    //This method performs binary search to find the indices of where we want to place a new element.
    //The new element is passed as a parameter and an int is returned indicating the position in which it will be placed
    private int getIndexForInsertion(E element) {
        int low = 0;
        int high = size - 1;
        int mid = 0;
        while (low <= high) {
            //sets out midpoint to compare the index with our element
            mid = low + (high - low) / 2;
            //compares our given element to the element at the mid index location
            if (compare(mySet[mid], element) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    //Binary search will
    private int binarySearch(E element) {
        int low = 0;
        int high = size - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (mySet[mid].equals(element)) {
                return mid;
            } else if (compare(mySet[mid], element) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    //This compare function will compare the values of the passed in elements and return an int indicating which is larger
    private int compare(E elemInArray, E elemToPlace) {
        //checks if this has a comparator
        if (this.hasComparator) {
            //if has a comparator, compares based on that
            return comparator_.compare(elemInArray, elemToPlace);
        } else {
            //else compares based on the default comparator
            return ((Comparable<E>) elemInArray).compareTo((E) elemToPlace);
        }

    }

    //This method is called if the size matches the capacity, so expansion is necessary
    private void doubleSetCapacity() {
        //create a new set to store the values in
        E[] expandedCapacitySet = (E[]) new Object[capacity * 2];
        //copy over all the elements
        for (int eachElem = 0; eachElem < mySet.length; eachElem++) {
            expandedCapacitySet[eachElem] = mySet[eachElem];
        }
        //expand the capacity
        this.capacity *= 2;
        //mySet becomes the expanded set
        mySet = expandedCapacitySet;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean objectsAdded = false;
        for (E elem : elements) {
            add(elem);
            objectsAdded = true;
        }
        return objectsAdded;
    }

    //This method will remove all the elements from an set
    @Override
    public void clear() {
        //A VERY SNEAKY WAY OF MAKING GARBAGE COLLECTION clear this muah hah hah ah a
        capacity = 10;
        size = 0;
        mySet = (E[]) new Object[capacity];
        //make back up in case professors hate this:
    }

    //This method is called to verify that this set contains a specific element
    //the element being checked for is passed in as a parameter
    //A boolean is returned based on whether the element is contained or not
    @Override
    public boolean contains(E element) {
        //returns false if the size is 0, because then it definitely does not contain the passed in element
        if (size == 0) {
            return false;
        }
        int indexOfElem = binarySearch(element);
        //if the index is -1, the element does not exit, otherwise, it is contained and will return true
        return indexOfElem != -1;
    }

    //this method checks if all of a collection of given elements is contained in this collection
    //a collection of elements is passed it (The elements we want to verify are within this set or not)
    //A boolean is returned on whether they were all contained or not
    @Override
    public boolean containsAll(Collection<? extends E> elements) {
        boolean containsElem = false;
        //for each loop of the elements we are looking for
        for (E element : elements) {
            //If this set does not contain this element, return false
            if (this.contains(element)) {
                containsElem = true;
            }
        }
        //else it contains all of them and we return true
        return containsElem;
    }

    //This method checks and returns whether the data set is empty or not
    @Override
    public boolean isEmpty() {
        //Returns true if the data set length is greater than 0, else, returns false
        return (size == 0);
    }

    //This creates and returns a new data set iterator for use in other methods
    @Override
    public Iterator<E> iterator() {
        //call to create and return a new iterator
        return new DataSetIterator();
    }

    //This method with remove a specified element from our set of elements
    //The specified element to remove is passed in as a parameter
    @Override
    public boolean remove(E element) {
        if (size == 0) {
            return false;
        }
        //Create a new iterator to iterate over the elements
        Iterator<E> iterator = iterator();
        //While there is a next element in the set
        while (iterator.hasNext()) {
            //Get the next element
            E currentElem = iterator.next();
            //If the element equals the desired element
            if (currentElem.equals(element)) {
                //remove it
                iterator.remove();
                //decrease the size for each element removed
                //return true because it was successfully removed
                return true;

            }
        }
        //Was not found, so retrun false
        return false;
    }

    //!!!!!!!!!!!!!!!!!!!!!! what if not all elements are contained????!!!!!!!!!!!!!!!!!!!!
    //This method will attempt to remove all the given passed in elements
    //If the elements are all contained and removed from the set, it will return true
    //If the elements are not contained/cannot be removed, it will return false
    @Override
    public boolean removeAll(Collection<? extends E> elements) {
        boolean elementsRemoved = false;
        //first if all elements are not contained in this, return false
        //otherwise, for each element we want to remove
        for (E elemToRemove : elements) {
            if (contains(elemToRemove)) {
                //remove it, ignore returned boolean, irrelevant
               elementsRemoved =  remove(elemToRemove);

            }
        }
        //return true as all elements were successfully removed
        return elementsRemoved;
    }

    //returns the number of elements in this set
    @Override
    public int size() {
        return size;
    }

    //returns the sorted set of data for this object
    @Override
    public E[] toArray() {

        return mySet;
    }

    //This method returns the element at a given index, for use in testing
    public E get(int index) {
        return mySet[index];
    }

    //returns the capacity of this
    public int capacity() {
        return capacity;
    }

    private class DataSetIterator implements Iterator<E> {
        private int position = 0;

        //This method is called on an set of the binary search set and checks if there is a
        // next index element in the set
        @Override
        public boolean hasNext() throws NoSuchElementException {
            //checks if the current position is less than the length of our search set
            //returns true or false based on whether there is a next available index or not
            if (position == size) {
                throw new NoSuchElementException();
            } else {
                return position <= size - 1;
            }
        }

        //This method is called on an set of the binary search set and will return the next element in the set
        @Override
        public E next() throws NoSuchElementException {
            //This line verifies there is a next element in the set
            if (this.hasNext()) {
                //returns the element if it does exist
                canRemove = true;
                return mySet[position++];
            } else {
                //If there is no next element, returns null
                return null;
            }
        }

        @Override
        public void remove() throws IllegalStateException {
//            Iterator.super.remove();
            for (int i = position - 1; i < size; i++) {
                mySet[i] = mySet[i + 1];
            }
            mySet[size - 1] = null;
            canRemove = false;
            size--;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            Iterator.super.forEachRemaining(action);
        }
    }
}

