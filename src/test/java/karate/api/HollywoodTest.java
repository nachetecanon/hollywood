package karate.api;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Karate.class)
@CucumberOptions(plugin = {"html:target/cucumber", "json:target/cucumber/cucumber-json-report.json"}, tags = {"~@ignore"})
public class HollywoodTest {
}
