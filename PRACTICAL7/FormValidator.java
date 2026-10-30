import java.lang.annotation.*;
import java.lang.reflect.*;
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}
class SignupForm {
    @NotBlank
    String name;
    @NotBlank
    @MaxLength(30)
    String email;
    @MaxLength(10)
    String password;
    SignupForm(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
public class FormValidator {
    public static void validate(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();

        int errors = 0;

        for (Field field : fields) {
            field.setAccessible(true);
            String value = (String) field.get(obj);

            if (field.isAnnotationPresent(NotBlank.class)) {
                if (value == null || value.trim().isEmpty()) {
                    System.out.println(field.getName() + " cannot be blank");
                    errors++;
                }
            }

            if (field.isAnnotationPresent(MaxLength.class)) {
                MaxLength max = field.getAnnotation(MaxLength.class);

                if (value != null && value.length() > max.value()) {
                    System.out.println(field.getName() + " exceeds maximum length " + max.value());
                    errors++;
                }
            }
        }

        if (errors == 0) {
            System.out.println("Form is valid");
        } else {
            System.out.println("Total errors: " + errors);
        }
    }

    public static void main(String[] args) throws Exception {

        SignupForm form = new SignupForm("", "tanvi@example.com", "123456789012");

        validate(form);
    }
}