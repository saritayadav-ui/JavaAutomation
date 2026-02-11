import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Selenium1 {
    public static void main(String[] args) throws InterruptedException {

    WebDriver driver = new ChromeDriver();

	       driver.get("https://the-internet.herokuapp.com/forgot_password");
	       Thread.sleep(1000);

}
}
