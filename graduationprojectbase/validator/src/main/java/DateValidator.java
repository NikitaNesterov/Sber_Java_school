import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;

    private String datePattern =
            "(0?[1-9]|[12][0-9]|[3][01])([/.-])(0?[1-9]|1[012])([/.-])((19|20)\\d\\d)";

    public DateValidator() {
    }

    @Override
    public boolean ifValid(String str) throws IOException {
        pattern = Pattern.compile(datePattern);
        matcher = pattern.matcher(str);

        if (str.matches(datePattern)) {
//            matcher.reset();

            if (matcher.find()) {
                String day = matcher.group(1);
                String month = matcher.group(3);
                int year = Integer.parseInt(matcher.group(5));

                if (day.equals("31") &&
                        (month.equals("4") || month.equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month.equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if (year % 4 == 0) {
                        if (day.equals("30") || day.equals("31")) {
                            System.out.println("1");
                            return false;
                        } else {
                            return true;
                        }
                    } else {

                        if (day.equals("29") || day.equals("30") || day.equals("31")) {
                            System.out.println("2");
                            return false;
                        } else {
                            return true;
                        }
                    }
                } else {
                    return true;
                }
            } else {
                System.out.println("3");
                return false;
            }
        } else {
            System.out.println("4");
            return false;
        }
    }
}
