package sample;

public enum Operand {
    SUM('+'),SUB('-'),MUL('*'),DIV('/'),
    EQUAL('='),POW('^');
    private char opr;
    Operand(char opr) {
        this.opr = opr;
    }

    public char getOpr() {
        return opr;
    }
    public static boolean isOperand(char opr) {
        for (Operand operand: Operand.values()) {
            if (operand.getOpr() == opr) {
                return true;
            }
        }
        return false;
    }
}
