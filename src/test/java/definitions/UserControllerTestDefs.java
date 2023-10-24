package definitions;

import com.example.mealappserver.MealAppServerApplication;
import com.example.mealappserver.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

public class UserControllerTestDefs extends TestDefsConfig{

    private Response response;
    private String jwtToken;

    @When("A new user registers with email and password")
    public void aNewUserRegistersWithEmailAndPassword() throws JSONException {

        RequestSpecification request = RestAssured.given();
        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");
        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email1@email.com");
        requestBody.put("password", "password12");

        // Send a POST request to the authentication endpoint
         response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register/");
         Assert.assertEquals(201, response.getStatusCode());
    }

    @Then("The user is added")
    public void theUserIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @Given("The user exists")
    public void theUserExists() throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");
        Assert.assertNotNull(requestBody);
    }

    @When("The user enters valid credentials")
    public void theUserEntersValidCredentials() throws JSONException {
        //create request
        RequestSpecification request = RestAssured.given();
        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");
        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");

        // Send a POST request to the authentication endpoint
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");
        Assert.assertEquals(200, response.getStatusCode());
        //extract jwt from response
         jwtToken = response.jsonPath().getString("jwt");
    }

    @Then("The user is authenticated")
    public void theUserIsAuthenticated() {
        Assert.assertNotNull(jwtToken);
    }
}
