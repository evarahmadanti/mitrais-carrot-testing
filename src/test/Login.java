package test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import page.Globals;
import page.admin.indexAdmin;
import page.login.LoginPage;
import page.staff.indexStaff;

public class Login {
    private static WebDriver driver;
    private static LoginPage loginPage;
    private static indexAdmin indexAdmin;
    private static indexStaff indexStaff;

    @BeforeClass
    public static void beforeLogin(){
        System.setProperty(Globals.chromeDriverName, Globals.chromeDriverPath);

        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        indexAdmin = new indexAdmin(driver);
    }

    @Test
    public void admin_login(){
        loginPage.login("admin","admin");

        indexAdmin.assertInIndex();
    }

    @Test
    public void logout(){
        loginPage.login("admin","admin");

        indexAdmin.logoutAdmin();
    }

    @Test
    public void staff_login(){
        loginPage.login("staff", "staff");

        indexStaff.assertInIndex();
    }

    @Test
    public void wrong_username(){
        loginPage.login("sdxsadeww","admin");

        loginPage.assertAlertError();
    }

    @Test
    public void wrong_password(){
        loginPage.login("admin","sdadwweas");

        loginPage.assertAlertError();
    }

    //After each test
    @After
    public void clearCache(){
        //Delete cookies to logout user
        driver.manage().deleteAllCookies();
    }

    //After all tests
    @AfterClass
    public static void closeBrowser(){
        //Terminate the WebDriver
        driver.quit();
    }
}
