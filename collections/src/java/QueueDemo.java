import java.util.ArrayDeque;
import java.util.Deque;

public class QueueDemo {
    public static void main(String[] args) {
        Deque<Integer> q = new ArrayDeque<>();
        q.addFirst(10);
        q.push(2);
        q.addLast(-1);
        q.add(5);
        // 2 10 -1 5
        assert q.getFirst() == 2;
        assert q.getLast() == 5;
        assert q.pop() == 2;
        // 10 -1 5
        assert q.getFirst() == 10;
        assert q.peekLast() == 5;
        assert q.pollLast() == 5;
        // 10 -1
        assert q.pollFirst() == 10;
        // -1
        assert q.getFirst() == -1;
        assert q.pop() == -1;
        // null
        assert q.peek() == null;
    }
}
