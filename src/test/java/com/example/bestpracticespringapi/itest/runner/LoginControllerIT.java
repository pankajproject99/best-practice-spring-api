package com.example.bestpracticespringapi.itest.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) //This defines to run as Cucumber and option combines StepDefs
@CucumberOptions(glue = {"com.example.bestpracticespringapi.itest.glue"},
        features = "src/test/resources/com/example/bestpracticespringapi/itest/controller/login-controller.feature",
        plugin = {"pretty", "html:target/cucumber-reports/login-controller-it.html"}
)
public class LoginControllerIT {
}
