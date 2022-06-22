package exs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Exercises {
    public static void main (String[] args) throws IOException {
        Recursively recursively = new Recursively();
        double[] array = new double[15];
        for (int i = 0; i< array.length; i++)
        {
            array[i] = i+1;
        }
    }
    //Заполните массив случайным числами и выведите максимальное, минимальное и среднее значение.
    public static void first_zero()
    {
        double min=Double.MAX_VALUE;
        double max = 0;
        double avg = 0;
        double [] array = new double[100];
        for (int i =0; i<array.length; i++)
        {
            array[i]=Math.random();
            if (max<array[i])
            {
                max=array[i];
            }
            if (min>array[i])
            {
                min=array[i];
            }
            avg += array[i];
        }
        avg = avg/100;
        System.out.println(max);
        System.out.println(min);
        System.out.println(avg);
    }
    //Реализуйте алгоритм сортировки пузырьком для сортировки массива
    public static void first_one() {
        int[] array = new int[10];
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            for (int j =0; j< array.length-1-i;j++) {
                array[i] = r.nextInt(0, 100);
                System.out.println(array[i]);
            }
        }
        boolean end = true;
        boolean check=false;
        int helper;
        int count=0;
        while (end)
        {
            for (int i = 0; i<array.length-1;i++){
                if (array[i]>array[i+1])
                {
                    helper = array[i];
                    array [i] = array[i+1];
                    array[i+1] = helper;
                    check=true;
                }

            }
            if(!check)
            {
                end=false;
            }
            check =false;
            }
        for (int j : array) {
            System.out.println(j);
        }

    }
    //Напишите программу, которая выводит на консоль простые числа в промежутке от [2, 100].
    //Используйте для решения этой задачи оператор "%" (остаток от деления) и циклы.
    public static void first_two()
    {
        for(int i =2; i<=100; i++)
        {
            if((i%2!=0 || i==2) && (i%3!=0 ||i==3) && (i%5!=0 || i==5) &&(i%7!=0||i==7))
            {
                System.out.println(i);
            }
        }
        System.out.println("-------------");
        for(int i = 2; i <= 100; i ++){
            boolean isPrime = true;

            for(int j = 2; j < i; j++){
                if(i % j == 0){
                    isPrime = false;
                    break;
                }
            }

            if(isPrime){
                System.out.println(i);
            }
        }
    }
    //Дан массив целых чисел и ещё одно целое число. Удалите все вхождения этого числа из массива (пропусков быть не должно).
    public static void first_three() throws IOException {
        int del_count=0;
        int [] first_array = new int[15];
        BufferedReader reading = new BufferedReader(new InputStreamReader(System.in));
        int del = Integer.parseInt(reading.readLine());
        Random r = new Random();
        for (int i = 0; i < first_array.length;i++)
        {
            first_array[i] = r.nextInt(1,5);
            if (first_array[i]==del)
            {
                first_array[i]=0;
                del_count++;
            }
            System.out.println(first_array[i]);
        }
        int helper;
        for (int i = 0; i < first_array.length;i++)
        {
            for (int j = 0; j<first_array.length-1-i;j++)
            {
                if(first_array[j]==0)
                {
                   helper =first_array[j+1];
                    first_array[j+1]=first_array[j];
                    first_array[j] = helper;
                }
            }
        }
            System.out.println("--------");
        int [] second_array = new int[first_array.length - del_count];
        for (int i = 0; i < first_array.length;i++)
        {
            if (first_array[i]!=0)
            {
                second_array[i]= first_array[i];
            }
            System.out.println(second_array[i]);
        }

    }
}

