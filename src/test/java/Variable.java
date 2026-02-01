public class Variable {

    public static void main(String[] args) {
        String browserName ="Chrome";
        String url="https://navbharattimes.indiatimes.com/";
        int timeout= 60;

        System.out.println("Browser name is : " +browserName);
        System.out.println("Url  is : " +url);
        System.out.println("Timeout is : " +timeout);

        browserName ="Firefox";
        url="https://hindi.economictimes.com/";
        timeout= 62;


        System.out.println(" \nUpdated values are: ");
        System.out.println("Browser name is : " +browserName);
        System.out.println("Url  is : " +url);
        System.out.println("Timeout is : " +timeout);

    }

}