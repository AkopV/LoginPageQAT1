package com.vardanian.iua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logout {

    private WebDriver driver;

    @FindBy(linkText = "Выход")
    private WebElement linkLogoutFromIUa;

    @FindBy(xpath = "//span[@onclick=\"Autoload.call('I_Popup.showMenu', this, " +
            "'accountSettingsPopup', {static: true})\"]")
    private WebElement elementMenu;

    @FindBy(linkText = "Выйти")
    private WebElement linkLogoutFromEmail;

    public Logout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logoutFromIUa() {
        linkLogoutFromIUa.click();
    }

    public void logoutFromEmail() throws InterruptedException {
        elementMenu.click();
        Thread.sleep(2000);
        linkLogoutFromEmail.click();
    }
}