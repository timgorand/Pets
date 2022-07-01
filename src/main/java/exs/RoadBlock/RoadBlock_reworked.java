package exs.RoadBlock;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Написать программу, которая при помощи csv файла(он прилагается к заданию) определить на каких улицах Санкт-Петербурга
//заблокированно движение в указанную пользователем дату
public class RoadBlock_reworked {
    //Переделан после проверки
    public static void main(String[] args) throws IOException, CsvValidationException, ParseException {
        String text = "^\\d{8}\\Z";
        Pattern pattern = Pattern.compile(text);
        String csvFile = "./src/main/java/exs/RoadBlock/Road_block.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        String[] stringOfData;
        BufferedReader readln = new BufferedReader(new InputStreamReader(System.in));
        boolean check=false;
        LocalDate date_start = LocalDate.now();
        LocalDate date_end = date_start;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter formatter_input = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        System.out.println("Введите дату в формате 'дд.мм.гггг'");
        LocalDate corret_date = LocalDate.parse(readln.readLine(),formatter_input);
        while ((stringOfData = reader.readNext()) != null) {
            if (!stringOfData[0].equals("Код")){
                outerloop:
                {
                    for (String s : stringOfData) {
                        Matcher matcher = pattern.matcher(s);
                        while (matcher.find()) {
                            if (!check) {
                                date_start = LocalDate.parse(s, formatter);
                                check = true;
                            } else {
                                date_end = LocalDate.parse(s, formatter);
                                check = false;
                                break outerloop;
                            }
                        }
                    }
                }
            }
            if(!date_start.equals(LocalDate.now()) && !date_end.equals(LocalDate.now())){
                if(corret_date.isAfter(date_start) && corret_date.isBefore(date_end)){
                    date_end = LocalDate.now();
                    date_start = date_end;
                    System.out.println(stringOfData[7]);
                }
            }
        }
    }
}
