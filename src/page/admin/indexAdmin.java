package page.admin;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import page.Globals;
import page.PageTitle;
import page.login.LoginPage;

public class indexAdmin {
    WebDriver driver;
    
    public indexAdmin(WebDriver driver){
        this.driver = driver;
    }

    public void assertInIndex(){
        String expectedUrl = Globals.baseLink + "/admin";
        String expectedTitle = "CARROT SUMMARY";

        // Assert expected link to actual link
        Assert.assertEquals(this.driver.getCurrentUrl(),expectedUrl);

        // Assert expected title to actual title
        Assert.assertEquals(this.driver.findElement(PageTitle.pageTitle_loc).getText(),expectedTitle);
    }

    public void logoutAdmin(){
        String expectedUrl = Globals.baseLink;
        String expectedBtnText = "LOGIN";

        Assert.assertEquals(expectedUrl,this.driver.getCurrentUrl());
        Assert.assertEquals(expectedBtnText,this.driver.findElement(LoginPage.login_btn).getText());
    }

}
