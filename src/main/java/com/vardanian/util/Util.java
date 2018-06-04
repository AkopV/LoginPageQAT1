package com.vardanian.util;

public class Util {

    public static final String BASE_URL_1 = "http://www.i.ua/";
    public static final String BASE_URL_2 = "https://www.mailinator.com/";
    public static final String EMAIL_ADDRESS = "niykogonettakogoemail@mailinator.com";

    public static void chooseOS() {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac"))
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/driver/chromedriver");
        else
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
    }
}
