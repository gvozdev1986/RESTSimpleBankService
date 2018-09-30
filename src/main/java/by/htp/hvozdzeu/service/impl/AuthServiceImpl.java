package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.AuthDAOImpl;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The service for mapping request from client
 */
@Path("/auth")
public class AuthServiceImpl {

    private AuthDAOImpl authDAO = DAOFactory.getAuthDAOImpl();

    /**
     * The method for mapping GET query for getting tokenRest
     *
     * @param creditCardNumber String card number
     * @param cvCode           String code for verification credit card
     * @param firstName        String first name owner credit card
     * @param lastName         String last name owner credit card
     * @param monthValid       String month valid credit card in format (xx)
     * @param yearValid        String year valid credit card in format (xx)
     * @return String token for next query (request)
     * @throws DAOException Exception
     */
    @GET
    @Produces({MediaType.APPLICATION_XML})
    public String tokenAccess(@HeaderParam("creditCardNumber") String creditCardNumber,
                              @HeaderParam("cvCode") String cvCode,
                              @HeaderParam("firstName") String firstName,
                              @HeaderParam("lastName") String lastName,
                              @HeaderParam("monthValid") String monthValid,
                              @HeaderParam("yearValid") String yearValid) throws DAOException {
        return authDAO.getToken(creditCardNumber, cvCode, firstName, lastName, monthValid, yearValid);
    }

}
