package Application;

import Model.ListProduct;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Controller {

    private static WebDriver driver = null;
    private static int checkStatusDriver = 0;
    private static int d = 0;
    public static boolean status = true;

    public static void setListDriverClosed() {
        checkStatusDriver = 0;
    }

    public static void quitListDriver() {
        if (driver != null) {
            PreListDriver.quitDriver(driver, checkStatusDriver);
        }
        setListDriverClosed();
    }

    public static void runShop(int soLuong, String shopLink, String nameExcel) {
        status = true;
        d = 0;
        HashMap images = new HashMap();
        images.put("images", 2);
        HashMap pre = new HashMap();
        pre.put("profile.default_content_setting_values", images);

        ChromeOptions options1 = new ChromeOptions();
        options1.addArguments("--disable-notifications");
        options1.addArguments("--disable-infobars");
        options1.addArguments("--disable-extensions");
        options1.addArguments("--disable-gpu");
        options1.addArguments("--disable-dev-shm-usage");
        options1.addArguments("--no-sandbox");
        options1.addArguments("--incognito");
        options1.addArguments("--headless");
        options1.setExperimentalOption("prefs", pre);

        quitListDriver();
        String userProfile = "C:\\profile";
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", pre);
        options.addArguments("user-data-dir=" + userProfile);
        try {
            driver = new ChromeDriver(options);
            checkStatusDriver = 1;
        } catch (SessionNotCreatedException e) {
            JOptionPane.showMessageDialog(null, "Vui long tat trinh duyet cu truoc khi chay chuong trinh");
        } catch (WebDriverException e) {
            JOptionPane.showMessageDialog(null, "Kiem tra chrome Driver");
        }

        List<String> listProductUrl = getUrlProduct.getUrl(driver, shopLink);

        quitListDriver();
        List<ListProduct> listProduct = new ArrayList<>();
        for (int i = 0; i < listProductUrl.size(); i++) {
            int finalI = i;
            if (!status) {
                return;
            }
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    d++;
                    WebDriver driver1 = new ChromeDriver(options1);
                    listProduct.add(getTitleandImg.getFarTitleAndImg(driver1, listProductUrl.get(finalI), finalI + 1));
                    driver1.quit();
                    d--;
                }
            });
            while (d >= soLuong) {
                Utilities.sleep(1000);
            }
            thread.start();
            Utilities.sleep(5000);
        }

        while (d > 0) {
            Utilities.sleep(1000);
        }

        try {
            WriteExcel.writeExcel(listProduct, nameExcel, nameExcel + ".xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            TrayIconDemo.displayTray("FindAdsAndPage", "Finish");
            JOptionPane.showMessageDialog(null, "Hoan thanh!");
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public static void runProduct(String productLink, String nameExcel) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                status = true;

                HashMap images = new HashMap();
                images.put("images", 2);
                HashMap pre = new HashMap();
                pre.put("profile.default_content_setting_values", images);
                ChromeOptions options1 = new ChromeOptions();
                options1.addArguments("--disable-notifications");
                options1.addArguments("--disable-infobars");
                options1.addArguments("--disable-extensions");
                options1.addArguments("--disable-gpu");
                options1.addArguments("--disable-dev-shm-usage");
                options1.addArguments("--no-sandbox");
                //   options1.addArguments("--headless");
                options1.setExperimentalOption("prefs", pre);

                final int[] checkStatusDriver1 = {0};
                List<ListProduct> listProduct = new ArrayList<>();
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WebDriver driver1 = new ChromeDriver(options1);
                        listProduct.add(getTitleandImg.getFarTitleAndImg(driver1, productLink, 1));
                        driver1.quit();
                        checkStatusDriver1[0] = 1;
                    }
                });
                thread.start();
                while (checkStatusDriver1[0] == 0) {
                    Utilities.sleep(1000);
                }

                try {
                    WriteExcel.writeExcel(listProduct, nameExcel, nameExcel + ".xlsx");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    TrayIconDemo.displayTray("GETLINKPRODUCT", "Finish");
                    JOptionPane.showMessageDialog(null, "Hoan thanh!");
                } catch (
                        AWTException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
