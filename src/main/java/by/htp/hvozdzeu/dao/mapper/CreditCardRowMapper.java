package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.CreditCard;
import by.htp.hvozdzeu.model.report.BalanceAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.htp.hvozdzeu.util.Decoder.encrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;

/**
 * Class for build entity from ResultSet query use Builder pattern
 */
public class CreditCardRowMapper {

    private static final String CREDIT_CARD_ID = "Id";
    private static final String CREDIT_CARD_NUMBER = "CardNumber";
    private static final String CREDIT_CARD_CV_CODE = "CVCode";
    private static final String CREDIT_CARD_BALANCE = "BalanceBankAccount";
    private static final String CREDIT_CARD_FIRST_NAME = "FirstName";
    private static final String CREDIT_CARD_LAST_NAME = "LastName";
    private static final String CREDIT_CARD_MONTH_VALID = "MonthValid";
    private static final String CREDIT_CARD_YEAR_VALID = "YearValid";

    protected CreditCard buildCreditCardRowMapper(ResultSet resultSet) throws SQLException {
        return CreditCard.getBuilder()
                .id(resultSet.getLong(CREDIT_CARD_ID))
                .creditCardNumber(resultSet.getString(CREDIT_CARD_NUMBER))
                .cvCode(resultSet.getString(CREDIT_CARD_CV_CODE))
                .creditCardBalance(resultSet.getBigDecimal(CREDIT_CARD_BALANCE))
                .firstName(resultSet.getString(CREDIT_CARD_FIRST_NAME))
                .lastName(resultSet.getString(CREDIT_CARD_LAST_NAME))
                .monthValid(resultSet.getString(CREDIT_CARD_MONTH_VALID))
                .yearValid(resultSet.getString(CREDIT_CARD_YEAR_VALID))
                .build();
    }

}
