package exs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/*

Напишите метод filter который принимает на вход массив (любого типа) и реализацию интерфейса Filter c методом
apply(Object o), чтобы убрать из массива лишнее. Проверьте как он работает на строках или других объектах.
 */
public class Filtring {
    interface Filter {
        boolean apply(Object o);
    }

    public static Object[] filter(Object[] array, Filter filter) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if (!filter.apply(array[i])) {
                count++;
            } else array[i - count] = array[i];
        }
        return Arrays.copyOf(array, array.length - count);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[]{10, 20, 30, 40, 50};
        Integer[] new_array = (Integer[]) filter(array, new Filter() {
            @Override
            public boolean apply(Object o) {
                return (o != (Integer) 40);
                //Работает на ура с String и Integer. Но сделать гибко, чтобы принимал не конкретный тип, а любой основной не вышло
            }
        });
    }

}
