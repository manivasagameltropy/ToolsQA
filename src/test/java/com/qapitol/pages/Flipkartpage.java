package com.qapitol.pages;

import com.qapitol.Util.ExcelUtil;
import com.qapitol.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class Flipkartpage extends BaseClass {
    public Flipkartpage() {
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@title='Search for Products, Brands and More']")
    public WebElement searchBox;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement searchButton;
    @FindBy(xpath = "//span[contains(@id, 'productRating')]/../preceding-sibling::div")
    public List<WebElement> productNames;


    public void enterandSearchProduct(String productName) {
        setText(searchBox,productName);
        click(searchButton);
    }
    public void validateSearchProducts(){
        for(WebElement productName : productNames){
            System.out.println(productName.getText());
        }
    }
    public void enterandSearchProductUsingExcel() throws IOException {
        String userDir = System.getProperty("user.dir");
        // Construct the path to the properties file relative to the project root directory
        String filePath = userDir + "\\src\\test\\resources\\Book1.xlsx";
        String data = String.valueOf(ExcelUtil.readCellDataByHeader(filePath,"Name",0));
        System.out.println(data);
        setText(searchBox,data);
        click(searchButton);

    }
}
