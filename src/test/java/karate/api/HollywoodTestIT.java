package karate.api;

import com.intuit.karate.junit4.Karate;
import cucumber.api.CucumberOptions;
import net.pi.platform.hollywood.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;



@RunWith(Karate.class)
@CucumberOptions(plugin = {"html:target/cucumber", "json:target/cucumber/cucumber-json-report.json"}, tags = {"~@ignore"})
public class HollywoodTestIT {

    static {
        System.setProperty("spring.cloud.config.enabled", "false");
        System.setProperty("spring.profiles.active", "integration-test");
    }

    private static ConfigurableApplicationContext context;
    private static final Logger logger = LoggerFactory.getLogger(HollywoodTestIT.class);

    @BeforeClass
    public static void beforeClass() throws Exception {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        context = SpringApplication.run(Application.class, "--server.port=8075");
        logger.info("started spring context");


    }

    @AfterClass
    public static void afterClass() {
        context.stop();
        logger.info("stopped spring context");

    }

}
