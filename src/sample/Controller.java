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

/**
 * Controller.
 */
public class Controller {

    // a Calculator.
    private MyCalculator calculator = new MyCalculator();
    // a boolean to determine a specific state.
    private boolean afterReceiveAnswer = false;
    // TextField of the calculator.
    @FXML private TextField textField = new TextField();

    /** any normal buttons that has got clicked, except some special one
     * (like AC,Back, sin, cos...) will send an ActionEvent to this method.
     * @param ae
     */

    @FXML
    private void button(ActionEvent ae) {
        buttonPressed(((Button)ae.getSource()).getText());
    }

    /** that will do the required works after a button has been pressed.
     *
     * @param str
     */
    public void buttonPressed(String str) {
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

    /** that will do the required works after a key pressed.
     *
     * @param ae
     */
    @FXML
    private void keyPressed(KeyEvent ae) {
        // that will show, the key was an operand key or not.
        boolean isOperand = false;
        // to determine the key was operand or not.
        try {
            isOperand = Operand.isOperand(ae.getText().charAt(0));
        } catch (StringIndexOutOfBoundsException e) {}
        // to determine the key is a normal key or a special key.
        if (ae.getCode().isDigitKey() || ae.getCode().isKeypadKey() ||
                ae.getCode() == KeyCode.EQUALS || ae.getCode() == KeyCode.PERIOD ||
                isOperand || ae.getText().equals(".")) {
            // pass the button text to normal key method.
            buttonPressed(ae.getText());
            // Escape key has been synced to AC button.
        } else if (ae.getCode() == KeyCode.ESCAPE) {
            acButton(new ActionEvent());
            // BackSpace key has been synced to Back button.
        } else if (ae.getCode() == KeyCode.BACK_SPACE) {
            backButton(new ActionEvent());
            // Enter key has been synced to
        } else if (ae.getCode() == KeyCode.ENTER) {
            buttonPressed("=");
        }
    }

    /** that deletes a character of the input and set the new string to the text field.
     *
     * @param ae
     */
    @FXML
    public void backButton(ActionEvent ae) {
        if (isAfterReceiveAnswer()) {
            resetInput();
            setAfterReceiveAnswer(false);
        }
        try {
            textField.setText(textField.getText().substring(0,textField.getLength() - 1));
        } catch (StringIndexOutOfBoundsException e) {

        }
    }

    /**
     * Sets after receive answer.
     *
     * @param afterReceiveAnswer the after receive answer
     */
    public void setAfterReceiveAnswer(boolean afterReceiveAnswer) {
        this.afterReceiveAnswer = afterReceiveAnswer;
    }

    /**
     * Is after receive answer boolean.
     *
     * @return the boolean
     */
    public boolean isAfterReceiveAnswer() {
        return afterReceiveAnswer;
    }

    /**
     * that will process the input to do the write thing.
     */
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
        } catch (StringIndexOutOfBoundsException | IllegalArgumentException e) {
            invalidInput();
        }
    }

    /**
     * that will reset input when AC button pressed.
     * @param ae
     */
    @FXML
    private void acButton(ActionEvent ae) {
        resetInput();
    }

    /**
     * that will convert input to double.
     * @return input that has been converted to double.
     */
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

    /**
     * that method call when a trigonometric button pressed.
     * @param ae
     */
    @FXML
    public void trigonometricFuncs(ActionEvent ae) {
        String opr = ((Button)ae.getSource()).getText();
        switch (opr) {
            case "sin":
                sinButton();
                break;
            case "cos":
                cosButton();
                break;
            case "tan":
                tanButton();
                break;
            case "cot":
                cotButton();
                break;
        }
        setAfterReceiveAnswer(true);
    }
    /**
     * that will calculate sine of input when sine button pressed.
     */
    private void sinButton() {
        try {
            setInput(calculator.sin(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {}
    }

    /**
     * that will calculate cosine of input when cosine button pressed.
     */
    private void cosButton() {
        try {
            setInput(calculator.cos(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {}
    }

    /**
     * that will calculate tangent of input when tangent button pressed.
     */
    private void tanButton() {
        try {
            setInput(calculator.tan(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {
            invalidInput();
        }
    }

    /**
     * that will calculate cotangent of input when cotangent button pressed.
     */
    private void cotButton() {
        try {
            setInput(calculator.cot(Objects.requireNonNull(inputToDouble())));
        } catch (NullPointerException e) {
            invalidInput();
        }
    }

    /**
     * that will set input to a suitable message when an invalid input typed.
     */
    private void invalidInput() {
        textField.setText("invalid input!");
    }

    /**
     * that add a string to input.
     * @param str
     */
    private void addInput(String str) {
        textField.setText(textField.getText() + str);
    }

    /**
     * that clear the input.
     */
    private void resetInput() {
        textField.setText("");
    }

    /**
     * that calculate with using the calculator object based on its param.
     * @param a
     * @param b
     * @param opr
     * @return
     */
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

    /**
     * set input to a double.
     * @param num
     */
    private void setInput(double num) {
        textField.setText(new DecimalFormat().format(num));
    }

    /**
     * set input to a string.
     * @param str
     */
    private void setInput(String str) {
        textField.setText(str);
    }
    /**
     * @return the input string
     */
    private String getInput() {
        return textField.getText();
    }
}
