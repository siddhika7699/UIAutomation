package com.stepDefinition;

import com.utility.CustomLogger;
import com.utility.Hook;
import com.utility.PropertiesFileReader;
import com.pages.FlipkartPage;
import com.utility.CommonMethods;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Properties;

import static com.utility.Hook.driver;

public class FlipkartSteps {

    PropertiesFileReader propertiesData = new PropertiesFileReader();
    Properties properties = propertiesData.getProperty();
    public static String applicationUrl;
    FlipkartPage flipKart;
    Hook hook;
    CommonMethods commonMethods;


    public FlipkartSteps(Hook hook) {
        this.hook = hook;
        flipKart = new FlipkartPage(driver);
        commonMethods = new CommonMethods(driver);
        applicationUrl = properties.getProperty("BaseURL");
    }

    //--------------------
    @Given("User open the url of Flipkart Application")
    public void userOpenTheUrlOfFlipkartApplication() {
        CustomLogger.info("Launching browser");
        driver.get(applicationUrl);
        Assert.assertTrue("Navigation to the Flipkart Dashboard page fails !", flipKart.verifyFlipkartLogoDisplay());
        CustomLogger.info("User is navigated successfully to the Flipkart Page..!!");
    }



    @When("User searches for {string} in the search bar.")
    public void userSearchesForInTheSearchBar(String productName) throws InterruptedException {
        CustomLogger.info("User trying to click and Enter the Product Name..!!");
        flipKart.EnterProductName(productName);
        CustomLogger.info("User successfully search the Product Name.");
    }

    @Then("User adds {string} in the Cart")
    public void userAddsInTheCart(String productName) throws InterruptedException {
        CustomLogger.info("User trying select Product ..!!");
        flipKart.addProductInToTheCard(productName);
    }

    @Then("User verify that {string} product added in the cart")
    public void userVerifyThatProductAddedInTheCart(String productName) {
        CustomLogger.info("User Verifying Product added to cart ..!!");
        flipKart.verifyProductAddedInCart(productName);
        CustomLogger.info("User Verified Product successfully added to cart ..!!");
    }
}
