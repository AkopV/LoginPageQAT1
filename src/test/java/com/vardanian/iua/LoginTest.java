package com.vardanian.iua;

import com.vardanian.util.Util;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;
    private String os;

    private HomeIUA homeIUA;
    private LoginPage login;

    @Before
    public void setUp(){
        os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac"))
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
        else System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testWrongLoginOrPassword() throws InterruptedException {
        login = PageFactory.initElements(driver, LoginPage.class);
        login.loginToIUa(Util.BASE_URL_1, "user", "pass");

        String actualResult = driver.findElement(By.cssSelector("div.content.clear")).getText();
        String expectedResult = "Неверный логин или пароль";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testSuccessLogin() throws InterruptedException {
        login = PageFactory.initElements(driver, LoginPage.class);
        login.loginToIUa(Util.BASE_URL_1,"Qatat1", "Qatat1zzz");

        String actualResult1 = driver.findElement(By.linkText("Привет, User")).getText();
        String expectedResult1 = "Привет, User";
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = driver.findElement(By.linkText("Выход")).getText();
        String expectedResult2 = "Выход";
        assertEquals(expectedResult2, actualResult2);
    }

    @Test
    public void testSendMessage() throws InterruptedException {
        login = PageFactory.initElements(driver, LoginPage.class);

        login.sendEmail(Util.BASE_URL_1,"Qatat1", "Qatat1zzz")
                .goToCreateNewLetter()
                .sendNewMessage(Util.EMAIL_ADDRESS,
                        "Send a letter from your mail service account to mailinator.com", "Test");

        Thread.sleep(3000);

        String actualResult3 = driver.findElement(By.xpath("//div/div/div[5]")).getText();
        String expectedResult3 = "Письмо успешно отправлено адресатам";
        assertEquals(expectedResult3, actualResult3);
    }

    @Test
    public void tearDown(){
        driver.quit();
    }
}