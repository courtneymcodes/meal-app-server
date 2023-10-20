package definitions;

import com.example.mealappserver.MealAppServerApplication;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MealAppServerApplication.class)
public class UserControllerTestDefs {

    private final Logger logger = (Logger) LoggerFactory.getLogger(UserControllerTestDefs.class);

    private static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    String port;  //will store random port number

    @When("A new user registers with email and password")
    public void aNewUserRegistersWithEmailAndPassword() {

    }
}
