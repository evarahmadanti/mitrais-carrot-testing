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
import org.openqa.selenium.support.ui.WebDriverWait;

import page.staff.HistoryTransactionPage;
import page.staff.Staff;

public class ShareCarrot {
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

    // There are positive test and negative test suites in this code:
    // 1. Successfully share transaction: shareTrx()
    // 2. Failed to share: insufficientAmount(), zeroAmount(), blankField()

    @Test
    public void shareTrx() {
        // Open the history Trx menu
        staffDashboardPage.openHistoryTrx();

        // Open the share carrot modal
        historyTrxPage.openShareTrx();

        // Assert if user can see they current carrot amount
        historyTrxPage.assertCurrentCarrotsAmount("42");

        // Fullfil the field
        historyTrxPage.sendCarrotFields("coco", "lorem ipsum", "1");

        // Click Send
        historyTrxPage.sendCarrotButton();

        // Check the alert message
        historyTrxPage.assertAlertText("The Transaction Success!");

        // Accept the alert
        historyTrxPage.acceptAlert();

        // Reload the page
        driver.navigate().refresh();

        // Check the basket history
        historyTrxPage.assertSpentCarrotBasket("0", "0", "-9");
    }

    @Test
    public void insufficientAmount() {
        // Open the history trx menu
        staffDashboardPage.openHistoryTrx();

        // Open the share carrot modal
        historyTrxPage.openShareTrx();

        // Fullfil the field
        historyTrxPage.sendCarrotFields("coco", "lorem ipsum", "500");

        // Assert the insufficient warning
        historyTrxPage.assertAmountWarning("The carrot amount is insufficient");

        // Click Send
        historyTrxPage.sendCarrotButton();

        // Assert the failed trx warning
        historyTrxPage.assertAlertText("Something is wrong! Please Try Again.");
    }

    @Test
    public void zeroAmount() {
        // Open the history trx menu
        staffDashboardPage.openHistoryTrx();

        // Open the share carrot modal
        historyTrxPage.openShareTrx();

        // Fullfil the field
        historyTrxPage.sendCarrotFields("coco", "lorem ipsum", "0");

        // Assert the insufficient warning
        historyTrxPage.assertAmountWarning("The minimum amount is 1");
    }

    @Test
    public void blankField() {
        // Open the history trx menu
        staffDashboardPage.openHistoryTrx();

        // Open the share carrot modal
        historyTrxPage.openShareTrx();

        // all blank fields
        historyTrxPage.sendCarrotFields("", "", "");
        historyTrxPage.sendCarrotButton();

        // Assert the user blank warning
        historyTrxPage.assertAlertText("Please select a user");
        historyTrxPage.acceptAlert();

        // message and amount blank fields
        historyTrxPage.sendCarrotFields("coco", "", "");
        historyTrxPage.sendCarrotButton();

        // Assert the message warning
        historyTrxPage.assertAlertText("Please enter a message");
        historyTrxPage.acceptAlert();

        // amount blank fields
        historyTrxPage.sendCarrotFields("coco", "test", "");
        historyTrxPage.sendCarrotButton();

        // Assert the message warning
        historyTrxPage.assertAlertText("Something is wrong! Please Try Again.");
        historyTrxPage.acceptAlert();
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
