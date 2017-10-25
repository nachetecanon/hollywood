package net.pi.platform.hollywood.karate.api;

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
@CucumberOptions(plugin = {"html:target/cucumber", "json:target/cucumber/cucumber-json-report" +
        ".json", "junit:target/cucumber/cucumber-junit.xml", "junit:target/cucumber/cucumber-junit" +
        ".xml"}, tags = {"~@ignore"})
public class HollywoodIT {

    static {
        System.setProperty("spring.cloud.config.enabled", "false");
        System.setProperty("spring.profiles.active", "integration-test");
        System.setProperty("karate.env", "local");
    }

    private static ConfigurableApplicationContext context;
    private static final Logger logger = LoggerFactory.getLogger(HollywoodIT.class);

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
