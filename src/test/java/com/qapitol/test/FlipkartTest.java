package com.qapitol.test;

import com.qapitol.pages.Flipkartpage;
import org.testng.annotations.Test;

public class FlipkartTest extends Flipkartpage {
    Flipkartpage flipkartpage;
    @Test(description = "This Test validates the Products seach")
     public void contextsearch(){
        openlink("https://www.flipkart.com/");
        flipkartpage = new Flipkartpage();
        flipkartpage.validateSearchProducts();

    }
}
