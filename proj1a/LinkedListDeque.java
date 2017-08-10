public class LinkedListDeque<Item> {
    private class StuffNode {
        public Item item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(Item i, StuffNode n, StuffNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private StuffNode sentinel;
    private int size;

    /** Creates an empty linked list deque. */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Checks if a list is empty */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Adds x to the front of the list */
    public void addFirst(Item x) {
        StuffNode f = new StuffNode(x, null, sentinel);
        if (this.isEmpty()) {
            f.next = sentinel;
            sentinel.prev = f;
        } else {
            StuffNode oldfirst = sentinel.next;
            oldfirst.prev = f; // SO MUCH PAIN!!!
            f.next = oldfirst;
        }
        this.sentinel.next = f;
        size += 1;

    }

    /** Adds x to the end of the list */
    public void addLast(Item x) {
        StuffNode last = new StuffNode(x, sentinel, null);
        if (this.isEmpty()) {
            last.prev = sentinel;
            sentinel.next = last;
        } else {
            StuffNode oldlast = sentinel.prev;
            last.prev = oldlast;
            oldlast.next = last; // SO MUCH PAIN!!!
        }
        this.sentinel.prev = last;
        size += 1;

    }


    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the Deque from first to last, separated by a space. */
//    public void printDeque() {
//        String result = "lalaland";
//        StuffNode ptr = sentinel.next;
//
//        while (ptr.item != null) {
//            //result = result + ptr.item + " ";
//            System.out.println(ptr.item);
//            ptr = ptr.next;
//        }
//
//        System.out.println(result);
//    }
    public void printDeque() {
        StuffNode p = this.sentinel.next;

        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }


    /** Removes and returns the item at the front of the Deque.
     * If no such item exists, returns null.*/
    public Item removeFirst() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            StuffNode i = sentinel.next;
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
            size -= 1;
            return i.item;
        } else {
            StuffNode i = sentinel.next;
            sentinel.next = i.next;
            i.next.prev = sentinel;
            size -= 1;
            return i.item;
        }
    }

    /** Removes and returns the item at the back of the Deque.
     * If no such item exists, returns null. */
    public Item removeLast() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            StuffNode i = sentinel.prev;
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
            size -= 1;
            return i.item;
        } else {
            StuffNode i = sentinel.prev;
            i.prev.next = sentinel;
            sentinel.prev = i.prev;
            size -= 1;
            return i.item;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque!*/
    public Item get(int index) {
        StuffNode ptr = sentinel;
        int ind = 0;
        if ((index + 1 > this.size()) || this.isEmpty() ) {
            return null;
        }
        while (ind <= index) {
            ptr = ptr.next;
            ind += 1;
        }
        return ptr.item;
    }

    /** Same as get, but uses recursion. */
    public Item recursivHelper(StuffNode x, int index) {
        if (index == 0) {
            return x.item;
        } else {
            return recursivHelper(x.next, index-1);
        }
    }

    public Item getRecursive(int index) {
        if (index + 1 > this.size() || this.isEmpty()) {
            return null;
        } else {
            StuffNode x = sentinel.next;
            return recursivHelper(x, index);
        }
    }

    /** I only want to use the main to check printDeque, get and getRecursive */
    public static void main(String[] args){
        LinkedListDeque<String> lld = new LinkedListDeque<>();
        lld.addFirst("Hello");
        lld.addLast("Java");
        lld.addLast("!");

        String s0 = lld.getRecursive(0);
        String s1 = lld.getRecursive(1);
        String s2 = lld.getRecursive(2);
        System.out.println(s0 + s1 + s2);
        System.out.println(lld.getRecursive(556));
        //lld.getRecursive(2);
    }

}
