package by.htp.hvozdzeu.dao;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.response.Response;

/**
 * The interface for AccountDAOImpl
 */
public interface AccountDAO {

    /**
     * The method for getting tokenRest by parameters and
     * save him to DB.
     *
     * @param cardNumber    String card number
     * @param cvCode        String code for verification credit card
     * @param firstName     String first name owner credit card
     * @param lastName      String last name owner credit card
     * @param monthValid    String month valid credit card in format (xx)
     * @param yearValid     String year valid credit card in format (xx)
     * @param appSecretCode String special code for verify then request went from current client
     * @return String token for next query (request)
     * @throws DAOException Exception
     */
    Response getToken(String cardNumber,
                      String cvCode,
                      String firstName,
                      String lastName,
                      String monthValid,
                      String yearValid,
                      String appSecretCode) throws DAOException;

    /**
     * Method for find token for check before do methods
     *
     * @param restToken String tokenRest from request
     * @return Boolean (true/false) result query
     * @throws DAOException Exception
     */
    boolean findToken(String restToken) throws DAOException;

}
