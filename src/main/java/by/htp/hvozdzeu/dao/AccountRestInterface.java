package by.htp.hvozdzeu.dao;

import javax.ws.rs.core.Response;

/**
 * The interface for Auth
 */
public interface AccountRestInterface {

    /**
     * The method for check status of server for client
     *
     * @param appSecretCode String special code for verify then request went from current client
     * @return String status of server
     */
    Response getStatusServer(String appSecretCode);

}
