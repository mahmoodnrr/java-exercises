package com.amigoscode._1_beginners._1_thebasics;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Mini Project: Simple Calculator
 *
 * Put together everything you have learned so far to build a simple calculator.
 * The calculator should display a menu, read the user's choice and two numbers,
 * perform the chosen operation, and print the result.
 */
public class MiniProject {

    public static void main(String[] args) {

        // TODO: 1 - Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);


        // TODO: 2 - Display a menu with the following options:
        // Print:
        //   "=== Simple Calculator ==="
        //   "1. Add"
        //   "2. Subtract"
        //   "3. Multiply"
        //   "4. Divide"
        //   "Choose an operation (1-4): "
        System.out.println("=== Simple Calculator ===");
        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("Choose an operation (1-4): ");


        // TODO: 3 - Read the user's choice into an int variable

        try {
            int input = scanner.nextInt();

            // TODO: 4 - Prompt and read two double numbers from the user
            // Print "Enter first number: " and read it.
            // Print "Enter second number: " and read it.

            System.out.println("Enter first number:");
            double firstNum = scanner.nextDouble();

            System.out.println("Enter second number:");
            double secondNum = scanner.nextDouble();


            switch(input){
                case 1:
                    System.out.println("Result " + (firstNum + secondNum));
                    break;
                case 2:
                    System.out.println("Result " + (firstNum - secondNum));
                    break;
                case 3:
                    System.out.println("Result " + (firstNum * secondNum));
                    break;
                case 4:
                    if(secondNum == 0){
                        System.out.println("Error: cannot divide by zero");
                    }
                    else {
                        System.out.println("Result " + (firstNum + secondNum));
                    }
                    break;
                default :
                    System.out.println("Invalid choice");
            }

        // TODO: 5 - Use a switch statement to perform the chosen operation and print the result
        // Case 1: Print "Result: " + (num1 + num2)
        // Case 2: Print "Result: " + (num1 - num2)
        // Case 3: Print "Result: " + (num1 * num2)
        // Case 4: Perform division (but handle division by zero first - see TODO 6)
        // Default: Print "Invalid choice"


        // TODO: 6 - Inside case 4, handle division by zero with an if statement
        // If the second number is 0, print "Error: Cannot divide by zero"
        // Otherwise, print the result of the division

        } catch (InputMismatchException e) {
            System.out.println("Please type a number");
        }

        scanner.close();
    }
}
