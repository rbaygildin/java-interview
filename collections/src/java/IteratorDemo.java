import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IteratorDemo
{
    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6);
        Iterator<Integer> it = l.iterator();
        while(it.hasNext()){
            System.out.println("Element = " + it.next());
        }
        l = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        it = l.iterator();
        System.out.println("Print only odd");
        while(it.hasNext()){
            Integer i = it.next();
            if(i % 2 == 0){
                it.remove();
            }
            else{
                System.out.println("Element = " + i);
            }
        }
    }
}
