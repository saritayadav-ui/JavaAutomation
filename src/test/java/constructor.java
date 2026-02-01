public class constructor {

    public constructor(String browsername) {
        System.out.println("Launching " +browsername+ " browser" );
    }

    public static void main(String[] args) {
        constructor bt = new constructor("Chrome");

    }

}