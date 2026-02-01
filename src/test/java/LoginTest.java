public class LoginTest {

    public  void enterUsername() {
        System.out.println("User name Entered");
    }
    public  void enterPassword() {
        System.out.println("Password name Entered");
    }
    public  void clicklogin() {
        System.out.println("Clicked on login");
    }
    public static void main (String[] args) {
        LoginTest login = new LoginTest();
        login.enterUsername();
        login.enterPassword();
        login.clicklogin();
    }

}