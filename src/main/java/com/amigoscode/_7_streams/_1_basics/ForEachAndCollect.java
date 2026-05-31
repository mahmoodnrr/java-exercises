package com.amigoscode._7_streams._1_basics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exercise: forEach and collect
 * <p>
 * Learn the two most common terminal operations: forEach for performing
 * an action on each element, and collect for gathering results into
 * a collection.
 */
public class ForEachAndCollect {

    public static void main(String[] args) {
        List<String> languages = List.of("Java", "Python", "JavaScript", "Go", "Rust", "Java", "Python");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // TODO: 1 - Use forEach to print each element of 'languages' on a separate line
        //           Use a lambda expression
        languages.forEach(e -> System.out.println(e));


        // TODO: 2 - Use forEach with a method reference to print each element of 'numbers'
        //           Use System.out::println
        numbers.forEach(System.out::println);


        // TODO: 3 - Filter 'numbers' to keep even numbers, then collect the results to a List
        //           Print the resulting list
        List<Integer> nums = numbers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList());
        System.out.println("nums = " + nums);

        // TODO: 4 - Collect 'languages' stream results to a Set (removes duplicates automatically)
        //           Print the resulting set
        System.out.println();
        languages.stream().collect(Collectors.toSet()).forEach(System.out::println);


        // TODO: 5 - Collect 'numbers' stream to an unmodifiable list
        //           Use Collectors.toUnmodifiableList() or .toList()
        //           Print the result
        System.out.println();
        numbers.stream().toList().forEach(System.out::println);

        // TODO: 6 - Use Collectors.toCollection() to collect 'languages' into a LinkedList
        //           Print the resulting LinkedList and its class name to verify the type
        //           Hint: Collectors.toCollection(LinkedList::new)
        System.out.println();
        LinkedList<String> linkedList = languages.stream().collect(Collectors.toCollection(LinkedList::new));
        System.out.println("linkedList = " + linkedList);

    }
}
