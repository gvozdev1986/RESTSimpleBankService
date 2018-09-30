package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.report.BalanceAccount;

import java.math.BigDecimal;

public interface BalanceBankAccountDAO {

    BalanceAccount balanceBankAccount(String tokenRest, String cardNumber) throws DAOException;
    boolean writeOffBalanceBankAccount(String tokenRest, String cardNumber, BigDecimal amount) throws DAOException;
}
