package main;

/**
 * Calculator Interface.
 */
public interface Calculator {
    double sum(double a, double b);

    /**
     * Subtract double.
     *
     * @param a the a
     * @param b the b
     * @return the double
     */
    double subtract(double a,double b);

    /**
     * Multiply double.
     *
     * @param a the a
     * @param b the b
     * @return a * b
     */
    double multiply(double a, double b);

    /**
     * Division double.
     *
     * @param a the a
     * @param b the b
     * @return a / b
     */
    Double division(double a, double b);

    /**
     * Power double.
     *
     * @param a the a
     * @param b the b
     * @return a power of b
     */
    double power(double a, double b);

    /**
     * Sin double.
     *
     * @param a the a
     * @return sine of a
     */
    double sin(double a);

    /**
     * Cos double.
     *
     * @param a the a
     * @return cosine of a
     */
    double cos(double a);

    /**
     * Tan double.
     *
     * @param a the a
     * @return tangent of a
     */
    Double tan(double a);

    /**
     * Cot double.
     *
     * @param a the a
     * @return cotangent of a
     */
    Double cot(double a);
}
