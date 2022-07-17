package Application;

import Model.ListProduct;
import Model.Product;
import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.List;

public class getTitleandImg {
    public static Product getSonTitleAndImg(WebDriver driver, String url) {

        driver.get(url);
        driver.get(url);
        String title;
        int d = 0;
        while (true) {
            d++;
            if (d == 15) {
                d = 0;
                driver.get(url);
            }
            Utilities.sleep(1000);
            try {
                title = driver.findElement(By.id("title_feature_div")).getText();
                break;
            } catch (Exception e) {
            }
        }
        System.out.println(title);
        List<String> imgUrl = new ArrayList<>();
        List<WebElement> webElement = driver.findElements(By.xpath("//*[contains(@class,\"a-spacing-small item imageThumbnail a-declarative\")]"));

        String textElements = driver.findElement(By.xpath("//*[@id=\"imageBlock_feature_div\"]/script[1]")).getAttribute("innerHTML");
//        System.out.println(textElements);
//        Pattern pattern = Pattern.compile("(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])");

        while (textElements.contains("hiRes")) {
            //     Matcher matcher = pattern.matcher(textElements);
            int indexHires = textElements.indexOf("hiRes");
            //  matcher.find();
            //  String urlImg = matcher.group(1) + "://" + matcher.group(2) + matcher.group(3);
            String urlImg = textElements.substring(indexHires + 8, textElements.indexOf("\"", indexHires + 8));
            imgUrl.add(urlImg);
            System.out.println(urlImg);
            textElements = textElements.substring(textElements.indexOf("hiRes") + 6);
        }

        return new Product("", title, imgUrl);
    }

    public static ListProduct getFarTitleAndImg(WebDriver driver, String url, int ID) {
        driver.get(url);
        driver.get(url);
        int d = 0;
        String title;
        while (true) {
            d++;
            if (d == 15) {
                d = 0;
                driver.get(url);
            }
            Utilities.sleep(1000);
            try {
                title = driver.findElement(By.id("title_feature_div")).getText();
                break;
            } catch (Exception e) {
            }
        }
        List<String> imgUrl = new ArrayList<>();
//        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(@class,\"a-spacing-small item\")]//descendant::img"));
//        for (WebElement element : elements) {
//            String src = element.getAttribute("src");
//            imgUrl.add(Utilities.to1000Px(src));
//        }

        String textElements = driver.findElement(By.xpath("//*[@id=\"imageBlock_feature_div\"]/script[1]")).getAttribute("innerHTML");
//        System.out.println(textElements);
//        Pattern pattern = Pattern.compile("(http|ftp|https):\\/\\/([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:\\/~+#-]*[\\w@?^=%&\\/~+#-])");
        System.out.println(title);

        while (textElements.contains("hiRes")) {
            //     Matcher matcher = pattern.matcher(textElements);
            int indexHires = textElements.indexOf("hiRes");
            //  matcher.find();
            //  String urlImg = matcher.group(1) + "://" + matcher.group(2) + matcher.group(3);
            String urlImg = textElements.substring(indexHires + 8, textElements.indexOf("\"", indexHires + 8));
            imgUrl.add(urlImg);
            System.out.println(urlImg);
            textElements = textElements.substring(textElements.indexOf("hiRes") + 6);
        }

        ListProduct listProduct = new ListProduct(new Product(String.valueOf(ID), title, imgUrl));
        List<WebElement> sonProduct = driver.findElements(By.xpath("//*[@id=\"variation_color_name\"]//descendant::a"));
        List<String> urlListSonProduct = new ArrayList<>();
        for (WebElement element : sonProduct) {
            String href = element.getAttribute("href");
            urlListSonProduct.add(href);
        }
        for (String url1 : urlListSonProduct) {
            listProduct.addProduct(getSonTitleAndImg(driver, url1));
        }
        return listProduct;
    }
}
