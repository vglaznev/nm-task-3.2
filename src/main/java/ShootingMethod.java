import function.Function;
import function.TableFunction;
import ode.FourArgumentFunction;
import ode.RungeWithVariableParameter;
import splitter.UniformSplitter;

public class ShootingMethod extends BisectionMethod{
    private FourArgumentFunction[] system;
    private double A, B, C;
    private double[] nodes;
    private double step;

    private TableFunction[] currentSolution;

    public ShootingMethod(FourArgumentFunction[] system, double a, double b, double c, double[] nodes, double step) {
        this.system = system;
        A = a;
        B = b;
        C = c;
        this.nodes = nodes;
        this.step = step;
    }

    private double calculatePhi(double shootingParameter) {
        RungeWithVariableParameter method = new RungeWithVariableParameter(system, A, B, nodes[0], step, nodes);
        currentSolution = method.solve(shootingParameter);
        return (currentSolution[1].y()[nodes.length - 1] - C);
    }

    public double solve(double a1, double a2, double epsilon){
        return solve(this::calculatePhi, a1, a2, epsilon);
    }

    public TableFunction[] getSolution() {
        return currentSolution;
    }

    public static class Builder {
        private FourArgumentFunction[] system;

        private double A, B, C;
        private double[] nodes;
        private double step;

        public Builder system(FourArgumentFunction[] system) {
            this.system = system;
            return this;
        }


        public Builder initialValues(double A, double B, double C) {
            this.A = A;
            this.B = B;
            return this;
        }

        public Builder nodes(double[] nodes) {
            this.nodes = nodes;
            return this;
        }

        public Builder step(double step) {
            this.step = step;
            return this;
        }


        public ShootingMethod build() {
            return new ShootingMethod(system, A, B, C, nodes, step);
        }
    }
}
