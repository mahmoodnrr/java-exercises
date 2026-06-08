package com.amigoscode._8_testing._9_mocking;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Exercise: Mockito Argument Matchers
 * <p>
 * Practice using Mockito's argument matchers for flexible stubbing and verification.
 * Matchers let you specify patterns for arguments rather than exact values.
 */
@DisplayName("Mockito Argument Matchers Tests")
@ExtendWith(MockitoExtension.class)
class MatchersTest {

    @Mock
    private PaymentService paymentService;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    // TODO: 1 - Use any() matcher with when().thenReturn().
    //  Stub paymentService.charge to return true for ANY String and ANY double:
    //  when(paymentService.charge(any(), anyDouble())).thenReturn(true);
    //  Place an order and assert it completes successfully.

    @Test
    void canUseAnyMatcher() {

        when(paymentService.charge(any(), anyDouble())).thenReturn(true);

        Order order = orderService.placeOrder(new Order("123", "cust99", 90.00));

        assertThat(order.getStatus()).isEqualTo("COMPLETED");
    }

    // TODO: 2 - Use anyString() matcher.
    //  Stub paymentService.charge to return true when called with
    //  anyString() for customer ID and eq(99.99) for amount.
    //  Place an order with amount 99.99 and verify it works.
    //  Place another order with a different customer ID but same amount and verify.
    @Test
    void canUseAnyStringMatcher() {

        when(paymentService.charge(anyString(), eq(99.99))).thenReturn(true);

        Order order = orderService.placeOrder(new Order("123", "cust99", 99.99));
        assertEquals("COMPLETED", orderService.placeOrder(order).getStatus());

        assertThat(order.getStatus()).isEqualTo("COMPLETED");
        Order order2 = orderService.placeOrder(new Order("ORD-2", "CUST-2", 99.99));

        assertThat(order2.getStatus()).isEqualTo("COMPLETED");
        verify(paymentService).charge(anyString(), eq(99.99));
    }

    // TODO: 3 - Use eq() for exact match combined with other matchers.
    //  Stub paymentService.charge to return true for eq("VIP-CUSTOMER") and anyDouble().
    //  Create an order with customerId "VIP-CUSTOMER" and place it.
    //  Assert the order status is "COMPLETED".
    //  Important: When using any matcher in a call, ALL arguments must use matchers.
    @Test
    void canUseExactStringMatcher() {

        when(paymentService.charge(eq("VIP-CUSTOMER"), anyDouble())).thenReturn(true);

        Order order = orderService.placeOrder(new Order("123", "VIP-CUSTOMER", 99.99));

        assertThat(order.getStatus()).isEqualTo("COMPLETED");
    }

    // TODO: 4 - Use argThat() with a custom matcher.
    //  Stub paymentService.charge to return true when the amount is greater than 0:
    //  when(paymentService.charge(anyString(), argThat(amount -> amount > 0)))
    //      .thenReturn(true);
    //  Place an order with a positive amount and verify success.
    @Test
    void canUseArgThatMatcher() {

        when(paymentService.charge(eq("VIP-CUSTOMER"), argThat(amount -> amount > 0))).thenReturn(true);

        Order order = orderService.placeOrder(new Order("123", "VIP-CUSTOMER", 99.99));

        assertThat(order.getStatus()).isEqualTo("COMPLETED");
    }

    // TODO: 5 - Combine matchers in verify().
    //  Place an order, then verify the interactions using matchers:
    //  verify(paymentService).charge(eq("CUST-1"), argThat(amount -> amount > 50));
    //  verify(orderRepository).save(argThat(order ->
    //      order.getStatus().equals("COMPLETED")));
    @Test
    void canUseArgThat_And_VerifyMatchers() {

        when(paymentService.charge(eq("VIP-CUSTOMER"), argThat(amount -> amount > 50))).thenReturn(true);

        Order order = orderService.placeOrder(new Order("123", "VIP-CUSTOMER", 99.99));

        assertThat(order.getStatus()).isEqualTo("COMPLETED");

        verify(paymentService).charge(anyString(), argThat(amount -> amount > 50));
        verify(orderRepository).save(argThat(o -> o.getStatus().equals("COMPLETED")));
    }

    // TODO: 6 - Use argument matchers with when() for different return values.
    //  Stub charge to return true for amounts <= 1000 and false for amounts > 1000:
    //  when(paymentService.charge(anyString(), doubleThat(a -> a <= 1000)))
    //      .thenReturn(true);
    //  when(paymentService.charge(anyString(), doubleThat(a -> a > 1000)))
    //      .thenReturn(false);
    //  Test placing an order with amount 500 (should succeed).
    //  Test placing an order with amount 1500 (should throw RuntimeException).
    @Test
    void canUseWhenMatchers() {

        when(paymentService.charge(eq("VIP-CUSTOMER"), doubleThat(a -> a <= 1000))).thenReturn(true);
        when(paymentService.charge(eq("VIP-CUSTOMER2"), doubleThat(a -> a > 1000))).thenReturn(false);

        Order order = new Order("123", "VIP-CUSTOMER", 1000);
        Order order2 = new Order("1234", "VIP-CUSTOMER2", 1500);

        assertThat(orderService.placeOrder(order).getStatus()).isEqualTo("COMPLETED");
        assertThatThrownBy(() -> orderService.placeOrder(order2)).isInstanceOf(RuntimeException.class);

        verify(paymentService).charge(anyString(), doubleThat(amount -> amount <= 1000));
        verify(paymentService).charge(anyString(), doubleThat(amount -> amount <= 1000));
        verify(orderRepository).save(argThat(o -> o.getStatus().equals("COMPLETED")));
    }
}
