package tests;

import base.BaseTest;
import pages.HomePage;
import utils.ScreenshotUtil;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class LifestyleFullExerciseTest extends BaseTest {

    @Test
    public void completeExercise() {

        // ===== Navigation get() =====
        driver.navigate().to("https://navbharattimes.indiatimes.com/");

        HomePage home = new HomePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        // ===== Explicit Wait =====
        wait.until(ExpectedConditions.visibilityOf(home.getLifestyleMenu()));

        // ===== Element state checks =====
        System.out.println(home.getLifestyleMenu().isDisplayed());
        System.out.println(home.getLifestyleMenu().isEnabled());

        // ===== Element text =====
        System.out.println(home.getLifestyleMenu().getText());

        // ===== Element attributes =====
        System.out.println(home.getLifestyleMenu().getAttribute("href"));

        // ===== Geometry =====
        System.out.println(home.getLifestyleMenu().getLocation());
        System.out.println(home.getLifestyleMenu().getSize());

        // ===== Hover =====
        home.hoverLifestyle();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("container_2354729")));

        List<WebElement> menus = home.getSubMenus();

        for (int i = 0; i < menus.size(); i++) {

            home.hoverLifestyle();
            menus = home.getSubMenus();

            WebElement element = menus.get(i);

            String text = element.getText();

            // ===== Scroll Methods =====
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,300)");
            js.executeScript("arguments[0].scrollIntoView(true);", element);

            try {
                element.click();
            } catch (Exception e) {
                // ===== JS click fallback =====
                js.executeScript("arguments[0].click();", element);
            }

            // ===== Wait for URL =====
            wait.until(ExpectedConditions.urlContains(".cms"));

            System.out.println("Navigated to: " + driver.getCurrentUrl());

            // ===== Screenshot =====
            ScreenshotUtil.capture(driver, text);

            // ===== Navigation back/refresh =====
            driver.navigate().back();
            driver.navigate().refresh();
        }

        // ===== Frames Handling =====
        try {
            driver.switchTo().frame(0);
            driver.switchTo().defaultContent();
        } catch (Exception ignored) {}

        // ===== Windows Handling =====
        String parent = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            if (!handle.equals(parent)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(parent);

        // ===== Alert Handling =====
        try {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception ignored) {}

        wait.until(ExpectedConditions.numberOfWindowsToBe(1));
    }
}
