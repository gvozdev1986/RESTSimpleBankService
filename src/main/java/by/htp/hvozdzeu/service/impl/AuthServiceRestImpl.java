package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.AuthRestInterfaceImpl;
import by.htp.hvozdzeu.model.dto.CreditCardDto;
import by.htp.hvozdzeu.model.dto.ServerStatusDto;
import by.htp.hvozdzeu.model.response.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import static by.htp.hvozdzeu.util.Decoder.decrypt;
import static by.htp.hvozdzeu.util.DecoderProperties.getSecretKey;

/**
 * The service for mapping request from client
 */
@Path("/auth")
public class AuthServiceRestImpl {

    private AuthRestInterfaceImpl authDAO = DAOFactory.getAuthDAOImpl();

    /**
     * The method for mapping POST query for getting tokenRest
     *
     * @return String token for next query (request)
     * @throws DAOException Exception
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tokenAccess(CreditCardDto input) throws DAOException {
        return authDAO.getToken(
                decrypt(input.getCreditCardNumber(), getSecretKey()),
                decrypt(input.getCvCode(), getSecretKey()),
                decrypt(input.getFirstName(), getSecretKey()),
                decrypt(input.getLastName(), getSecretKey()),
                decrypt(input.getMonthValid(), getSecretKey()),
                decrypt(input.getYearValid(), getSecretKey()),
                input.getAppSecretCode()
        );
    }

    @POST
    @Path("/status/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus(ServerStatusDto input) {
        return authDAO.getStatusServer(input.getAppSecretCode());
    }

}
