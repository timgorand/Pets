package exs;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

//Напишите итератор по двумерному массиву
public class Iteration_2<T> implements Iterator<T> {
    private T[][] array;
    private static int index;
    private static int index_2;
    public static void main(String[] args)
    {
        Integer [][] tester = new Integer[8][7];
        Random r = new Random();
        for (int i = 0; i<tester.length; i++){
        for (int j = 0; j<tester[i].length; j++){
            tester[i][j] = r.nextInt(1,177);
        }
        }
        Iteration_2 Q = new Iteration_2(tester);
        for (int i = 0; i<tester.length; i++) {
            for (int j = 0; j<tester[i].length; j++) {
            if (Q.hasNext()) System.out.print(Q.next()+"|");
        }
            System.out.println(" ");

            index=i+1;
            index_2=0;
        }
    }
    Iteration_2(T[][]array){this.array = array;}
    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public T next() {
        if(!hasNext()){
             throw new NoSuchElementException();
        }
        return array[index][index_2++];
    }
}
