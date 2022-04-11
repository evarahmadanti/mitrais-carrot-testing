package page.staff;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Assert;

public class Staff {
    WebDriver driver;

    By historyTrxPage_loc = By.xpath("//a[@class='badge badge-white']");

    public Staff(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:3000/staff");
    }

    public void openHistoryTrx() {
        driver.findElement(historyTrxPage_loc).click();
    }
}
