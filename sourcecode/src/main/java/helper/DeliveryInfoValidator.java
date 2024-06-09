package helper;

import common.exception.InvalidDeliveryInfoException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeliveryInfoValidator {
    private static final int PHONE_NUMBER_LENGTH = 10;
    private static final String PHONE_NUMBER_PREFIX = "0";
    private static final String NAME_PATTERN = "^[a-zA-Z\\s]*$";
    private static final String ADDRESS_PATTERN = "^[a-zA-Z0-9\\s,]*$";
    /**
     * The method validates the info
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public static void validateDeliveryInfo(HashMap<String, String> info) throws InvalidDeliveryInfoException {
        if (validatePhoneNumber(info.get("phone"))
                || validateName(info.get("name"))
                || validateAddress(info.get("address"))) return;
        else throw new InvalidDeliveryInfoException();
    }

    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != PHONE_NUMBER_LENGTH) return false;
        if (!phoneNumber.startsWith(PHONE_NUMBER_PREFIX )) return false;
        try {
            Integer.parseInt(phoneNumber);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean validateName(String name) {
        if (Objects.isNull(name)) return false;
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean validateAddress(String address) {
        if (Objects.isNull(address)) return false;
        Pattern pattern = Pattern.compile(ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }
}
