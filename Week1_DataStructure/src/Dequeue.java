import java.util.ArrayList;
import java.util.List;

public class Dequeue {
    private List<Integer> deque = new ArrayList<>();

    public void insertFront(int item) {
        deque.add(0, item);
    }

    public void insertLast(int item) {
        deque.add(item);
    }

    public void removeFront() {
        if (deque.isEmpty()) {
            return;
        } else {
            int removedItem = deque.remove(0);
        }
    }

    public void removeRear() {
        if (deque.isEmpty()) {
            return;
        } else {
            int removedItem = deque.remove(deque.size() - 1);
        }
    }

    public int peakRear() {
        return deque.get(deque.size() - 1);
    }

    public int peakFront() {
        return deque.get(0);
    }

    public boolean isEmpty() {
        return deque.size() == 0;
    }
}
