package com.qapitol.pages;

import com.qapitol.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class ElementsPage extends BaseClass {
    public ElementsPage() {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@id='userName']")
    public WebElement userNamebox;

    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement userEmailbox;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    public WebElement currentAddressbox;

    @FindBy(xpath = "//textarea[@id='permanentAddress']")
    public WebElement permanentAddressbox;

    @FindBy(xpath = "//button[@id='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//*[text()='Text Box']/parent::li")
    public WebElement textBox;

    @FindBy(xpath = "//*[text()='Check Box']/parent::li")
    public WebElement checkBox;

    @FindBy(xpath = "//*[text()='Radio Button']/parent::li")
    public WebElement radioButton;

    @FindBy(xpath = "//*[text()='Web Tables']/parent::li")
    public WebElement webTable;

    @FindBy(xpath = "//*[text()='Buttons']/parent::li")
    public WebElement buttons;

    @FindBy(xpath = "//*[text()='Links']/parent::li")
    public WebElement links;

    @FindBy(xpath = "//*[text()='Upload and Download']/parent::li")
    public WebElement uploadAndDownload;

    @FindBy(xpath = "//button[@aria-label='Toggle' and @class='rct-collapse rct-collapse-btn']")
    public WebElement toggleButton;

    @FindBy(xpath = "//span[text()='Desktop']/../input")
    public WebElement desktopCheckbox;

    @FindBy(xpath = "//span[text()='Documents']/../input")
    public WebElement documentsCheckbox;

    @FindBy(xpath = "//span[text()='Downloads']/../input")
    public WebElement downloadsCheckbox;

    @FindBy(xpath = "//input[@id='yesRadio']")
    public WebElement yesRadioButton;

    @FindBy(xpath = "//div[@class='rt-resizable-header-content']")
    public List<WebElement> tableHeaders;

    public void setTextInUserNameBox(String name) {
        setText(userNamebox,name);
    }
    public void setTextInUserEmailBox(String email) {
        setText(userEmailbox,email);

    }
    public void setTextInCurrentAddressBox(String address) {
        setText(currentAddressbox,address);
    }
    public void setTextInPermanentAddressBox(String address) {
        setText(permanentAddressbox,address);
    }
    public void clickSubmitButton() {
        click(submitButton);
    }
    public void clickTextBox() {
        clickElementUsingJS(textBox);
    }
    public void clickCheckBox() {
        clickElementUsingJS(checkBox);
    }
    public void clickToggleButton() {
        click(toggleButton);
    }
    public void clickDesktopCheckbox() {
        clickElementUsingJS(desktopCheckbox);
    }
    public void clickRadioButton() {
        clickElementUsingJS(radioButton);
    }
    public void clickYesRadioButton() {
        clickElementUsingJS(yesRadioButton);
    }
    public void clickWebTable() {
        clickElementUsingJS(webTable);
    }
    public List<String> printTableHeaders() {
        List<String> headerTexts = new ArrayList<>();
        for (WebElement header : tableHeaders) {
            headerTexts.add(header.getText());
        }
        return headerTexts;
    }


}
