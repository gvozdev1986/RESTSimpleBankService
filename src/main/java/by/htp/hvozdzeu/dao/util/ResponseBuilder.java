package by.htp.hvozdzeu.dao.util;

import by.htp.hvozdzeu.model.response.Response;

/**
 * The class for build custom Response in JSON format
 */
public class ResponseBuilder {

    /**
     * Private constructor
     */
    private ResponseBuilder() {
    }

    /**
     * The method for build custom Response in JSON format
     * @param status Boolean status (true/false)
     * @param message String message
     * @return Response entity
     */
    public static Response buildResponse(boolean status, String message){
        return Response.getBuilder()
                .status(status)
                .message(message)
                .build();
    }

}
