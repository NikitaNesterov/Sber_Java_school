import javafx.scene.control.SliderBuilder;

import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class BankCard implements Serializable {

    public static final long serialVersionUID = 1L;
    private String cardNumber;
    private LocalDate validFrom;
    private LocalDate validTo;

    public static String visa = "4276";
    public static String masterCard = "5745";
    public static String mir = "2245";

    public static ArrayList<BankCard> listOfCards = new ArrayList<BankCard>();

    public BankCard() {
    }

    public BankCard(BankCard bankCard) {
        this.cardNumber = bankCard.getCardNumber();
        this.validFrom = bankCard.getValidFrom();
        this.validTo = bankCard.getValidTo();
    }

    public BankCard(String emitentType, int year, int month, int day) {

        this.cardNumber = BankCardService.createCardNumber(emitentType);
        validFrom = LocalDate.of(year, month, day);
        this.validTo = validFrom.plusYears(5l);
    }

     public BankCard(String cardNumber, LocalDate localDate) {
        this.cardNumber = cardNumber;
        this.validFrom = localDate;
        this.validTo = this.validFrom.plusYears(5l);
    }



    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public void setValidFrom(int year, int month, int day) {
        this.validFrom = LocalDate.of(year, month, day);
    }

     public void setLocalDateValidFrom(LocalDate localDate) {
        this.validFrom = localDate;
    }


    public static boolean ifPresent(BankCard bankCard) {
        if(bankCard.getCardNumber() != "" && bankCard.getValidFrom() != null) {
            return true;
        } return false;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankCard bankCard = (BankCard) o;
        return cardNumber.equals(bankCard.cardNumber) && validFrom.equals(bankCard.validFrom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, validFrom);
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", validFrom=" + validFrom +
                ", validTo=" + validTo +
                '}';
    }
}
