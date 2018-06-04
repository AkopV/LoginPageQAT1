package com.vardanian.iua;

import com.vardanian.util.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckEmail {

    private WebDriver driver;
    @FindBy(id = "inboxfield")
    private WebElement inboxFiled;

    @FindBy(xpath = "(//button[@type='button'])[2]")
    private WebElement btnGo;

    @FindBy(xpath = "//*[@id=\"row_niykogonettakogoemail-1528137011-2087\"]/div/div[4]")
    private WebElement linkOpenMessage;

    public CheckEmail(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkEmail(String baseUrl, String email) throws InterruptedException {
        driver.get(baseUrl);
        inboxFiled.sendKeys(email);
        btnGo.click();
    }

    public void openEmail(String baseUrl, String email) throws InterruptedException {
        driver.get(baseUrl);
        inboxFiled.sendKeys(email);
        btnGo.click();
        linkOpenMessage.click();
    }
}