package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import main.MyCalculator;
import java.text.DecimalFormat;
import java.util.Objects;

public class Controller {

    private MyCalculator calculator = new MyCalculator();
    private boolean afterReceiveAnswer = false;
    @FXML private TextField textField = new TextField();
    @FXML
    private void button(ActionEvent ae) {
        buttonPressed(((Button)ae.getSource()).getText());
    }
    private void buttonPressed(String str) {
        if (isAfterReceiveAnswer()) {
            boolean state = false;
            for (Operand operand : Operand.values()) {
                String operandStr = "" + operand.getOpr();
                if (str.equals(operandStr)) {
                    state = true;
                    break;
                }
            }
            setAfterReceiveAnswer(false);
            if (!state) {
                resetInput();
            }
        }
        addInput(str);
        if (str.equals("=")) {
            setAfterReceiveAnswer(true);
            processInput();
        }
    }

    @FXML
    private void keyPressed(KeyEvent ae) {
        boolean isOperand = false;
        try {
            isOperand = Operand.isOperand(ae.getText().charAt(0));
        } catch (StringIndexOutOfBoundsException e) {}
        if (ae.getCode().isDigitKey() || ae.getCode().isKeypadKey() ||
                ae.getCode() == KeyCode.EQUALS || ae.getCode() == KeyCode.PERIOD ||
                isOperand || ae.getText().equals(".")) {
            buttonPressed(ae.getText());
        } else if (ae.getCode() == KeyCode.ESCAPE) {
            acButton(new ActionEvent());
        } else if (ae.getCode() == KeyCode.BACK_SPACE) {
            backButton(new ActionEvent());
        } else if (ae.getCode() == KeyCode.ENTER) {
            buttonPressed("=");
        }
    }
    @FXML
    private void backButton(ActionEvent ae) {
        try {
            textField.setText(textField.getText().substring(0,textField.getLength() - 1));
        } catch (StringIndexOutOfBoundsException e) {

        }
    }

    public void setAfterReceiveAnswer(boolean afterReceiveAnswer) {
        this.afterReceiveAnswer = afterReceiveAnswer;
    }

    public boolean isAfterReceiveAnswer() {
        return afterReceiveAnswer;
    }

    private void processInput() {
        char opr = 0;
        double num1 = 0;
        double num2 = 0;
        String input = textField.getText();
        input = input.replaceAll("\\s","");
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
            textField.setText(Objects.requireNonNull(new DecimalFormat().format(res)));
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            invalidInput();
        }
    }

    @FXML
    private void acButton(ActionEvent ae) {
        resetInput();
    }

    private Double inputToDouble() {
        double num = 0;
        try {
            num = Double.parseDouble(getInput());
        } catch (NumberFormatException e) {
            invalidInput();
            return null;
        }
        return num;
    }
    @FXML
    private void sinButton(ActionEvent ae) {
        try {
            setInput(calculator.sin(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {}
    }
    @FXML
    private void cosButton(ActionEvent ae) {
        try {
            setInput(calculator.cos(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {}
    }
    @FXML
    private void tanButton(ActionEvent ae) {
        try {
            setInput(calculator.tan(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {}
    }
    @FXML
    private void cotButton(ActionEvent ae) {
        try {
            setInput(calculator.cot(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {}
    }
    private void invalidInput() {
        textField.setText("invalid input!");
    }
    private void addInput(String str) {
        textField.setText(textField.getText() + str);
    }
    private void resetInput() {
        textField.setText("");
    }
    private Double calculate(double a, double b, char opr) {
        if (opr == Operand.SUM.getOpr()) {
            return calculator.sum(a,b);
        } else if (opr == Operand.SUB.getOpr()) {
            return calculator.subtract(a,b);
        } else if (opr == Operand.DIV.getOpr()) {
            return calculator.division(a,b);
        } else if (opr == Operand.MUL.getOpr()) {
            return calculator.multiply(a,b);
        } else if (opr == Operand.POW.getOpr()) {
            return calculator.power(a,b);
        }
        return null;
    }
    private void setInput(double num) {
        textField.setText(new DecimalFormat().format(num));
    }
    private String getInput() {
        return textField.getText();
    }
}
