package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String name) {

        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File src = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("screenshots/" + name + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
