package com.amigoscode._4_datastructures._5_linkedlists;

// Exercise: LinkedList vs ArrayList Performance Comparison
// Understand when to use LinkedList vs ArrayList by measuring operation times.

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LinkedListVsArrayList {

    public static void main(String[] args) {

        // TODO: 1 - Create both an ArrayList<Integer> and a LinkedList<Integer>
        //           Fill each with 10000 elements (0 to 9999) using a for loop
        List<Integer> numbersArr = new ArrayList<>();
        List<Integer> numbersLink = new LinkedList<>();

        for (int i = 0; i < 10000; i++) {
            numbersArr.add(i);
            numbersLink.add(i);
        }

        // TODO: 2 - Measure time to add an element at the beginning (index 0) for both lists
        //           Use System.nanoTime() before and after the operation
        //           Perform the add(0, value) operation 1000 times for each list
        //           Print the time taken for each in milliseconds
        //           (divide nanoseconds by 1_000_000 to get milliseconds)

        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) numbersArr.add(0, i);
        long alAddBegin = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < 1000; i++) numbersLink.add(0, i);
        long llAddBegin = System.nanoTime() - start;

        System.out.println("Add at beginning - ArrayList: " + alAddBegin / 1_000_000 + "ms, LinkedList: " + llAddBegin / 1_000_000 + "ms");

        // TODO: 3 - Measure time to add an element at the end for both lists
        //           Perform the add(value) operation 1000 times for each list
        //           Print the time taken for each

        long start1 = System.nanoTime();
        for (int i = 0; i < 1000; i++) numbersArr.add(i);
        long alAddBegin1 = System.nanoTime() - start1;

        start1 = System.nanoTime();
        for (int i = 0; i < 1000; i++) numbersLink.add(i);
        long llAddBegin1 = System.nanoTime() - start1;

        System.out.println("Add at last - ArrayList: " + 1 / 1_000_000 + "ms, LinkedList: " + llAddBegin1 / 1_000_000 + "ms");

        // TODO: 4 - Measure time to get an element at the middle index for both lists
        //           Perform get(list.size() / 2) operation 1000 times for each list
        //           Print the time taken for each
        long start2 = System.nanoTime();
        for (int i = 0; i < 1000; i++) numbersArr.get(numbersArr.size() / 2);
        long alAddBegin2 = System.nanoTime() - start2;

        start2 = System.nanoTime();
        for (int i = 0; i < 1000; i++) numbersLink.get(numbersLink.size() / 2);
        long llAddBegin2 = System.nanoTime() - start2;

        System.out.println("get middle - ArrayList: " + alAddBegin2 / 1_000_000 + "ms, LinkedList: " + llAddBegin2 / 1_000_000 + "ms");

        // TODO: 5 - Print a summary explaining the differences
        //           Use System.out.println() to explain:
        //           - Why LinkedList is faster for insertions at the beginning
        //           - Why ArrayList is faster for random access (get by index)
        //           - When you would choose one over the other

    }
}
