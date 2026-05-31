package com.amigoscode._7_streams._6_transformations;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exercise: Map Transformations
 * <p>
 * Practice using map(), mapToInt(), and mapToDouble() to transform
 * stream elements. Learn to chain map operations and use method references.
 */
public class MapExercise {

    record Person(String name, int age, String email) {
    }

    record PersonDTO(String name, String email) {
    }

    record Product(String name, double price, int quantity) {
    }

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alice", 30, "alice@example.com"),
                new Person("Bob", 25, "bob@example.com"),
                new Person("Charlie", 35, "charlie@example.com"),
                new Person("Diana", 28, "diana@example.com")
        );

        List<Product> products = List.of(
                new Product("Laptop", 999.99, 2),
                new Product("Mouse", 29.99, 10),
                new Product("Keyboard", 79.99, 5),
                new Product("Monitor", 449.99, 3)
        );

        List<String> sentences = List.of(
                "Hello World",
                "Java Streams",
                "Functional Programming"
        );

        // TODO: 1 - Map 'people' to extract just their names
        //           Collect to a list and print it
        List<String> collect = people.stream().map(Person::name).collect(Collectors.toList());
        System.out.println("collect = " + collect);

        // TODO: 2 - Map Person objects to PersonDTO objects (dropping the age field)
        //           Collect to a list and print each DTO
        people.stream().map(p -> new PersonDTO(p.name, p.email)).forEach(System.out::println);

        // TODO: 3 - Use mapToInt to get the ages of all people
        //           Calculate and print the sum of ages
        int sum = people.stream().mapToInt(Person::age).sum();
        System.out.println("sum = " + sum);

        // TODO: 4 - Use mapToDouble to get all product prices
        //           Calculate and print the sum of prices
        double sum1 = products.stream().mapToDouble(product -> product.price()).sum();
        System.out.println("sum1 = " + sum1);

        // TODO: 5 - Chain map operations on 'sentences':
        //           First map to lowercase, then map to the first word only (split by space)
        //           Print each result
        sentences.stream()
                .map(sentence -> sentence.toLowerCase())
                .map(sentence -> sentence.split(" "))
                .forEach(System.out::println);

        // TODO: 6 - Use map with a method reference: map 'sentences' to uppercase
        //           using String::toUpperCase
        //           Collect to a list and print it
        List<String> list = sentences.stream()
                .map(sentence -> sentence.toUpperCase())
                .toList();
        System.out.println("list = " + list);

    }
}
