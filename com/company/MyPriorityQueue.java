package com.company;

/**
 * Custom priority queue, with similar functionality as the java.util one
 */
public class MyPriorityQueue {

    /**
     * A heap of type pair, represented by an array of pairs.
     */
    private Pair[] pairHeap;

    /**
     * The current size of the priority queue, represented as an int.
     */
    private int size;

    /**
     * The current capacity of the priority queue, represented as an int.
     */
    private int capacity;


    /**
     * No parameter constructor.
     * Sets properties to default values.
     */
    public MyPriorityQueue() {

    }

    /**
     * One parameter constructor.
     * Sets the capacity to the desired one and other properties to default
     * values.
     *
     * @param capacity The capacity that the priority queue will have.
     */
    public MyPriorityQueue(int capacity) {
        pairHeap = new Pair[capacity];
        this.capacity = capacity;
    }

    /**
     * Gets the heap of pairs.
     *
     * @return The heap of pairs, represented as an array of pairs.
     */
    public Pair[] getPairHeap() {
        return pairHeap;
    }

    /**
     * Sets the heap of pairs.
     *
     * @param pairArray The heap of pairs that will be set, represented as an
     *                 array of pairs.
     */
    public void setPairHeap(Pair[] pairArray) {
        this.pairHeap = pairArray;
    }

    /**
     * Gets the current size of the priority queue.
     *
     * @return The current size of the priority queue, represented as an int.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of the priority queue.
     *
     * @param size The size of the priority queue to be set, represented as
     *             an int.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the capacity of the priority queue.
     *
     * @return The capacity of the priority queue, represented as an int.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Sets the capacity of the priority queue.
     *
     * @param capacity The capacity of the priority queue to be set,
     *                 represented as an int.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Checks if the priority queue is empty or not.
     *
     * @return A boolean value that represents if the priority queue is empty
     * or not.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the priority queue is full or not.
     *
     * @return A boolean value that represents if the priority queue is full
     * or not.
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Removes the highest priority element from the priority queue and
     * returns it.
     *
     * @return The highest priority element from the priority queue,
     * represented as an object of type Pair.
     */
    public Pair poll() {

        // Empty heap
        if (isEmpty()) return null;

        Pair p = pairHeap[0];

        //Moves all elements one to the left
        size--;
        int pos = 1;
        while (pos <= size) {
            pairHeap[pos - 1] = pairHeap[pos];
            pos++;
        }
        return p;
    }

    /**
     * Adds a new element to the priority queue.
     *
     * @param p An element to be added to the priority queue, represented by
     *          an object of type Pair.
     */
    public void offer(Pair p) {

        // Full heap
        if (isFull()) return;

        //Puts the new pair at the end of the heap
        pairHeap[size] = p;
        int pos = size;

        //Moves the new pair along the heap towards the right position
        while (pos > 0 && pairHeap[pos].compareTo(pairHeap[pos - 1]) < 0) {
            swap(pos);
            pos--;
        }
        size++;
    }

    /**
     * Private helper method.
     * Swaps the positions of 2 elements inside the heap.
     *
     * @param pos The position at which the swap starts, represented as an int.
     *            The second position will be pos - 1;
     */
    private void swap(int pos) {
        Pair temp = pairHeap[pos];
        pairHeap[pos] = pairHeap[pos - 1];
        pairHeap[pos - 1] = temp;
    }
}
