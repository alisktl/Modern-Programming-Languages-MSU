package exception;

/**
 * @author alisher
 * @project Task2
 */
public class WrongCountParenthesesException extends RuntimeException {

    public WrongCountParenthesesException() {
        super();
    }

    public WrongCountParenthesesException(String s) {
        super(s);
    }
}
