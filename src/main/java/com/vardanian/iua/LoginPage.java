package com.vardanian.iua;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    private WebDriver driver;

    @FindBy(linkText = "Вход")
    private WebElement linkLogIn;

    @FindBy(css = "fieldset > p > input[name=\"login\"]")
    private WebElement loginField;

    @FindBy(css = "fieldset > p > input[name=\"pass\"]")
    private WebElement passwordField;

    @FindBy(css = "form[name=\"lFloat\"] > input[type=\"submit\"]")
    private WebElement loginButton;

    @FindBy(linkText = "Почта")
    private WebElement linkEmail;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void loginToIUa(String baseUrl, String login, String password) throws InterruptedException {
        driver.get(baseUrl);
        linkLogIn.click();
        Thread.sleep(2000);
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public HomeIUA sendEmail(String baseUrl, String login, String password) throws InterruptedException {
        driver.get(baseUrl);
        linkLogIn.click();
        Thread.sleep(2000);
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
        loginButton.click();
        linkEmail.click();
        return new HomeIUA(driver);
    }
}