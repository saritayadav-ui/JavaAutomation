public class TestRunner {

    public static void main(String[] args) {

        LoginTest1 login = new LoginTest1(); // creating object

        login.enterUsername();
        login.enterPassword();
        login.clickLogin();
    }
}