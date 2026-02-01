public class ForEachLoop {

    public static void main(String[] args) {
        for(int i=1; i<=10; i++){

            System.out.println(i);
        }
        String[] MenuName = {"Burger", "Pizza", "Pasta", "Sandwich"};
        for(String Menu : MenuName) {
            System.out.println(Menu);
        }

    }

}
