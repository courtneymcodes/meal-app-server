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

    @When("A new user registers with email and password")
    public void aNewUserRegistersWithEmailAndPassword() throws JSONException {

        RequestSpecification request = RestAssured.given();
        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");
        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");

        // Send a POST request to the authentication endpoint
         response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/register/");
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
    public String theUserEntersValidCredentials() throws JSONException {
        //create request
        RequestSpecification request = RestAssured.given();
        // Set the content-type header to indicate JSON data
        request.header("Content-Type", "application/json");
        // Create a JSON request body with user email and password
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");

        // Send a POST request to the authentication endpoint
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login/");
        Assert.assertEquals(200, response.getStatusCode());
        // Extract and return the JWT key from the authentication response
        return response.jsonPath().getString("jwt");
    }

    @Then("The user is authenticated")
    public void theUserIsAuthenticated() {
        Assert.assertNotNull(response.jsonPath().getString("jwt"));
    }
}
