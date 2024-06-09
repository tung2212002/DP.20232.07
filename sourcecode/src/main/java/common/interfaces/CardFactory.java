package common.interfaces;

import common.exception.PaymentException;
import common.exception.UnrecognizedException;
import dto.payment.CardDTO;
import entity.payment.Card;


public interface CardFactory<T extends CardDTO> {
    Card create(T cardInfo) throws PaymentException, UnrecognizedException;

}
