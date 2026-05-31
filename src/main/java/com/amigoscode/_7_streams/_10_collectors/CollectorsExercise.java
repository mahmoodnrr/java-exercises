package com.amigoscode._7_streams._10_collectors;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * Exercise: Advanced Collectors
 * <p>
 * Practice using advanced collectors including toMap(), toUnmodifiableList(),
 * partitioningBy(), collectingAndThen(), and teeing().
 */
public class CollectorsExercise {

    record Person(String name, int age, String city) {
    }

    record Product(String name, double price, String category) {
    }

    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie", "Diana", "Eve");

        List<Person> people = List.of(
                new Person("Alice", 30, "London"),
                new Person("Bob", 25, "Paris"),
                new Person("Charlie", 35, "London"),
                new Person("Diana", 22, "Berlin"),
                new Person("Eve", 28, "Paris")
        );

        List<Product> products = List.of(
                new Product("Laptop", 999.99, "Electronics"),
                new Product("Mouse", 29.99, "Electronics"),
                new Product("Desk", 299.99, "Furniture"),
                new Product("Chair", 199.99, "Furniture"),
                new Product("Keyboard", 79.99, "Electronics")
        );

        List<Person> peopleWithDuplicateCities = List.of(
                new Person("Alice", 30, "London"),
                new Person("Bob", 25, "London"),
                new Person("Charlie", 35, "Paris")
        );

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // TODO: 1 - Collect 'names' to an unmodifiable list
        //           Use Collectors.toUnmodifiableList() or stream .toList()
        //           Print the result and try to verify it's unmodifiable
        //           (optional: try adding to it and catch UnsupportedOperationException)
        List<String> unmodifiableList = names.stream().collect(Collectors.toUnmodifiableList());

//        try {
//            unmodifiableList.add("test");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


        // TODO: 2 - Collect 'people' to a Map where key is name and value is age
        //           Use Collectors.toMap(Person::name, Person::age)
        //           Print the resulting map
        Map<String, Integer> peopleMap = people.stream().collect(Collectors.toMap(Person::name, Person::age));
        peopleMap.forEach((k, v) -> System.out.println(k + " " + v));

        // TODO: 3 - Handle duplicate keys in toMap:
        //           Collect 'peopleWithDuplicateCities' to a Map<String, String>
        //           where key is city and value is name
        //           Since London appears twice, use a merge function: (name1, name2) -> name1 + ", " + name2
        //           Print the result
        Map<String, String> dupCities = peopleWithDuplicateCities.stream()
                .collect(Collectors.toMap(Person::city, Person::name,
                        (name1, name2) -> name1 + ", " + name2));
        System.out.println("dupCities = " + dupCities);

        // TODO: 4 - Collect 'people' to a ConcurrentMap using Collectors.toConcurrentMap()
        //           Key: name, Value: city
        //           Print the result and its class name
        ConcurrentMap<String, String> concurrentMap = people.stream()
                .collect(Collectors
                        .toConcurrentMap(Person::name, Person::city));
        System.out.println("concurrentMap = " + concurrentMap);

        // TODO: 5 - Use Collectors.partitioningBy to split 'numbers' into
        //           even (true) and odd (false) groups
        //           Print both partitions
        Map<Boolean, List<Integer>> partioning = numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("partioning = " + partioning);

        // TODO: 6 - Use Collectors.collectingAndThen to collect 'names' to a list
        //           and then transform the result to its size (count)
        //           Hint: collectingAndThen(Collectors.toList(), List::size)
        //           Print the result
        Integer collectToint = names.stream().sorted()
                .collect(Collectors.collectingAndThen(Collectors.toList(), List::size));
        System.out.println("collectToint = " + collectToint);

        // TODO: 7 - Use Collectors.teeing() (Java 12+) to compute both the sum
        //           and count of 'numbers' simultaneously, then calculate the average
        //           teeing(Collectors.summingInt(...), Collectors.counting(), (sum, count) -> ...)
        //           Print the calculated average
        double result = numbers.stream()
                .collect(
                        Collectors.teeing(
                                Collectors.summingInt(Integer::intValue),
                                Collectors.counting(),
                                (sum, count) -> (double) sum / count
                        ));

        System.out.println("result = " + result);

    }
}
