package StragetyPayment;

import entity.order.Order;

public interface IDistanceCalculatorStrategy {
    int calculateFee(Order order);
}

