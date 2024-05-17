package evprimefrontendtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pages.ContactPage;
import pages.SidePanel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ContactTests {
    private WebDriver driver;
    private ContactPage contactPage;
    private SidePanel sidePanel;

    @Before
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(2000);
        contactPage = new ContactPage(driver);
        sidePanel = new SidePanel(driver);
        sidePanel.navigateTo("http://localhost:3000");
        sidePanel.clickMenuIcon();
    }

    @Test
    public void contactPageValidationTest() throws InterruptedException {
        Thread.sleep(1500);
        sidePanel.clickContactsIcon();
        Thread.sleep(2000);
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("document.body.style.zoom = '90%'");
        Thread.sleep(2000);

        assertTrue(contactPage.isContactPageTitleDisplayed());
        assertEquals("Want to reach out?", contactPage.getTitleText());

        String htmlContentContactElement = contactPage.setupHtmlContent();
        String htmlContentContactInformation = contactPage.setupHtmlContent2();

        String[] contactElementTexts = contactPage.extractTextFromContactPageElements(htmlContentContactElement, "MuiTypography-root");
        String[] contactInformationTexts = contactPage.extractTextFromContactPageElements(htmlContentContactInformation, "MuiTypography-root");

        assertEquals("Address" + " " + "Rampo Lefkata 1" , contactElementTexts[0] + " " + contactInformationTexts[0]);
        assertEquals("Email" + " " + "ev@rampo.com" , contactElementTexts[1] + " " + contactInformationTexts[1]);
        assertEquals("Phone Number" + " " + "+389 75 500 000", contactElementTexts[2] + " " + contactInformationTexts[2]);
        assertEquals("SEND",contactPage.getSendButtonText());
        assertEquals("#304ffe",contactPage.getSendButtonColor());
        assertEquals("\"Josefin Sans\"",contactPage.getSendButtonFontType());
        assertEquals("14px",contactPage.getSendButtonFontSize());
    }

//    @Test
//    public void contactPageTest() throws InterruptedException {
//        Thread.sleep(1500);
//        sidePanel.clickContactsIcon();
//        contactPage.insertUser("John Smith");
//        contactPage.insertEmail("john.smith@example.com");
//        contactPage.insertMessage("Just wanted to drop a quick note to say thank you for providing such an amazing event creation platform.");
//        assertEquals("SEND", contactPage.getSendButtonText());
//        contactPage.clickSendButton();
//    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
