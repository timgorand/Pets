package exs;

import org.apache.commons.math3.util.Precision; //Библиотека для команды округления

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

//Дано два потока — производитель и потребитель. Производитель генерирует некоторые данные (в примере — числа).
// Производитель «потребляет» их.
//Два потока разделяют общий буфер данных, размер которого ограничен. Если буфер пуст, потребитель должен ждать,
// пока там появятся данные. Если буфер заполнен полностью, производитель должен ждать, пока место освободится.
public class Take_took {
    public static int size;
    public Queue<Double> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//Ввод с консоли
        size = reader.read();
        LinkedList<Double>queue_list = new LinkedList<Double>();
        Thread place = new Thread(new make(queue_list, size));
        place.start();
        Thread take = new Thread(new take(queue_list));
        take.start();
    }
}

class make extends Take_took implements Runnable{
    public make(Queue<Double>queue_make, int size_make)
    {
        this.queue = queue_make;
        size = size_make;

    }
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("produce:" + made());
                } catch (Exception e) {
                throw new RuntimeException(e);
            }
            }
    }
    public double made() throws InterruptedException {
        double new_waiter = 0;
        Random r = new Random();
        synchronized (queue){
        if(queue.size() == size)
        {
            queue.wait();
        }
            new_waiter = Precision.round(r.nextDouble(1,3),1);
            queue.add(new_waiter);
            queue.notifyAll();
        }
        return new_waiter;
    }
}

class take extends Take_took implements Runnable
{
    public take(Queue<Double>queue_take)
    {
        this.queue = queue_take;
    }
    @Override
    public void run() {
        while (true){
            try {
                System.out.println("Taked: "+ took());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public Double took() throws InterruptedException {
        while (true) {
            synchronized (queue) {
                if (queue.isEmpty()) {
                    queue.wait();
                }
                queue.notifyAll();
                return queue.poll();
            }
        }
    }
}
