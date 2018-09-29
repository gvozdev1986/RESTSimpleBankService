package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.model.CreditCard;

import java.math.BigDecimal;
import java.util.*;

public class CreditCardDAOImpl {

    private static final Map<String, CreditCard> cardsMap = new HashMap<>();

    static {
        initCreditCards();
    }

    private static void initCreditCards() {
        CreditCard creditCard1 = new CreditCard("1111 1111 1111 1111", "ALIAKSANDR HVOZDZEU", new BigDecimal("25.25"));
        CreditCard creditCard2 = new CreditCard("2222 2222 2222 2222", "ALIAKSANDR HVOZDZEU", new BigDecimal("0.00"));
        CreditCard creditCard3 = new CreditCard("3333 3333 3333 3333", "ALIAKSANDR HVOZDZEU", new BigDecimal("785.05"));

        cardsMap.put(creditCard1.getCreditCardNo(), creditCard1);
        cardsMap.put(creditCard2.getCreditCardNo(), creditCard2);
        cardsMap.put(creditCard3.getCreditCardNo(), creditCard3);
    }

    public static CreditCard getCreditCard(String creditCardNo) {
        return cardsMap.get(creditCardNo);
    }

    public static CreditCard addCreditCard(CreditCard creditCard) {
        cardsMap.put(creditCard.getCreditCardNo(), creditCard);
        return creditCard;
    }

    public static CreditCard updateCreditCard(CreditCard creditCard) {
        cardsMap.put(creditCard.getCreditCardNo(), creditCard);
        return creditCard;
    }

    public static void deleteCreditCard(String creditCard) {
        cardsMap.remove(creditCard);
    }

    public static List<CreditCard> getAllCreditCard() {
        Collection<CreditCard> c = cardsMap.values();
        List<CreditCard> list = new ArrayList<>();
        list.addAll(c);
        return list;
    }

    List<CreditCard> list;

}
