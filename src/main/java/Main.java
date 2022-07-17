import View.JobMainFrame;

public class Main {

    public static void main(String[] args) {
     //   WebDriverManager.chromedriver().setup();
                   System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        new JobMainFrame();
    }
}
