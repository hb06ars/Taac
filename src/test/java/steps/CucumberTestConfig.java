package steps;

import io.cucumber.spring.CucumberContextConfiguration;
import com.projeto.TaacApplication;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = TaacApplication.class)
public class CucumberTestConfig {
}
