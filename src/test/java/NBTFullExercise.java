import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;
import java.util.*;

public class NBTFullExercise {

    // ==============================
    // ThreadLocal WebDriver management
    // ==============================
//    static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static void main(String[] args) throws IOException {

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "Galaxy S5");
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        WebDriver driver = new ChromeDriver(chromeOptions);
        // ==============================
        // Window sizing and maximize
        // ==============================
        driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1200, 800));

        // ==============================
        // Driver timeouts
        // ==============================
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));

        // ==============================
        // Navigation (get)
        // ==============================
        driver.get("https://navbharattimes.indiatimes.com/");

        // ==============================
        // Navigation (refresh, back, to)
        // ==============================
//        driver.navigate().refresh();
//        driver.navigate().to("https://navbharattimes.indiatimes.com/");
//        driver.navigate().back();
//        driver.navigate().forward();

        // ==============================
        // Locators (By.tagName)
        // ==============================
        WebElement body = driver.findElement(By.tagName("body"));

        // ==============================
        // Locators (By.cssSelector)
        // ==============================
        List<WebElement> links = driver.findElements(By.cssSelector("a"));

        // ==============================
        // Locators (By.xpath)
        // ==============================
        List<WebElement> images = driver.findElements(By.xpath("//img"));

        // ==============================
        // Element lookup (findElement, findElements)
        // ==============================
        System.out.println("Total Links: " + links.size());
        System.out.println("Total Images: " + images.size());

        if (links.size() > 0) {

            WebElement firstLink = links.get(0);

            // ==============================
            // Element state checks
            // ==============================
            System.out.println("Displayed: " + firstLink.isDisplayed());
            System.out.println("Enabled: " + firstLink.isEnabled());

            // ==============================
            // Element text
            // ==============================
            System.out.println("Link Text: " + firstLink.getText());

            // ==============================
            // Element attributes
            // ==============================
            System.out.println("Href: " + firstLink.getAttribute("href"));

            // ==============================
            // Element geometry
            // ==============================
            System.out.println("Location: " + firstLink.getLocation());
            System.out.println("Size: " + firstLink.getSize());

            // ==============================
            // Element interactions (click)
            // ==============================
            firstLink.click();
        }

        // ==============================
        // Explicit waits
        // ==============================
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("navbharattimes"));

        // ==============================
        // Wait for visibility
        // ==============================
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

        // ==============================
        // JavaScript execution
        // ==============================
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('JS Executed')");

        // ==============================
        // Scrolling via JS
        // ==============================
        js.executeScript("window.scrollTo(0, 500)");
        js.executeScript("window.scrollBy(0, 300)");
        js.executeScript("arguments[0].scrollIntoView(true);", body);

        // ==============================
        // JS click fallback
        // ==============================
        if (links.size() > 1) {
            js.executeScript("arguments[0].click();", links.get(1));
        }

        // ==============================
        // Windows/tabs handling
        // ==============================
        String parentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String win : allWindows) {
            if (!win.equals(parentWindow)) {
                driver.switchTo().window(win);
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);

        // ==============================
        // Actions API
        // ==============================
        Actions actions = new Actions(driver);
        actions.moveToElement(body).perform();

        // ==============================
        // Frames/iframes handling
        // ==============================
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        if (frames.size() > 0) {
            driver.switchTo().frame(frames.get(0));
            driver.switchTo().defaultContent();
        }

        // ==============================
        // Alerts handling (if present)
        // ==============================
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            System.out.println("No Alert Present");
        }

        // ==============================
        // Screenshots
        // ==============================
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(srcFile.toPath(), new File("NBT_Screenshot.png").toPath());

        // ==============================
        // PageFactory (POM init)
        // ==============================
        PageFactory.initElements(driver, NBTFullExercise.class);

        // ==============================
        // Quit
        // ==============================
        driver.quit();
    }
}
