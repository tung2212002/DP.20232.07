package entity.shipping;

import common.interfaces.StrategyShippingFeeCalculator;
import org.example.DistanceCalculator;

import entity.order.Order;

/*
 * SOLID - Single responsibility principle: DeliveryInfo đang thực hiện nhiều hơn 1 nhiệm vụ đó là cung cấp thông tin giao hàng và tính phí ship
 * Có xem xét tách phần tính phí ship ra thành class khác
 */

/*
 * SOLID - Open/closed principle: DeliveryInfo khó mở rộng nếu trong tương lai nếu có thêm cách thức tính phí mới hoặc sử dụng thư viện tính toán khác có thể xem xét tạo interface cho DistanceCalculator
 * với method calculateDistance() để các class tính phí ship khác có thể implement
 */

/*
 * SOLID - Dependency inversion principle: DeliveryInfo phụ thuộc vào DistanceCalculator ảnh hưởng đến việc mở rộng nếu có thêm cách tính phí ship hay thư viện tính toán khác
 */

// Communicational cohesion: calculateShippingFee() thực hiện các thao tác liên quan đến việc tính phí ship dựa trên order có dữ liệu liên quan đến DeliveryInfo

public class DeliveryInfo {

    private String name;
    private String phone;
    private String province;
    private String address;
    private String shippingInstructions;
    private DistanceCalculator distanceCalculator;
    private StrategyShippingFeeCalculator strategyShippingFeeCalculator;

    public DeliveryInfo(String name, String phone, String province, String address, String shippingInstructions,
                        DistanceCalculator distanceCalculator) {
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.address = address;
        this.shippingInstructions = shippingInstructions;
        this.distanceCalculator = distanceCalculator;
    }

    public void setStrategyShippingFeeCalculator(StrategyShippingFeeCalculator strategyShippingFeeCalculator) {
        this.strategyShippingFeeCalculator = strategyShippingFeeCalculator;
    }

    // Stamp coupling: Truyền vào order nhưng không dùng đến
    public int calculateShippingFee(Order order) {
        int distance = distanceCalculator.calculateDistance(address, province);
        return strategyShippingFeeCalculator.calculateShippingFee(order, distance);

    }

    //Sử dụng strategy pattern tại điểm này để tính phí ship, giúp linh hoạt thay đổi các phương thức tính phí ship
    //Nếu sử dụng một phương thức tính phí khác, tạo một class implement IDistanceCalculatorStrategy và thay đổi
    // bổ sung UI button dẫn tới trực tiếp gọi setStrategy() để thay đổi phương thức tính phí
    //với tham số là một instance của phương thức tính phí mới
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getProvince() {
        return province;
    }

    public String getAddress() {
        return address;
    }

    public String getShippingInstructions() {
        return shippingInstructions;
    }
}
