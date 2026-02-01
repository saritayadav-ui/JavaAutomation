public class Methods {

    public static void launchBrowser() {
        System.out.println("Browser launced");
    }
    public static void openUrl(String url) {
        System.out.println("Opening url: " + url);
    }
    public static void closeBrowser() {
        System.out.println("Browser closed");
    }
    public static void main (String[] args) {
        launchBrowser();
        openUrl("www.nbt.in");
        closeBrowser();

    }

}
