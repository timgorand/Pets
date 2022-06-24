package exs;

import org.apache.commons.math3.util.Precision;

public class Equation {
    /*Найдите корень уравнения
$cos(x^5) + x^4 - 345.3 * x - 23 = 0$
на отрезке [0; 10] с точностью по x не хуже, чем 0.001. Известно, что на этом промежутке корень единственный.*/
    private static double Find_root(double x) {
        return (Math.cos(Math.pow(x, 5)) + Math.pow(x, 4) - 345.3 * x - 23);
    }

    private static double last_start = 0;
    private static double last_end = 0;
    private static double toch = 0;

    public static double solve(double start, double end) {
        if ((last_end == end && last_start == start)) { // Без этой проверки рекурсия не останавливается НИКОГДА в некоторых случаях
            return start;
        }
        if ((start - end <= 0.001 && start - end > 0) || (start - end) * -1 <= 0.001) // Защита от отрицательных згачений start-end
        {
            return start;
        }
        last_start = start;
        last_end = end;
        double x = start + (end - start) / 2;
        if (Find_root(x) * Find_root(start) > 0) {
            return solve(x, end);
        } else return solve(start, x);
    }

    public static void main(String... args) {
        System.out.println("~" + Precision.round(solve(4, 40), 3));
    }

}
