package exs;

class Synhronized extends Thread {
    private Object syn;
    //Напишите программу, в которой создаются два потока, которые выводят на консоль своё имя по очереди.
    public Synhronized(Object object) {
        this.syn = object;
    }
    @Override
    public void run() {
        while (true)
        {
            synchronized (syn)
            {
                try {
                    System.out.println(getName());
                    syn.notify();
                    syn.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //Работает бесконечно!
    public static void main(String[] strings) {
        Object lock = new Object();
        new Synhronized(lock).start();
        new Synhronized(lock).start();
    }
}
