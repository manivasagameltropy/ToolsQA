package com.qapitol.test;

import com.qapitol.base.TestData;
import com.qapitol.pages.ElementsPage;
import com.qapitol.pages.FormsPage;
import com.qapitol.pages.ToolsQaHomePage;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class ToolsQAFormsTest extends FormsPage {
    ToolsQaHomePage toolsQaHomePage;
    ElementsPage elementsPage;
    FormsPage formsPage;
    @Test(description = "This test will Fill out forms and submit in ToolsQA page")
    public void fillForms() throws MalformedURLException {
        openlink("https://demoqa.com/");
        toolsQaHomePage = new ToolsQaHomePage();
        toolsQaHomePage.clickForms();
        formsPage = new FormsPage();
        formsPage.clickPracticeForm();
        formsPage.setTextInFirstNameBox(TestData.get("firstname"));
        formsPage.setTextInLastNameBox(TestData.get("lastname"));
        formsPage.setTextInUserEmailBox(TestData.get("email"));
        formsPage.setTextInMobileNumberBox(TestData.get("mobilenumber"));
        formsPage.selectGender(TestData.get("gender"));
        formsPage.selectDateOfBirth(TestData.get("year"),TestData.get("month"),TestData.get("day"));
        formsPage.clickSubmitButton();

    }


}
