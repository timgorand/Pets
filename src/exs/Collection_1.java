package exs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

public class Collection_1 {
    public static <T> Collection<T> removeDuplicates(Collection<T> collection) {
        return new HashSet<>(collection);
    }
}

class Collection_2{
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