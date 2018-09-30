package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.Account;

public interface AccountDAO {

    String getToken(Account account) throws DAOException;
    boolean findToken(String restToken) throws DAOException;

}
