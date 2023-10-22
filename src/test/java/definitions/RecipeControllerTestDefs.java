package definitions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class RecipeControllerTestDefs extends TestDefsConfig{
    private static final Logger log = Logger.getLogger(RecipeControllerTestDefs.class.getName());
    private static Response response;

    /**
     * Retrieves a JWT (JSON Web Token) key by sending a POST request to the authentication endpoint.
     * This method sets up the necessary HTTP request with user credentials and sends it to the
     * authentication endpoint to obtain a JWT key for user authentication.
     * @return The JWT key (token) received from the authentication response.
     * @throws JSONException If there is an issue with JSON processing.
     */
    public String getJWTKey() throws JSONException {
            // Set the base URI and create a request
            RestAssured.baseURI = BASE_URL;
            RequestSpecification request = RestAssured.given();

            // Set the content-type header to indicate JSON data
            request.header("Content-Type", "application/json");

            // Create a JSON request body with user email and password
            JSONObject requestBody = new JSONObject();
            requestBody.put("emailAddress", "email@email.com");
            requestBody.put("password", "password123");

          request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/register/");
            // Send a POST request to the authentication endpoint
            Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");
            log.info("Showing jwt: " + response.jsonPath().getString("jwt"));
            // Extract and return the JWT key from the authentication response
            return response.jsonPath().getString("jwt");
    }

    @Given("A list of recipe favorites are available")
    public void aListOfRecipeFavoritesAreAvailable() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + getJWTKey());
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            //api call to url: http://localhost:port/api/recipes/, GET request, request body is empty for get request, response type of string is expected
            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/recipes/", HttpMethod.GET, entity, String.class);

            List<Map<String, String>> recipeFavorites = JsonPath.from(String.valueOf(response.getBody())).get("data"); //extract data from response as a list of maps with String values
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK); //check if it returns a 200
            Assert.assertTrue(recipeFavorites.size() > 0);
        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @When("I add a recipe to my favorites list")
    public void iAddARecipeToMyFavoritesList() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Spaghetti");
        requestBody.put("instructions", "Step 1: Boil water...");
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + getJWTKey());
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/recipes/");
    }

    @Then("The recipe is added")
    public void theRecipeIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I remove recipe")
    public void iRemoveRecipe() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        response = request.delete(BASE_URL + port + "/api/recipes/1/");
    }

    @Then("The recipe is removed")
    public void theRecipeIsRemoved() {
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Recipe with id 1 has been deleted", message);
    }
}
