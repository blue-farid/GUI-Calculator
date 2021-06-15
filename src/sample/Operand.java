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
}
