package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class Hooks {
    protected WebDriver driver;

    private WebDriver createWebDriver() {
        System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        return new FirefoxDriver(firefoxOptions);
    }

    @BeforeMethod
    public void setUp() {
        try {
            driver = createWebDriver();
            configureDriver();
            System.out.println("Initialized Firefox browser successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize browser: Firefox", e);
        }
    }

    private void configureDriver() {
        if (driver != null) {
            driver.manage().window().setSize(new Dimension(1280, 800));
            driver.manage().window().maximize();
        }
    }

    public void setBrowserSize(int width, int height) {
        if (driver != null) {
            driver.manage().window().setSize(new Dimension(width, height));
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("Browser session ended.");
            } catch (Exception e) {
                System.err.println("Failed to quit browser: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}
