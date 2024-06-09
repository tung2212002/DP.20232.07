package helper;

import common.exception.InvalidCardException;

import java.util.Calendar;

public class CardValidator {

    /**
     * Validate the input date which should be in the format "mm/yy", and then
     * return a {@link String String} representing the date in the
     * required format "mmyy" .
     *
     * @param date - the {@link String String} represents the input date
     * @return {@link String String} - date representation of the required
     *         format
     * @throws InvalidCardException - if the string does not represent a valid date
     *                              in the expected format
     */
    public static String getExpirationDate(String date) throws InvalidCardException {
        String[] dateParts  = date.split("/");
        if (dateParts .length != 2) {
            throw new InvalidCardException();
        }

        String expirationDate = null;

        try {
            int month = Integer.parseInt(dateParts [0]);
            int year = Integer.parseInt(dateParts [1]);
            if(isInvalidMonth(month) || isInvalidYear(year)) {
                throw new InvalidCardException();
            }
            expirationDate = dateParts [0] + dateParts [1];

        } catch (Exception ex) {
            throw new InvalidCardException();
        }

        return expirationDate;
    }

    private static boolean isInvalidMonth(int month) {
        return month < 1 || month > 12;
    }

    private static boolean isInvalidYear(int year) {
        return year < Calendar.getInstance().get(Calendar.YEAR) % 100 || year > 100;
    }
}
