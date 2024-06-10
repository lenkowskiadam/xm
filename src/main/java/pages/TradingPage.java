package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TradingPage {
    WebDriver driver;

    public TradingPage(WebDriver driver) {
        this.driver = driver;
    }

    By stocksLink = By.linkText("Stocks");

    public void clickStocks() {
        driver.findElement(stocksLink).click();
    }

    public void filterByNorway() {
        driver.findElement(By.xpath("//button[text()='Norway']")).click();
    }

    public String getStockData(String stockName) {
        return driver.findElement(By.xpath("//td[text()='" + stockName + "']/following-sibling::td")).getText();
    }

    public void clickReadMore(String stockName) {
        driver.findElement(By.xpath("//td[text()='" + stockName + "']/following-sibling::td/a[text()='READ MORE']")).click();
    }
}
