package definitions;

import io.cucumber.java.en.And;
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

public class IngredientControllerTestDefs extends TestDefsConfig{

    String token;

    Response response;
    @Given("A user is currently logged in")
    public void aUserIsCurrentlyLoggedIn() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "email@email.com");
        requestBody.put("password", "password123");

        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/auth/users/login/");

        token = response.jsonPath().getString("jwt");
        Assert.assertNotNull(token);
    }

    @And("A user has a shopping cart")
    public void aUserHasAShoppingCart() throws JSONException {
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "My Shopping Cart");
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/cart/");
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I add an ingredient to my cart")
    public void iAddAnIngredientToMyCart() throws JSONException {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Onion");
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/cart/1/ingredients/");
    }

    @Then("The ingredient is added")
    public void theIngredientIsAdded() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I remove an ingredient from my cart")
    public void iRemoveAnIngredientFromMyCart() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.delete(BASE_URL + port + "/api/cart/1/ingredients/1/");
    }

    @Then("The ingredient is removed")
    public void theIngredientIsRemoved() {
        JsonPath jsonPath = response.jsonPath();
        String message = jsonPath.get("message");
        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals("Ingredient has been removed from shopping cart", message);
    }
}
