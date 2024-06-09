package entity.shipping;

import common.interfaces.StrategyShippingFeeCalculator;
import entity.order.Order;

public class NewShippingFeeCalculator implements StrategyShippingFeeCalculator {

    public final double SHIPPING_FEE = 2.0;

    @Override
    public int calculateShippingFee(Order order, int distance) {
        // Implement new calculation method with length, width, height,... of order
        return 0;
    }
}
