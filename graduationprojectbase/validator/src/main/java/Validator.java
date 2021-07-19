import java.io.IOException;

public interface Validator {
    boolean ifValid(String str) throws IOException;
}
