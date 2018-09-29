package by.htp.hvozdzeu.dao.mapper;

import by.htp.hvozdzeu.model.report.BalanceAccount;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceAccountRowMapper {

    private static final String BALANCE_ACCOUNT_ID = "Id";
    private static final String BALANCE_ACCOUNT_CARD_NUMBER = "CardNumber";
    private static final String BALANCE_ACCOUNT_BALANCE = "BalanceBankAccount";

    protected BalanceAccount buildBalanceAccountRowMapper(ResultSet resultSet) throws SQLException {
        return BalanceAccount.getBuilder()
                .id(resultSet.getLong(BALANCE_ACCOUNT_ID))
                .cardNumber(resultSet.getString(BALANCE_ACCOUNT_CARD_NUMBER))
                .balanceBankAccount(resultSet.getBigDecimal(BALANCE_ACCOUNT_BALANCE))
                .build();
    }

}
