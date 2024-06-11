package controller;

import java.sql.SQLException;

import entity.cart.Cart;

/**
 * This class controls the flow of events when users view the Cart
 *
 * @author nguyenlm
 */
public class ViewCartController extends BaseController {

    /**
     * This method checks the available products in Cart
     *
     * @throws SQLException
     */

    // Common coupling: checkAvailabilityOfProduct() sử dụng global data
    // SessionInformation là cartInstance
    public void checkAvailabilityOfProduct() throws SQLException {
        Cart.getInstance().checkAvailabilityOfProduct();
    }

    /**
     * This method calculates the cart subtotal
     *
     * @return subtotal
     */
    public int getCartSubtotal() {
        return Cart.getInstance().calSubtotal();
    }

}
