package adapter.distance;

import org.example.DistanceCalculator;

public class DCAdapter extends DistanceCalculator {
    private NewDistanceCalculator newDistanceCalculator;

    public DCAdapter() {
        newDistanceCalculator = new NewDistanceCalculator();
    }
    @Override
    public int calculateDistance(String address, String province) {
        return newDistanceCalculator.newCalculateDistance(address, province);
    }

}