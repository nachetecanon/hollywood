package net.pi.platform.hollywood.karate.api;

import com.intuit.karate.junit4.Karate;

import net.pi.platform.hollywood.Application;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.context.ConfigurableApplicationContext;

import cucumber.api.CucumberOptions;

@RunWith(Karate.class)
@CucumberOptions(plugin = {"html:target/cucumber", "json:target/cucumber/cucumber-json-report.json",
  "junit:target/cucumber/cucumber-junit.xml", "junit:target/cucumber/cucumber-junit.xml"},
  tags = {"~@ignore"})
public class HollywoodIT {

  private static final Logger logger = LoggerFactory.getLogger(HollywoodIT.class);
  private static ConfigurableApplicationContext context;

  static {
    System.setProperty("spring.cloud.config.enabled", "false");
    System.setProperty("spring.profiles.active", "integration-test");
    System.setProperty("karate.env", "local");
    System.setProperty("server.port", "8075");
    System.setProperty("java.net.preferIPv4Stack", "true");
  }

  @ClassRule
  public static StubRunnerRule mockServer =
    new StubRunnerRule()
      .downloadStub("net.pi.platform.authx", "authx-service", "+", "stubs")
      .workOffline(true);

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
