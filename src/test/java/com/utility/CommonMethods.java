package com.utility;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormatSymbols;
import java.time.Duration;
import java.util.*;

public class CommonMethods {
    public WebDriver driver;
    Actions action;
    public final static int EXPLICITLY_WAIT_SECONDS = 30;
    public HashMap<String, HashMap<String, Object>> saveDatastoreDetails = new HashMap<>();
    public HashMap<String, HashMap<String, Object>> savePolicyDetails = new HashMap<>();
    public HashMap<String, HashMap<String, Object>> saveDatabaseDetails = new HashMap<>();
    PropertiesFileReader propertiesData = new PropertiesFileReader();
    Properties properties = propertiesData.getProperty();

    public CommonMethods(WebDriver driver) {
        this.driver = driver;
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }


    public void clickElement(WebElement element) {
        CustomLogger.info("Trying to click the element with locator : " + element);
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT_SECONDS));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (NoSuchElementException | ElementClickInterceptedException e) {
            CustomLogger.info("Caught Exception " + e + " Now trying click through Javascript Executor");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        } catch (StaleElementReferenceException se) {
            new WebDriverWait(driver, Duration.ofSeconds(EXPLICITLY_WAIT_SECONDS))
                    .until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));
            CustomLogger.info("Caught StaleElementReferenceException. Waiting for DOM to refresh and then try click through Javascript Executor");
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", element);
        }
        CustomLogger.info("Element is clicked");
    }



}