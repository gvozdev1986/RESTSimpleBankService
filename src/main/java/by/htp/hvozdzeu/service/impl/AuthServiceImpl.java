package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.AuthDAOImpl;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/auth")
public class AuthServiceImpl {

    private AuthDAOImpl authDAO = DAOFactory.getAuthDAOImpl();

    @GET
    @Produces({ MediaType.APPLICATION_XML})
    public String tokenAccess(@HeaderParam("creditCardNumber") String creditCardNumber,
                              @HeaderParam("cvCode") String cvCode,
                              @HeaderParam("firstName") String firstName,
                              @HeaderParam("lastName") String lastName,
                              @HeaderParam("monthValid") String monthValid,
                              @HeaderParam("yearValid") String yearValid) throws DAOException {
        return authDAO.getToken(creditCardNumber, cvCode, firstName, lastName, monthValid, yearValid);
    }

}
