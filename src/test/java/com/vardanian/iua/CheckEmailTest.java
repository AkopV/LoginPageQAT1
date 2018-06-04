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

public class CheckEmailTest {

    private WebDriver driver;
    private String os;
    private CheckEmail checkEmail;

    @Before
    public void setUp(){
        os = System.getProperty("os.name").toLowerCase();
        if (os.contains("mac"))
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
        else System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckEmail() throws InterruptedException {
        checkEmail = PageFactory.initElements(driver, CheckEmail.class);
        checkEmail.checkEmail(Util.BASE_URL_2, Util.EMAIL_ADDRESS);

        String actualResult4 = driver.findElement(By.xpath("//*[@id=\"row_niykogonettakogoemail-1528137011-2087\"]/div/div[3]")).getText();
        String expectedResult4 = "User";
        assertEquals(expectedResult4, actualResult4);

        String actualResult5 = driver.findElement(By.xpath("//*[@id=\"row_niykogonettakogoemail-1528137011-2087\"]/div/div[4]")).getText();
        String expectedResult5 = "Send a letter from your mail service account to mailinator.com";
        assertEquals(expectedResult5, actualResult5);
    }

    @Test
    public void testOpenEmails() throws InterruptedException {
        checkEmail = PageFactory.initElements(driver, CheckEmail.class);
        checkEmail.openEmail(Util.BASE_URL_2, Util.EMAIL_ADDRESS);
    }


    @Test
    public void tearDown(){
        driver.quit();
    }
}
