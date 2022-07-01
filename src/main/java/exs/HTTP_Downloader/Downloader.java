package exs.HTTP_Downloader;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//Создать программу, скачивающая файлы по ссылке из .txt файла и отправляла в нужную директорию
//Работать должна со скоростью 2КБ/с и на 3-х потоках.
public class Downloader {
    static String outer;
    static double speed = 2.048;//Скорость скачивания Б/с (Требуется 2КБ)

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите директорию для сохранения файлов");
        outer = reader.readLine();//ввод директории для сохранения
        if (outer.equals("")) {//Но если она пуста, то отправляют в корневую папку
            outer = "./";
        }
        int threads = 3;
        System.out.println("Запуск работы - ожидайте");
        System.out.println("Скорость скачки файлов 2КБ\n Количество потоков - " + threads);
        String urls = "./src/main/java/exs/HTTP_Downloader/Urls.txt";//txt файл,с которого всё скачивается
        String content = readUsingFiles(urls);
        String[] lines = content.split("\r\n");
        List<String>[] lists = new ArrayList[3];
        for (int i = 0; i < lists.length; i++) {
            lists[i] = new ArrayList<>();
        }
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            lists[i % 3].add(line);
        }

        IntStream.rangeClosed(1, threads).forEach(i -> {
            new Thread(new Asynx(i, lists[i - 1])).start();
        });
    }

    private static String readUsingFiles(String fileName) throws IOException {//Метод, помогающий считать из txt-файла данные
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

class Asynx extends Downloader implements Runnable {

    private int num;
    private List<String> list;

    public Asynx(int num, List<String> files) {
        this.num = num;
        this.list = files; //Получаем список ссылок
    }

    @Override
    public void run() {
        System.out.println("%d starting ...".formatted(num));
        String name = "";//Хранит имя и формат для файла
        InputStream in = null;//Принимает файл
        FileOutputStream out = null;//Отправляет файл в директорию
        URL url;//Хранит вычленённую ссылку
        try {
            for (String urls : list) {//Вычленяем ссылку и прогоняем её по алгоритму
                url = new URL(urls);
                in = new BufferedInputStream(url.openStream());
                for (String s : String.valueOf(url).split("/")) {//Вычленяем имя и формат файла из ссылки
                    name = s;//Подразумевается,что в ссылке содержится формат и имя файла(Например, картинки)
                }//По сути мы просто берём последний сегмент, т.к. 99% ссылок файлов кончается на имя и формат файла
                out = new FileOutputStream(outer + name);
                byte[] buffer = new byte[1024];//Начинаем запись по КБ
                int count = -1;//Проверка конца
                while (true) {
                    count = in.read(buffer);//Важно присвоить значение ЗДЕСЬ, т.к. приём фотографий может начинаться с 0
                    if (count == -1) {
                        break;//Если нашли конец
                    }
                    out.write(buffer, 0, count);//Запись КБ в файлик(Лучше его не трогать, пока не загрузит весь)
                    try {
                        Thread.sleep((long) (count / speed));                    // RateLimiter НЕ работает!
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("%d поток загрузил '%s' файл!".formatted(num, name));//Когда окончится закачка файла, то выйдет оповещение
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("%d is done".formatted(num));
    }
}