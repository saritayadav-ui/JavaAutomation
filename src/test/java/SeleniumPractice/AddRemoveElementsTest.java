package SeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddRemoveElementsTest {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

            // Maximize window
            driver.manage().window().maximize();

            Thread.sleep(1000);

            // Click "Add Element" button
            WebElement addButton = driver.findElement(By.xpath("//button[text()='Add Element']"));
            addButton.click();
            System.out.println("Add operation completed successfully.");

            Thread.sleep(1000);

            // Locate "Delete" button
            WebElement deleteButton = driver.findElement(By.xpath("//button[text()='Delete']"));
            deleteButton.click();

            System.out.println("Remove operation completed successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser
            driver.quit();
        }
    }
}
