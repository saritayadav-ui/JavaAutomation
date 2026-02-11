package TOI;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Set;

public class Toiamp_verifyad {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(412, 768));

        driver.get("https://timesofindia.indiatimes.com/india/centre-asks-those-who-have-still-not-registered-their-waqf-properties-on-portal-to-hurry/amp_articleshow/125710467.cms");

        System.out.println("Current Url: " + driver.getCurrentUrl());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for ad iframe to load
        WebElement adIframe = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//div[contains(@class,'ampv3-ad')]//iframe")
                )
        );

        // Lead Image (hero image)
        WebElement leadImage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='lead-image']")
                )
        );

        // Get Ad container for position validation
        WebElement adContainer = driver.findElement(
                By.xpath("//div[contains(@class,'ampv3-ad')]")
        );

        // Validate display
        System.out.println("Ad Displayed: " + adContainer.isDisplayed());
        System.out.println("Lead Image Displayed: " + leadImage.isDisplayed());

        // Validate position
        int adY = adContainer.getLocation().getY();
        int imageY = leadImage.getLocation().getY();

        System.out.println("Ad Y position: " + adY);
        System.out.println("Lead Image Y position: " + imageY);

        if (adY < imageY) {
            System.out.println("PASS: Ad is above the lead image");
        } else {
            System.out.println("FAIL: Ad is NOT above the lead image");
        }

        // Switch to iframe to capture ad URL
        driver.switchTo().frame(adIframe);

        WebElement adLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("a"))
        );

        String fullHref = adLink.getAttribute("href");
        System.out.println("Full Ad Href: " + fullHref);

        // Extract adurl parameter
        String expectedLandingUrl = "";
        if (fullHref.contains("adurl=")) {
            String[] parts = fullHref.split("adurl=");
            expectedLandingUrl = URLDecoder.decode(parts[1], StandardCharsets.UTF_8);
        }

        System.out.println("Expected Landing URL: " + expectedLandingUrl);

        // Store main window
        String mainWindow = driver.getWindowHandle();

        // Click the ad
        adLink.click();
        Thread.sleep(2000);

        driver.switchTo().defaultContent();

        // Wait for new tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Wait for redirect to complete
        wait.until(ExpectedConditions.urlContains("http"));

        String actualLandingUrl = driver.getCurrentUrl();
        System.out.println("Actual Landing URL: " + actualLandingUrl);

        // Validate by domain (more reliable than full match)
        if (!expectedLandingUrl.isEmpty() &&
                actualLandingUrl.contains(getDomain(expectedLandingUrl))) {
            System.out.println("PASS: Ad landing URL matches expected domain");
        } else {
            System.out.println("FAIL: Ad landing URL does NOT match expected domain");
        }

        driver.quit();
    }

    // Helper method to extract domain
    public static String getDomain(String url) {
        try {
            return new java.net.URL(url).getHost();
        } catch (Exception e) {
            return "";
        }
    }
}
