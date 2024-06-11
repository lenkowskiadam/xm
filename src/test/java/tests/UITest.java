package tests;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Hooks;

public class UITest extends Hooks {

    @Test
    @Parameters("browser")
    public void test(@Optional("firefox") String browser) {
        HomePage homePage = new HomePage(driver);
        driver.manage().window().maximize();
        runTest(homePage);
        setBrowserSize(1024, 768);
        runTest(homePage);
        setBrowserSize(800, 600);
        runTest(homePage);
    }

    private void runTest(HomePage homePage) {

        //GIVEN user open home page
        homePage.openHomePage();
        //WHEN he clicks on TRADING link
        homePage.clickTrading();
    }
}