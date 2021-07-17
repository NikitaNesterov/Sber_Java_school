import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

public class BankCardServiceTest {

    private static String visa = "4276";
    private static String masterCard = "5745";
    private static String mir = "2245";

    PrivateCustomer privateCustomer;
    BankCardService bankCardService;
    BankCard bankCard;
    private ArrayList<BankCard> listOfCards1;
    private boolean flagOfExistence;
    private ArrayList<PrivateCustomer> listOfCustomers1;
    private int customerIndex;
    private HashMap<BankCard, PrivateCustomer> bankHashMap1;


    @Before
    public void setUp() {
        privateCustomer = new PrivateCustomer("Никита", "29.07.1982", "mirnes1809@hotmail.com");
        bankCardService = new BankCardService(new HashMap<>());
        bankCard = new BankCard(visa, 2014, 11, 19);
        listOfCustomers1 = new ArrayList<PrivateCustomer>();
        listOfCards1 = new ArrayList<BankCard>();
        bankHashMap1 = new HashMap<BankCard, PrivateCustomer>();
    }

    @Test
    public void shouldCreateBankcardServiceInstance() {
        assertNotNull(bankCardService);
    }

    @Test
    public void shouldCreateCardNumberTest() {
        assertEquals(16, bankCardService.createCardNumber(visa).length());
        assertEquals(16, bankCardService.createCardNumber(masterCard).length());
        assertEquals(16, bankCardService.createCardNumber(mir).length());
    }

    @Test
    public void shouldCreateCardNumberTest1() {
        assertEquals("4276", bankCardService.createCardNumber(visa).substring(0, 4));
        assertEquals("5745", bankCardService.createCardNumber(masterCard).substring(0, 4));
        assertEquals("2245", bankCardService.createCardNumber(mir).substring(0, 4));
    }

    @Test
    public void shouldCreateCustomerTest() {
        assertEquals(privateCustomer.hashCode(), bankCardService.createCustomer(privateCustomer));
    }

    @Test
    public void shouldCreateCustomerTest1() {
        PrivateCustomer privateCustomer1 = new PrivateCustomer("Раиса", "23.10.1982", "raise@hotmail.com");
        assertEquals(privateCustomer1.hashCode(), bankCardService.createCustomer(privateCustomer1));
    }

    @Test
    public void shouldCreateBankCardTest() {
        assertEquals(bankCard, bankCardService.createBankCard(bankCard));
    }

    @Test
    public void shouldCreateBankCardTest1() {
        listOfCards1.add(bankCard);
        assertEquals(bankCard, bankCardService.createBankCard(bankCard));
    }


    @Test
    public void shouldCreateBankCardTest2() {
        listOfCards1.add(bankCard);
        BankCard bankCard1 = new BankCard(mir, 2015, 10, 11);
        assertEquals(bankCard1, bankCardService.createBankCard(bankCard1));
    }

    @Test
    public void shouldCheckAlreadyExistenceTest() {
        assertEquals(false, bankCardService.checkAlreadyExistence(privateCustomer));
    }

    @Test
    public void shouldCheckAlreadyExistenceTest1() {
        PrivateCustomer privateCustomer2 = new PrivateCustomer("Никита", "29.07.1982", "mirnes1809@hotmail.com");
        customerIndex = bankCardService.createCustomer(privateCustomer);
        assertEquals(customerIndex, privateCustomer2.hashCode());
    }

    @Test
    public void shouldcheckAlreadyExistenceTest2() {
        PrivateCustomer privateCustomer3 = new PrivateCustomer("Никита", "29.07.1982", "mirnes1809@hotmail.com");
        bankCardService.createCustomer(privateCustomer);
        assertEquals(true, bankCardService.checkAlreadyExistence(privateCustomer3));
    }

    @Test
    public void shouldSaveBankProductTest() {
        int customer = 5546321;
        assertEquals("Клиент с таким индексом отсутствует в базе данных банка: " + customer, bankCardService.saveBankProduct(5546321, bankCard));
    }

    @Test
    public void shouldSaveBankProductTest1() {
        int customer = bankCardService.createCustomer(privateCustomer);
        assertEquals("Создан продукт", bankCardService.saveBankProduct(customer, bankCard));
    }


    @After
    public void tearDown() {
        listOfCustomers1.clear();
    }

}
