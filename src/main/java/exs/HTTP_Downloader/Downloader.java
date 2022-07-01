package exs.HTTP_Downloader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
//Создать программу, скачивающая файлы по ссылке из .txt файла и отправляла в нужную директорию
//Работать должна со скоростью 2КБ/с и на 3-х потоках.
public class Downloader {
    public static String outer;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите директорию для сохранения файлов");
        outer = reader.readLine();//ввод директории для сохранения
        if(outer.equals("")){//Но если она пуста, то отправляют в корневую папку
            outer="./";
        }
        System.out.println("Запуск работы - ожидайте");
        System.out.println("Скорость скачки файлов 2КБ\n Количество потоков - 3");
        String urls = "./src/main/java/exs/HTTP_Downloader/Urls.txt";//txt файл,с которого всё скачивается
        String content = readUsingFiles(urls);
        int count = 1;
        String first = "";
        String second = "";
        String third = "";
        for (String s : content.split("\r\n")) {//Деление списка из файла на 3 части
            switch (count) {
                case (1):
                    first += s + "\r\n";//Вовзращаем формат выводу для дальнейшего парсинга
                    count++;
                    break;
                case (2):
                    second += s + "\r\n";
                    count++;
                    break;
                case (3):
                    third += s + "\r\n";
                    count = 1;
                    break;
            }
        }
        Thread thread_1 = new Thread(new First_Asynx(first));//Первый поток получает первый список
        Thread thread_2 = new Thread(new Second_Asynx(second));//Второй поток получает второй список
        Thread thread_3 = new Thread(new Third_Asynx(third));//Третий поток получает третий список
        thread_1.start();
        thread_2.start();
        thread_3.start();
    }

    private static String readUsingFiles(String fileName) throws IOException {//Метод, помогающий считать из txt-файла данные
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

class First_Asynx extends Downloader implements Runnable {

    private String list;

    public First_Asynx(String list) {//Все 3 потока индентичны друг другу
        this.list = list; //Получаем список ссылок
    }
    @Override
    public void run() {
        String name = "";//Хранит имя и формат для файла
        double speed = 2.048;//Скорость скачивания Б/с (Требуется 2КБ)
        InputStream in = null;//Принимает файл
        FileOutputStream out = null;//Отправляет файл в директорию
        URL url;//Хранит вычленённую ссылку
        for (String urls : list.split("\r\n")) {//Вычленяем ссылку и прогоняем её по алгоритму
            try {
                url = new URL(urls);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            try {//Мноооооооооооого одинаковых try{}catch{}. Решить проблему не удалось
                in = new BufferedInputStream(url.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String s : String.valueOf(url).split("/")) {//Вычленяем имя и формат файла из ссылки
                name = s;//Подразумевается,что в ссылке содержится формат и имя файла(Например, картинки)
            }//По сути мы просто берём последний сегмент, т.к. 99% ссылок файлов кончается на имя и формат файла
            try {
                out = new FileOutputStream(outer + name);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            byte[] buffer = new byte[1024];//Начинаем запись по КБ
            int count = -1;//Проверка конца
            while (true) {
                try {
                    count = in.read(buffer);//Важно присвоить значение ЗДЕСЬ, т.к. приём фотографий может начинаться с 0
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (count == -1) {
                    break;//Если нашли конец
                }
                try {
                    out.write(buffer, 0, count);//Запись КБ в файлик(Лучше его не трогать, пока не загрузит весь)
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {//Теперь, следует усыпить программу, дабы работать с указанной скорость. RateLimiter НЕ работает!
                    Thread.sleep((long) (count / speed));//t=S/u
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Первый поток загрузил файл!");//Когда окончится закачка файла, то выйдет оповещение
        }
    }
}
class Second_Asynx extends Downloader implements Runnable {//Начало следующего Потока. Ничем не отличается!
    private URL url;
    private String list;

    public Second_Asynx(String list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = "";
        double speed = 2.048;
        InputStream in = null;
        FileOutputStream out = null;
        for (String urls : list.split("\r\n")) {
            try {
                url = new URL(urls);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            try {
                in = new BufferedInputStream(url.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String s : String.valueOf(url).split("/")) {
                name = s;
            }
            try {
                out = new FileOutputStream(outer + name);

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            byte[] buffer = new byte[1024];
            int count = -1;
            while (true) {
                try {
                    count = in.read(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (count == -1) {
                    break;
                }
                try {
                    out.write(buffer, 0, count);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
               try {
                    Thread.sleep((long) (count / speed));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("Второй поток загрузил файл!");
        }
    }
}
class Third_Asynx extends Downloader implements Runnable {//И этот поток ничем не отличается!
    private URL url;
    private String list;

    public Third_Asynx(String list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = "";
        double speed = 2.048;
        InputStream in = null;
        FileOutputStream out = null;
        for (String urls : list.split("\r\n")) {
            try {
                url = new URL(urls);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            try {
                in = new BufferedInputStream(url.openStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String s : String.valueOf(url).split("/")) {
                name = s;
            }
            try {
                out = new FileOutputStream(outer + name);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            byte[] buffer = new byte[1024];
            int count = -1;
            while (true) {
                try {
                    count = in.read(buffer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (count == -1) {
                    break;
                }
                try {
                    out.write(buffer, 0, count);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                   try {
                    Thread.sleep((long) (count / speed));
                } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }

            }
            System.out.println("Третий поток загрузил файл!");
        }
    }
}