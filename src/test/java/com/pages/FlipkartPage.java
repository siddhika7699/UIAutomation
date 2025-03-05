package com.pages;

import com.utility.CustomLogger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Iterator;
import java.util.Set;

public class FlipkartPage {
    public WebDriver driver;

    public FlipkartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//img[@title='Flipkart']")
    public WebElement flipkartLogo;

    @FindBy(xpath = "//input[@name='q']")
    public WebElement searchProductDropDown;
    @FindBy(xpath = "//*[@class='KRzcNw' or contains(text(),'Add to cart') or contains(text(),'GO TO CART')]")
    public WebElement addToCartBtn;

    public void EnterProductName(String productName) throws InterruptedException {
        searchProductDropDown.click();
        searchProductDropDown.sendKeys(productName);
        Thread.sleep(5000); //Wait method not implemented so added Thread.sleep
        searchProductDropDown.sendKeys(Keys.RETURN);
    }
    public boolean verifyFlipkartLogoDisplay() {
        return flipkartLogo.isDisplayed();
    }

    public void addProductInToTheCard(String productName) throws InterruptedException {
        Thread.sleep(2000);//Wait method not implemented so added Thread.sleep
        // search page select First Iphone with same name
        driver.findElement(By.xpath("(//div[contains(text(),'" + productName + "')])[1]")).click();

        Thread.sleep(2000); //Wait method not implemented so added Thread.sleep

        //*********** The below code manages the opening of a new window tab during runtime.***** GET FROM CHATGPT
        // Get all window handles (handles to all the open tabs)
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();

        // Get the handle for the original window (first tab)
        String originalWindow = iterator.next();
        CustomLogger.info("originalWindow :" + originalWindow);

        // Get the handle for the new window (second tab)
        String newWindow = iterator.next();
        CustomLogger.info("newWindow :" + newWindow);

        // Switch to the new window (the second tab)
        driver.switchTo().window(newWindow);
        Thread.sleep(5000);
        addToCartBtn.click();
        Thread.sleep(5000);
    }

    public void verifyProductAddedInCart(String productName) {
        WebElement productLnk = driver.findElement(By.xpath("//a[contains(text(),'" + productName + "')]"));
        Assert.assertTrue("Expected Product NOT Displayed in to the Cart :", productLnk.isDisplayed());
        Assert.assertEquals("Place Order Button NOT Displayed..!", "Place Order".toLowerCase(), driver.findElement(By.xpath("//span[text()='Place Order']/parent::button")).getText().toLowerCase());
    }
}
