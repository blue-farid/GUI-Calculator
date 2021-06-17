package main;

/**
 * My calculator.
 */
public class MyCalculator implements Calculator {

    public double sum(double a, double b) {
        return a + b;
    }
    public double subtract(double a,double b) {
        return a - b;
    }
    public double multiply(double a, double b) {
        return a * b;
    }
    public Double division(double a, double b) {
        // to avoiding "division by zero" error.
        if (b == 0) {
            return null;
        }
        return  a / b;
    }
    public double power(double a, double b) {
        return Math.pow(a,b);
    }
    public double sin(double a) {
        return Math.sin(Math.toRadians(a));
    }
    public double cos(double a) {
        return Math.cos(Math.toRadians(a));
    }
    public Double tan(double a) {
        if (a != 0 && a % 90 == 0 && (a / 90) % 2 != 0) {
            return null;
        }
        return Math.tan(Math.toRadians(a));
    }
    public Double cot(double a) {
        double b = Math.tan(Math.toRadians(a));
        // to avoiding "division by zero" error.
        if (a % 180 == 0) {
            return null;
        }
        return 1 / b;
    }
}
