package TOI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Popupexample {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/windows");

        // Create a Map to store window handles
        Map<String, String> windowMap = new HashMap<>();

        // Store parent window handle
        String parentHandle = driver.getWindowHandle();
        windowMap.put("parent", parentHandle);

//        System.out.println("Parent Window Handle: " + parentHandle);

        // Click link to open new window
        driver.findElement(By.linkText("Click Here")).click();

        // Get all window handles
        Set<String> allWindows = driver.getWindowHandles();

        // Find the new window handle and store in map
        for (String handle : allWindows) {
            if (!handle.equals(windowMap.get("parent"))) {
                windowMap.put("child", handle);
            }
        }

        // Switch to new window using map and print title and handle
        driver.switchTo().window(windowMap.get("child"));
        System.out.println("New Window Handle: " + windowMap.get("child"));
        System.out.println("New Window Title: " + driver.getTitle());

        // Close new window
        driver.close();

        // Switch back to parent window using map and print title and handle
        driver.switchTo().window(windowMap.get("parent"));
        System.out.println("Parent Window Handle: " + windowMap.get("parent"));
        System.out.println("Parent Window Title: " + driver.getTitle());

        driver.quit();
    }
}
