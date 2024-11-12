package com.qapitol.test;

import com.qapitol.pages.ElementsPage;
import com.qapitol.pages.ToolsQaHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ToolsQaHomepageTest extends ToolsQaHomePage {
    ToolsQaHomePage toolsQaHomePage;
    ElementsPage elementsPage;
    List<String> headers = Arrays.asList("First Name", "Last Name", "Age", "Email", "Salary", "Department", "Action");

    @Test
    public void sampleTest() {
        startChrome();
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
        closeBrowser();
    }
}
