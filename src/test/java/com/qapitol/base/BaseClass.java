package com.qapitol.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class BaseClass {
    public static WebDriver driver;
    public static void startChrome() {
        if (driver == null) { // Ensure driver is only set up once
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }
    public WebElement  findElementbyXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }
    public WebElement findElementByText(String dynamicText) {
        String xpath = String.format("//*[text()='%s']", dynamicText);
        return driver.findElement(By.xpath(xpath));
    }
    public void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }
    public void openlink(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }
    public void clickUsingActions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
    public void setText(WebElement element, String text) {
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
    }
    public void clickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }
    public void closeBrowser(){
        driver.close();
    }

}
