public class PasswordChecker {
    // Rule 1: Length >= 8
    public boolean hasLength(String pw) {
        return pw.length() >= 8;
    }
    public boolean hasUpperCase(String pw) {
        return pw.matches(".*[A-Z].*");
    }

    // Rule 3: Contains digit
    public boolean hasDigit(String pw) {
        return pw.matches(".*[0-9].*");
    }

    // Rule 4: Contains special character
    public boolean hasSpecialChar(String pw) {
        return pw.matches(".*[^a-zA-Z0-9].*");
    }

    // Password strength
    public String strength(String pw) {
        int count = 0;

        if (hasLength(pw)) count++;
        if (hasUpperCase(pw)) count++;
        if (hasDigit(pw)) count++;
        if (hasSpecialChar(pw)) count++;

        if (count <= 1)
            return "Weak";
        else if (count <= 3)
            return "Medium";
        else
            return "Strong";
    }
}