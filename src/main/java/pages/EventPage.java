package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;

public class EventPage extends BasePage{

    private By eventTitle = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div[1]/div[1]/div[1]/h2");
    private By eventTitleWhenLoggedIn = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[1]/div[1]/h2");
    private By eventDate = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div[1]/div[1]/div[2]/h6");
    private By eventLocation = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div[1]/div[1]/div[3]/h6");
    private By eventDescription = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div[2]/span");
    private By backToEventsButton = By.xpath("//*[@id=\"root\"]/div/div/main/div[2]/div[1]/div[2]/button/a");
    private By backToEventsButtonColor = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[2]/button[2]");
    private By editEventButton = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[2]/button[1]");
    private By eventTitleField = By.name("title");
    private By updateButton = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/form/div/button");
    private By deleteButton = By.xpath("//*[@id=\"root\"]/div/div[1]/main/div[2]/div[1]/div[1]/div[1]/button");
    private By confirmDeleteButton = By.xpath("/html/body/div[2]/div[3]/div/div/button[1]");

    public EventPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public CreateUserLoginPage newInstance(WebDriver driver) {
        return new CreateUserLoginPage(driver);
    }

    public String getEventTitle(){
        return getTextFromElement(eventTitle);
    }
    public String getEventDate(){
        return getTextFromElement(eventDate);
    }
    public String getEventLocation(){
        return getTextFromElement(eventLocation);
    }
    public String getEventDescription(){
        return getTextFromElement(eventDescription);
    }
    public void clickEditEventButton(){
        clickElement(editEventButton);
    }
    public void clickUpdateEventButton(){
        clickElement(updateButton);
    }
    public void clickBackToEventsButton(){
        clickElement(backToEventsButton);
    }
    public void clickDeleteEventButton(){
        clickElement(deleteButton);
    }
    public String getTextFromBackToEventsButton(){
        return getTextFromElement(backToEventsButton);
    }
    public String getTextFromDeleteEventButton(){
        return getTextFromElement(deleteButton);
    }
    public void eventTitleInsertText(String date){
        insertText(eventTitleField, date);
    }
    public String getEventTitleFieldWhenLoggedIn(){
        return getTextFromElement(eventTitleWhenLoggedIn);
    }
    public String getEditEventButtonText(){
        return getTextFromElement(editEventButton);
    }
    public String getUpdateButtonText(){
        return getTextFromElement(updateButton);
    }
    public void clickConfirmDeleteButton(){
        clickElement(confirmDeleteButton);
    }
    public String getUpdateButtonColor(){
        Color backgroundUpdateButtonColor = Color.fromString(driver.findElement(updateButton).getCssValue("background-color"));
        return backgroundUpdateButtonColor.asHex();
    }

    public String getUpdateButtonFontType() {
        return driver.findElement(updateButton).getCssValue("font-family");
    }

    public String getUpdateButtonFontSize() {
        return driver.findElement(updateButton).getCssValue("font-size");
    }

    public String getDeleteButtonColor(){
        Color backgroundDeleteButtonColor = Color.fromString(driver.findElement(deleteButton).getCssValue("background-color"));
        return backgroundDeleteButtonColor.asHex();
    }

    public String getDeleteButtonFontType() {
        return driver.findElement(deleteButton).getCssValue("font-family");
    }

    public String getDeleteButtonFontSize() {
        return driver.findElement(deleteButton).getCssValue("font-size");
    }

    public String getEditButtonColor(){
        Color backgroundEditButtonColor = Color.fromString(driver.findElement(editEventButton).getCssValue("background-color"));
        return backgroundEditButtonColor.asHex();
    }

    public String getEditButtonFontType() {
        return driver.findElement(editEventButton).getCssValue("font-family");
    }

    public String getEditButtonFontSize() {
        return driver.findElement(editEventButton).getCssValue("font-size");
    }

    public String getBackToEventsButtonColor(){
        Color backgroundBackToEventsButtonColor = Color.fromString(driver.findElement(backToEventsButtonColor).getCssValue("background-color"));
        return backgroundBackToEventsButtonColor.asHex();
    }

    public String getBackToEventsButtonFontType() {
        return driver.findElement(backToEventsButton).getCssValue("font-family");
    }

    public String getBackToEventsButtonFontSize() {
        return driver.findElement(backToEventsButton).getCssValue("font-size");
    }
}
