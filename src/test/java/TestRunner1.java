public class TestRunner1 {

    public static void main(String[] args) {

        BaseTest test = new LoginTest3(); // parent reference, child object
        test.setup(); // calls overridden method (LoginTest)
    }
}