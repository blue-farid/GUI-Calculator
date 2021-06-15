package main;
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
        if (b == 0) {
            return null;
        }
        return  a / b;
    }
    public double power(double a, double b) {
        return Math.pow(a,b);
    }
    public double sin(double a) {
        return Math.sin(a);
    }
    public double cos(double a) {
        return Math.cos(a);
    }
    public double tan(double a) {
        return Math.tan(a);
    }
    public Double cot(double a) {
        double b = Math.tan(a);
        if (b == 0) {
            return null;
        }
        return 1 / b;
    }
}
