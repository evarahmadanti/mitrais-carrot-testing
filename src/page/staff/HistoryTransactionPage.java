package page.staff;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HistoryTransactionPage {
    WebDriver driver;

    // Define XPath
    String basketCard_xpath = "(//div[@class='row box-reward px-0 mr-0'])";
    String sharedSpent_xpath = "//*[@id='root']/div/div/div/div/div/section/div/div[3]/div/div[2]/h2";
    String bazaarSpent_xpath = "//*[@id='root']/div/div/div/div/div/section/div/div[1]/div/div[2]/h2";
    String donationSpent_xpath = "//*[@id='root']/div/div/div/div/div/section/div/div[2]/div/div[2]/h2";
    String shareTrx_xpath = "//*[@id='root']/div/div/div/div/div/section/div/div[2]/div/div[2]/a";
    String nameShareTrx_xpath = "//*[@id='user']/div/div[1]";
    String messageShareTrx_xpath = "//*[@id='message']";
    String amountShareTrx_xpath = "//*[@id='amount']";
    String sendButtonTrx_xpath = "/html/body/div[3]/div/div/div[3]/button[2]";
    String currentAmount_xpath = "//*[@id='carrot-left']";
    String amountWarning_xpath = "//*[@id='send-carrot-form']/div[3]/div";

    // Define Locators
    By sharedSpent_loc = By.xpath(sharedSpent_xpath + "[1]");
    By donationSpent_loc = By.xpath(donationSpent_xpath + "[2]");
    By bazaarSpent_loc = By.xpath(bazaarSpent_xpath + "[1]");
    By shareTrx_loc = By.xpath(shareTrx_xpath);
    By nameShareTrx_loc = By.xpath(nameShareTrx_xpath);
    By messageShareTrx_loc = By.xpath(messageShareTrx_xpath);
    By amountShareTrx_loc = By.xpath(amountShareTrx_xpath);
    By sendButtonTrx_loc = By.xpath(sendButtonTrx_xpath);
    By currentAmountTrx_loc = By.xpath(currentAmount_xpath);
    By amountWarning_loc = By.xpath(amountWarning_xpath);

    public HistoryTransactionPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:3000/staff/history-transaction");
    }

    public void assertInHistoryTrxPage() {
        String expectedURL = "http://localhost:3000/staff/history-transaction";
        String expectedPageTittle = "HISTORY TRANSACTIONS";

        // Assert URL is as expected
        Assert.assertEquals(this.driver.getCurrentUrl(), expectedURL);
        // Assert Page Title is as expected
        Assert.assertEquals(this.driver.findElement(Header.pageTitle_loc).getText(), expectedPageTittle);
    }

    public void assertSpentCarrotBasket(String bazaar, String donation, String share) {
        String expectedBazaar = bazaar + " Carrot";
        String expectedDonation = donation + " Carrot";
        String expectedShare = share + " Carrots";

        // Assert Bazaar Spent Carrot is as expected
        Assert.assertEquals(this.driver.findElement(bazaarSpent_loc).getText(), expectedBazaar);
        Assert.assertEquals(this.driver.findElement(donationSpent_loc).getText(), expectedDonation);
        Assert.assertEquals(this.driver.findElement(sharedSpent_loc).getText(), expectedShare);
    }

    public void openShareTrx() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(sharedSpent_loc));

        driver.findElement(shareTrx_loc).click();
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void assertAlertText(String expectedAlert) {
        // wait until the alert is present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());

        Assert.assertEquals(driver.switchTo().alert().getText(), expectedAlert);
    }

    public void sendCarrotFields(String name, String message, String amount) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameShareTrx_loc));

        // Get Username and password input field
        WebElement name_elm = this.driver.findElement(this.nameShareTrx_loc);
        WebElement message_elm = this.driver.findElement(this.messageShareTrx_loc);
        WebElement amount_elm = this.driver.findElement(this.amountShareTrx_loc);

        // Type the username and password into the field
        name_elm.sendKeys(name);
        message_elm.sendKeys(message);
        amount_elm.sendKeys(amount);
    }

    public void sendCarrotButton() {
        // Find the send button
        WebElement sendButton = this.driver.findElement(this.sendButtonTrx_loc);

        // Click the login button
        sendButton.click();
    }

    public void assertCurrentCarrotsAmount(String currentAmount) {
        Assert.assertEquals(this.driver.findElement(currentAmountTrx_loc).getText(), currentAmount);
    }

    public void assertAmountWarning(String warningMessage) {
        Assert.assertEquals(this.driver.findElement(amountWarning_loc), warningMessage);
    }
}
