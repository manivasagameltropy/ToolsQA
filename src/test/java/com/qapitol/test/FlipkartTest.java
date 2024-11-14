package com.qapitol.test;

import com.qapitol.pages.Flipkartpage;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlipkartTest extends Flipkartpage {
    Flipkartpage flipkartpage;
    @Test(description = "This Test validates the Products seach")
     public void contextsearch(){
        openlink("https://www.flipkart.com/");
        flipkartpage = new Flipkartpage();
        flipkartpage.validateSearchProducts();

    }

    @Test(description = "This Test validates the Products seach")
     public void dataDriven() throws IOException {
        openlink("https://www.flipkart.com/");
        flipkartpage = new Flipkartpage();
        flipkartpage.enterandSearchProductUsingExcel();

    }


}
