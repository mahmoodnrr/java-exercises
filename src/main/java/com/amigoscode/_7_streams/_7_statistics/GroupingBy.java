package com.amigoscode._7_streams._7_statistics;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Exercise: GroupingBy
 * <p>
 * Learn how to use Collectors.groupingBy() to group stream elements
 * into a Map. Practice with downstream collectors for counting, summing,
 * and multi-level grouping.
 */
public class GroupingBy {

    record Person(String name, int age, String city) {
    }

    record Order(String product, double amount, String status) {
    }

    public static void main(String[] args) {
        List<Person> people = List.of(
                new Person("Alice", 30, "London"),
                new Person("Bob", 25, "Paris"),
                new Person("Charlie", 35, "London"),
                new Person("Diana", 22, "Berlin"),
                new Person("Eve", 28, "Paris"),
                new Person("Frank", 45, "Berlin"),
                new Person("Grace", 19, "London"),
                new Person("Henry", 33, "Paris")
        );

        List<Order> orders = List.of(
                new Order("Laptop", 999.99, "COMPLETED"),
                new Order("Mouse", 29.99, "PENDING"),
                new Order("Keyboard", 79.99, "COMPLETED"),
                new Order("Monitor", 449.99, "SHIPPED"),
                new Order("Headset", 59.99, "PENDING"),
                new Order("Webcam", 39.99, "COMPLETED"),
                new Order("Cable", 9.99, "SHIPPED")
        );

        List<String> words = List.of(
                "apple", "banana", "avocado", "blueberry", "cherry",
                "apricot", "blackberry", "coconut", "cranberry", "almond"
        );

        // TODO: 1 - Group 'people' by city
        //           Result type: Map<String, List<Person>>
        //           Print each city and its list of people
        Map<String, List<Person>> pCity = people.stream()
                .collect(
                        Collectors.groupingBy(Person::city)
                );
        System.out.println("cities = " + pCity);

        // TODO: 2 - Group 'orders' by status
        //           Print each status and its list of orders
        Map<String, List<Order>> orderStatus = orders.stream()
                .collect(
                        Collectors.groupingBy(Order::status)
                );
        System.out.println("orderStatus = " + orderStatus);

        // TODO: 3 - Group 'words' by their first letter
        //           Hint: Use word.charAt(0) or word.substring(0, 1)
        //           Print each letter and its words
        Map<Character, List<String>> firstLetter = words.stream()
                .collect(
                        Collectors.groupingBy(w -> w.charAt(0))
                );
        System.out.println("firstLetter = " + firstLetter);

        // TODO: 4 - Group 'orders' by status and count how many orders per status
        //           Use Collectors.groupingBy with Collectors.counting() as downstream
        //           Print each status and its count
        Map<String, Long> orderCount = orders.stream()
                .collect(
                        Collectors.groupingBy(
                                Order::status, Collectors.counting()
                        ));
        System.out.println("orderCount = " + orderCount);

        // TODO: 5 - Group 'orders' by status and sum the amounts per status
        //           Use Collectors.groupingBy with Collectors.summingDouble as downstream
        //           Print each status and its total amount
        Map<String, Double> orderStatusSum = orders.stream().collect(
                Collectors.groupingBy(
                        Order::status, Collectors.summingDouble(Order::amount)
                ));
        System.out.println("orderStatusSum = " + orderStatusSum);

        // TODO: 6 - Group 'people' by city and collect names to a Set
        //           Use Collectors.groupingBy with Collectors.mapping + Collectors.toSet()
        //           Print each city and its set of names
        Map<String, Set<String>> groupByCity = people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::city, Collectors.mapping(
                                        Person::name, Collectors.toSet()
                                )));
        System.out.println("groupByCity = " + groupByCity);

        // TODO: 7 - Multi-level grouping: group 'people' by city, then by age range
        //           Age ranges: "Young" (< 25), "Adult" (25-35), "Senior" (> 35)
        //           Use nested groupingBy
        //           Hint: Create a helper method getAgeRange(int age)
        //           Print the result
        Map<String, Map<String, List<Person>>> groupByCity_Age = people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::city, Collectors.groupingBy(
                                        p -> getAgeRange(p.age())
                                )));
        System.out.println("groupByCity_Age = " + groupByCity_Age);

    }

    // Helper method for TODO 7
    private static String getAgeRange(int age) {
        if (age < 25) return "Young";
        if (age <= 35) return "Adult";
        return "Senior";
    }
}
