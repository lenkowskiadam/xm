package tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Hooks;
import pages.StockDetailPage;
import pages.TradingPage;

public class UITest extends Hooks {

    @Test
    @Parameters("browser")
    public void testStockData(@Optional("firefox") String browser) {
        HomePage homePage = new HomePage(driver);
        TradingPage tradingPage = new TradingPage(driver);
        StockDetailPage stockDetailPage = new StockDetailPage(driver);

        // Test for maximum resolution
        driver.manage().window().maximize();
        runTest(homePage, tradingPage, stockDetailPage);

        // Test for 1024x768 resolution
        setBrowserSize(1024, 768);
        runTest(homePage, tradingPage, stockDetailPage);

        // Test for 800x600 resolution
        setBrowserSize(800, 600);
        runTest(homePage, tradingPage, stockDetailPage);
    }

    private void runTest(HomePage homePage, TradingPage tradingPage, StockDetailPage stockDetailPage) {
        homePage.openHomePage("http://xm.com");
        homePage.clickTrading();
        tradingPage.clickStocks();
        tradingPage.filterByNorway();
        String stockData = tradingPage.getStockData("Orkla ASA (ORK.OL)");
        tradingPage.clickReadMore("Orkla ASA (ORK.OL)");

        String tradingConditionsData = stockDetailPage.getTradingConditionsData();
        Assert.assertEquals(stockData, tradingConditionsData, "Data mismatch!");
    }
}
