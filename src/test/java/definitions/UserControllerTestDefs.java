package definitions;

import com.example.mealappserver.MealAppServerApplication;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MealAppServerApplication.class)
public class UserControllerTestDefs {

    private static final String BASE_URL = "http://localhost:";

    private static RequestSpecification request = RestAssured.given();

    @LocalServerPort
    String port;  //will store random port number

    @When("A new user registers with email and password")
    public void aNewUserRegistersWithEmailAndPassword() throws JSONException {

        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");
        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");

        // Send a POST request to the authentication endpoint
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/register/");
    }
}
