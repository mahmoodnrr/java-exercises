package com.amigoscode._7_streams._7_statistics;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

/**
 * Exercise: Average and Sum
 *
 * Learn to calculate averages and sums using both stream primitive
 * specializations (mapToInt, mapToDouble) and Collectors.
 */
public class AverageAndSum {

    record Student(String name, int score) {}

    record Product(String name, double price, int quantity) {}

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Alice", 92),
                new Student("Bob", 85),
                new Student("Charlie", 78),
                new Student("Diana", 95),
                new Student("Eve", 88)
        );

        List<Product> products = List.of(
                new Product("Widget A", 10.0, 5),
                new Product("Widget B", 20.0, 3),
                new Product("Widget C", 15.0, 8),
                new Product("Widget D", 25.0, 2)
        );

        List<Student> emptyStudents = List.of();

        // TODO: 1 - Calculate the sum of all student scores
        //           Use mapToInt(Student::score).sum()
        //           Print the result
        int sum = students.stream().mapToInt(Student::score).sum();
        System.out.println("sum = " + sum);


        // TODO: 2 - Calculate the average of all student scores
        //           Use mapToDouble(Student::score).average()
        //           Print the result (remember: average() returns OptionalDouble)
        OptionalDouble average = students.stream().mapToDouble(Student::score).average();
        System.out.println("average = " + average.orElse(-1));

        // TODO: 3 - Use Collectors.summingInt() to sum all student scores
        //           Print the result
        Integer collect = students.stream().collect(Collectors.summingInt(Student::score));
        System.out.println("collect = " + collect);

        // TODO: 4 - Use Collectors.averagingDouble() to average all product prices
        //           Print the result
        Double collect1 = products.stream().collect(Collectors.averagingDouble(Product::price));
        System.out.println("collect1 = " + collect1);

        // TODO: 5 - Calculate the weighted average of products:
        //           weighted avg = sum(price * quantity) / sum(quantity)
        //           Hint: Use mapToDouble for the numerator and mapToInt for denominator
        //           Print the result
        double numerator = products.stream()
                .mapToDouble(p -> p.price * p.quantity).sum();
        int denominator = products.stream()
                .mapToInt(p -> p.quantity).sum();
        System.out.println("result " + numerator / denominator);


        // TODO: 6 - Handle an empty stream: calculate the average of 'emptyStudents' scores
        //           Use orElse(0.0) to provide a default value when no elements exist
        //           Print the result
        double avg = emptyStudents.stream().mapToDouble(Student::score).average().orElse(1.1);
        System.out.println("avg = " + avg);

    }
}
