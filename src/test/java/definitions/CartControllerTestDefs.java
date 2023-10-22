package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.logging.Logger;

public class CartControllerTestDefs extends TestDefsConfig {

    String token;

    Response response;

    private static final Logger log = Logger.getLogger(RecipeControllerTestDefs.class.getName());

    @Given("A user is logged in")
    public void aUserIsLoggedIn() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");

        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");

        token = response.jsonPath().getString("jwt");
        log.info("Token is: " + token);
        Assert.assertNotNull(token);
    }

    @When("A post request is sent the cart endpoint")
    public void aPostRequestIsSentTheCartEndpoint() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Spaghetti");
        requestBody.put("instructions", "Step 1: Boil water...");
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/cart/");
    }

    @Then("The cart is created")
    public void theCartIsCreated() {
        Assert.assertEquals(201, response.getStatusCode());
    }
}
