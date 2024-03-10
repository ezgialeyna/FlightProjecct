package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BaseTest {
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    public static WebDriver driver;
    private final String selectedBrowser = System.getProperty("browser","CHROME").toUpperCase();

    @BeforeEach
    public void initializeTestEnvironment() {
        driver=setUpWebDriver(selectedBrowser);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void cleanUpTestEnvironment(){
        closeWebDriver();
    }

    private WebDriver setUpWebDriver(String browser) {
        switch (browser) {
            case "CHROME":
                return new ChromeDriver(createChromeOptions());
            case "FIREFOX":
                return new FirefoxDriver(createFirefoxOptions());
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }
    }
    private ChromeOptions createChromeOptions(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito","--disable-gpu");
        return chromeOptions;
    }

    private FirefoxOptions createFirefoxOptions(){
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--private");
        firefoxOptions.addPreference("dom.webnotifications.enabled", false);
        return firefoxOptions;
    }

    private void closeWebDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
