package SeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Checkbox {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        // Locate all checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));

        // Select first checkbox
        WebElement checkbox1 = checkboxes.get(0);

        // ✅ Check if not selected
        if (!checkbox1.isSelected()) {
            checkbox1.click();
            System.out.println("Checkbox 1 checked");
        }

        Thread.sleep(2000);

        // ✅ Uncheck if selected
        if (checkbox1.isSelected()) {
            checkbox1.click();
            System.out.println("Checkbox 1 unchecked");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
