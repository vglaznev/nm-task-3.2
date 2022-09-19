import java.util.function.UnaryOperator;

import static java.lang.Math.abs;

public class BisectionMethod {
    public double solve(UnaryOperator<Double> function, double a1, double a2, double epsilon) {
        if (function.apply(a1) * function.apply(a2) < 0) {
            throw new IllegalArgumentException("Функция принимает одинаковые знаки на концах отрезка!");
        }
        double a3, f1, f2, f3;
        Double result = null;
        while (abs(a2 - a1) >= 2 * epsilon) {
            a3 = (a1 + a2) / 2;
            f3 = function.apply(a3);

            if (abs(f3) <= 2 * epsilon) {
                result = a3;
                break;
            }
            f1 = function.apply(a1);
            if (f1 * f3 < 0) {
                a2 = a3;
            } else {
                a1 = a3;
            }
        }
        if(result == null){
            result = a3 = (a1 + a2) / 2;
        }
        return result;
    }
}
