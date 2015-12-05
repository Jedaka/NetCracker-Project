package test.project;

import com.project.communication.JsonResponse;
import com.project.communication.RegistrationRequest;
import com.project.mvc.AuthController;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Юрий on 06.12.2015.
 */
public class AuthControllerTest {

    static JsonResponse jsonResponse = null;
    static JsonResponse jsonResponseOK = null;
    static AuthController authController = null;
    @Before
    public void setUp() throws Exception {
        jsonResponse = new JsonResponse();
        jsonResponseOK = new JsonResponse();
        jsonResponseOK.setStatus(JsonResponse.Status.OK);
        authController = new AuthController();
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRegister() throws Exception {
        jsonResponseOK.setMessage("Good job");
        RegistrationRequest registrationRequest = new RegistrationRequest();
        registrationRequest.setEmail("masdasdil@mail.ru");
        registrationRequest.setPassword("1asdasdas231");
        assertEquals(jsonResponseOK, authController.register(registrationRequest));
    }

    @Test
    public void testChangePassword() throws Exception {

    }

    @Test
    public void testProfile() throws Exception {

    }
}