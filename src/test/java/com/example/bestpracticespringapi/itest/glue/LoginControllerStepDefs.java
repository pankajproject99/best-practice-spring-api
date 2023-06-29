package com.example.bestpracticespringapi.itest.glue;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginControllerStepDefs {

    private WebDriver driver;

    @Given("I am on the login page")
    public void goToLoginPage() {
        System.setProperty("webdriver.chrome.driver", "D:\\SFT\\chromedriver_win32\\chromedriver.exe");
        //Chrome options so it runs in headless mode, so window will not appear
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless"); //comment this and it becomes no headless

        driver = new ChromeDriver(options);
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
//        WebElement loginButton = driver.findElement(By.id("login-button"));
//        loginButton.click();
        //Fixed: With Theamleaf there is no Id so you can submit form directly
        driver.findElement(By.tagName("form")).submit();

    }

    @Then("I should be redirected to the home page")
    public void verifyHomePage() {
        assertThat(driver.getCurrentUrl()).contains("/home");
    }

    @Then("I should see {string} message")
    public void verifyWelcomeMessage(String expectedMessage) {
        WebElement welcomeMessage = driver.findElement(By.tagName("h1"));
        assertThat(welcomeMessage.getText()).contains(expectedMessage);
    }

    @Then("I should see username {string} and password {string}")
    public void verifyUsernameInWelcomeMessage(String expectedUsername, String expectedPassword) {
        WebElement usernameRow = driver.findElement(By.xpath("//table//tr[contains(.,'User Name:')]//td[2]"));
        WebElement passwordRow = driver.findElement(By.xpath("//table//tr[contains(.,'Password:')]//td[2]"));

        String username = usernameRow.getText();
        String password = passwordRow.getText();

        assertEquals(expectedUsername, username);
        assertEquals(expectedPassword, password);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
