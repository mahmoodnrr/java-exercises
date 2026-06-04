package com.amigoscode._8_testing._8_tdd;

/**
 * TDD Exercise: Shipping Cost Calculator
 * <p>
 * This class is intentionally EMPTY. Students should:
 * 1. First write tests in ShippingCostCalculatorTest.java (test-first approach)
 * 2. Then implement this class to make the tests pass
 * <p>
 * Shipping rules to implement:
 * - Domestic shipping: $5.00 base + $0.50 per kg
 * - International shipping: $15.00 base + $1.50 per kg
 * - Weight of 0 kg = free shipping ($0.00)
 * - Negative weight should throw IllegalArgumentException
 * - Null destination should throw IllegalArgumentException
 * - Heavy package surcharge: weight > 50 kg adds $25.00 extra
 */
public class ShippingCostCalculator {

    /**
     * Calculates the shipping cost based on weight and destination.
     *
     * @param weight      the weight of the package in kilograms
     * @param destination "domestic" or "international"
     * @return the total shipping cost
     * @throws IllegalArgumentException if weight is negative or destination is null
     */
    public double calculate(double weight, String destination) {
        // TODO: Students implement this after writing tests first (TDD approach)

        int surchage = weight > 50 ? 25 : 0;
        double cost = 0.0;

        if (weight < 0) throw new IllegalArgumentException("Weight cannot be negative number");

        if (destination == null) throw new IllegalArgumentException("Destination cannot be null");

        if (weight == 0)
            return cost;

        if (destination.equalsIgnoreCase("domestic"))
            cost = 5 + (weight * 0.50) + surchage;

        if (destination.equalsIgnoreCase("international"))
            cost = 15 + (weight * 1.50) + surchage;

        return cost;
    }
}
