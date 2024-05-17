package evprimefrontendtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateUserLoginPage;
import pages.EventPage;
import pages.EventsPage;
import pages.SidePanel;
import util.DateBuilder;
import util.DbClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class EventsTests {

    private WebDriver driver;
    private SidePanel sidePanel;
    private EventsPage eventsPage;
    private EventPage eventPage;
    private CreateUserLoginPage createUserLoginPage;
    DateBuilder dateBuilder = new DateBuilder();
    String email = "mail"+ dateBuilder.currentTime() + "@mail.com";
    String pass = "password123";

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        sidePanel = new SidePanel(driver);
        createUserLoginPage = new CreateUserLoginPage(driver);
        eventsPage = new EventsPage(driver);
        eventPage = new EventPage(driver);
        sidePanel.navigateTo("http://localhost:3000");
        sidePanel.clickMenuIcon();
        sidePanel.clickLoginButton();
    }

    @Test
    public void createAnEventTest()throws InterruptedException{
        createUserLoginPage.insertEmail("emailuser@mail.com");
        createUserLoginPage.insertPassword("123456");
        createUserLoginPage.clickGoButton();
        Thread.sleep(1500);

        sidePanel.clickChevronIcon();
        eventsPage.hoverOnPlusButton();
        Thread.sleep(1500);
        eventsPage.clickAddEventButton();

        eventsPage.insertEventTitle("Dallas Mavericks - LA Clippers");
        eventsPage.insertEventImage("https://s.hdnux.com/photos/01/37/20/53/24972014/3/960x0.webp");
        eventsPage.insertEventDate("05-03-2026");
        eventsPage.insertEventLocation("American Airlines Center, Dallas, Texas, USA");
        Thread.sleep(1500);
        eventsPage.insertEventDescription("Expect high-energy play and intense competition in this exciting basketball showdown.");
        eventsPage.clickCreateEventButton();
    }

    @Test
    public void eventValidationTest() throws InterruptedException {
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickLoginButton();
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickEventsButton();
        Thread.sleep(2000);
        eventsPage.clickOnEventByIndex(1);
        Thread.sleep(2000);
        sidePanel.clickChevronIcon();

        assertEquals("Dallas Mavericks - LA Clippers",eventPage.getEventTitle());
        assertEquals("05-03-2026", eventPage.getEventDate());
        assertEquals("American Airlines Center, Dallas, Texas, USA", eventPage.getEventLocation());
        assertEquals("Expect high-energy play and intense competition in this exciting basketball showdown.", eventPage.getEventDescription());
    }

    @Test
    public void eventValidationWhenUserIsNotLoggedInTest()throws InterruptedException{
        sidePanel.clickEventsButton();
        Thread.sleep(2000);
        eventsPage.clickOnEventByIndex(1);
        Thread.sleep(2000);

        assertEquals("Dallas Mavericks - LA Clippers",eventPage.getEventTitle());
        assertEquals("05-03-2026", eventPage.getEventDate());
        assertEquals("American Airlines Center, Dallas, Texas, USA", eventPage.getEventLocation());
        assertEquals("Expect high-energy play and intense competition in this exciting basketball showdown.", eventPage.getEventDescription());
        assertEquals("BACK TO EVENTS", eventPage.getTextFromBackToEventsButton());
    }

    @Test
    public void validateBackToEventsButtonTest() throws InterruptedException {
        sidePanel.clickEventsButton();
        Thread.sleep(2000);
        eventsPage.clickOnEventByIndex(1);
        Thread.sleep(1000);
        eventPage.clickBackToEventsButton();
        WebElement eventList = eventsPage.findEventList();
        assertTrue(eventList.isDisplayed());
    }

    @Test
    public void updateEventTest() throws InterruptedException {
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickLoginButton();
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickEventsButton();
        Thread.sleep(2000);
        sidePanel.clickChevronIcon();
        eventsPage.clickOnEventByIndex(3);
        Thread.sleep(2000);

        assertEquals("EDIT EVENT", eventPage.getEditEventButtonText());
        eventPage.clickEditEventButton();
        eventPage.eventTitleInsertText("-Basketball match");
        Thread.sleep(3000);

        assertEquals("UPDATE EVENT", eventPage.getUpdateButtonText());
        eventPage.clickUpdateEventButton();
        Thread.sleep(2000);
        assertEquals("Dallas Mavericks - LA Clippers-Basketball match", eventPage.getEventTitleFieldWhenLoggedIn());
    }

    @Test
    public void deleteAnEventTest() throws InterruptedException {
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickLoginButton();
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickEventsButton();
        Thread.sleep(2000);
        eventsPage.clickOnEventByIndex(2);
        Thread.sleep(2000);
        sidePanel.clickChevronIcon();

        eventPage.clickDeleteEventButton();
        assertEquals("DELETE EVENT",eventPage.getTextFromDeleteEventButton());
        DbClient.deleteEventById(2);
        eventPage.clickConfirmDeleteButton();
    }

    @Test
    public void cssPropertiesTest() throws InterruptedException {
        sidePanel.clickLoginButton();
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickLoginButton();
        createUserLoginPage.insertEmail(email);
        createUserLoginPage.insertPassword(pass);
        Thread.sleep(2000);
        createUserLoginPage.clickGoButton();

        Thread.sleep(2000);
        sidePanel.clickEventsButton();
        Thread.sleep(7000);
        sidePanel.clickChevronIcon();
        eventsPage.clickOnEventByIndex(1);

        assertEquals("#d32f2f",eventPage.getDeleteButtonColor());
        assertEquals("\"Josefin Sans\"",eventPage.getDeleteButtonFontType());
        assertEquals("14px",eventPage.getDeleteButtonFontSize());

        assertEquals("#304ffe",eventPage.getEditButtonColor());
        assertEquals("\"Josefin Sans\"",eventPage.getEditButtonFontType());
        assertEquals("14px",eventPage.getEditButtonFontSize());

        assertEquals("#9c27b0",eventPage.getBackToEventsButtonColor());
        assertEquals("\"Josefin Sans\"",eventPage.getBackToEventsButtonFontType());
        assertEquals("14px",eventPage.getBackToEventsButtonFontSize());

        eventPage.clickEditEventButton();
        assertEquals("#2e7d32",eventPage.getUpdateButtonColor());
        assertEquals("\"Josefin Sans\"",eventPage.getUpdateButtonFontType());
        assertEquals("14px",eventPage.getUpdateButtonFontSize());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
