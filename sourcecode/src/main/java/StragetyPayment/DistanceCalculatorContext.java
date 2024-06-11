package StragetyPayment;

import entity.order.Order;

public class DistanceCalculatorContext {
    private IDistanceCalculatorStrategy strategy;

    public DistanceCalculatorContext(IDistanceCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IDistanceCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateShippingFee(Order order) {
        return strategy.calculateFee(order);
    }
}
