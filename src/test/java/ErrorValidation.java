public class ErrorValidation {

    public static void main(String[] args) {
        String[] errorMessage = {"Invalid username","Invalid password","Login failed","Account locked"};
        String givenError = "Invald username";
        boolean found = false;
        for (String error : errorMessage){
            if (error.equals(givenError)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Error message exists in the array");
        } else {
            System.out.println("Error message does not exist in the array");
        }

    }

}