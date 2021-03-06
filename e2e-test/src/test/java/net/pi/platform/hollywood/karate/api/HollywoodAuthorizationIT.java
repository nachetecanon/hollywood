package net.pi.platform.hollywood.karate.api;

import com.intuit.karate.junit4.Karate;

import net.pi.platform.hollywood.Application;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import cucumber.api.CucumberOptions;

@RunWith(Karate.class)
@CucumberOptions(plugin = {"html:target/cucumber", "json:target/cucumber/cucumber-json-report.json",
  "junit:target/cucumber/cucumber-junit.xml", "junit:target/cucumber/cucumber-junit.xml"},
  tags = {"@authorization"})
public class HollywoodAuthorizationIT {

  private static final Logger logger = LoggerFactory.getLogger(HollywoodAuthorizationIT.class);
  private static ConfigurableApplicationContext context;

  static {
    System.setProperty("spring.cloud.config.enabled", "false");
    System.setProperty("spring.profiles.active", "authx-qa-test");
    System.setProperty("karate.env", "local");
    System.setProperty("server.port", "8076");
    System.setProperty("java.net.preferIPv4Stack", "true");
  }

  @BeforeClass
  public static void beforeClass() {
    context = SpringApplication.run(Application.class);
    logger.info("started spring context");
  }

  @AfterClass
  public static void afterClass() {
    context.stop();
    logger.info("stopped spring context");
  }

}
