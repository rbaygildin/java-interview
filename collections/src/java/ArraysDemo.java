import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArraysDemo {
    public static void main(String[] args) {
        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        l.forEach(System.out::println);
        l = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
        Collections.shuffle(l);
        l.sort(null);
        int index = Arrays.binarySearch(l.toArray(), 50);
        assert index == 49;
        index = Arrays.binarySearch(l.toArray(), 50, (x, y) -> {
            int xUnboxed = (Integer) x;
            int yUnboxed = (Integer) y;
            if (xUnboxed % 2 == 0 && yUnboxed % 2 == 0) {
                return Integer.compare(xUnboxed, yUnboxed);
            } else {
                if (xUnboxed % 2 == 0)
                    return -1;
                else if (yUnboxed % 2 == 0)
                    return 1;
                else
                    return Integer.compare(xUnboxed, yUnboxed);
            }
        });
        System.out.println("Index of 50 = " + index);
        int[] a = l.stream().mapToInt(i -> i).toArray();
        int[] newA = Arrays.copyOf(a, 30);
        assert newA.length == 30;
        assert newA[newA.length - 1] == 30;
        newA = Arrays.copyOfRange(a, 10, 30);
        assert newA.length == 20;
        assert newA[0] == 11;
        assert newA[newA.length - 1] == 30;
        Arrays.fill(a, 100);
        for (int anA : a)
            assert anA == 100;
        a = new int[]{
                1, 2, 3, 4, 5, 6
        };
        int[] b = new int[]{
                1, 2, 3, 4, 5, 6
        };
        assert Arrays.equals(a, b);
        int[] c = new int[]{
                -1, -1, -1, -1, -1, -1
        };
        assert !Arrays.equals(a, c);
        c = new int[]{
                1, 2
        };
        assert !Arrays.equals(a, c);
        System.out.println("Hashcode of array a = " + Arrays.hashCode(a));
    }
}
