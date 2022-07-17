package Application;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utilities {
    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String to1000Px(String url) {
        int index = url.indexOf("_AC_") + 4;
        while (url.charAt(index) < '0' || url.charAt(index) > '9') {
            index++;
        }
        url = url.substring(0, index) + "1000" + "_.jpg";
        System.out.println(url);
        return url;
    }

    public static String getBaseUrl(String url) {
        String baseUrl = null;
        try {
            baseUrl = url.substring(0, url.indexOf("/", 8));
        } catch (Exception e) {
            baseUrl = "about:blank";
            System.out.println("fail to get base url");
        }
        return baseUrl;
    }

    public static void swtichTab(WebDriver driver, String handle) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.switchTo().window(handle);
    }

    public static void openUrlWithNoCheck(String url, WebDriver driver) {
        try {
            driver.get(url);
        } catch (Exception e) {
            System.out.println("fail in openUrlWithnoCheck!" + url);
        }
    }

    public static boolean openUrlWithCheck(String url, WebDriver driver) {
        try {
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.get(url);
        } catch (org.openqa.selenium.TimeoutException te) {
            System.out.println("timeout openUrlWithCheck");
            return false;
        } catch (Exception e) {
            System.out.println("fail in openUrlWithCheck!");
            return false;
        }
        return true;
    }

    public static void openUrlNewTabWithNoCheck(String url, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open('" + url + "')");
    }

    public static void openUrlNewTabWithCheck(String url, WebDriver driver) {
        try {
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            ((JavascriptExecutor) driver).executeScript("window.open('" + url + "')");
        } catch (org.openqa.selenium.TimeoutException te) {
            System.out.println("timeout openUrlNewTabWithCheck");
        } catch (Exception e) {
            System.out.println("fail openUrlNewTabWithCheck");
        }
    }

    public static void goToEndPage(final WebDriver driver) {
        try {
            // driver.findElement(By.cssSelector("body")).sendKeys(Keys.END);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception ex) {
            System.out.println("fail to end page");
        }

    }
}
