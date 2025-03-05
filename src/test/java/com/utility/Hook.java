package com.utility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Collections;
import java.util.logging.Logger;

public class Hook {

    public static Scenario scn = null;
    public static String browser = System.getenv("browser");
    public static WebDriver driver;
    private static final Logger logger = Logger.getLogger(Hook.class.getName());

    public Hook() {
        logger.info("Inside Hook Constructor");
        CustomLogger.startTestCase(new Throwable().getStackTrace()[0].getMethodName());
    }

    @Before
    public void initDriver(Scenario scenario) {
        scn = scenario;
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true); // To disable SSL
        chromeOptions.addArguments("--incognito"); //To run scripts in incognito mode
        chromeOptions.addArguments("-remote-allow-origins=*");
        chromeOptions.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE); // To ignore any unhandled alerts
        chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); //To remove Chrome is being controlled by automated software
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    @After
    public void afterScenario() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            byte[] data = screenshot.getScreenshotAs(OutputType.BYTES);
            scn.attach(data, "image/png", scn.getName());
        } catch (WebDriverException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
