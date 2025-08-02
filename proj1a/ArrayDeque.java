public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int current = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++){
            newArray[i] = items[current];
            current = (current + 1) % items.length;
        }
        items = newArray;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    public void addFirst(T item) {
        if (size == items.length){
            resize(2 * items.length);
        }
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size++;
    }

    public void addLast(T item) {
        if (size == items.length){
            resize(2 * items.length);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size(){
        return size;
    }

    public void printDeque() {
        int curr = (nextFirst + 1) % items.length;
        for (int i = 0; i < size; i++){
            System.out.print(items[curr]);
            if (i < size - 1){
                System.out.print(" ");
            }
            curr = (curr + 1) % items.length;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T result = items[nextFirst];
        items[nextFirst] = null;
        size = size - 1;

        if (items.length >= 16 && size < 0.25 * items.length){
            resize(items.length / 2);
        }
        return result;
    }

    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;

        T result = items[nextLast];
        items[nextLast] = null;
        size = size - 1;

        if (items.length >= 16 && size < 0.25 * items.length){
            resize(items.length / 2);
        }
        return result;
    }

    public T get(int index) {
        int realidx = (index + nextFirst + 1) % items.length;
        return items[realidx];
    }

    public ArrayDeque(ArrayDeque<T> other) {
        this.size = other.size;
        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
        this.items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }
}
