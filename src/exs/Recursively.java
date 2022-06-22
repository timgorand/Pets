package exs;

public class Recursively {
/*Напишите метод, который проверяет, входит ли в массив заданный элемент или нет.*/
    public static void main(String[] args)
    {
        double[] array =  {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
        System.out.println(Search_in_Array(array, 7.0));
    }
    public static int Search_in_Array(double[] array, double key)
    {
        return Search_in_Recursively(array, key, 0, array.length-1);
    }
    private static int Search_in_Recursively(double[] array, double key, int low, int high)
    {
        int mid = (low + high)/2;
        System.out.println(low+" " + mid +" " +high);
        System.out.println("------------");
        double mid_Val = array[mid];
        if (low==high) return -1;
        else if (array[low]==key) return 1;
        else if (array[high]==key) return 1;
        else if(mid==low || mid==high) return -1;
        else if (mid_Val < key)
        {
            return Search_in_Recursively(array,key, mid+1, high-1);
        }
        else if(mid_Val > key)
        {
            return Search_in_Recursively(array,key,low+1,mid-1);
        }
        else if(mid_Val==key) return 1;
        else return -1;
    }

}
