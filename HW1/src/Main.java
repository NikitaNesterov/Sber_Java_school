public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Person Nikita = new Person(true, "Nikita");
        Person Raya = new Person(false, "Raya");
        Person Sveta = new Person(false, "Sveta");


        System.out.println(Nikita.marry(Raya));
        System.out.println(Nikita.marry(Sveta));
        System.out.println(Raya.marry(Sveta));
    }
}
