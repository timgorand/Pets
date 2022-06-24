package exs;

import java.util.*;

public class Collection_1 {
    /*Напишите метод, который на вход получает коллекцию объектов, а возвращает коллекцию уже без дубликатов.*/
    public static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        return new HashSet<>(collection);
    }

    /*Напишите метод, который добавляет 1000000 элементов в ArrayList и LinkedList. Напишите еще один метод, который
    выбирает из заполненного списка элемент наугад 100000 раз. Замерьте время, которое потрачено на это.
    Сравните результаты и предположите, почему они именно такие.*/
    private static Random r = new Random();
    static long starttime;
    static long[] endtime = new long[4];

    public static ArrayList bigFull_Array(ArrayList<Integer> bigList) {
        starttime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            bigList.add(r.nextInt(1, 700));
        }
        endtime[0] = System.currentTimeMillis() - starttime;
        return bigList;
    }

    public static LinkedList<Integer> bigFull_Link(LinkedList<Integer> bigLink) {
        starttime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            bigLink.add(r.nextInt(1, 700));
        }
        endtime[2] = System.currentTimeMillis() - starttime;
        return bigLink;
    }

    public static void bigChoose_list(ArrayList<Integer> bigList) {
        starttime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            System.out.println(bigList.get(r.nextInt(1, bigList.size())));
        }
        endtime[1] = System.currentTimeMillis() - starttime;
    }

    public static void bigChoose_Link(LinkedList<Integer> bigLink) {
        starttime = System.currentTimeMillis();
        for(int i = 0; i < 1000000; i++) {
            bigLink.add(r.nextInt(1, 700));
        }
        endtime[3] = System.currentTimeMillis() - starttime;
    }

    public static void main(String[] args) {
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> big_List = new ArrayList<>();
        LinkedList<Integer> big_Link = new LinkedList<>();
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                array.add(j + 1);
            }
        }
        System.out.println("-----------"); // для проверки скорости
        bigChoose_list(bigFull_Array(big_List));
        bigChoose_Link(bigFull_Link(big_Link));
        System.out.println("Создание и выборка из ArrayList");
        System.out.print(endtime[0] + "ms | "); // 30~80ms
        System.out.println(endtime[1] + "ms"); // 1500~1700ms
        System.out.println("---------");
        System.out.println("Создание и выборка из LinkedList");
        System.out.print(endtime[2] + "ms | "); // 15~60ms
        System.out.println(endtime[3] + "ms");// 150~300ms
        System.out.println("-----------");
        System.out.println("-----------");
        System.out.println(array); // для проверки задания для уборки дубликатов
        System.out.println("-----------");
        System.out.println(removeDuplicates(array));
    }
}