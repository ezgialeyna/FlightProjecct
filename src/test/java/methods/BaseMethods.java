package methods;

import base.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseMethods extends BaseTest {

    public static final Logger logger = LogManager.getLogger(BaseMethods.class);
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(2);
    public BaseMethods(){
        super();
    }
    public void setUp(){
        initializeTestEnvironment();
    }
    public void closeDriver(){
        cleanUpTestEnvironment();
    }
    public WebElement findElement(By by){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }
    public void clickElement(WebElement webElement){
        webElement.click();
    }

    public void clickToElement(By by) throws InterruptedException {
        clickElement(findElement(by));
        Thread.sleep(5000);
        System.out.println(by + " element clicked");
    }

    public void sendKey(By by, String text) throws InterruptedException {
        WebElement element = findElement(by);
        element.sendKeys(text);
        Thread.sleep(2000);
        element.sendKeys(Keys.ENTER);
        System.out.println(by + " element " + text + " The text was typed, waited 2 seconds and the Enter key was pressed.");
    }
    public void goToUrl(String url){
        driver.navigate().to(url);
        System.out.println("page title " +driver.getTitle());
    }
    public WebElement waitForCondition(By locator, String condition, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element;

        switch (condition.toLowerCase()) {
            case "visible":
                element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                break;
            case "clickable":
                element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                break;
            default:
                throw new IllegalArgumentException("Unsupported condition: " + condition);
        }
        return element;
    }

    public WebElement waitForElement(By locator, String condition) {
        WebElement element;
        try {
            element = waitForCondition(locator, condition, DEFAULT_TIMEOUT.getSeconds());
            logger.info("Element found with condition: " + condition);
        } catch (Exception e) {
            logger.error("Element not found with condition: " + condition, e);
            throw e;
        }
        return element;
    }

    public void waitBySeconds(int seconds) {
        try {
            logger.info("Waiting for " + seconds + " seconds.");
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
