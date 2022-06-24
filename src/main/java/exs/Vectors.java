package exs;

import java.util.Random;

/*

У него должны быть:

    конструктор с параметрами в виде списка координат x, y, z
    метод, вычисляющий длину вектора. Корень можно посчитать с помощью Math.sqrt():

    $\sqrt{x^2 + y^2 + z^2}$
    метод, вычисляющий скалярное произведение:

    $x_1x_2 + y_1y_2 + z_1z_2$
    метод, вычисляющий векторное произведение с другим вектором:

    $(y_1z_2 - z_1y_2, z_1x_2 - x_1z_2, x_1y_2 - y_1x_2)$
    метод, вычисляющий угол между векторами (или косинус угла): косинус угла между векторами равен скалярному произведению
    векторов, деленному на произведение модулей (длин) векторов:

    $\frac{(a,b)}{|a| \cdot |b|}$
    методы для суммы и разности:

    $(x_1 + x_2, y_1 + y_2, z_1 + z_2)$

    $(x_1 - x_2, y_1 - y_2, z_1 - z_2)$

    статический метод, который принимает целое число N, и возвращает массив случайных векторов размером N.


Если метод возвращает вектор, то он должен возвращать новый объект, а не менять базовый.*/
public class Vectors {
    public double x;
    public double y;
    public double z;

    public Vectors(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double scalar(Vectors vectors) {
        return vectors.x * x + vectors.y * y + vectors.z * z;
    }

    public Vectors vec_prod(Vectors vectors) {
        return new Vectors(x * vectors.z - z * vectors.y,
                z * vectors.x - x * vectors.z,
                x * vectors.y - y * vectors.x);

    }

    public double cos(Vectors vectors) {
        return scalar(vectors) / length() * vectors.length();
    }

    public Vectors vec_plus(Vectors vectors) {
        return new Vectors(x + vectors.x, y + vectors.y, z + vectors.z);
    }

    public Vectors vec_minus(Vectors vectors) {
        return new Vectors(x - vectors.x, y - vectors.y, z - vectors.z);
    }

    public static double[][] vector_generator(int N) {
        double[][] vectors_array = new double[N][3];
        Random r = new Random();
        for(int i = 0; i < N; i++) {
            vectors_array[i][0] = Math.round(r.nextDouble(1, 10));
            vectors_array[i][1] = Math.round(r.nextDouble(1, 10));
            vectors_array[i][2] = Math.round(r.nextDouble(1, 10));
        }
        return vectors_array;
    }
}
