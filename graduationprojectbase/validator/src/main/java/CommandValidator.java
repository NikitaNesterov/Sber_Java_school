import javax.xml.bind.ValidationException;

public class CommandValidator implements Validator {

    @Override
    public boolean ifValid(String str) {

        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Введенное выражение содержит не числовые символы\n");
            return false;
        }

        if (str.length() > 1) {
            try {
                throw new ValidationException("Введенное количество символов превышает 1", str);
            } catch (ValidationException e) {
                System.out.println("Введенное количество символов превышает 1 \n");
                return false;
            }
        }

        if (Integer.parseInt(str) < 1 || Integer.parseInt(str) > 4) {
            try {
               throw new ValidationException("Список команд не содержит введенного номера", str);
            } catch (ValidationException e) {
                System.out.println("Список команд не содержит введенного Вами номера \n");
                return false;
            }
        }
        return true;
    }
}
