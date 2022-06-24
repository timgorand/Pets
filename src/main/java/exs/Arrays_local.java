package exs;

import java.util.Random;

/*Напишите класс, конструктор которого принимает два массива: массив значений и массив весов значений.
Класс должен содержать метод, который будет возвращать элемент из первого массива случайным образом, с учётом его веса.
Пример:
Дан массив [1, 2, 3], и массив весов [1, 2, 10].
В среднем, значение «1» должно возвращаться в 2 раза реже, чем значение «2» и в десять раз реже, чем значение «3».*/
// т.е. получится массив [1223333333333] в котором нужно будет выбрать 1 цифру по индексу
public class Arrays_local {

    public static int takes(int[] numbers_p, int[] weights_p) {
        int left_index = 0;
        int sum = 0;
        for(int weight : weights_p) {
            sum += weight;
        }
        int[] final_array = new int[sum];
        for(int i = 0; i < weights_p.length; i++) {
            for(int j = 0; j < weights_p[i]; j++) {
                final_array[j + left_index] = numbers_p[i];
            }
            left_index += weights_p[i];
        }
        return get_Random(final_array);
    }

    public static int get_Random(int[] array) // Без статиков ни takes, ни get_Random НЕ хотят работать с main
    {
        Random r = new Random();
        return (array[r.nextInt(0, array.length - 1)]);
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 5, 2, 9};
        int[] weight = new int[]{3, 2, 7, 4};
        System.out.println(takes(numbers, weight));
    }
}
