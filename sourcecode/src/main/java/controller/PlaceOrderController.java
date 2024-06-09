package controller;

import common.exception.InvalidDeliveryInfoException;
import entity.cart.Cart;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.shipping.DeliveryInfo;
import entity.shipping.OldShippingFeeCalculator;
import helper.DeliveryInfoValidator;
import org.example.DistanceCalculator;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class controls the flow of place order usecase in our AIMS project
 *
 * @author nguyenlm
 */

// Temporal cohesion: validateDeliveryInfo(), validatePhoneNumber(),
// validateName(), validateAddress() chỉ thực hiện theo thứ tự thời gian bởi các
// method của lớp, nên tách riêng ra thành các class riêng

public class PlaceOrderController extends BaseController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the availability of product when user click PlaceOrder
     * button
     *
     * @throws SQLException
     */

    // Common coupling: placeOrder() sử dụng global data SessionInformation là
    // cartInstance
    public void placeOrder() throws SQLException {
        Cart.getInstance().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     *
     * @return Order
     * @throws SQLException
     */

    // Common coupling: createOrder() sử dụng global data SessionInformation là
    // cartInstance
    public Order createOrder() throws SQLException {
        return new Order(Cart.getInstance());
    }

    /**
     * This method creates the new Invoice based on order
     *
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     *
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public DeliveryInfo processDeliveryInfo(HashMap info)
            throws InterruptedException, IOException, InvalidDeliveryInfoException {
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        DeliveryInfoValidator.validateDeliveryInfo(info);
        DeliveryInfo deliveryInfo = new DeliveryInfo(
                String.valueOf(info.get("name")),
                String.valueOf(info.get("phone")),
                String.valueOf(info.get("province")),
                String.valueOf(info.get("address")),
                String.valueOf(info.get("instructions")),
                new DistanceCalculator());
        deliveryInfo.setStrategyShippingFeeCalculator(new OldShippingFeeCalculator());
        System.out.println(deliveryInfo.getProvince());
        return deliveryInfo;
    }

}
