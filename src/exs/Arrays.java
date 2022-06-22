package exs;

import java.util.Random;

public class Arrays {
    private int [] numbers;
    private int [] weights;
    private int [] final_array;
    private  int left_index=0;
    private int sum=0;

    public void takes (int [] numbers_p, int[]weights_p)
    {
        numbers = numbers_p;
        weights = weights_p;
        for (int weight : weights)
        {
            sum += weight;
        }
        final_array = new int[sum];
        for(int i = 0; i<weights.length; i++)
        {
            for(int j =0; j<weights[i]; j++)
            {
                final_array[j+left_index]=numbers[i];
            }
            left_index += weights[i];
        }
    }
    public int get_Random ()
    {
        Random r = new Random();
        return (final_array[r.nextInt(0,final_array.length-1)]);
    }
}
