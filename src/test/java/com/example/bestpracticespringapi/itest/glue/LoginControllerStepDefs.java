package com.example.bestpracticespringapi.itest.glue;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerStepDefs {

    private WebDriver driver;

    @Given("I am on the login page")
    public void goToLoginPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/login");
    }

    @When("I enter username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
    }

    @When("I click the login button")
    public void clickLoginButton() {
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    @Then("I should be redirected to the home page")
    public void verifyHomePage() {
        assertThat(driver.getCurrentUrl()).isEqualTo("http://localhost:8080/home");
    }

    @Then("I should see {string}")
    public void verifyWelcomeMessage(String expectedMessage) {
        WebElement welcomeMessage = driver.findElement(By.id("welcome-message"));
        assertThat(welcomeMessage.getText()).isEqualTo(expectedMessage);
    }

    @After
    public void tearDown() {
//        driver.quit();
    }
}
