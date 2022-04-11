package page.staff;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class HistoryTransactionPage {
    WebDriver driver;

    // Define XPath
    String basketCard_xpath = "(//div[@class='row box-reward px-0 mr-0'])";
    String sharedSpent_xpath = "(//h2[@class='text-white'][normalize-space()='-3 Carrots'])";
    String bazaarSpent_xpath = "(//h2[@class='text-white'][normalize-space()='0 Carrot'])";
    String donationSpent_xpath = "(//h2[@class='text-white'][normalize-space()='0 Carrot'])";
    String shareTrx_xpath = "//a[@class='badge badge-white']";
    String nameShareTrx_xpath = "(//div[contains(@class,'css-319lph-ValueContainer')])[1]";
    String messageShareTrx_xpath = "//textarea[@id='message']";

    // Define Locators
    By sharedSpent_loc = By.xpath(sharedSpent_xpath + "[1]");
    By donationSpent_loc = By.xpath(donationSpent_xpath + "[2]");
    By bazaarSpent_loc = By.xpath(bazaarSpent_xpath + "[1]");
    By shareTrx_loc = By.xpath(shareTrx_xpath);
    By nameShareTrx_loc = By.xpath(nameShareTrx_xpath);
    By messageShareTrx_loc = By.xpath(messageShareTrx_xpath);

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

    public void assertSpentCarrotBasket() {
        String expectedBazaar = "0 Carrot";
        String expectedDonation = "0 Carrot";
        String expectedShare = "-3 Carrots";

        // Assert Bazaar Spent Carrot is as expected
        Assert.assertEquals(this.driver.findElement(bazaarSpent_loc).getText(), expectedBazaar);
        Assert.assertEquals(this.driver.findElement(donationSpent_loc).getText(), expectedDonation);
        Assert.assertEquals(this.driver.findElement(sharedSpent_loc).getText(), expectedShare);
    }

    public void openShareTrx() {
        driver.findElement(shareTrx_loc).click();
    }

    public void sendCarrot(String name, String message) {
        // Get Username and password input field
        WebElement name_elm = this.driver.findElement(this.nameShareTrx_loc);
        WebElement message_elm = this.driver.findElement(this.messageShareTrx_loc);

        // Type the username and password into the field
        name_elm.sendKeys(name);
        message_elm.sendKeys(message);

        // , String message, int amount
        // // Find the login button
        // WebElement loginButton = this.driver.findElement(this.loginButton_loc);

        // // Click the login button
        // loginButton.click();

    }
}
