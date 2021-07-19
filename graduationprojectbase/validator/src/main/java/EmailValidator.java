import java.io.IOException;

public class EmailValidator implements Validator {

    private String emailPattern = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";

    public EmailValidator() {
    }

    @Override
    public boolean ifValid(String str) throws IOException {
        try {
            if (!str.matches(emailPattern)) {
                throw new IllegalArgumentException("Неверный формат адреса электронной почты");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Неверный формат адреса электронной почты\n");
            return false;
        }

        return true;
    }
}
