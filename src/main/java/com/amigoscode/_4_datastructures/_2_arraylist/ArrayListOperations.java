package com.amigoscode._4_datastructures._2_arraylist;

// Exercise: ArrayList Operations
// Learn advanced ArrayList operations including sorting, reversing, and converting.

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayListOperations {

    public static void main(String[] args) {

        // TODO: 1 - Create an ArrayList of Integers called 'numbers'
        //           Add these 10 numbers: 45, 12, 78, 34, 56, 23, 89, 67, 11, 90

        List<Integer> numbers = new ArrayList<>();
        numbers.add(45);
        numbers.add(12);
        numbers.add(78);
        numbers.add(34);
        numbers.add(56);
        numbers.add(23);
        numbers.add(89);
        numbers.add(67);
        numbers.add(11);
        numbers.add(90);

        // TODO: 2 - Sort the list in ascending order using Collections.sort()
        //           Print the sorted list
        Collections.sort(numbers);
        System.out.println("numbers sorted = " + numbers);

        // TODO: 3 - Reverse the list using Collections.reverse()
        //           Print the reversed list
        Collections.reverse(numbers);
        System.out.println("numbers reversed = " + numbers);

        // TODO: 4 - Find and print the maximum value using Collections.max()
        Collections.max(numbers);
        System.out.println("numbers max = " + numbers);

        // TODO: 5 - Create a subList containing the first 5 elements (indices 0 to 4)
        //           Print the subList
        //           Note: subList returns a view, not a copy

        System.out.println("subList = " + numbers.subList(0, 4));

        // TODO: 6 - Convert the ArrayList to an Integer array using toArray()
        //           Print the array length to verify
        System.out.println(numbers.toArray().length);

        // TODO: 7 - Clear the list using clear() and verify it's empty
        //           Print the size and the result of isEmpty()
        numbers.clear();
        System.out.println("numbers clear = " + numbers);
    }
}
