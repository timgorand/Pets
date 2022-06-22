package exs.Vectors;

import java.util.Random;

public class Vectors {
    public double x;
    public double y;
    public double z;

    public Vectors (double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double length ()
    {
        return Math.sqrt(x*x + y*y + z*z);
    }

    public double scalar(Vectors vectors)
    {
        return vectors.x*x+ vectors.y*y+ vectors.z*z;
    }

    public Vectors vec_prod(Vectors vectors)
    {
        return new Vectors(x * vectors.z - z*vectors.y,
                z*vectors.x- x*vectors.z,
                x*vectors.y- y*vectors.x);
                
    }
    public double cos (Vectors vectors)
    {
        return scalar(vectors) / length() * vectors.length();
    }

    public Vectors vec_plus(Vectors vectors)
    {
        return new Vectors(x +vectors.x, y+vectors.y, z + vectors.z);
    }
    public Vectors vec_minus(Vectors vectors)
    {
        return new Vectors(x -vectors.x, y-vectors.y, z - vectors.z);
    }
    public static double[][] vector_generator(int N)
    {
        double [][] vectors_array = new double[N][3];
        Random r = new Random();
        for(int i = 0; i<N; i++)
        {
            vectors_array[i][0] = Math.round(r.nextDouble(1, 10));
            vectors_array[i][1] = Math.round(r.nextDouble(1, 10));
            vectors_array[i][2] = Math.round(r.nextDouble(1, 10));
        }
        return vectors_array;
    }
}
