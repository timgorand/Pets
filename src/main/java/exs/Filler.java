package exs;

import java.util.function.Function;

public class Filler {

    public static <T> void fill(T[]objects, Function<Integer, ? extends T> function){
        for (int i =0; i<objects.length;i++){
            objects[i]=function.apply(i);
        }
    }

    public static void main(String[] args){
        Integer[]squares = new Integer[100];
        fill(squares, integer -> integer * integer);
    }
}
