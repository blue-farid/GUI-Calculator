package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.MyCalculator;

import java.text.DecimalFormat;
import java.util.Objects;

public class Controller {
    private MyCalculator calculator = new MyCalculator();
    private String input = "";
    @FXML
    private Label label = new Label();
    public void button(ActionEvent ae) {
        String str = ((Button) ae.getSource()).getText();
        addInput(str);
        if (str.equals("=")) {
            processInput();
            resetInput();
        } else {
            setInputToLabel();
        }
    }

    private void processInput() {
        char opr = 0;
        double num1 = 0;
        double num2 = 0;
        int index = input.indexOf('=');
        input = input.substring(0,index);
        int i = 0;
        for (char c: input.toCharArray()) {
            if (!((c > 47 && c < 58) || c == 46)) {
                opr = c;
                break;
            }
            i++;
        }
        try {
            num1 = Double.parseDouble(input.substring(0, i));
            num2 = Double.parseDouble(input.substring(i + 1));
            Double res = calculate(num1, num2, opr);
            label.setText(Objects.requireNonNull(new DecimalFormat().format(res)));
        } catch (NumberFormatException e) {
            label.setText("Invalid Input!");
            resetInput();
        }
    }

    @FXML
    private void acButton(ActionEvent ae) {
        resetInput();
        setInputToLabel();
    }
    private void addInput(String str) {
        input += str;
    }
    private void resetInput() {
        input = "";
    }
    private void setInputToLabel() {
        label.setText(input);
    }
    private Double calculate(double a, double b, char opr) {
        if (opr == '+') {
            return calculator.sum(a,b);
        } else if (opr == '-') {
            return calculator.subtract(a,b);
        } else if (opr == '/') {
            return calculator.division(a,b);
        } else if (opr == '*') {
            return calculator.multiply(a, b);
        }
        return null;
    }
}
