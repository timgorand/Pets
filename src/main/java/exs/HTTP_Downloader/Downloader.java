package exs.HTTP_Downloader;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Downloader {
    public static FileWriter writer;
    public static void main(String[] args) throws IOException {
        String urls = "./src/main/java/exs/HTTP_Downloader/Urls.txt";
        String content = readUsingFiles(urls);
        int count = 1;
        String first = "";
        String second = "";
        String third = "";
        for (String s : content.split("\r\n")) {
            switch (count) {
                case (1):
                    first += s + "\r\n";
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
        System.out.println(first);
        System.out.println("-------");
        Thread thread_1 = new Thread(new First_Asynx(first));
        Thread thread_2 = new Thread(new First_Asynx(second));
        Thread thread_3 = new Thread(new First_Asynx(third));
        thread_1.start();
        thread_2.start();
        thread_3.start();
    }

    private static String readUsingFiles(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

class First_Asynx extends Downloader implements Runnable {
    private URL url;
    private String list;

    public First_Asynx(String list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = "";
        long sizer = 0;
        int bytik = 1024;
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
                out = new FileOutputStream("./src/main/java/exs/HTTP_Downloader/" + name);
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
                sizer += count;
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
            System.out.println("Первый поток загрузил картинку!");
        }
    }
}
class Second_Asynx extends Downloader implements Runnable {
    private URL url;
    private String list;

    public Second_Asynx(String list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = "";
        long sizer = 0;
        int bytik = 1024;
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
                out = new FileOutputStream("./src/store/" + name);
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
                sizer += count;
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
            System.out.println("Второй поток загрузил картинку!");
        }
    }
}
class Third_Asynx extends Downloader implements Runnable {
    private URL url;
    private String list;

    public Third_Asynx(String list) {
        this.list = list;
    }

    @Override
    public void run() {
        String name = "";
        long sizer = 0;
        int bytik = 1024;
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
                out = new FileOutputStream("./src/store/" + name);
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
                sizer += count;
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
                System.out.println("Третий поток загрузил картинку!");
            }
        }
    }
}