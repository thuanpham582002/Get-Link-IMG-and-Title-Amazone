package Application;

import Model.ListProduct;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class getUrlProduct {
    public getUrlProduct() {
    }

    public static List<String> getUrl(WebDriver driver, String linkShop) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get(linkShop);
        List<String> listProductUrl = new ArrayList<>();


        break_all:
        while (true) {
            List<WebElement> webElement = driver.findElements(By.xpath("//*[contains(@class,\"a-link-normal s-no-outline\")]"));
            String eleNumOfProduct = driver.findElement(By.xpath("//*[@class=\"a-section a-spacing-small a-spacing-top-small\"]//span")).getText();
            System.out.println("Number of product: " + eleNumOfProduct);
            int firstNum = 0;
            int lastNum = 0;
            int numOfProduct = 0;
            try {
                String SfirstNum = eleNumOfProduct.substring(0, eleNumOfProduct.indexOf("-"));
                String SlastNum = (eleNumOfProduct.substring(eleNumOfProduct.indexOf("-") + 1, eleNumOfProduct.indexOf(" ")));
                SfirstNum = SfirstNum.replace(",", "");
                SlastNum = SlastNum.replace(",", "");
                firstNum = Integer.parseInt(SfirstNum);
                lastNum = Integer.parseInt(SlastNum);
                numOfProduct = lastNum - firstNum + 1;
            } catch (Exception e) {
            	e.printStackTrace();
                numOfProduct = Integer.parseInt(eleNumOfProduct.substring(0, eleNumOfProduct.indexOf(" ")));
            }


            System.out.println("firstNum: " + firstNum);
            System.out.println("lastNum: " + lastNum);
            System.out.println("numOfProduct: " + numOfProduct);

            break_loop:
            while (true) {
            	Utilities.sleep(1000);
                if (!Controller.status) {
                    return null;
                }
                try {
                    driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[1]/div/div/div/div[1]/span[1]"));
                    break break_all;
                } catch (Exception e) {
                    System.out.println("not finish");
                }

                if (webElement.size() >= numOfProduct) {
                    break break_loop;
                }
                webElement = driver.findElements(By.xpath("//*[contains(@class,\"a-link-normal s-no-outline\")]"));
                System.out.println("webElement.size(): " + webElement.size());
            }


            for (int i = 0; i < webElement.size(); i++) {
                String url = webElement.get(i).getAttribute("href");
                listProductUrl.add(url);
            }

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,\"s-pagination-item s-pagination-next\")]")));
            } catch (Exception e) {
                break break_all;
            }
            List<WebElement> nextPage = driver.findElements(By.xpath("//*[contains(@class,\"s-pagination-item s-pagination-next\")]"));

            try {
                driver.get(nextPage.get(0).getAttribute("href"));
                System.out.println("click");
            } catch (Exception e) {
                e.printStackTrace();
                break break_all;
            }
        }

        System.out.println(listProductUrl.size());
        return listProductUrl;
    }
}
