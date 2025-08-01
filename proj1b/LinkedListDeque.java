public class LinkedListDeque<T> implements Deque<T>{
    /** Create the Node class */
    public class StaffNode {
        public T item;
        public StaffNode next;
        public StaffNode prev;

        public StaffNode(StaffNode p, T i, StaffNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    public StaffNode sentinel;
    public int size = 0;
    public LinkedListDeque(){
        sentinel = new StaffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /** Adds an item of type T to the front of the deque */
    @Override
    public void addFirst(T item){
        StaffNode added = new StaffNode(null, item, null);
        added.prev = sentinel;
        added.next = sentinel.next;
        sentinel.next.prev = added;
        sentinel.next = added;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque */
    @Override
    public void addLast(T item){
        StaffNode added = new StaffNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = added;
        sentinel.prev = added;
        size += 1;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line. */
    @Override
    public void printDeque(){
        int size1 = size;
        StaffNode p = sentinel;
        String whitespace = " ";
        while (size1 > 0){
            p = p.next;
            System.out.print(p.item);
            if (size1 != 1){
                System.out.printf(whitespace);
                }
            size1 -= 1;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.*/
    @Override
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }

        T i = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return i;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.*/
    @Override
    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        T i = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return i;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    @Override
    public T get(int index){
        if (index >= size()){
            return null;
        }
        StaffNode p = sentinel.next;
        while (index > 0){
            p = p.next;
            index -= 1;
        }
        return p.item;
    }
    /** create a deep copy of other*/
    public LinkedListDeque(LinkedListDeque<T> other){
        sentinel = new StaffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        for (int i = 0; i < other.size(); i++){
            this.addLast(other.get(i));
        }
    }

    public T getRecursive(int index) {
        if (index >= size()) {
            return null;
        }
        StaffNode p = sentinel.next;
        return getRecursiveHelper(index, p);
    }
    private T getRecursiveHelper(int index, StaffNode p){
        if (index == 0){
            return p.item;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
}
