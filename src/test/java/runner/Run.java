package runner;

import methods.BaseMethods;
import org.junit.jupiter.api.Test;
import steps.FlightHomePage;
import steps.FlightSearchPage;

public class Run {

    @Test
    public void case01() throws InterruptedException  {

            BaseMethods baseMethods = new BaseMethods();
            FlightHomePage flightHomePage = new FlightHomePage();
            FlightSearchPage flightSearchPage = new FlightSearchPage();

            baseMethods.setUp();
            baseMethods.goToUrl("https://www.enuygun.com/");
            baseMethods.sendKey(FlightHomePage.originInput, "İstanbul");
            baseMethods.sendKey(FlightHomePage.destinationInput, "Ankara");
            flightHomePage.selectDate("3");
            flightHomePage.selectReturnDate("6");
            baseMethods.clickToElement(FlightHomePage.submitButton);
            baseMethods.clickToElement(FlightHomePage.departureTime);
            flightSearchPage.setSliderValue(FlightSearchPage.leftSlider, 100);
            baseMethods.waitBySeconds(5);
            flightSearchPage.setSliderValue(FlightSearchPage.rightSlider, -60);
            baseMethods.waitBySeconds(5);
            flightSearchPage.checkFlightTimes("10:00","18:00");
            baseMethods.waitBySeconds(5);
            baseMethods.closeDriver();
    }

    @Test
    public void case02() throws InterruptedException {

        BaseMethods baseMethods = new BaseMethods();
        FlightHomePage flightHomePage = new FlightHomePage();
        FlightSearchPage flightSearchPage = new FlightSearchPage();

        baseMethods.setUp();
        baseMethods.goToUrl("https://www.enuygun.com/");
        baseMethods.sendKey(FlightHomePage.originInput, "İstanbul");
        baseMethods.sendKey(FlightHomePage.destinationInput, "Ankara");
        flightHomePage.selectDate("3");
        flightHomePage.selectReturnDate("6");
        baseMethods.clickToElement(FlightHomePage.submitButton);
        baseMethods.clickToElement(FlightHomePage.departureTime);
        flightSearchPage.setSliderValue(FlightSearchPage.leftSlider, 100);
        baseMethods.waitBySeconds(5);
        flightSearchPage.setSliderValue(FlightSearchPage.rightSlider, -60);
        baseMethods.waitBySeconds(5);
        flightSearchPage.checkAsce("Türk Hava Yolları", "flightDepartureTime", "summaryAirline","flightSummaryPrice");
        baseMethods.closeDriver();
    }
}

