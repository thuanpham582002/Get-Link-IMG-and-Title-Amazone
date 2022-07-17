package Application;

import org.openqa.selenium.WebDriver;

@SuppressWarnings("unused")
public class PreListDriver {

    public PreListDriver() {}

    public static void quitDriver(final WebDriver driver, int check) {
        Thread t = new Thread(new Runnable() {
            // @Override
            public void run() {
                driver.quit();
            }
        });
        if (check == 1) {
            t.start();
        }
    }

    
}
