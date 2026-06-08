package com.amigoscode._8_testing._9_mocking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exercise: Your First Mocks
 * <p>
 * Learn the basics of Mockito: creating mocks, stubbing methods,
 * verifying interactions, and using annotations.
 */
@DisplayName("First Mock Tests")
// TODO: 1 - Add @ExtendWith(MockitoExtension.class) to this class.
//  This annotation integrates Mockito with JUnit 5 and enables
//  the @Mock and @InjectMocks annotations below.
@ExtendWith(MockitoExtension.class)
class FirstMockTest {

    // TODO: 2 - Create mocks using @Mock annotation.
    //  Declare a PaymentService field annotated with @Mock.
    //  Declare an OrderRepository field annotated with @Mock.
    //  These will be automatically initialized by MockitoExtension.
    @Mock
    private PaymentService paymentService;

    @Mock
    private OrderRepository orderRepository;

    // TODO: 3 - Use @InjectMocks for the class under test.
    //  Declare an OrderService field annotated with @InjectMocks.
    //  Mockito will automatically inject the @Mock fields into OrderService's constructor.
    @InjectMocks
    private OrderService orderService;

    // TODO: 4 - Set up a stub with when().thenReturn() and test placeOrder.
    //  Create an Order: new Order("ORD-1", "CUST-1", 99.99)
    //  Stub the payment: when(paymentService.charge("CUST-1", 99.99)).thenReturn(true);
    //  Call orderService.placeOrder(order).
    //  Assert that the returned order's status is "COMPLETED".

    @Test
    void canOrder() {
        Order order = new Order("ORD-1", "CUST-1", 99.99);
        when(paymentService.charge("CUST-1", 99.99)).thenReturn(true);
        orderService.placeOrder(order);

        // TODO: 5 - Verify that methods were called with verify().
        //  After calling placeOrder (from TODO 4), verify interactions:
        //  verify(paymentService).charge("CUST-1", 99.99);
        //  verify(orderRepository).save(order);
        //  This confirms that the service called charge() and save() with correct arguments.
        verify(paymentService).charge("CUST-1", 99.99);
        verify(orderRepository).save(order);
        assertThat(order.getStatus()).isEqualTo("COMPLETED");
    }


    // TODO: 6 - Verify method was called with specific arguments.
    //  Create a new test method.
    //  Stub paymentService.charge to return true for any arguments.
    //  Place an order with amount 49.99.
    //  Verify that paymentService.charge was called with exactly "CUST-1" and 49.99.
    //  Verify that orderRepository.save was called exactly once.
    //  Hint: verify(paymentService).charge(eq("CUST-1"), eq(49.99));

    @Test
    void canVerifySpecificArguments() {
        Order order = new Order("ORD-1", "CUST-1", 49.99);
        when(paymentService.charge(anyString(), eq(49.99))).thenReturn(true);
        orderService.placeOrder(order);

        verify(paymentService).charge(eq("CUST-1"), eq(49.99));
        verify(orderRepository, times(1)).save(order);
    }
}
