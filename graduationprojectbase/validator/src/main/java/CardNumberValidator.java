public abstract class CardNumberValidator {
    protected CardNumberValidator next;

    public void setNext(CardNumberValidator validator) {
        next = validator;
    }

    public static CardNumberValidator createValidatorsChain(CardNumberValidator ... validators) {
        CardNumberValidator first = validators[0];
        CardNumberValidator previous = first;

        for (int i = 1; i < validators.length; i++) {
            CardNumberValidator current = validators[i];
            previous.setNext(current);
            previous = current;
        }

        return first;
    }

    protected abstract boolean validateCard(String cardNumber);
}

