package com.vardanian;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;
    private String baseUrl;
    private String os;

    @Before
    public void setUp(){
         os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac"))
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
        else System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");

        driver = new ChromeDriver();
        baseUrl = "http://www.i.ua/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testSuccessLoginRu() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(3000);

        driver.findElement(By.linkText("Вход")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("fieldset > p > input[name=\"login\"]")).sendKeys("Qatat1");
        driver.findElement(By.cssSelector("fieldset > p > input[name=\"pass\"]")).sendKeys("Qatat1zzz");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("form[name=\"lFloat\"] > input[type=\"submit\"]")).click();

        String actualResult1 = driver.findElement(By.linkText("Привет, User")).getText();
        String expectedResult1 = "Привет, User";
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = driver.findElement(By.linkText("Выход")).getText();
        String expectedResult2 = "Выход";
        assertEquals(expectedResult2, actualResult2);

        Thread.sleep(3000);
    }

    @Test
    public void testWrongLoginOrPassword() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(3000);
        driver.findElement(By.linkText("Вход")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("fieldset > p > input[name=\"login\"]")).sendKeys("login");
        driver.findElement(By.cssSelector("fieldset > p > input[name=\"pass\"]")).sendKeys("wrong");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("form[name=\"lFloat\"] > input[type=\"submit\"]")).click();

        String actualResult = driver.findElement(By.cssSelector("div.content.clear")).getText();
        String expectedResult = "Неверный логин или пароль";

        assertEquals(expectedResult, actualResult);

        Thread.sleep(3000);
    }

    @Test
    public void tearDown(){
        driver.quit();
    }
}
