import java.util.ArrayList;


public class Main {

    public static void printList(ArrayList<Car> al) {
        try {
            System.out.println("Список моделей с кузовом " + al.get(0).getType());
            for (Car car : al) {
                System.out.println(car.getModel());
            }
            System.out.println("---------------------------");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Нет моделей с другими типами кузова");
        }
    }

    public static void main(String[] args) {

        ArrayList<Car> sedanList = new ArrayList<>();
        ArrayList<Car> universalList = new ArrayList<>();
        ArrayList<Car> coupeList = new ArrayList<>();
        ArrayList<Car> hatchbackList = new ArrayList<>();
        ArrayList<Car> suvList = new ArrayList<>();
        ArrayList<Car> minivanList = new ArrayList<>();
        ArrayList<Car> pickupList = new ArrayList<>();


        for (int counter = 0; counter <= 20; counter++) {
            Car car = Car.carGeneration();
            switch (car.getType()) {
                case SEDAN:
                    sedanList.add(car);
                    break;

                case UNIVERSAL:
                    universalList.add(car);
                    break;

                case COUPE:
                    coupeList.add(car);
                    break;

                case HATCHBACK:
                    hatchbackList.add(car);
                    break;

                case SUV:
                    suvList.add(car);
                    break;

                case MINIVAN:
                    minivanList.add(car);
                    break;

                default:
                    pickupList.add(car);
                    break;
            }
        }

        printList(sedanList);
        printList(universalList);
        printList(coupeList);
        printList(hatchbackList);
        printList(suvList);
        printList(minivanList);
        printList(pickupList);

    }
}
