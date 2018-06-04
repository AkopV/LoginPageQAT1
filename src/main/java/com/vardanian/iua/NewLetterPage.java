package com.vardanian.iua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewLetterPage {

    public void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private WebDriver driver;

    @FindBy(id = "to")
    WebElement sendTo;

    @FindBy(name = "subject")
    WebElement subject;

    @FindBy(id = "text")
    WebElement letterContent;

    @FindBy(xpath = "(//input[@name='send'])[3]")
    WebElement sendButton;

    public NewLetterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterPage sendNewMessage(String email, String letterSubject, String letterText) {
        sendTo.sendKeys(email);
        subject.sendKeys(letterSubject);
        letterContent.sendKeys(letterText);
        sendButton.click();
        return new NewLetterPage(driver);
    }
}