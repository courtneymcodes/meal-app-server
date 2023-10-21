package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RecipeControllerTestDefs extends TestDefsConfig{

    private static final String BASE_URL = "http://localhost:";

    @Given("A list of recipe favorites are available")
    public void aListOfRecipeFavoritesAreAvailable() {
    }

    @When("I add a recipe to my favorites list")
    public void iAddARecipeToMyFavoritesList() {
        
    }

    @Then("The recipe is added")
    public void theRecipeIsAdded() {
    }
}
