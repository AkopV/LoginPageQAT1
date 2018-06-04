package com.vardanian.iua;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeIUA {

    private WebDriver driver;

    @FindBy(xpath = "(//a[contains(text(),'Создать письмо')])[2]")
    private WebElement createLetter;

    public HomeIUA(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewLetterPage goToCreateNewLetter() {
        createLetter.click();
        return new NewLetterPage(driver);
    }
}
