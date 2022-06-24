package exs;

import java.util.ArrayList;

/*Реализуйте иерархию классов:Класс Box является контейнером, он можем содержать в себе другие фигуры. Метод add()
принимает на вход Shape. Нужно добавлять новые фигуры до тех пор, пока для них хватаем места в Box
(будем считать только объём, игнорируя форму. Допустим, мы переливаем жидкость). Если места для добавления новой фигуры
 не хватает, то метод должен вернуть false.*/
public class Shape {
    double volume;

    public Shape(double volume) {
        this.volume = volume;
    }

    public double GetVolume() {
        return volume;
    }
}

class SolidAdd extends Shape {
    double radius;

    public SolidAdd(double volume, double radius) {
        super(volume);
        this.radius = radius;
    }

    public double GetRadius() {
        return radius;
    }

}

class Ball extends SolidAdd { // конкретный класс
    public Ball(double radius) {
        super(Math.PI * Math.pow(radius, 3) * 4 / 3, radius);
    }
}

class Cylinder extends SolidAdd {
    private double heights;

    public Cylinder(double radius, double height) {
        super(Math.PI * Math.pow(radius, 3) * 4 / 3, radius);
        this.heights = height;
    }
}

class Pyramid extends Shape {
    private double height;
    private double s;

    public Pyramid(double height, double s) {
        super(height * s * 4 / 3);
        this.height = height;
        this.s = s;
    }
}

class Box extends Shape {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private double avalible;

    public Box(double avalible) {
        super(avalible);
        this.avalible = avalible;
    }

    public boolean add(Shape shape) {
        if (avalible >= shape.GetVolume()) {
            shapes.add(shape);
            avalible -= shape.GetVolume();
            return true;
        } else return false;
    }
}

class Main {

    public static void main(String[] args) {
        Ball ball = new Ball(4.5);
        Cylinder cylyinder = new Cylinder(2, 2);
        Pyramid pyramid = new Pyramid(100, 100);

        Box box = new Box(1000);

        System.out.println(box.add(ball)); // ok
        System.out.println(box.add(cylyinder)); // ok
        System.out.println(box.add(pyramid)); // failed
    }
}
