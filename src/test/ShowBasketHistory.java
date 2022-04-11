package test;

import org.junit.BeforeClass;
import org.checkerframework.checker.interning.qual.CompareToMethod;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.staff.HistoryTransactionPage;
import page.staff.Staff;

public class ShowBasketHistory {
    private static WebDriver driver;
    private static HistoryTransactionPage historyTrxPage;
    private static Staff staffDashboardPage;

    // Before All Tests
    @BeforeClass
    public static void beforeLogin() {
        System.setProperty("webdriver.chrome.driver",
                "D://Mitrais Fresh Graduate Trainee 2022//Assignment//mitrais-carrot-testing//webdriver//chromedriver.exe");
        driver = new ChromeDriver();
        staffDashboardPage = new Staff(driver);
        historyTrxPage = new HistoryTransactionPage(driver);
    }

    // Before each Test
    // @Before
    // public void goToLoginPage() {
    // // STEP 1: Login first
    // loginPage.login("standard_user", "secret_sauce");
    // }

    @Test
    public void showBasketHistory() {
        // Open The History Trx Menu
        staffDashboardPage.openHistoryTrx();

        // Assert if user can see Home page
        historyTrxPage.assertInHistoryTrxPage();
        historyTrxPage.assertSpentCarrotBasket();
    }

    // After each test
    @After
    public void clearCache() {
        // Delete cookies to logout user
        driver.manage().deleteAllCookies();
    }

    // After all tests
    @AfterClass
    public static void closeBrowser() {
        // Terminate the WebDriver
        driver.quit();
    }
}
