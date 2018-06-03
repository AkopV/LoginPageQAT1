package com.vardanian;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class CheckEmailTest {

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
        baseUrl = "https://www.mailinator.com/";
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @Test
    public void testCheckEmail() throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(3000);

        driver.findElement(By.id("inboxfield")).sendKeys("niykogonettakogoemail@mailinator.com");

        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        Thread.sleep(2000);

        String actualResult1 = driver.findElement(By.xpath("//li[@id='row_niykogonettakogoemail-1528064956-2087']/div/div[3]")).getText();
        String expectedResult1 = "User";
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = driver.findElement(By.cssSelector("div.all_message-min_text.all_message-min_text-3")).getText();
        String expectedResult2 = "Send a letter from your mail service account to mailinator.com";
        assertEquals(expectedResult2, actualResult2);

        driver.findElement(By.cssSelector("div.all_message-min_text.all_message-min_text-3")).click();

        // This part doesn't work
//        String actualResult3 = driver.findElement(By.cssSelector("body")).getText();
//        String expectedResult3 = "Test\\n\\n-- реклама -----------------------------------------------------------\\nМилі квартири \" +\n" +
//                "                \"чекають на милих господарів! \\nЧекають саме Вас! https://goo.gl/iptNm5";
//        assertEquals(expectedResult3, actualResult3);

        Thread.sleep(2000);
    }

    @Test
    public void tearDown(){
        driver.quit();
    }
}
