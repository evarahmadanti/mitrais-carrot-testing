package page.staff;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import page.Globals;
import page.PageTitle;
import page.login.LoginPage;
import page.Logout;

public class indexStaff {

    WebDriver driver;

    public indexStaff(WebDriver driver){
        this.driver = driver;
    }

    public void assertInIndex(){
        String expectedUrl = Globals.baseLink + "/staff";
        // By expectedBtn = By.xpath("(//a[normalize-space()='BAZAAR'])[1]");
        String expectedTitle = "DASHBOARD";

        // Assert expected link to actual link
        Assert.assertEquals(this.driver.getCurrentUrl(),expectedUrl);

        // Assert expected a button to actual button on page
        // Assert.assertEquals(expectedBtn,this.driver.findElement(expectedBtn));
        Assert.assertEquals(this.driver.findElement(PageTitle.pageTitle_loc).getText(),expectedTitle);

    }

}
