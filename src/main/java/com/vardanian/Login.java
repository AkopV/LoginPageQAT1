package com.vardanian;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class Login {

    private WebDriver driver;
    public static String BASE_URL = "http://www.i.ua/";

    public void loginSuccess(String baseUrl) throws InterruptedException {
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

        String actualResult2 = driver.findElement(By.linkText("Выход")).getText();
        String expectedResult2 = "Выход";
        assertEquals(expectedResult2, actualResult2);

        logout();
    }

    public void wrongLoginOrPassword(String baseUrl) throws InterruptedException {
        driver.get(baseUrl);
        Thread.sleep(5000);

        driver.findElement(By.linkText("Вход")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("fieldset > p > input[name=\"login\"]")).sendKeys("login");
        driver.findElement(By.cssSelector("fieldset > p > input[name=\"pass\"]")).sendKeys("wrong");

        Thread.sleep(5000);
        driver.findElement(By.cssSelector("form[name=\"lFloat\"] > input[type=\"submit\"]")).click();

        String actualResult = driver.findElement(By.cssSelector("div.content.clear")).getText();
        String expectedResult = "Неверный логин или пароль";

        assertEquals(expectedResult, actualResult);
    }

    public void chooseOS() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac"))
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
        else System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
    }

    public void logout() {
        driver.findElement(By.linkText("Выход")).click();
    }

    public static void main(String[] args) throws InterruptedException {

        Login login = new Login();
        login.chooseOS();

        login.driver = new ChromeDriver();

        login.loginSuccess(BASE_URL);
        Thread.sleep(3000);

        login.wrongLoginOrPassword(BASE_URL);
        Thread.sleep(3000);

        login.driver.quit();
    }
}