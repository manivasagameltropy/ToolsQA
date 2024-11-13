package com.qapitol.pages;

import com.qapitol.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ToolsQaHomePage extends BaseClass {
    public ToolsQaHomePage() {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//*[text()='Elements']/..")
    public WebElement elements;
    @FindBy(xpath = "//*[text()='Forms']/..")
    public WebElement forms;
    @FindBy(xpath = "//*[text()='Alerts, Frame & Windows']/..")
    public WebElement alertsandFrames;
    @FindBy(xpath = "//*[text()='Widgets']/..")
    public WebElement widgets;
    @FindBy(xpath = "//*[text()='Interactions']/..")
    public WebElement interactions;
    @FindBy(xpath = "//*[text()='Book Store Application']/..")
    public WebElement bookStoreApplication;

    public void clickElements() {
        click(elements);
    }
    public void clickForms() {
        clickElementUsingJS(forms);
    }
    public void clickAlertsandFrames() {
        clickUsingActions(alertsandFrames);
    }
    public void clickWidgets() {
        clickUsingActions(widgets);
    }
    public void clickInteractions() {
        clickUsingActions(interactions);
    }
    public void clickBookStoreApplication() {
        clickUsingActions(bookStoreApplication);
    }


}
