package com.amigoscode._4_datastructures._7_maps;

// Exercise: Advanced Map Operations (Java 8+)
// Learn modern Map methods: getOrDefault, putIfAbsent, computeIfAbsent, merge, replaceAll, and immutable maps.

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class MapAdvanced {

    public static void main(String[] args) {

        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 90);
        scores.put("Bob", 85);
        scores.put("Charlie", 78);
        System.out.println("Initial map: " + scores);

        // TODO: 1 - Use getOrDefault() to get the score for "Diana" (who is not in the map)
        //           Use a default value of 0
        //           Also get the score for "Alice" using getOrDefault with default 0
        //           Print both results
        System.out.println("Diana is in map? " + scores.getOrDefault("Diana", 0));


        // TODO: 2 - Use putIfAbsent() to try adding "Bob" with value 100
        //           Then use putIfAbsent() to add "Diana" with value 92
        //           Print the map - Bob's value should remain 85, Diana should be added
        System.out.println("Is Diana present? " + scores.putIfAbsent("Diana", 92));
        System.out.println("Is Bob present? " + scores.putIfAbsent("Bob", 100));

        // TODO: 3 - Use computeIfAbsent() to add "Eve" with a computed value
        //           The computation: the key's length * 10 (so "Eve" -> 3 * 10 = 30)
        //           Print the map after the operation

        System.out.println("Compute if absent " + scores.computeIfAbsent("Eve", key -> key.length() * 10));

        // TODO: 4 - Use merge() to add 5 bonus points to "Charlie"'s score
        //           merge("Charlie", 5, Integer::sum) adds 5 to the existing value
        //           Also merge "Frank" with value 88 (new entry since key doesn't exist)
        //           Print the map

        scores.merge("Charlie", 5, Integer::sum);
        scores.merge("Frank", 88, Integer::sum);
        System.out.println(scores);

        // TODO: 5 - Use replaceAll() to add 10 points to every student's score
        //           replaceAll takes a BiFunction (key, value) -> newValue
        //           Print the map after the operation

        BiFunction<String, Integer, Integer> biFunc = (a, b) -> b + 10;
//        for (Map.Entry<String, Integer> score : scores.entrySet()) {
            scores.replaceAll(biFunc);
        System.out.println(scores);
//        }


        // TODO: 6 - Create an immutable map using Map.of() with 3 entries:
        //           "Math" -> 95, "Science" -> 88, "English" -> 92
        //           Print the immutable map
        //           Try adding to it (will throw UnsupportedOperationException) - wrap in try-catch

       Map<String, Integer> myMap =  Map.of(
                "Math", 95,
                "Science", 88,
                "English", 92
        );

        System.out.println(myMap);

        try {
            myMap.put("test", 10);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
