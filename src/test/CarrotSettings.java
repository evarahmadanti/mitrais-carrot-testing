package test;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import page.rootAdmin.CarrotSettingsPage;

public class CarrotSettings {
    private static WebDriver driver;
    private static CarrotSettingsPage carrotSettingsPage;

    @BeforeClass
    public static void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "D://Training//mitrais-carrot-testing//webdriver//chromedriver.exe");
        driver = new ChromeDriver();
        carrotSettingsPage = new CarrotSettingsPage(driver);
    }

    @Test
    public void standardEdit(){
        String minimum = "150";
        String birthdayShare = "25";
        String initial = "10";
        carrotSettingsPage.editSetting(minimum, birthdayShare, initial);

        String[] expectedValue = {minimum, birthdayShare, initial};

        List<String> currentValue = carrotSettingsPage.getSettingsValue();
        Assert.assertArrayEquals(expectedValue, currentValue.toArray());
    }
}
