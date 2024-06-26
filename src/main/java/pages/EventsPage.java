package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EventsPage extends BasePage {

    private By plusButton = By.xpath("//*[@id=\"root\"]/div/div[2]/button");
    private By addEventButton = By.xpath("//*[@id=\"SpeedDial-actions\"]/button");
    private By eventTitle = By.name("title");
    private By eventImage = By.name("image");
    private By eventDate = By.name("date");
    private By eventLocation = By.name("location");
    private By eventDescription = By.id(":r7:");
    private By createEventButton = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/form/div/button");
    private String eventListXPath = "//*[@id='root']/div/div[1]/main/div[2]/ul";

    public EventsPage(WebDriver driver){
        super(driver);
    }
    @Override
    public CreateUserLoginPage newInstance(WebDriver driver){
        return new CreateUserLoginPage(driver);
    }

    public void hoverOnPlusButton(){
        hoverElement(plusButton);
    }
    public void clickAddEventButton(){
        clickElement(addEventButton);
    }
    public void insertEventTitle(String title){
        insertText(eventTitle,title);
    }
    public void insertEventImage(String image){
        insertText(eventImage,image);
    }
    public void insertEventDate(String date){
        insertText(eventDate,date);
    }
    public void insertEventLocation(String location){
        insertText(eventLocation,location);
    }
    public void insertEventDescription(String description){
        insertText(eventDescription,description);
    }
    public void clickCreateEventButton(){
        clickElement(createEventButton);
    }
    public void clickOnEventByIndex (int index) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement eventList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(eventListXPath)));

        WebElement eventItem = eventList.findElement(By.xpath("./li[" + index + "]"));

        wait.until(ExpectedConditions.elementToBeClickable(eventItem));

        eventItem.click();
    }
    public WebElement findEventList() {
        return driver.findElement(By.xpath(eventListXPath));
    }
}
