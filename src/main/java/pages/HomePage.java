package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String homePageUrl = "https://www.xm.com/";

    @FindBy(xpath = "//button[text()='ACCEPT ALL']")
    private WebElement acceptCookieButton;

    @FindBy(xpath = "//li[@class='main_nav_trading']")
    private WebElement tradingLinkMaxRes;

    @FindBy(xpath = "//a[@class='navbar-nav__toggleArrow collapsed']//i[@class='fa fa-chevron-down']")
    private WebElement tradingLinkOtherRes;

    @FindBy(xpath = "//button[@class='toggleLeftNav']")
    private WebElement menuButton;

    @FindBy(xpath = "//a[@href='https://www.xm.com/account-types']")
    private WebElement tradingAccountTypesLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    public void openHomePage() {
        driver.get(homePageUrl);
        acceptCookieIfPresent();
        verifyHomePage();
    }

    private void verifyHomePage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, homePageUrl, "User is not on the home page URL!");
    }

    private boolean isElementPresent(WebElement element) {
        try {
            return element != null && element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private void acceptCookieIfPresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(acceptCookieButton));
            if (isElementPresent(acceptCookieButton)) {
                System.out.println("Accepting cookies.");
                acceptCookieButton.click();
                wait.until(ExpectedConditions.invisibilityOf(acceptCookieButton));
            }
        } catch (TimeoutException e) {
            System.out.println("Cookie acceptance button not found: " + e.getMessage());
        }
    }

    public void clickTrading() {
        try {
            int width = driver.manage().window().getSize().getWidth();
            if (width <= 800) {
                if (isElementPresent(menuButton)) {
                    menuButton.click();
                    scrollToElement(tradingLinkOtherRes);
                    clickElement(tradingLinkOtherRes);
                } else {
                    Assert.fail("Trading link for lower resolution is not present!");
                }
            } else {
                if (isElementPresent(tradingLinkMaxRes)) {
                    scrollToElement(tradingLinkMaxRes);
                    clickElement(tradingLinkMaxRes);
                } else {
                    Assert.fail("Trading link for max resolution is not present!");
                }
            }

            wait.until(ExpectedConditions.visibilityOf(tradingAccountTypesLink));
            Assert.assertTrue(tradingAccountTypesLink.isDisplayed(), "Trading Account Types link is not visible!");

        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        } catch (TimeoutException e) {
            System.out.println("Timeout waiting for element: " + e.getMessage());
            Assert.fail("Exception occurred: " + e.getMessage());
        }
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
