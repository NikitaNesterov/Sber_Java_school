import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BankCardService {


    public static String cardNum = "";
    private HashMap<BankCard, PrivateCustomer> bankHashMap = new HashMap<BankCard, PrivateCustomer>();
    private PrivateCustomer privateCustomer;
    private BankCard bankCard;
    private int customerIndex;
    private boolean flagOfExistence;

    public BankCardService(HashMap<BankCard, PrivateCustomer> bankHashMap) {
        this.bankHashMap = bankHashMap;
    }

    public BankCardService() {
    }

    static CardNumberValidator validator = CardNumberValidator.createValidatorsChain(new StringNumberValidator(),
            new LuhnNumberValidator());

    public static String createCardNumber(String str) {
        cardNum = new CardNumberGenerator().generate(str);
        if (validator.validateCard(cardNum)) {
            return cardNum;
        } else return "Ошибка в генерации номера карты";
    }

    public int createCustomer(PrivateCustomer privateCustomer) {
        if (checkAlreadyExistence(privateCustomer)) {
            return customerIndex;
        } else {
            PrivateCustomer.listOfCustomers.add(privateCustomer);
        }
        return privateCustomer.hashCode();
    }

    public BankCard createBankCard(BankCard bc) {

        BankCard.listOfCards.forEach(a -> {
            if (a.getCardNumber().equals(bankCard.getCardNumber())) {
                bankCard = new BankCard(a);
            }
        });
        BankCard.listOfCards.add(bc);
        bankCard = new BankCard(bc);
        return bankCard;

    }

    public boolean checkAlreadyExistence(PrivateCustomer privateCustomer) {

        PrivateCustomer.listOfCustomers.forEach(a -> {
            if (a.equals(privateCustomer)) {
                customerIndex = a.hashCode();
                flagOfExistence = true;
            }
        });
        return flagOfExistence;
    }

    public String saveBankProduct(int customerCode, BankCard bankCard) {
        int count = 0;
        int before = bankHashMap.size();
        PrivateCustomer.listOfCustomers.forEach(a -> {
            if (a.hashCode() == customerCode) {
                bankHashMap.put(bankCard, a);
            }
        });
        int after = bankHashMap.size();
        if (after == before) {
            return ("Клиент с таким индексом отсутствует в базе данных банка: " + customerCode);
        } else return "Создан продукт";
    }


    public PrivateCustomer getPrivateCustomer(BankCard bankCard) {
        if (bankHashMap.containsKey(bankCard)) {
            return bankHashMap.get(bankCard);
        } else {
            System.out.println("Карта с таким номером не создавалась");
        }
        return null;
    }

    public void deleteCard(String cardNumber) {
        Set<BankCard> bankCards = bankHashMap.keySet();
        boolean ifFound = false;
        for (BankCard bankCard : bankCards) {
            if (bankCard.getCardNumber().equals(cardNumber)) {
                bankHashMap.remove(bankCard);
                System.out.println("Карта " + bankCard.getCardNumber() + " удалена");
                ifFound = true;
            }
        }
        if(!ifFound) {
            System.out.println("В данном банке карта с таким номером не открывалась");
        }
    }

    public void changeOldCard(int threadNmber) throws IOException {
        Set<BankCard> bankCards = bankHashMap.keySet();
        ExecutorService executor = Executors.newFixedThreadPool(threadNmber);
        Set<BankCard> cardsForReemission = new HashSet<>();
        File file = new File("C:/Users/r06678/Desktop/Others/SberJavaSchool/graduationprojectbase/src/main/resources/changes.doc");

        int countActiveCard = 0;
        for (BankCard bankCard : bankCards) {
            if (bankCard.getValidTo().equals(LocalDate.now())) {
                cardsForReemission.add(bankCard);
                countActiveCard++;
            }
        }
        if (countActiveCard == 0) {
            System.out.println("Нет карт для перевыпуска");
        }

        for (BankCard oldCard : cardsForReemission) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                        String lineSeparator = System.getProperty("line.separator");
                        BankCard newBankCard = new BankCard(oldCard.getCardNumber(), LocalDate.now());
                        cardReissue(oldCard, newBankCard);
                        writer.write("Выпущена новая карта " + newBankCard.getCardNumber() +
                                " со сроком действия до: " + newBankCard.getValidTo() + "\n");
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            file.delete();
            executor.execute(runnable);
        }
        executor.shutdown();
    }

    private synchronized void cardReissue(BankCard oldBankCard, BankCard newBankCard) {
        PrivateCustomer privateCustomertoReset = bankHashMap.get(oldBankCard);
        bankHashMap.remove(oldBankCard, privateCustomertoReset);
        bankHashMap.put(newBankCard, privateCustomertoReset);
    }


    public void serialize(File file) {
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream ous = new ObjectOutputStream(fos)) {
            ous.writeObject(bankHashMap);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public HashMap deserialize(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (HashMap) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBankHashMap(HashMap deserialize) {
        this.bankHashMap = deserialize;
    }

    public HashMap getListOfCustomers() {
        return bankHashMap;
    }
}
