package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.exception.ErrorGetResponseException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class AuthServiceImplQueryTest {

    private static final String CONTEXT_TYPE = "Content-Type";
    private static final String CONTEXT_TYPE_VALUE = "application/json";
    private static final String QUERY_TYPE_GET = "GET";
    private static final Integer RESPONSE_CODE_OK = 200;
    private static final String RESPONSE_STATUS = "status";
    private static final String RESPONSE_MESSAGE = "message";
    private String urlRestServer;
    private Map<Object, Object> parameters;

    @Before
    public void setUp() {
        urlRestServer = "http://localhost:8090/RESTServer/rest/auth";

        parameters = new HashMap<>();
        parameters.put("creditCardNumber", "k/XxG2P6B4gMgj1Uct7xIW3jluG2lWTTezl/8h7mu8o=");
        parameters.put("cvCode", "m0+1jortepSaw+4Sq1Jhww==");
        parameters.put("firstName", "1BQqLx1nsSnl51ONlVzcZA==");
        parameters.put("lastName", "pVijpMrdQQIAodOPCAK0dw==");
        parameters.put("monthValid", "ma7N60am44TIgZqsIWHgjQ==");
        parameters.put("yearValid", "iErK/eGFbmLrYI4+NCBoWw==");
        parameters.put("appSecretCode", "5250adab-d985-431c-961a-3977d88e3432");
    }

    @Test
    public void tokenAccess() throws IOException {

        URL url = new URL(urlRestServer);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(QUERY_TYPE_GET);
        conn.addRequestProperty(CONTEXT_TYPE, CONTEXT_TYPE_VALUE);

        for(Map.Entry prop : parameters.entrySet()) {
            conn.setRequestProperty(prop.getKey().toString(), prop.getValue().toString());
        }

        if (conn.getResponseCode() != RESPONSE_CODE_OK) {
            throw new ErrorGetResponseException("Failed : HTTP error code : " + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;

        while ((output = br.readLine()) != null) {

            JsonElement jsonParser = new JsonParser().parse(output);
            JsonObject jsonObject = jsonParser.getAsJsonObject();
            String status = jsonObject.get(RESPONSE_STATUS).getAsString();
            String message = jsonObject.get(RESPONSE_MESSAGE).getAsString();

            assertNotNull(status);
            assertNotNull(message);

            System.out.println(status);
            System.out.println(message);

        }
        conn.disconnect();
    }

}