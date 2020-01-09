package exception;

/**
 * @author alisher
 * @project Task2
 */
public class UnknownSymbolException extends RuntimeException {

    public UnknownSymbolException() {
        super();
    }

    public UnknownSymbolException(String s) {
        super(s);
    }
}
