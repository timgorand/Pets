package exs.Search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
//Написать программу,которая получает запрос в консоли и возвращает ответ на него. Запрос отправляется на Википедию, откуда
//в виде JSON возвращается файл с данными, достать оттуда ответы

//Чтобы сделать хорошо - надо сделать плохо
//Код рабочий, но грубый.
public class SearchWiki_orkType {
    public static String chronos = "NeveRickAsfJsdIZ;sAWrF";//Антилок
    public static ArrayList<String> taker = new ArrayList<>();//хранитель названий и описаний
    public static ArrayList<String> correcter = new ArrayList<>();//Хранит нужные поля
    public static String crafter = "";//соединяет разделённый текст вместе

    public static Boolean first = false;//Запуск с нужной строчки
    public static Boolean second = false;//correcter нашёл название
    public static Boolean third = false;//correcter нашёл описание
    public static void main(String[] args) throws IOException {
        correcter.add("\"title\"");
        correcter.add("\"snippet\"");
        Parser(JsonToString());//т.к. переменные вне модулей, возвращать ничего не нужно!
        String connecter = "";
        boolean two = false;//Каждый второй объект в листе - описание. Это факт!
        for (String s : taker){
            if (two){
                two = false;
                connecter += s;//Получаем описание
                connecter = connecter.replaceAll("([\"])", " ");//Убираем множество ковычек
                connecter = connecter.replaceAll("\\<.+?\\>", " ");//Убираем теги <span>
                connecter+="...";//В самом файле описание выдаётся очень странными кусками, потому добавляем многоточие
                System.out.println(connecter);//И всё работает!
            }
            else {
                connecter =s + ":";
                two = true;

            }
        }
    }
    public static String JsonToString() throws IOException {//Метод для получения Jsona и последующим преобразованием в строку
        String link = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";//URL един
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        link += URLEncoder.encode( reader.readLine(), StandardCharsets.UTF_8);//Получение и экранирование запроса
        String line = null;
        HttpURLConnection connection = null;
        URL url;
        InputStreamReader isR = null;//Принимает json
        BufferedReader bfR = null;//Отдаёт json
        try {
            url = new URL(link);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//Запрос подключению
            connection.setConnectTimeout(200); //Без таймаута не работает
            connection.connect();
            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()){
                isR = new InputStreamReader(connection.getInputStream());
                bfR = new BufferedReader(isR);
                line = bfR.readLine();
            } else {
                System.out.printf("Fail %s", connection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                isR.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bfR.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return line;
    }
    public static void Parser(String s){//Парсерь строк
        String check = s;
        if(first){
            for (String pars : s.split(":")){//После делим по двоеточию
                if (second){//Название
                    taker.add(pars);
                    second =false;
                }
                else if(third){//Описание
                    crafter += pars;//Из-за деления по запятой, описание может делиться, потому собираем, пока есть
                }
                else if(correcter.get(0).equals(pars) || correcter.get(1).equals(pars) ){
                    if (correcter.get(1).equals(pars)){//Проверка на содержимое
                        third = true;
                    }else {
                        second = true;
                    }
                }
                else {
                    break;
                }
            }
        }
        else {
            for (String pars : s.split(",")){//Сначала делим по запятым
                if(chronos.equals(pars)){ //Если всё совпадает, то вываливаемся на следующий уровень
                    break;
                }
                else {
                    chronos = pars;
                    first=true;//Всё началось
                    Parser(pars);//Рекурсия
                    if (!crafter.equals("")){//Выход из рекурсии - проверка описания
                        taker.add(crafter);
                        crafter="";
                        third=false;
                    }
                }
            }
        }
    }
}
