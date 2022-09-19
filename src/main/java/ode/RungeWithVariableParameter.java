package ode;

import function.TableFunction;

public class RungeWithVariableParameter extends RungeKuttaFourthMethod {

    public RungeWithVariableParameter(FourArgumentFunction[] odes, double initialValue0, double initialValue2, double x0, double step, double[] nodes) {
        super(odes, new double[]{initialValue0, 0, initialValue2}, x0, step, nodes);
    }

    public TableFunction[] solve(double initialValue1) {
        initialValues[1] = initialValue1;
        return solve();
    }
}
