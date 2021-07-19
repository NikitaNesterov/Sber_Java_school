import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static junit.framework.TestCase.assertEquals;

public class BankCardTest {

    BankCard bankCard = new BankCard();
    public static String visa = "4276";
    public static String masterCard = "5745";
    public static String mir = "2245";

    @Before
    public void setUp() {
        bankCard.setCardNumber("4276152314526520");
        bankCard.setValidFrom(2014, 11, 18);

    }

    @Test
    public void shouldCreateBankCardInstanceTest1() {
        assertEquals("4276152314526520", bankCard.getCardNumber());
        assertEquals(LocalDate.of(2014, 11, 18), bankCard.getValidFrom());
    }

    @Test
    public void shouldCreateBankCardInstanceTest2() {
      assertEquals(bankCard.getCardNumber().substring(0, 4), BankCardService.createCardNumber(visa).substring(0, 4));
      assertEquals(16, BankCardService.createCardNumber(visa).length());
      assertEquals(LocalDate.of(2014, 11, 18), bankCard.getValidFrom());
    }

}
