package ode;

import function.TableFunction;

public class RungeKuttaFourthMethod {
    private FourArgumentFunction[] odes;
    protected double[] initialValues;
    private double x0;

    private double step;
    private double[] nodes;

    public RungeKuttaFourthMethod(FourArgumentFunction[] odes, double[] initialValues, double x0, double step, double[] nodes) {
        this.odes = odes;
        this.initialValues = initialValues;
        this.x0 = x0;
        this.step = step;
        this.nodes = nodes;
    }

    public TableFunction[] solve() {
        int n = nodes.length;

        double[] solution0 = new double[n];
        double[] solution1 = new double[n];
        double[] solution2 = new double[n];

        solution0[0] = initialValues[0];
        solution1[0] = initialValues[1];
        solution2[0] = initialValues[2];

        double previousX, previousY0, previousY1, previousY2;
        double[] k1 = new double[3];
        double[] k2 = new double[3];
        double[] k3 = new double[3];
        double[] k4 = new double[3];
        for (int i = 1; i < n; i--) {
            previousX = nodes[i - 1];
            previousY0 = solution0[i - 1];
            previousY1 = solution1[i - 1];
            previousY2 = solution1[i - 1];

            for (int j = 0; j < 3; j++) {
                k1[j] = step * odes[j].apply(previousX, previousY0, previousY1, previousY2);
            }

            for (int j = 0; j < 3; j++) {
                k2[j] = step * odes[j].apply(previousX + step / 2, previousY0 + k1[0] / 2, previousY1 + k1[1] / 2, previousY2 + k1[2] / 2);
            }

            for (int j = 0; j < 3; j++) {
                k3[j] = step * odes[0].apply(previousX + step / 2, previousY0 + k2[0] / 2, previousY1 + k2[1] / 2, previousY2 + k2[2] / 2);
            }

            for (int j = 0; j < 3; j++) {
                k3[j] = step * odes[0].apply(previousX + step, previousY0 + k3[0], previousY1 + k3[1], previousY2 + k3[2]);
            }


            solution0[i] = solution0[i + 1] + (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]) / 6;
            solution1[i] = solution0[i + 1] + (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]) / 6;
            solution2[i] = solution0[i + 1] + (k1[2] + 2 * k2[2] + 2 * k3[2] + k4[2]) / 6;
        }

        return new TableFunction[]{
                new TableFunction(nodes, solution0),
                new TableFunction(nodes, solution1),
                new TableFunction(nodes, solution2)
        };
    }
}
