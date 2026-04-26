package com.amigoscode._4_datastructures._6_sets;

// Exercise: HashSet Operations
// Learn how to use HashSet - an unordered collection that contains no duplicate elements.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HashSetExercises {

    public static void main(String[] args) {

        // TODO: 1 - Create a HashSet of Strings called 'fruits'
        Set<String> fruits = new HashSet<>();

        // TODO: 2 - Add elements: "Apple", "Banana", "Cherry", "Apple", "Date", "Banana"
        //           Print the set after adding all elements
        //           Observe that duplicates are automatically ignored
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Apple");
        fruits.add("Date");
        fruits.iterator().forEachRemaining(System.out::println);


        // TODO: 3 - Check if "Cherry" exists in the set using contains()
        //           Print the result
        System.out.println("Check if \"Cherry\" exists " + fruits.contains("Cherry"));

        // TODO: 4 - Remove "Date" from the set
        //           Print the set after removal
        System.out.println("Remove \"Date\"" + fruits.remove("Date"));

        // TODO: 5 - Get and print the size of the set
        System.out.println("size " + fruits.size());

        // TODO: 6 - Iterate over the set using a for-each loop and print each element
        //           Note: the order may not match insertion order (HashSet is unordered)
        fruits.forEach(System.out::println);

        // --- Removing Duplicates from a List ---
        System.out.println("\n--- Removing Duplicates ---");
        List<String> listWithDuplicates = new ArrayList<>(
                Arrays.asList("Java", "Python", "Java", "C++", "Python", "Go", "Java")
        );
        System.out.println("Original list: " + listWithDuplicates);

        HashSet<String> noDup = new HashSet<>(listWithDuplicates);
        List<String> deduplicated = new ArrayList<>(noDup);
        System.out.println("Updated list: " + deduplicated);

        // TODO: 7 - Use a HashSet to remove duplicates from 'listWithDuplicates'
        //           Create a new HashSet from the list, then create a new ArrayList from the set
        //           Print the deduplicated list


    }
}
