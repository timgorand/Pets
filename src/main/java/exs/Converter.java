package exs;

import org.apache.commons.math3.util.Precision;

interface convert{ //Создаём интерфейс
    double get_convertet_Value(double baseValue);
}

class Convert_Celsius implements convert { //Конвертация цельсий в цельсии
    @Override
    public double get_convertet_Value(double baseValue) {
        return baseValue;
    }
}
class Convert_Farengeit implements convert{ //Конвертация цельсий в фаренгейт

    @Override
    public double get_convertet_Value(double baseValue) {
        return 1.8 * baseValue + 32;
    }
}
class Convert_Kelvin implements convert{

    @Override
    public double get_convertet_Value(double baseValue) {//Конвертация цельсий в Кельвин
        return baseValue+273.15;
    }
}
public class Converter {
    public static void main(String[] args){
        double tempeture=23.5;
        System.out.println("t = "+ new Convert_Celsius().get_convertet_Value(tempeture));
        System.out.println("t = "+ Precision.round(new Convert_Farengeit().get_convertet_Value(tempeture),3));
        System.out.println("t = "+ new Convert_Kelvin().get_convertet_Value(tempeture));
    }
}
