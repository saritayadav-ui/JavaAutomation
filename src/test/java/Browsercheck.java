public class Browsercheck {

    public static void main(String[] args) {
        String browsername = "Chrome";

        if (browsername.equalsIgnoreCase("Chrome")) {
            System.out.println("This is chrome");
        }
        else if (browsername.equalsIgnoreCase("firefox")) {
            System.out.println("This is firefox");
        }
        else if (browsername.equalsIgnoreCase("edge")) {
            System.out.println("This is edge");
        }
        else  {
            System.out.println("No browser will launch");
        }

    }

}
