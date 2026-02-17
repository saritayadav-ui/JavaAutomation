package TOI;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Toiamp_verifyad1 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(412, 768));

        driver.get("https://timesofindia.indiatimes.com/india/centre-asks-those-who-have-still-not-registered-their-waqf-properties-on-portal-to-hurry/amp_articleshow/125710467.cms");
        System.out.println("Current Url: " +driver.getCurrentUrl());

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement adElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[@class='ampv3-ad atf']//amp-ad[@type='doubleclick']")
                )
        );


        // Lead Image (hero image)
        WebElement leadImage = driver.findElement(
                By.xpath("//div[@class=\"lead-image\"]")
        );
        Thread.sleep(3000);
        adElement.click();
        Thread.sleep(3000);
        String adUrl = driver.getCurrentUrl();
        System.out.println("Ad url: " + adUrl);

        Thread.sleep(3000);

        // Check if ad is displayed
        if (adElement.isDisplayed()) {
            System.out.println("Ad is displayed");
        } else {
            System.out.println("Ad is NOT displayed");
        }
        if (leadImage.isDisplayed()) {
            System.out.println("Lead image is displayed");
        } else {
            System.out.println("Lead image is NOT displayed");
        }

        // Get ad dimensions
        int height = adElement.getSize().getHeight();
        int width = adElement.getSize().getWidth();
        System.out.println("Ad Height: " + height);
        System.out.println("Ad Width: " + width);

        // Get Lead image Y locations
        int adY = adElement.getLocation().getY();
        int imageY = leadImage.getLocation().getY();

        System.out.println("Ad Y position: " + adY);
        System.out.println("Lead Image Y position: " + imageY);

        // Verify ad is above lead image
        if (adY < imageY) {
            System.out.println("PASS: Ad is above the lead image");
        } else {
            System.out.println("FAIL: Ad is NOT above the lead image");
        }

        driver.quit();
    }
}
