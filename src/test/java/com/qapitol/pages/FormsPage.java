package com.qapitol.pages;

import com.qapitol.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormsPage extends BaseClass {
    public FormsPage() {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//span[text()='Practice Form']")
    public WebElement practiceForm;
    @FindBy(xpath = "//input[@id='userEmail']")
    public WebElement userEmailbox;
    @FindBy(xpath = "//input[@placeholder='First Name']")
    public WebElement firstNamebox;
    @FindBy(xpath = "//input[@placeholder='Last Name']")
    public WebElement lastNamebox;
    @FindBy(xpath = "//input[@placeholder='Mobile Number']")
    public WebElement mobileNumberbox;
    @FindBy(xpath = "//input[@id='dateOfBirthInput']")//
    public WebElement dateOfBirthInput;
    @FindBy(xpath = "//select[@class='react-datepicker__year-select']")
    public WebElement yearSelect;
    @FindBy(xpath = "//select[@class='react-datepicker__month-select']")
    public WebElement monthSelect;
    @FindBy(xpath = "//input[@id='subjectsInput']")
    public WebElement subjectsContainer;
    @FindBy(xpath = "//div[@id='state']")
    public WebElement state;
    @FindBy(xpath = "//div[@id='city']")
    public WebElement city;
    @FindBy(xpath = "//button[@id='submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//textarea[@id='currentAddress']")
    public WebElement currentAddressbox;
    @FindBy(xpath ="//input[@id='uploadFile']")
    public WebElement uploadFile;

    public void clickPracticeForm() {
        clickElementUsingJS(practiceForm);
    }
    public void setTextInUserEmailBox(String email) {
        setText(userEmailbox,email);
    }
    public void setTextInFirstNameBox(String name) {
        setText(firstNamebox,name);
    }
    public void setTextInLastNameBox(String name) {
        setText(lastNamebox,name);
    }
    public void setTextInMobileNumberBox(String number) {
        setText(mobileNumberbox,number);
    }
    public void selectDateOfBirth(String year, String month, String day) {
        clickElementUsingJS(dateOfBirthInput);
        selectByText(yearSelect,year);
        selectByText(monthSelect,month);
        selectDate(day);
    }
    public void selectGender(String gender) {
        selectRadioButton(gender);
    }
    public void selectSubject(String subject) {
        clickElementUsingJS(subjectsContainer);
        subjectsContainer.sendKeys(subject);
        selectSubjectByText(subject);
    }





}

