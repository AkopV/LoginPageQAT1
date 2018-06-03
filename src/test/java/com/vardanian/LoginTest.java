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
    public void testSendMessage() throws  InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(5000);

        driver.findElement(By.linkText("Вход")).click();

        Thread.sleep(2000);

        driver.findElement(By.cssSelector("fieldset > p > input[name=\"login\"]")).sendKeys("Qatat1");
        driver.findElement(By.cssSelector("fieldset > p > input[name=\"pass\"]")).sendKeys("Qatat1zzz");
        Thread.sleep(5000);

        driver.findElement(By.cssSelector("form[name=\"lFloat\"] > input[type=\"submit\"]")).click();
        Thread.sleep(3000);

        String actualResult1 = driver.findElement(By.linkText("Привет, User")).getText();
        String expectedResult1 = "Привет, User";
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = driver.findElement(By.linkText("Почта")).getText();
        String expectedResult2 = "Почта";
        assertEquals(expectedResult2, actualResult2);

        driver.findElement(By.linkText("Почта")).click();
        Thread.sleep(3000);


        driver.findElement(By.xpath("(//a[contains(text(),'Создать письмо')])[2]")).click();
        driver.findElement(By.id("to")).clear();
        Thread.sleep(3000);


        driver.findElement(By.id("to")).clear();
        driver.findElement(By.id("to")).sendKeys("qwertyuiop@mailinator.com");

        driver.findElement(By.name("subject")).clear();
        driver.findElement(By.name("subject")).sendKeys("Send a letter from your mail service account to mailinator.com");

        driver.findElement(By.id("text")).click();
        driver.findElement(By.id("text")).clear();
        driver.findElement(By.id("text")).sendKeys("Test");

        driver.findElement(By.xpath("(//input[@name='send'])[3]")).click();
        Thread.sleep(5000);

        String actualResult3 = driver.findElement(By.xpath("//div/div/div[5]")).getText();
        String expectedResult3 = "Письмо успешно отправлено адресатам";
        assertEquals(expectedResult3, actualResult3);

        driver.findElement(By.xpath("//span[@onclick=\"Autoload.call('I_Popup.showMenu', this, 'accountSettingsPopup', {static: true})\"]")).click();
        Thread.sleep(3000);

        driver.findElement(By.linkText("Выйти")).click();
    }

    @Test
    public void tearDown(){
        driver.quit();
    }
}
