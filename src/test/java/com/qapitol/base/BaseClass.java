package com.qapitol.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    // WebDriver and ExtentReports related variables
    protected ExtentReports extent;
    protected ExtentTest test;
    public static WebDriver driver;

    // Log4j Logger instance
    private static final Logger logger = LogManager.getLogger(BaseClass.class);

    // Start the Chrome WebDriver and set timeouts
    public static void startChrome() {
        if (driver == null) { // Ensure driver is only set up once
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            logger.info("Chrome WebDriver started.");
        }
    }

    public void click(WebElement element) {
        scrollToElement(element);
        logger.info("Clicking on element: " + element);
        element.click();
    }

    public void openlink(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        logger.info("Opened URL: " + url);
    }

    public void clickUsingActions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        logger.info("Performed click action on element: " + element);
    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        logger.debug("Scrolled to element: " + element);
    }

    public void setText(WebElement element, String text) {
        scrollToElement(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Set text: '" + text + "' in element: " + element);
    }

    public void clickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
        logger.info("Clicked element using JavaScript: " + element);
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



    // Close the browser (only called after all tests are completed)
    public void closeBrowser() {
        driver.quit();
        logger.info("Browser closed.");
    }

    // Setup Extent Reports
    @BeforeSuite
    public void startingSession() {
        startChrome();
    }

    @BeforeClass
    public void setupReport() {
        // Ensure report folder exists
        String reportDir = System.getProperty("user.dir") + "/test-output/";
        File reportDirFile = new File(reportDir);
        if (!reportDirFile.exists()) {
            reportDirFile.mkdirs();
        }

        // Setup Extent Spark Reporter
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportDir + "ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Environment", "QA");
        logger.info("Extent report setup complete.");
    }

    @BeforeMethod
    public void createTest(Method method) {
        test = extent.createTest(method.getName());
        logger.info("Test started: " + method.getName());
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // Log the failure
            test.fail("Test failed: " + result.getThrowable());
            try {
                // Capture and add a screenshot to the report
                String screenshotPath = captureScreenshot(result.getName());
                test.addScreenCaptureFromPath(screenshotPath, "Screenshot on Failure");
            } catch (IOException e) {
                test.fail("Failed to attach screenshot due to: " + e.getMessage());
            }
            logger.error("Test failed: " + result.getThrowable());
        } else {
            test.pass("Test passed");
            logger.info("Test passed: " + result.getName());
        }
    }

    @AfterClass
    public void flushReport() {
        extent.flush();
        logger.info("Extent report flushed.");
    }

    // AfterSuite method to close the browser after all test suites are finished
    @AfterSuite
    public void tearDownSuite() {
        if (driver != null) {
            driver.quit();  // Close the browser after all tests are done
            logger.info("Browser closed after all tests are completed.");
        }
    }

    private String captureScreenshot(String screenshotName) throws IOException {
        // Get the absolute path for saving screenshots
        String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
        File screenshotDirFile = new File(screenshotDir);
        if (!screenshotDirFile.exists()) {
            screenshotDirFile.mkdirs();  // Create the directory if it doesn't exist
        }

        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        // Specify the destination path for the screenshot
        String destination = screenshotDir + screenshotName + ".png";
        File finalDestination = new File(destination);

        // Copy the screenshot to the desired destination
        FileUtils.copyFile(source, finalDestination);

        // Return relative path for the report
        // Use a relative path based on the location of the report
        return "screenshots/" + screenshotName + ".png";
    }

    // Simplified method to log a passed assertion
    protected void logPass(String message) {
        test.pass(message);
        logger.info(message);
        Assert.assertTrue(true, message); // This ensures the test continues as passed
    }

    // Method to log a failed assertion
    protected void logFail(String message) {
        test.fail(message);
        logger.error(message);
        Assert.fail(message); // To fail the test explicitly
    }

    protected void loggerMsg(String message) {
        logger.info(message);
    }
}
