package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.factory.DAOFactory;
import by.htp.hvozdzeu.dao.impl.AuthRestInterfaceImpl;
import by.htp.hvozdzeu.model.dto.ServerStatusDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * The service for mapping request from client
 */
@Path("/auth")
public class AuthServiceRestImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceRestImpl.class);

    private AuthRestInterfaceImpl authDAO = DAOFactory.getAuthDAOImpl();

    @POST
    @Path("/status/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus(ServerStatusDto input) {
        LOGGER.info("Getting status server.");
        return authDAO.getStatusServer(input.getAppSecretCode());
    }

}
