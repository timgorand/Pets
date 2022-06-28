package exs.RoadBlock;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//Написать программу, которая при помощи csv файла(он прилагается к заданию) определить на каких улицах Санкт-Петербурга
//заблокированно движение в указанную пользователем дату
public class RoadBlock {
    public static void main(String[] args) throws IOException, CsvValidationException, ParseException {
        String csvFile = "./src/main/java/exs/RoadBlock/Road_block.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        String[] stringOfData;
        BufferedReader readln = new BufferedReader(new InputStreamReader(System.in));

        Date date_start;
        Date date_end;
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formater_input = new SimpleDateFormat("dd.MM.yyyy");

        System.out.println("Введите дату в формате 'дд.мм.гггг'");
        Date corret_date = formater_input.parse(readln.readLine());
        while ((stringOfData = reader.readNext()) != null) {
            if (!stringOfData[0].equals("Код")){
                try {
                    date_start=formater.parse(stringOfData[10]);
                    date_end = formater.parse(stringOfData[11]);
                } catch (Exception ignored) {
                    date_start=formater.parse(stringOfData[11]);
                    date_end=formater.parse(stringOfData[12]);
                }
                if(date_start.getTime() < corret_date.getTime() && corret_date.getTime()<date_end.getTime()){
                    System.out.println(stringOfData[7]);
                }
            }

        }
    }
}
