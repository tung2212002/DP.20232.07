package common.interfaces;

import entity.order.Order;

public interface StrategyShippingFeeCalculator {
    public int calculateShippingFee(Order order, int distance);
}
