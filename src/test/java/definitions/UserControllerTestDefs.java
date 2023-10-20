package definitions;

import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTestDefs {
    @When("A new user registers with email and password")
    public void aNewUserRegistersWithEmailAndPassword() {

    }
}
