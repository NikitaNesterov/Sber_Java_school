import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class Streams<T>{

    private Collection collection;



    public Streams() {
    }

    public <E> Streams(List<E> list) {
        this.collection = list;
    }


    public static <E> Streams of(List<E> list) {

        return new Streams<>(list);
    }


    public <E> Streams<T> filter(Predicate<E> predicate) {

        Streams<T> newStreams;
        List<E> newCollection = new ArrayList();
         for (Object tStreams : getCollection()) {
            if (predicate.test((E) tStreams)) {
                newCollection.add((E) tStreams);
            } }
            newStreams = of(newCollection);
            return newStreams;
    }

//
//    public Streams transform(........) {
//           ...
//         return this;
//    }
//
//     public Map toMap(........) {
//           ...
//    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

}
