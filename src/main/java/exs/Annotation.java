package exs;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// Создайте свою аннотацию Repeat с целочисленным параметром.
// Расширьте класс ThreadPoolExecutor и переопределите метод execute следующим образом: если экземпляр Runnable имеет
// Repeat, то его метод run выполняется несколько раз
public class Annotation {
}

@Repeat(3)
class MyRunable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hi, mark");
    }
}


@Retention(RetentionPolicy.RUNTIME)
@interface Repeat {
    int value();
}

class CustomThreadPoolExecutor extends ThreadPoolExecutor {
    public CustomThreadPoolExecutor(int corePool) {
        super(corePool, Integer.MAX_VALUE, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    }

    @Override
    public void execute(Runnable commands) {
        if (commands != null) {
            Class<? extends Runnable> runn_class = commands.getClass();
            Repeat repeat = runn_class.getAnnotation(Repeat.class);
            if (repeat == null) super.execute(commands);
            else {
                for(int i = 0; i < repeat.value(); i++) {
                    super.execute(commands);
                }
            }
        }
    }

    public static void main(String[] args) {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(10);
        customThreadPoolExecutor.execute(new MyRunable());
    }

}