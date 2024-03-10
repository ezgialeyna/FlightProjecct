package steps;

import base.BaseTest;
import methods.BaseMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightSearchPage extends BaseTest {

    BaseMethods baseMethods = new BaseMethods();
    public static final Logger logger = LogManager.getLogger(FlightHomePage.class);

    public static final By leftSlider = By.xpath("//div[@data-testid='departureDepartureTimeSlider']//div[4]");
    public static final By rightSlider = By.xpath("//div[contains(@data-testid,'departureDepartureTimeSlider')]//div[5]");
    public static final By flightDepartureTime = By.xpath("//div[@class='flight-departure-time']");
    public static final By summaryAirline = By.xpath("//div[@class='summary-airline']");
    public static final By flightSummaryPrice = By.xpath("//div[@class='flight-summary-price']");



    public void setSliderValue(By leftSlider, Integer targetValue) {
        WebElement targetSlider = baseMethods.waitForElement(leftSlider, "clickable");
        new Actions(driver).dragAndDropBy(targetSlider, targetValue, 0).perform();
    }

    public void checkFlightTimes(String startTimeString, String endTimeString) {

        List<WebElement> flightTimes = driver.findElements(flightDepartureTime);
        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalTime endTime = LocalTime.parse(endTimeString);
        for (WebElement flightTimeElement : flightTimes) {
            String flightTimeText = flightTimeElement.getText();
            LocalTime flightTime = LocalTime.parse(flightTimeText);
            if (flightTime.isBefore(startTime) || flightTime.isAfter(endTime)) {
                System.out.println("Flight time is not within the desired range: " + flightTime);
            } else {
                System.out.println("Flight time is within the desired range: " + flightTime);
            }
        }
    }

    public void checkAsce(String airportName, String flightElements, String airlinesElement, String priceElement) {

        List<WebElement> flights = driver.findElements(By.xpath(flightElements));
        List<Integer> airlinesPriceList = new ArrayList<>();
        for (WebElement flight : flights) {
            WebElement airlines = flight.findElement(By.xpath("." + airlinesElement));
            String airport = airlines.getText();
            if (airport.contains(airportName)) {
                WebElement price = flight.findElement(By.xpath("." + priceElement));
                String priceText = price.getText().replace("TL", "").replace(".", "").trim();
                airlinesPriceList.add(Integer.parseInt(priceText));
            }
        }
        List<Integer> sortedList = new ArrayList<>(airlinesPriceList);
        Collections.sort(sortedList);
        boolean isAsce = airlinesPriceList.equals(sortedList);

        if (isAsce) {
            System.out.println(airportName + " prices are in ascending order");
        } else {
            System.out.println(airportName + " prices are not in ascending order.");
        }
    }
}
