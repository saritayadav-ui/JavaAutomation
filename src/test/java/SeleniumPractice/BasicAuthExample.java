package SeleniumPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasicAuthExample {

    public static void main(String[] args) throws InterruptedException {


        WebDriver driver = new ChromeDriver();

        // Username and Password
        String username = "admin";
        String password = "admin";
        Thread.sleep(1000);

        // Pass credentials in URL
        String url = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";

        driver.get(url);

        // Optional: maximize window
        driver.manage().window().maximize();

        System.out.println("Login attempted successfully.");
        // Locate success message
        WebElement message = driver.findElement(By.cssSelector("div.example p"));

        String actualText = message.getText();
        String expectedText = "Congratulations! You must have the proper credentials.";

        // Verify text
        if (actualText.equals(expectedText)) {
            System.out.println("✅ Test Passed - Text Verified Successfully");
        } else {
            System.out.println("❌ Test Failed");
            System.out.println("Actual Text: " + actualText);
        }

        driver.quit();
    }
}

