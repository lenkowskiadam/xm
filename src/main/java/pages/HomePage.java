package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    By tradingLink = By.linkText("Trading");

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void clickTrading() {
        driver.findElement(tradingLink).click();
    }
}
