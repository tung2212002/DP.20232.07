package StragetyPayment;

public class DistanceCalculatorContext {
    private IDistanceCalculatorStrategy strategy;

    public DistanceCalculatorContext(IDistanceCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(IDistanceCalculatorStrategy strategy) {
        this.strategy = strategy;
    }

    public int calculateDistance(String address, String province) {
        return strategy.calculateDistance(address, province);
    }
}
