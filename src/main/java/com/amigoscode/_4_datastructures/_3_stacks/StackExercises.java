package com.amigoscode._4_datastructures._3_stacks;

// Exercise: Stack Operations
// Learn how to use the Stack data structure - Last In, First Out (LIFO).

import java.util.Stack;

public class StackExercises {

    public static void main(String[] args) {

        // TODO: 1 - Create a Stack of Strings called 'stack'
        Stack<String> stack = new Stack<>();

        // TODO: 2 - Push 5 elements onto the stack: "Java", "Python", "C++", "JavaScript", "Go"
        stack.push("Java");
        stack.push("Python");
        stack.push("C++");
        stack.push("JavaScript");
        stack.push("Go");

        // TODO: 3 - Peek at the top element without removing it
        //           Print the result (should be "Go")
        System.out.println(stack.peek());

        // TODO: 4 - Pop an element from the stack and print it
        //           Then print the stack to see the remaining elements
        System.out.println("before pop " + stack.pop());
        System.out.println("after pop " + stack.peek());

        // TODO: 5 - Check if the stack is empty using isEmpty()
        //           Print the result
        System.out.println("Is empty " + stack.isEmpty());

        // --- String Reversal ---
        System.out.println("\n--- String Reversal ---");
        String original = "Hello World";

        // TODO: 6 - Use a Stack to reverse the string 'original'
        //           Push each character onto a stack, then pop them all to build the reversed string
        //           Print both original and reversed strings
        Stack<Character> characters = new Stack<>();

        for (char letter : original.toCharArray()) {
            characters.add(letter);
        }

        System.out.println("original");

        for (char letter : characters) {
            System.out.print(letter);
        }

        System.out.println();
        System.out.println("reversed");

        for (char letter : characters.reversed()) {
            System.out.print(letter);
        }

        // --- Balanced Brackets ---
        System.out.println("\n--- Balanced Brackets ---");
//        String balanced = "({[()]})";
        String balanced = "(())";
//        String unbalanced = "({[})";
        String unbalanced = "({[]}";
        // TODO: 7 - Implement a balanced brackets checker
        //           Use a Stack to check if a string of brackets is balanced
        //           For each char: if opening bracket, push it; if closing bracket, check
        //           that the top of stack is the matching opening bracket
        //           Test with both 'balanced' and 'unbalanced' strings
        //           Print whether each string is balanced or not

        brackets(balanced);
        brackets(unbalanced);
    }

    private static void brackets(String str) {

        if (str.charAt(0) == '}' || str.charAt(0) == ')' || str.charAt(0) == ']') return;

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {

            if (c == '{' || c == '[' || c == '(') {
                stack.add(c);
                continue;
            }

            char top = stack.peek();

            if (c == '}' && top == '{') {
                stack.pop();
            } else if (c == ')' && top == '(') {
                stack.pop();
            } else if (c == ']' && top == '[') {
                stack.pop();
            } else {
                return;
            }
        }

        if(stack.isEmpty()) {
            System.out.println("Balanced");
        } else {
            System.out.println("unbalanced");
        }
    }
}
