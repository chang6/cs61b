import java.lang.reflect.Array;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty array deque. */
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /** Adds x to the front of the array deque. */
    public void addFirst(Item x) {
        items[nextFirst] = x;
        nextFirst = (nextFirst + 7) % 8;
        size += 1;
    }

    /** Adds x to the end of the array deque. */
    public void addLast(Item x) {
        items[nextLast] = x;
        nextLast = (nextLast + 1) % 8;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int count = 0;
        int ind = (nextFirst + 1) % 8;
        while (count < size) {
            System.out.print(items[ind] + " ");
            count += 1;
            ind = (ind + 1) % 8;
        }
    }

    public Item removeFirst() {
        int firstInd = (nextFirst + 1) % 8;
        Item x = items[firstInd];
        items[firstInd] = null;
        nextFirst = firstInd;
        size -= 1;
        return x;
    }

    public Item removeLast() {
        int lastInd = (nextLast + 7) % 8;
        Item x = items[lastInd];
        items[lastInd] = null;
        nextLast = lastInd;
        size -= 1;
        return x;
    }

    public Item get(int index) {
        int firstInd = (nextFirst + 1) % 8;
        int realInd = (firstInd + index) % 8;
        return items[realInd];
    }

//    public static void main(String[] args) {
//        ArrayDeque<String> ad = new ArrayDeque<>();
//        ad.addFirst("!");
//        ad.addLast("Java");
//        ad.addFirst("Aloha");
//        ad.addLast("~");
//        ad.printDeque();
//        //System.out.println(ad.size());
//    }
}

