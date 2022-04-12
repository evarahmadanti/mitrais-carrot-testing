package page.login;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import page.Globals;

public class LoginPage {
    WebDriver driver;

    //Define locators on LoginPage
    By username_loc = By.id("username-input");
    By password_loc = By.id("password-input");
    By login_button = By.xpath("(//a[normalize-space()='Login'])[1]");

    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    public static By login_btn = By.xpath(("//a[normalize-space()='Login'])[1]"));

    public void login(String username, String password){
        driver.get(Globals.baseLink);

        WebElement username_elm = this.driver.findElement(this.username_loc);
        WebElement password_elem = this.driver.findElement(this.password_loc);

        username_elm.sendKeys(username);
        password_elem.sendKeys(password);

        WebElement loginBtn = this.driver.findElement(this.login_button);

        loginBtn.click();
    }

    public void assertAlertError(){
        try {
            String expectedError = "Username or Password is wrong";
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();

            String textAlert = alert.getText();

            alert.accept();

            Assert.assertEquals(expectedError, textAlert);
        } catch (Exception e) {
            //exception handling
        }
    }
}
