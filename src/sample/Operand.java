package sample;

/**
 * The enum Operand.
 */
public enum Operand {
    /**
     * Sum operand.
     */
    SUM('+'),
    /**
     * Subtract operand.
     */
    SUB('-'),
    /**
     * Multiply operand.
     */
    MUL('*'),
    /**
     * Division operand.
     */
    DIV('/'),
    /**
     * Equal operand.
     */
    EQUAL('='),
    /**
     * Power operand.
     */
    POW('^');
    private char opr;
    Operand(char opr) {
        this.opr = opr;
    }

    /**
     * Gets opr
     *
     * @return the opr
     */
    public char getOpr() {
        return opr;
    }

    /**
     * Is operand boolean.
     *
     * @param opr the opr
     * @return true if @param is operand.
     *         false if @param is not operand.
     */
    public static boolean isOperand(char opr) {
        for (Operand operand: Operand.values()) {
            if (operand.getOpr() == opr) {
                return true;
            }
        }
        return false;
    }
}
