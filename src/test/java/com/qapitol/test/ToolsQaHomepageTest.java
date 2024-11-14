package com.qapitol.test;

import com.aventstack.extentreports.Status;
import com.qapitol.pages.ElementsPage;
import com.qapitol.pages.ToolsQaHomePage;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ToolsQaHomepageTest extends ToolsQaHomePage {
    ToolsQaHomePage toolsQaHomePage;
    ElementsPage elementsPage;
    List<String> headers = Arrays.asList("First Name", "Last Name", "Age", "Email", "Salary", "Department", "Action");

    @Test
    public void Test01() {
        test.log(Status.INFO, "Starting test case");
        openlink("https://demoqa.com/");
        toolsQaHomePage = new ToolsQaHomePage();
        toolsQaHomePage.clickElements();
        elementsPage = new ElementsPage();
        elementsPage.clickTextBox();
        elementsPage.setTextInUserNameBox("Test");
        elementsPage.setTextInUserEmailBox("<EMAIL>");
        elementsPage.setTextInCurrentAddressBox("Test");
        elementsPage.setTextInPermanentAddressBox("Test");
        elementsPage.clickSubmitButton();
        elementsPage.clickCheckBox();
        elementsPage.clickToggleButton();
        elementsPage.clickDesktopCheckbox();
        elementsPage.clickRadioButton();
        elementsPage.clickYesRadioButton();
        elementsPage.clickWebTable();
        List<String> ActualHeadings = elementsPage.printTableHeaders();
        Assert.assertEquals(ActualHeadings,headers,"Test Failed as Expected headings is not present in the table");
        elementsPage.clickuploadAndDownload();
        elementsPage.uploadFile("C:\\Users\\Qapitol\\Desktop\\ToolsQADemo\\NewDemo\\src\\test\\resources\\sampleFile (1).jpeg");
        test.pass("Navigate to DemoQA.com");
    }

    @Test
    public void Test02() {
        logPass("Welocom to test 2");
        openlink("https://google.com/");
        loggerMsg("Checking log4j");
        logFail("Failed");
    }


}
