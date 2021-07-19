import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerNameValidator implements Validator {

    public CustomerNameValidator() {
    }

    @Override
    public boolean ifValid(String str) throws IOException {
        try {
            int count = 0;
            String[] strSplit = str.split("\\s");
            String patternFormat = "[a-zA-Zа-яА-Я]*";
            for (int i = 0; i < strSplit.length; i++) {

                if (!strSplit[i].matches(patternFormat))
                     count++;}
            if(count > 0) {
                    throw new IllegalArgumentException("Введенные данные содержат небуквенные символы");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Введенные данные содержат небуквенные символы\n");
            return false;
        }

        return true;
    }
}

