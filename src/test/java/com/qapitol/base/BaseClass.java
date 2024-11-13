package com.qapitol.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
    public void closeBrowser() {
        driver.quit();
    }
    public void selectRadioButton(String labelText) {
        // Construct the dynamic XPath using the label text
        String xpath = "//label[text()='" + labelText + "']/preceding-sibling::input";

        // Find the radio button element
        WebElement radioButton = driver.findElement(By.xpath(xpath));

        // Click the radio button
        if (!radioButton.isSelected()) {
           clickElementUsingJS(radioButton);
        }
    }
    public void selectDate(String day) {
        // Construct the dynamic XPath for the date, excluding dates outside the current month
        String xpath = "//div[contains(@class, 'react-datepicker__day react-datepicker__day--" + day + "') " +
                "and not(contains(@class, 'day--outside-month'))]";

        // Find the date element
        WebElement dateElement = driver.findElement(By.xpath(xpath));

        // Use Actions to move to and click the date element
       clickElementUsingJS(dateElement);
    }
    public void selectSubjectByText(String text) {
        String xpath = "//div[text()='" + text + "']";
        WebElement element = driver.findElement(By.xpath(xpath));
        clickElementUsingJS(element);

    }
    public void selectByText(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByVisibleText(value);
    }
    public static void setTextUsingJS(WebElement element, String text) {
        // Check if the driver is set
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call setDriver() first.");
        }

        // Create JavaScriptExecutor instance
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Execute JavaScript to set the value of the input field
        js.executeScript("arguments[0].value = arguments[1];", element, text);
    }



}
