package exs;

public class thread {
//Выведите состояние потока перед его запуском, после запуска и во время выполнения.
    public static void main(String[] args) throws InterruptedException {
        Object syn = new Object();
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (syn)
                    {
                        System.out.println(getState());//Старт
                        syn.notifyAll();
                        syn.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println(thread.getState());//Бежит
        synchronized (syn)
        {
            thread.start();
            syn.wait();
            System.out.println(thread.getState());//Ждёт
            syn.notifyAll();
            System.out.println(thread.getState());//блокирован
            syn.wait(2000);
            System.out.println(thread.getState());//Уничтожен
            syn.notifyAll();
        }
    }
}
