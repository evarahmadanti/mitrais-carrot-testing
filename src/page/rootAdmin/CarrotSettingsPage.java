package page.rootAdmin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import page.Header;

public class CarrotSettingsPage {
    WebDriver driver;

    public CarrotSettingsPage(WebDriver driver){
        this.driver = driver;
    }

    //Define Xpath
    String minimumCarrot_xpath="//label[text()='Annual Carrot Left Minimum']/following-sibling::input[1]";
    String birthdayShare_xpath="//label[text()='Carrot Birthday Share']/following-sibling::input[1]";
    String initialCarrot_xpath="//label[text()='Initial Carrot']/following-sibling::input[1]";
    String buttonEdit_xpath="//button[contains(text(),'Edit')]";
    String buttonSave_xpath="//button[contains(text(),'Save Settings')]";

    //Define Locators
    By minimumCarrot_loc=By.xpath(minimumCarrot_xpath);
    By birthdayShare_loc=By.xpath(birthdayShare_xpath);
    By initialCarrot_loc=By.xpath(initialCarrot_xpath);
    By buttonEdit_loc=By.xpath(buttonEdit_xpath);
    By buttonSave_loc=By.xpath(buttonEdit_xpath);

    By settingValue_loc(int index){
        return By.xpath("//tr[" + index + "]/child::td[3]");
    }

    //Define Method that commonly used
    public void assertInSettingPage(){
        String expectedURL="http://localhost:3000/rootadmin/settings";
        String expectedTitle="CARROT SETTINGS";

        Assert.assertEquals(expectedURL, this.driver.getCurrentUrl());
        Assert.assertEquals(expectedTitle, this.driver.findElement(Header.pageTitle_loc).getText());
    }

    public void editSetting(String minimum, String birthdayShare, String initial){
        driver.get("http://localhost:3000/rootadmin/settings"); //delete this after login is complete

        WebElement editButton = this.driver.findElement(buttonEdit_loc);
        editButton.click();

        WebElement minimumCarrotInput=this.driver.findElement(minimumCarrot_loc);
        WebElement birthdayShareInput=this.driver.findElement(birthdayShare_loc);
        WebElement initialCarrotInput=this.driver.findElement(initialCarrot_loc);

        minimumCarrotInput.clear();
        minimumCarrotInput.sendKeys(minimum);
        birthdayShareInput.clear();
        birthdayShareInput.sendKeys(birthdayShare);
        initialCarrotInput.clear();
        initialCarrotInput.sendKeys(initial);

        WebElement saveButton = this.driver.findElement(buttonSave_loc);
        saveButton.click();
    }

    public List<String> getSettingsValue(){
        List<String> result = new ArrayList<String>();
        for(int i = 1; i <= 3; i++){
            result.add(driver.findElement(settingValue_loc(i)).getText());
        }
        return result;
    }
}
