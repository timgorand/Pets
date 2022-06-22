class StepThread extends Thread {

    // общий для двух потоков lock
    private Object lock;

    public StepThread(Object object) {
        this.lock = object;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    System.out.println(getName());
                    lock.notify();
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] strings) {
        Object lock = new Object();
        new StepThread(lock).start();
        new StepThread(lock).start();
    }
}
