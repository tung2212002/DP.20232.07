package entity.shipping;

import common.interfaces.StrategyShippingFeeCalculator;
import entity.order.Order;

public class OldShippingFeeCalculator implements StrategyShippingFeeCalculator {

    public final double SHIPPING_FEE = 1.2;

    @Override
    public int calculateShippingFee(Order order, int distance) {
        return (int) (SHIPPING_FEE * distance);
    }
}
