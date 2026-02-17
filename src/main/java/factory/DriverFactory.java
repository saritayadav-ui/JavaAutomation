package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.PageLoadStrategy;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void initDriver(String browser) {

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            // ✅ Better for heavy websites
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);

            options.addArguments("--disable-notifications");
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");

            tlDriver.set(new ChromeDriver(options));

        } else if (browser.equalsIgnoreCase("firefox")) {

            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");

            tlDriver.set(new FirefoxDriver(options));
        }

        // ===== Window sizing =====
        getDriver().manage().window().maximize();
        getDriver().manage().window()
                .setSize(new org.openqa.selenium.Dimension(1280, 800));

        // ===== Timeouts =====
        getDriver().manage().timeouts()
                .pageLoadTimeout(Duration.ofSeconds(120));

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        getDriver().manage().timeouts()
                .scriptTimeout(Duration.ofSeconds(30));
    }

    public static void quitDriver() {
        getDriver().quit();
        tlDriver.remove();
    }
}
