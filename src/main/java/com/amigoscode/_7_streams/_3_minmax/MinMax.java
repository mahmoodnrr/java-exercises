package com.amigoscode._7_streams._3_minmax;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Exercise: Min and Max
 *
 * Learn how to find minimum and maximum elements in a stream
 * using min(), max(), and reduce(). These operations return Optional
 * since the stream might be empty.
 */
public class MinMax {

    record Person(String name, int age) {}

    public static void main(String[] args) {
        List<Integer> numbers = List.of(45, 12, 89, 3, 67, 34, 91, 23, 56);
        List<String> words = List.of("Java", "Streams", "Hi", "Programming", "API", "Go");
        List<Person> people = List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Charlie", 35),
                new Person("Diana", 22),
                new Person("Eve", 28)
        );
        List<Integer> emptyList = List.of();

        // TODO: 1 - Find the minimum value in 'numbers' using stream min()
        //           Use Comparator.naturalOrder() or Integer::compareTo
        //           Print the result
        Optional<Integer> min = numbers.stream().min(Integer::compareTo);
        System.out.println(min.orElse(-1));

        Optional<Integer> min2 = numbers.stream().min(Comparator.naturalOrder());
        System.out.println(min2.orElse(-1));


        // TODO: 2 - Find the maximum value in 'numbers' using stream max()
        //           Print the result
        Optional<Integer> max = numbers.stream().max(Comparator.naturalOrder());
        System.out.println("max = " + max.orElse(-1));


        // TODO: 3 - Find the shortest string in 'words' by comparing string length
        //           Use Comparator.comparingInt(String::length)
        //           Print the result
        Optional<String> minLength = words.stream().min(Comparator.comparingInt(String::length));
        System.out.println("minLength = " + minLength.orElse("Not found"));

        // TODO: 4 - Find the youngest person from 'people'
        //           Use Comparator.comparingInt(Person::age)
        //           Print the person's name and age
        Optional<Person> minAge = people.stream().min(Comparator.comparingInt(Person::age));
        minAge.ifPresent(System.out::println);

        // TODO: 5 - Find the maximum value in 'numbers' using reduce() instead of max()
        //           Use Integer::max as the binary operator
        //           Print the result
        Optional<Integer> reduce = numbers.stream().reduce(Integer::max);
        reduce.orElse(-1);

        // TODO: 6 - Handle the empty stream case: try to find min of 'emptyList'
        //           Use orElse() to provide a default value of -1
        //           Print the result
        Optional<Integer> list = emptyList.stream().min(Integer::min);
        System.out.println("emptyList = " + list.orElse(-1));

    }
}
