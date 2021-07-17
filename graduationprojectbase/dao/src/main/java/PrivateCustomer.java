import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class PrivateCustomer implements Serializable {

    public static final long serialVersionUID = 1L;
    private String name;
    private String dateOfBirth;
    private String email;

   public static ArrayList<PrivateCustomer> listOfCustomers = new ArrayList<PrivateCustomer>();

    public PrivateCustomer(String name, String dateOfBirth, String email) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public static ArrayList<PrivateCustomer> getListOfCustomers() {
        return listOfCustomers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrivateCustomer that = (PrivateCustomer) o;
        return name.equals(that.name) && dateOfBirth.equals(that.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    @Override
    public String toString() {
        return "PrivateCustomer{" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
