package by.htp.hvozdzeu.dao.util;

import by.htp.hvozdzeu.model.response.Response;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static Response buildResponse(boolean status, String message){
        return Response.getBuilder()
                .status(status)
                .message(message)
                .build();
    }

}
