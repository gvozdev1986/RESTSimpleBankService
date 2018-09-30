package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;

public interface AccountDAO {

    String getToken(String cardNumber,
                    String cvCode,
                    String firstName,
                    String lastName,
                    String monthValid,
                    String yearValid) throws DAOException;

    boolean findToken(String restToken) throws DAOException;

}
