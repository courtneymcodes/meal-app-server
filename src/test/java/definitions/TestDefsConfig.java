package definitions;

import com.example.mealappserver.MealAppServerApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = MealAppServerApplication.class)
public class TestDefsConfig {

    public static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    public String port; //will store random port number
}
