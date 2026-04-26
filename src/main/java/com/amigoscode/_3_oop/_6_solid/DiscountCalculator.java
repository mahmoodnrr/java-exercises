package com.amigoscode._3_oop._6_solid;

public class DiscountCalculator {

    double calculate(Discount discount, double price) {
        return price - discount.apply(price);
    }

}
