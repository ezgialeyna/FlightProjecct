package steps;

import base.BaseTest;
import methods.BaseMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
public class FlightHomePage extends BaseTest {
    BaseMethods baseMethods = new BaseMethods();
    public static final Logger logger = LogManager.getLogger(FlightHomePage.class);

    public static final By originInput = By.xpath("//input[@data-testid='endesign-flight-origin-autosuggestion-input']");
    public static final By destinationInput = By.xpath("//input[@data-testid='endesign-flight-destination-autosuggestion-input']");
    public static final By datePickerInput = By.xpath("//input[@data-testid='enuygun-homepage-flight-departureDate-datepicker-input']");
    public static final By activeDayInput = By.xpath("//button[@data-testid='datepicker-active-day']");
    public static final By returnDate = By.xpath(" //div[@data-testid='enuygun-homepage-flight-returnDate-label']");
    public static final By submitButton = By.xpath("//button[@data-testid='enuygun-homepage-flight-submitButton']");
    public static final By departureTime = By.xpath("//div[@class='ctx-filter-departure-return-time card-header']//span[@class='card-title']");


    public void selectDate(String selectAfterDay) {
        try {
            WebElement departureDateInput = baseMethods.waitForElement(datePickerInput, "clickable");
            departureDateInput.click();
            int afterDay = Integer.parseInt(selectAfterDay);
            List<WebElement> days = driver.findElements(activeDayInput);
            WebElement selectedDate = days.get(afterDay);
            selectedDate.click();
            logger.info("Selected " + selectAfterDay + " days from now");
        } catch (Exception e) {
            Assertions.fail("Departure date could not be selected " + selectAfterDay + " days from now", e);
        }
    }

    public void selectReturnDate(String selectReturnDay) {
        try {
            WebElement departureDateInput = baseMethods.waitForElement(returnDate, "clickable");
            departureDateInput.click();
            int afterDay = Integer.parseInt(selectReturnDay);
            List<WebElement> days = driver.findElements(activeDayInput);
            WebElement selectedDate = days.get(afterDay);
            selectedDate.click();
            System.out.println("Selected " + selectReturnDay + " days from now");
        } catch (Exception e) {
            Assertions.fail("Departure date could not be selected " + selectReturnDay + " days from now", e);
        }
    }
}
