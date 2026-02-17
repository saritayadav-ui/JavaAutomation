package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ===== Locators =====
    By lifestyleMenu = By.xpath("//a[@class=\"nav_link\" and contains(text(),'लाइफस्टाइल')]");
    By subMenus = By.cssSelector("#container_2354729 ul.vertical-nav li.second-nav a");

    // ===== Hover using Actions =====
    public void hoverLifestyle() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(lifestyleMenu)).perform();
    }

    public List<WebElement> getSubMenus() {
        return driver.findElements(subMenus);
    }

    public WebElement getLifestyleMenu() {
        return driver.findElement(lifestyleMenu);
    }
}
