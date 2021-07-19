import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class PrivateCustomerTest {

    PrivateCustomer privateCustomer = new PrivateCustomer();

    private String name;
    private String dateOfBirth;
    private String email;



    @Before
    public void setUp() {
       privateCustomer.setName("Nikita");
       privateCustomer.setDateOfBirth("29/07/1982");
       privateCustomer.setEmail("mirnes1809@hotmail.com");

    }

    @Test
    public void shouldCreatePrivateCustomerInstanceTest1() {
        assertEquals("Nikita", privateCustomer.getName());
        assertEquals("29/07/1982", privateCustomer.getDateOfBirth());
        assertEquals("mirnes1809@hotmail.com", privateCustomer.getEmail());
    }


    @Test
    public void shouldCreateBankCardInstanceTest2() {
      PrivateCustomer testCustomer = new PrivateCustomer(
              "Raisa",
              "23/10/1982",
              "raisa@rambler.ru"
      );

      assertNotNull(testCustomer);
    }

}
