/**
 * Contains static methods which represen the different 
 * activation functions, associated with the enums we 
 * created in ActivationType.java. 
 * @author Showmick Das
 * @version 1.0
 */

public class Activation {
    /**
     * Uses the rectified linear unit activation function.
     * @param x the input data point
     * @return the output of the RELU function
     */
    public static double relu(double x) {
        return Math.max(0, x);
    }

    /**
     * Uses the exponential linear unit activation function
     * @param x the input data point
     * @param alpha the input constant for the computation of the ELU formula
     * @return the output of the computation using the formula below 
     */
    public static double elu(double x, double alpha) {
        if (alpha < 0.0 || alpha > 1.0) {
            alpha = 1.0;
        }

        if (x > 0) {
            return x;
        } else {
            return alpha * (Math.exp(x) - 1);
        }
    }

    /**
     * Uses the sigmoid activation function
     * @param x the input data point
     * @return the output of the computation using the formula below 
     */
    public static double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
}
