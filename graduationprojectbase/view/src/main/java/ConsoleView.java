import java.io.EOFException;
import java.io.File;
import java.io.IOException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class ConsoleView implements View {


    private BankCardService bankCardService;
    private String fileName = "bankCard.ser";
    private String implementationType;
    private File file = new File(fileName);


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
        String wish = getCustomerWishes();
        switch (wish) {
            case "1":
                start();
                break;

            case "2":
                System.out.println("Спасибо за то, что посетили наш банк! Хорошего дня!");
                bankCardService.serialize(file);
                   try {
            bankCardService.changeOldCard(6);
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }

    public PrivateCustomer createCustomer() {
        System.out.println("Введите имя клиента");
        Scanner scanner = new Scanner(System.in);
        String cardHolder = scanner.nextLine();

        System.out.println("Введите дату рождения клиента");
        Scanner scanner1 = new Scanner(System.in);
        String dateOfBirth = scanner1.nextLine();

        System.out.println("Введите email клиента");
        Scanner scanner2 = new Scanner(System.in);
        String email = scanner2.nextLine();

        return new PrivateCustomer(cardHolder, dateOfBirth, email);
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
                bankCardService.serialize(file);
                System.exit(0);
        } return null;
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
        System.out.println("\nПродолжите заводить карту? " +
                "\n1 - Да" +
                "\n2 - Нет");
        String commandNumber = new Scanner(System.in).nextLine();
        return commandNumber;
    }

}
