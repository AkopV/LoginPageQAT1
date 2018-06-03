package com.vardanian;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class CheckEmail {

    private WebDriver driver;
    public static String BASE_URL = "https://www.mailinator.com/";

    public void checkEmail(String baseUrl) throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(3000);

        driver.findElement(By.id("inboxfield")).sendKeys("niykogonettakogoemail@mailinator.com");

        driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
        Thread.sleep(2000);

        String actualResult1 = driver.findElement(By.xpath("//li[@id='row_niykogonettakogoemail-1528037066-2110']/div/div[3]")).getText();

        String expectedResult1 = "User";
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = driver.findElement(By.xpath("//li[@id='row_niykogonettakogoemail-1528037066-2110']/div/div[4]")).getText();
        String expectedResult2 = "Send a letter from your mail service account to mailinator.com";
        assertEquals(expectedResult2, actualResult2);

        driver.findElement(By.xpath("//li[@id='row_niykogonettakogoemail-1528037066-2110']/div/div[4]")).click();
        Thread.sleep(2000);

    }

    public static void main(String[] args) throws InterruptedException {

        CheckEmail email = new CheckEmail();
        Login login = new Login();
        login.chooseOS();

        email.driver = new ChromeDriver();

        email.checkEmail(BASE_URL);
        Thread.sleep(3000);

        email.driver.quit();
    }
}
