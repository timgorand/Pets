package exs;

public class Equation {
    /*Найдите корень уравнения

$cos(x^5) + x^4 - 345.3 * x - 23 = 0$
на отрезке [0; 10] с точностью по x не хуже, чем 0.001. Известно, что на этом промежутке корень единственный.*/
    private static double Find_root(double x)
    {
        return (Math.cos(Math.pow(x,5)) + Math.pow(x,4)-345.3 * x - 23);
    }
    public double solve (double start, double end)
    {
        if(start-end <= 0.001)
        {
            return start;
        }
        double x = start + (end-start)/2;
        if(Find_root(x)*Find_root(start)>0)
        {
            return solve(x,end);
        }
        else return solve(start,x);
    }

}
