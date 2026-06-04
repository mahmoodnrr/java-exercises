package com.amigoscode._8_testing._1_firsttest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise: Your First Unit Tests
 *
 * In this exercise you will write your first JUnit 5 tests for the Calculator class.
 * Complete each TODO to practice basic assertions and test lifecycle annotations.
 */
class CalculatorTest {

    // TODO: 1 - Declare a Calculator field and use @BeforeEach to create a fresh
    //  Calculator instance before each test. This ensures tests are independent.
    //  Hint: Write a setUp() method annotated with @BeforeEach that assigns
    //  a new Calculator() to your field.

    private Calculator calculatorTest;

    @BeforeEach
    void setUp() {
        calculatorTest = new Calculator();
    }


    // TODO: 2 - Write a test for the add method using assertEquals.
    //  Annotate it with @Test and @DisplayName("2 + 3 should equal 5").
    //  Call calculator.add(2, 3) and assert the result equals 5.
    @Test
    @DisplayName("2 + 3 should equal 5")
    void canAdd() {
        // when
        int actual = calculatorTest.add(2, 3);

        // then
        assertEquals(5, actual);
    }

    // TODO: 3 - Write a test for the subtract method.
    //  Annotate with @Test and @DisplayName.
    //  Test that calculator.subtract(10, 4) equals 6.

    @Test
    @DisplayName("10 - 4 should equal 6")
    void canSubtract() {
        // when
        int actual = calculatorTest.subtract(10, 4);

        // then
        assertEquals(6, actual);
    }


    // TODO: 4 - Write a test for the multiply method.
    //  Annotate with @Test and @DisplayName.
    //  Test that calculator.multiply(3, 7) equals 21.
    @Test
    @DisplayName("3 * 7 should equal 21")
    void canMultiply() {
        // when
        int actual = calculatorTest.multiply(3, 7);

        // then
        assertEquals(21, actual);
    }


    // TODO: 5 - Write a test that verifies dividing by zero throws ArithmeticException.
    //  Use assertThrows(ArithmeticException.class, () -> calculator.divide(10, 0)).
    //  Add a @DisplayName annotation.
    @Test
    @DisplayName("10 divide 0 should throw Arithmetic Exception")
    void cannotDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorTest.divide(10, 0));
    }

    // TODO: 6 - Write a test using assertNotEquals.
    //  Verify that calculator.add(2, 2) is NOT equal to 5.
    //  Add a @DisplayName annotation.
    @Test
    @DisplayName("2 + 2 should not equal 5")
    void cannotAdd() {
        assertNotEquals(5, calculatorTest.add(2, 2));
    }

    // TODO: 7 - Add @DisplayName annotations to ALL of your tests above (if not done already).
    //  Make the display names descriptive, e.g., "Adding 2 and 3 should return 5".
    //  Then write one more test: verify that calculator.divide(10, 2) equals 5.
    @Test
    @DisplayName("10 / 2 should equal 5")
    void canDivide() {
        assertEquals(5, calculatorTest.divide(10, 2));
    }

}
