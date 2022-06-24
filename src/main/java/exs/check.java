package exs;

import java.util.Arrays;

public class check {
    interface Filter {
        boolean apply(Object o);
    }

        public static Object[] filter(Object[] array, Filter filter) {
            int offset = 0;

            for(int i = 0; i< array.length; i++){
                if(!filter.apply(array[i])){
                    offset++;
                } else{
                    array[i - offset] = array[i];
                }
            }

            // Arrays.copyOf копирует значение из массива array в новый массив
            // с длинной array.length - offset
            return Arrays.copyOf(array, array.length - offset);
        }

        public static void main(String[] args) {
            Integer array[] =
                    new Integer[]{1,2,3,4,5,6};

            Integer[] newArray =  (Integer[]) filter(array, new Filter() {
                @Override
                public boolean apply(Object o) {
                    return o != (Integer)4;
                }
            });
        }
    }
