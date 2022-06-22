package exs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Collection_1 {
    /*Напишите метод, который на вход получает коллекцию объектов, а возвращает коллекцию уже без дубликатов.*/
    public static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        return new HashSet<>(collection);
    }
}

class Collection_2{
    /*Напишите метод, который добавляет 1000000 элементов в ArrayList и LinkedList. Напишите еще один метод, который
    выбирает из заполненного списка элемент наугад 100000 раз. Замерьте время, которое потрачено на это.
    Сравните результаты и предположите, почему они именно такие.*/
    private Random r = new Random();
    public ArrayList bigFull(ArrayList<Integer> bigList)
    {

        for (int i = 0; i<1000000; i++)
        {
            bigList.add(r.nextInt(1,700));
        }
        return bigList;
    }
    public void bigChoose (ArrayList<Integer> bigList){
        for (int i = 0; i<1000000; i++)
        {
            System.out.println(bigList.get(r.nextInt(1,bigList.size())));
        }
    }
}