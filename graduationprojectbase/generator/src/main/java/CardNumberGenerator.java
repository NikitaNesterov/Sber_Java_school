import java.util.Random;

public class CardNumberGenerator {
    private static final Random RNG = new Random(System.currentTimeMillis());

    private static final int CARD_NUMBER_LENGTH = 16;
    private static final int DIGIT_UPPER_LIMIT = 10;

    public CardNumberGenerator() {
    }

    public String generate(String bankIdNumber) {
        int randomNumberLength = CARD_NUMBER_LENGTH - (bankIdNumber.length() + 1);

        StringBuilder builder = new StringBuilder(bankIdNumber);

        for (int i = 0; i < randomNumberLength; i++)
            builder.append(RNG.nextInt(DIGIT_UPPER_LIMIT));

        builder.append(getCheckDigit(builder.toString()));

        return builder.toString();
    }

    public String generate() {
        return generate("");
    }

    private int getCheckDigit(String number) {
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            int digit = Integer.parseInt(number.substring(i, (i + 1)));

            if ((i % 2) == 0) {
                digit = digit * 2;

                if (digit > 9)
                    digit = (digit / 10) + (digit % 10);
            }
            sum += digit;
        }

        int mod = sum % 10;

        return ((mod == 0) ? 0 : 10 - mod);
    }

}
