package exs;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

// Написать итератор по массиву
public class Iteration<T> implements Iterator<T> {
    private T[] array;
    private int index;

    public static void main(String[] args) {
        Integer[] tester = new Integer[20];
        Random r = new Random();
        for(int i = 0; i < tester.length; i++) {
            tester[i] = r.nextInt(1, 177);
        }
        // Iteration<Iterator> Q = new Iteration<Integer>(tester);
        Iteration Q = new Iteration(tester);
        for(int i = 0; i < tester.length; i++) {
            System.out.println(Q.next()); // Выводит перебор массива
        }
    }

    Iteration(T[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return array[index++];
    }
}
