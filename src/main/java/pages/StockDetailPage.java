package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StockDetailPage {
    WebDriver driver;

    public StockDetailPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTradingConditionsData() {
        return driver.findElement(By.id("trading-conditions")).getText();
    }
}
