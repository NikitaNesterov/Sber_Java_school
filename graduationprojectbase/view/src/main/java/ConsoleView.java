import java.io.EOFException;
import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleView implements View {


    private BankCardService bankCardService = new BankCardService(new HashMap<>());
    private String fileName = "C:/Users/r06678/Desktop/Others/SberJavaSchool/graduationprojectbase/src/main/resources/bankCard.ser";
    private String implementationType;
    private File file = new File(fileName);
    private CustomerNameValidator customerNameValidator = new CustomerNameValidator();
    private DateValidator dateValidator = new DateValidator();
    private EmailValidator emailValidator = new EmailValidator();
    private StringNumberValidator stringNumberValidator = new StringNumberValidator();


    public ConsoleView() {

        if (file.exists()) {
            HashMap deserialize = bankCardService.deserialize(fileName);
            bankCardService.setBankHashMap(deserialize);
        }
    }


    @Override
    public void start() throws IOException {
        PrivateCustomer privateCustomer = createCustomer();
        BankCard bankCard = createBankCard();
        bankCardService.saveBankProduct(bankCardService.createCustomer(privateCustomer), bankCard);
        newCardInforming(bankCard);
        getFirstMenu();
    }


    public void getFirstMenu() {
        String customerWishes = getCustomerWishes();
        switch (customerWishes) {
            case "1":
                try {
                    start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "2":
                deleteBankCard();
                break;
            case "3":
                System.out.println("Спасибо за то, что посетили наш банк! Хорошего дня!");
                try {
                    bankCardService.changeOldCard(6);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bankCardService.serialize(file);
                System.exit(0);
        }
    }

    public PrivateCustomer createCustomer() {
        boolean nameMonitor = true;
        String cardHolder = "";
        while (nameMonitor) {
            System.out.println("Введите ФИО клиента, используя только английские или русские буквы");
            Scanner scanner = new Scanner(System.in);
            cardHolder = scanner.nextLine();
            try {
                if (!customerNameValidator.ifValid(cardHolder)) {
                    if (!ifContinue(nameMonitor)) {
                    }
                } else {
                    nameMonitor = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        boolean dateMonitor = true;
        String dateOfBirth = "";
        while (dateMonitor) {
            System.out.println("Введите дату рождения клиента в формате dd.mm.yyyy");
            Scanner scanner1 = new Scanner(System.in);
            dateOfBirth = scanner1.nextLine();
            try {
                if (!dateValidator.ifValid(dateOfBirth)) {
                    System.out.println("Введена несуществующая дата рождения");
                    if (!ifContinue(dateMonitor)) {
                    }
                } else {
                    dateMonitor = false;
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        boolean emailMonitor = true;
        String email = "";
        while (emailMonitor) {
            System.out.println("Введите email клиента");
            Scanner scanner2 = new Scanner(System.in);
            email = scanner2.nextLine();
            try {
                if (!emailValidator.ifValid(email)) {
                    if (!ifContinue(emailMonitor)) {
                    }
                } else emailMonitor = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new PrivateCustomer(cardHolder, dateOfBirth, email);
    }

    private boolean ifContinue(boolean monitor) {
        System.out.println("Продолжаем ?" +
                "\n1 - Да" +
                "\n2 - Нет");
        String commandNumber = new Scanner(System.in).nextLine();
        switch (commandNumber) {
            case "1":
                return monitor = true;
            case "2":
                System.out.println("Спасибо за то, что посетили наш банк! Хорошего дня!");
                try {
                    bankCardService.changeOldCard(6);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bankCardService.serialize(file);
                System.exit(0);
        }
        return false;
    }

    public void deleteBankCard() {
        boolean cardNumberMonitor = true;
        String cardNumber = "";
        while (cardNumberMonitor) {
            System.out.println("Введите номер карты для поиска в базе данных и аннулирования");
            Scanner scanner = new Scanner(System.in);
            cardNumber = scanner.nextLine();
            if (!stringNumberValidator.validateCard(cardNumber)) {
                System.out.println("Введен некорректный номер карты");
                if (!ifContinue(cardNumberMonitor)) {
                }
            } else {
                bankCardService.deleteCard(cardNumber);
                cardNumberMonitor = false;
            }
        }
        getFirstMenu();
    }


    public BankCard createBankCard() throws IOException {
        LocalDate validFrom = getDateValidFrom();
        String emitentType = getOperation();
        switch (emitentType) {
            case "1":
                String bankCardNumber = BankCardService.createCardNumber(BankCard.visa);
                BankCard bankCard = new BankCard(bankCardNumber, validFrom);
                BankCard cardToRecord = bankCardService.createBankCard(bankCard);
                return cardToRecord;
            case "2":
                String bankCardNumber1 = BankCardService.createCardNumber(BankCard.masterCard);
                BankCard bankCard1 = new BankCard(bankCardNumber1, validFrom);
                BankCard cardToRecord1 = bankCardService.createBankCard(bankCard1);
                return cardToRecord1;
            case "3":
                String bankCardNumber2 = BankCardService.createCardNumber(BankCard.mir);
                BankCard bankCard2 = new BankCard(bankCardNumber2, validFrom);
                BankCard cardToRecord2 = bankCardService.createBankCard(bankCard2);
                return cardToRecord2;
            case "4":
                System.out.println("Спасибо за то, что посетили наш банк! Хорошего дня!");
                bankCardService.changeOldCard(6);
                try {
                    bankCardService.changeOldCard(6);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bankCardService.serialize(file);
                System.exit(0);
        }
        return null;
    }

    private LocalDate getDateValidFrom() {
        System.out.println("Введите год выпуска карты");
        int year = new Scanner(System.in).nextInt();

        System.out.println("Введите месяц выпуска карты");
        int month = new Scanner(System.in).nextInt();

        System.out.println("Введите день выпуска карты");
        int day = new Scanner(System.in).nextInt();
        return LocalDate.of(year, month, day);
    }

    public String getOperation() throws IOException {
        System.out.println("Выберите платежную систему из списка:" +
                "\n1 - Visa" +
                "\n2 - MasterCard" +
                "\n3 - МИР" +
                "\n4 - Выход из меню\n");
        String commandNumber = new Scanner(System.in).nextLine();
        return commandNumber;
    }

    public void newCardInforming(BankCard bankCard) {
        System.out.println("Создана новая карта:" +
                "\nВладелец карты: " + bankCardService.getPrivateCustomer(bankCard) +
                "\nНомер карты: " + bankCard.getCardNumber() +
                "\nСрок действия карты: " + bankCard.getValidTo());
    }

    public String getCustomerWishes() {
        System.out.println("\nВыберите следующее действие: " +
                "\n1 - Открываем новую карту" +
                "\n2 - Аннулируем карту" +
                "\n3 - Завершаем работу");
        String commandNumber = new Scanner(System.in).nextLine();
        return commandNumber;
    }

}
