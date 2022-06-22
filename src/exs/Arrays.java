package exs;

import java.util.Random;
/*Напишите класс, конструктор которого принимает два массива: массив значений и массив весов значений.
Класс должен содержать метод, который будет возвращать элемент из первого массива случайным образом, с учётом его веса.
Пример:
Дан массив [1, 2, 3], и массив весов [1, 2, 10].
В среднем, значение «1» должно возвращаться в 2 раза реже, чем значение «2» и в десять раз реже, чем значение «3».*/
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
