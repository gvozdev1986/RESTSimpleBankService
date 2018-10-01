package by.htp.hvozdzeu.service.impl;

import by.htp.hvozdzeu.dao.exception.DAOException;
import by.htp.hvozdzeu.model.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class AuthServiceImplTest {

    private String creditCardNumber;
    private String cvCode;
    private String firstName;
    private String lastName;
    private String monthValid;
    private String yearValid;
    private String appSecretCode;

    @Mock
    private AuthServiceImpl authService = new AuthServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        creditCardNumber = "k/XxG2P6B4gMgj1Uct7xIW3jluG2lWTTezl/8h7mu8o=";
        cvCode = "m0+1jortepSaw+4Sq1Jhww==";
        firstName = "1BQqLx1nsSnl51ONlVzcZA==";
        lastName = "pVijpMrdQQIAodOPCAK0dw==";
        monthValid = "ma7N60am44TIgZqsIWHgjQ==";
        yearValid = "iErK/eGFbmLrYI4+NCBoWw==";
        appSecretCode = "5250adab-d985-431c-961a-3977d88e3432";
    }

    @Test
    public void tokenAccess_whenParametersIsCorrect() throws DAOException {
        //when(authService.tokenAccess(creditCardNumber, cvCode, firstName, lastName, monthValid, yearValid, appSecretCode)).thenReturn(any(Response.class));
    }


}