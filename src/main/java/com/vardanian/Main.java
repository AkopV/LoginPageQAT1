package com.vardanian;

import com.vardanian.iua.CheckEmail;
import com.vardanian.iua.LoginPage;
import com.vardanian.iua.Logout;
import com.vardanian.util.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Util.chooseOS();

        WebDriver driver = new ChromeDriver();

        String textLetter = "Test";

               new LoginPage(driver)
                .loginToIUa(Util.BASE_URL_1,"Qatat1", "Qatat1zzz");

        String actualResult1 = driver.findElement(By.linkText("Привет, User")).getText();
        String expectedResult1 = "Привет, User";
        assertEquals(expectedResult1, actualResult1);

        String actualResult2 = driver.findElement(By.linkText("Выход")).getText();
        String expectedResult2 = "Выход";
        assertEquals(expectedResult2, actualResult2);

        Thread.sleep(3000);

        new Logout(driver)
                .logoutFromIUa();

        Thread.sleep(3000);

        new LoginPage(driver)
                .loginToIUa(Util.BASE_URL_1,"user", "pass");

        String actualResult = driver.findElement(By.cssSelector("div.content.clear")).getText();
        String expectedResult = "Неверный логин или пароль";

        assertEquals(expectedResult, actualResult);

        new LoginPage(driver)
                .sendEmail(Util.BASE_URL_1,"Qatat1", "Qatat1zzz")
                .goToCreateNewLetter()
                .sendNewMessage(Util.EMAIL_ADDRESS,
                        "Send a letter from your mail service account to mailinator.com", textLetter);

        Thread.sleep(5000);

        String actualResult3 = driver.findElement(By.xpath("//div/div/div[5]")).getText();
        String expectedResult3 = "Письмо успешно отправлено адресатам";
        assertEquals(expectedResult3, actualResult3);

        new Logout(driver)
                .logoutFromEmail();

        new CheckEmail(driver)
                .checkEmail(Util.BASE_URL_2, Util.EMAIL_ADDRESS);
        Thread.sleep(2000);

        String actualResult4 = driver.findElement(By.xpath("//*[@id=\"row_niykogonettakogoemail-1528137011-2087\"]/div/div[3]")).getText();
        String expectedResult4 = "User";
        assertEquals(expectedResult4, actualResult4);

        String actualResult5 = driver.findElement(By.xpath("//*[@id=\"row_niykogonettakogoemail-1528137011-2087\"]/div/div[4]")).getText();
        String expectedResult5 = "Send a letter from your mail service account to mailinator.com";
        assertEquals(expectedResult5, actualResult5);

        Thread.sleep(2000);

        new CheckEmail(driver)
        .openEmail(Util.BASE_URL_2, Util.EMAIL_ADDRESS);

        Thread.sleep(2000);

        String actualResult6 = driver.findElement(By.xpath("//*[@id=\"msgpane\"]/div[1]/div[1]")).getText();
        String expectedResult6 = "Send a letter from your mail service account to mailinator.com";
        assertEquals(expectedResult6, actualResult6);

        Thread.sleep(4000);

        driver.close();
    }
}