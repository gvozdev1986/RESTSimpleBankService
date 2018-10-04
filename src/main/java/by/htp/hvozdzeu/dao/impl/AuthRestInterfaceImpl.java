package by.htp.hvozdzeu.dao.impl;

import by.htp.hvozdzeu.dao.AccountRestInterface;

import javax.ws.rs.core.Response;

import static by.htp.hvozdzeu.dao.util.ResponseBuilder.buildResponse;
import static by.htp.hvozdzeu.util.ConstantPool.APP_SECRET_CODE;

/**
 * The class for getting stat server
 */
public class AuthRestInterfaceImpl implements AccountRestInterface {

    /**
     * The method for check status of server
     *
     * @param appSecretCode String special code for verify then request went from current client
     * @return String token for next query (request)
     */
    @Override
    public Response getStatusServer(String appSecretCode) {
        if(APP_SECRET_CODE.equals(appSecretCode)){
            return Response.status(200).entity(buildResponse(true, "online")).build();
        } else {
            return Response.status(200).entity(buildResponse(false, "offline")).build();
        }
    }

}
