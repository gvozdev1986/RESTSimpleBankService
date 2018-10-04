package by.htp.hvozdzeu.dao.util;

import by.htp.hvozdzeu.model.response.ResponseEntity;

/**
 * The class for build custom ResponseEntity in JSON format
 */
public class ResponseBuilder {

    /**
     * Private constructor
     */
    private ResponseBuilder() {
    }

    /**
     * The method for build custom ResponseEntity in JSON format
     * @param status Boolean status (true/false)
     * @param message String message
     * @return ResponseEntity entity
     */
    public static ResponseEntity buildResponse(boolean status, String message){
        return ResponseEntity.getBuilder()
                .status(status)
                .message(message)
                .build();
    }

}
