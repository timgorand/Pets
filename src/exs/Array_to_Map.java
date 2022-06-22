package exs;

import org.apache.commons.math3.util.Precision;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class Array_to_Map {
    public static void main(String[]args) {
        Double [] array = new Double[10];
        Random r = new Random();
        for (int i=0;i< array.length;i++) {
            array[i] =Precision.round(r.nextDouble(1.1,1.9),1);
        }
        System.out.println(convert_To_Map(array));

    }

    public static <K> Map<K, Integer> convert_To_Map(K[] ks)
    {
        Map<K, Integer> Maps = new LinkedHashMap<>();
        for (K i : ks) {
            if (Maps.containsKey(i)) {
                Integer value = Maps.get(i);
                Maps.put(i,value+1);
            }
            else Maps.put(i, 1);
        }
            return Maps;
    }
}
