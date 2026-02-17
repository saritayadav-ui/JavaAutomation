package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NBTExcercise {
    public static void main(String[] args) {
        //WebDriver setup per browser
        WebDriver driver = new ChromeDriver();

        //Browser options (ChromeOptions, FirefoxOptions)
        ChromeOptions options = new ChromeOptions();

        //Headless mode and VM flags
        options.addArguments("--headless=new");

        //MobileEmulation
//        Map<String, String> mobileEmulation = new HashMap<>();
//        mobileEmulation.put("deviceName", "Galaxy S5");
//        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        // Window sizing and maximize
        driver.manage().window().setSize(new Dimension(1920, 1080));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        // Driver timeouts
//        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        try {
            // Navigate to main page
            driver.get("https://navbharattimes.indiatimes.com/");

        // Navigation (refresh, back, to)
//        driver.navigate().refresh();
//        driver.navigate().to("https://navbharattimes.indiatimes.com/");
//        driver.navigate().back();
//        driver.navigate().forward();

        //Locators (By.cssSelector, By.xpath, By.tagName)
//        driver.findElement(By.cssSelector("li._itm a[href*='lifestyle/articlelist']")).click();
//        driver.findElement(By.xpath("//a[contains(text(),'लाइफस्टाइल')]")).click();
//        driver.findElement(By.tagName("a")).click();
        // Click लाइफस्टाइल

        driver.manage().window().maximize();

        // Click लाइफस्टाइल link safely
        WebElement lifestyleLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'लाइफस्टाइल')]")
        ));

        // Scroll into view and click (JS fallback)
        js.executeScript("arguments[0].scrollIntoView(true);", lifestyleLink);
        try {
            lifestyleLink.click();
        } catch (ElementNotInteractableException e) {
            js.executeScript("arguments[0].click();", lifestyleLink);
        }

        // Wait for heading to be visible
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h1[contains(.,'लाइफस्टाइल')]")
        ));
        System.out.println("Heading: " + heading.getText());
        System.out.println("Heading is displayed: " + heading.isDisplayed());
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File screenshot = ts.getScreenshotAs(OutputType.FILE);

                // Save it to your project folder
                Path dest = Path.of("NBT_Lifestyle_Heading.png");
                Files.copy(screenshot.toPath(), dest);

                System.out.println("Screenshot saved at: " + dest.toAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }

        // Example: find multiple articles
        List<WebElement> articles = driver.findElements(By.cssSelector("li._v a.content"));
        System.out.println("Number of articles found: " + articles.size());

        if (!articles.isEmpty()) {
            WebElement firstArticle = articles.get(0);

            // Get article info
            System.out.println("Article href: " + firstArticle.getAttribute("href"));
            System.out.println("Article text: " + firstArticle.getText());
            System.out.println("Location: " + firstArticle.getLocation());
            System.out.println("Size: " + firstArticle.getSize());

            // Scroll and JS click
            js.executeScript("arguments[0].scrollIntoView(true);", firstArticle);
            try {
                firstArticle.click();
            } catch (ElementNotInteractableException e) {
                js.executeScript("arguments[0].click();", firstArticle);
            }

            // Wait for URL change
            wait.until(ExpectedConditions.urlContains("articleshow"));
            System.out.println("Navigated to: " + driver.getCurrentUrl());

            // Example: screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File("article_screenshot.png"));
            System.out.println("Screenshot saved");
        }

        // Example: open new tab and switch
        js.executeScript("window.open('https://www.indiatimes.com','_blank');");
        Set<String> windowHandles = driver.getWindowHandles();
        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            System.out.println("Switched to window: " + driver.getTitle());
        }

        // Switch back to first window
        driver.switchTo().window(driver.getWindowHandles().iterator().next());

        // Example: Actions API (hover)
        WebElement firstNavItem = driver.findElement(By.cssSelector("li._itm a"));
        actions.moveToElement(firstNavItem).perform();

        // Example: alert handling (just triggering an alert for demo)
        js.executeScript("alert('Selenium test alert');");
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        System.out.println("Alert text: " + alert.getText());
        alert.accept();

    } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
        driver.quit();
    }
}
}