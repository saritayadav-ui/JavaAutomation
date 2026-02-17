package SeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BrokenImage {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://the-internet.herokuapp.com/broken_images");
            driver.manage().window().maximize();

            // Find all images on the page
            List<WebElement> images = driver.findElements(By.tagName("img"));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            int brokenCount = 0;

            for (WebElement img : images) {
                // Check if image is loaded using naturalWidth
                Boolean imageLoaded = (Boolean) js.executeScript(
                        "return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                        img);

                String src = img.getAttribute("src");

                if (!imageLoaded) {
                    System.out.println("❌ Broken image found: " + src);
                    brokenCount++;
                } else {
                    System.out.println("✅ Image loaded: " + src);
                }
            }

            System.out.println("Total broken images: " + brokenCount);

        } finally {
            driver.quit();
        }
    }
}

