import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {

        Cat cat1  = new Cat(2, CatColor.BLACK);
        Cat cat2  = new Cat(4, CatColor.WHITE);
        Cat cat3  = new Cat(4, CatColor.BLACK);
        Cat cat4  = new Cat(3, CatColor.GREY);
        Cat cat5  = new Cat(2, CatColor.GREY);

        Predicate<Cat> testAge = cat -> cat.getColor().equals(CatColor.BLACK);


        List<Cat> catList = new ArrayList<>();
        catList.add(cat1);
        catList.add(cat2);
        catList.add(cat3);
        catList.add(cat4);
        catList.add(cat5);




        Streams streams = Streams.of(catList);

        Streams filter = streams.filter(testAge);
        System.out.println(Streams.of(catList).filter(testAge));




    }
}
