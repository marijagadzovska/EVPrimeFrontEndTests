package evprimefrontendtests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CreateUserLoginPage;
import pages.SidePanel;
import util.DateBuilder;

import static org.junit.Assert.*;

public class CreateUserLoginTests {

    private WebDriver driver;
    private SidePanel sidePanel;
    private CreateUserLoginPage createUserLoginPage;
    DateBuilder dateBuilder = new DateBuilder();

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        sidePanel = new SidePanel(driver);
        createUserLoginPage = new CreateUserLoginPage(driver);
        sidePanel.navigateTo("http://localhost:3000");
        sidePanel.clickMenuIcon();
        sidePanel.clickLoginButton();
    }

    @Test
    public void createUserTest() throws InterruptedException{
        createUserLoginPage.insertEmail("mail"+ dateBuilder.currentTime() +"@mail.com");
        createUserLoginPage.insertPassword("password123");

        createUserLoginPage.clickChangeStateButton();
        assertEquals("Create new user",createUserLoginPage.getTextFromFormTitle());
        createUserLoginPage.clickGoButton();

        sidePanel.clickLoginButton();
        assertEquals("Log in",createUserLoginPage.getTextFromFormTitle());

        createUserLoginPage.clickGoButton();
        Thread.sleep(1500);

        assertFalse(sidePanel.isLoginButtonDisplayed());
        assertEquals("Log out",sidePanel.getTextFromLogoutButton());
    }

    @Test
    public void invalidEmailOrPasswordErrorMessage() throws InterruptedException{
        createUserLoginPage.insertEmail("emailuser@mail.com");
        createUserLoginPage.clickGoButton();
        Thread.sleep(3000);
        assertEquals("Invalid email or password entered.",createUserLoginPage.returnErrorList().get(0).getText());
        assertEquals("Invalid credentials.",createUserLoginPage.getTextFromAuthentication());
    }

    @Test
    public void invalidPasswordAndFailedAuthenticationTest() throws InterruptedException{
        createUserLoginPage.insertEmail("mail"+ dateBuilder.currentTime() +"@mail.com");
        createUserLoginPage.insertPassword("123");
        Thread.sleep(1500);

        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();
        Thread.sleep(1500);

        assertEquals("Invalid password. Must be at least 6 characters long.",createUserLoginPage.returnErrorList().get(0).getText());
        assertEquals("User signup failed due to validation errors.",createUserLoginPage.getTextFromAuthentication());

        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();
        assertEquals("Authentication failed.",createUserLoginPage.getTextFromAuthentication());
    }

    @Test
    public void emailAlreadyExists() throws InterruptedException {
        createUserLoginPage.insertEmail("emailuser@mail.com");
        createUserLoginPage.insertPassword("123");
        Thread.sleep(1500);

        createUserLoginPage.clickChangeStateButton();
        createUserLoginPage.clickGoButton();
        assertEquals("Email exists already.\nInvalid password. Must be at least 6 characters long.",createUserLoginPage.returnErrorList().get(0).getText());
        assertEquals("User signup failed due to validation errors.",createUserLoginPage.getTextFromAuthentication());
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
