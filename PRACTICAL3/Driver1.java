import PasswordChecker;

package PRACTICAL3;
public class Driver1 {
    public static void main(String[] args) {

        PasswordChecker pc = new PasswordChecker();

        String[] passwords = {
            "abc",
            "Abcd1234!",
            "Password",
            "pass123",
            "Hello@123"
        };

        for (String pw : passwords) {
            System.out.println("Password: " + pw);
            System.out.println("Length >= 8: " + pc.hasLength(pw));
            System.out.println("Contains Uppercase: " + pc.hasUpperCase(pw));
            System.out.println("Contains Digit: " + pc.hasDigit(pw));
            System.out.println("Contains Special Character: " + pc.hasSpecialChar(pw));
            System.out.println("Strength: " + pc.strength(pw));
            System.out.println();
        }
    }
}