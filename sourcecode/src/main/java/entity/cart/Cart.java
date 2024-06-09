package entity.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.exception.MediaNotAvailableException;
import entity.media.Media;

public class Cart {

    private List<CartItem> lstCartItem;

    // Design Pattern: Singleton - Chỉ cần 1 giỏ hàng trong khi chương trình chạy và
    // cần quản lí sự thống nhất của giở hàng trong toàn bộ chương trình
    private static Cart instance;

    public static Cart getInstance() {
        if (instance == null)
            instance = new Cart();
        return instance;
    }

    private Cart() {
        lstCartItem = new ArrayList<>();
    }

    public void addCartMedia(CartItem cm) {
        lstCartItem.add(cm);
    }

    public void removeCartMedia(CartItem cm) {
        lstCartItem.remove(cm);
    }

    public List getListMedia() {
        return lstCartItem;
    }

    public void emptyCart() {
        lstCartItem.clear();
    }

    public int getTotalMedia() {
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getQuantity();
        }
        return total;
    }

    public int calSubtotal() {
        int total = 0;
        for (Object obj : lstCartItem) {
            CartItem cm = (CartItem) obj;
            total += cm.getPrice() * cm.getQuantity();
        }
        return total;
    }

    public void checkAvailabilityOfProduct() throws SQLException {
        boolean allAvailable = true;
        for (Object object : lstCartItem) {
            CartItem cartItem = (CartItem) object;
            int requiredQuantity = cartItem.getQuantity();
            int availQuantity = cartItem.getMedia().getQuantity();
            if (requiredQuantity > availQuantity)
                allAvailable = false;
        }
        if (!allAvailable)
            throw new MediaNotAvailableException("Some media not available");
    }

    // Stamp coupling: Truyền vào Cart, nhưng chỉ dụng Id của Media để so sánh
    public CartItem checkMediaInCart(int mediaId) {
        for (CartItem cartItem : lstCartItem) {
            if (cartItem.getMedia().getId() == mediaId)
                return cartItem;
        }
        return null;
    }

}
