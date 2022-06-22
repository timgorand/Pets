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
                        System.out.println(getState());
                        syn.notifyAll();
                        syn.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        System.out.println(thread.getState());
        synchronized (syn)
        {
            thread.start();
            syn.wait();
            System.out.println(thread.getState());
            syn.notifyAll();
            syn.wait(2000);
            System.out.println(thread.getState());
            syn.notifyAll();
        }
    }
}
