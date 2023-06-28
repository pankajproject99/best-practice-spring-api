package com.example.bestpracticespringapi.controller;

import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SpringBootTest
class LoginControllerIntegrationTest {

    @Test
    public void testLogin(){
        // Set Chrome exe path
        System.setProperty("webdriver.chrome.driver", "D:\\SFT\\chromedriver_win32\\chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver.port", "9515");

        //Create new instance of Chrome Driver
        System.out.println("Open Chrome1");
//        String profileName = "Meraghar";
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--profile-directory=" + profileName);
//        options.setCapability("goog:loggingPrefs", ImmutableMap.of("driver", "INFO"));
        options.addArguments("--disable-cloud-management");
        System.out.println("Open Chrome2");
        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
        System.out.println("Open Chrome3");
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        System.out.println("Open Chrome4");
        // Example: Visit a website
        System.out.println("Visit Page");
        driver.get("http://localhost:8080/login");

        // Find the username and password input fields
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));

        // Enter the username and password
        usernameInput.sendKeys("testuser");
        passwordInput.sendKeys("password");

        // Find the login button and click it
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        //Quit or Close browser
        driver.quit();
    }

//    @Test
//    public void testLoginFirefox(){
////        Set Path
////        System.setProperty()
//        System.setProperty("webdriver.gecko.driver", "D:\\SFT\\geckodriver-v0.33.0-win32\\geckodriver.exe");
//
//        // Create a new instance of FirefoxDriver
//        WebDriver driver = new FirefoxDriver();
//    }
}