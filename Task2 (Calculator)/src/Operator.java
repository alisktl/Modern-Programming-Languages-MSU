import java.util.HashMap;
import java.util.Map;

/**
 * @author alisher
 * @project Task2
 */
public enum Operator {

    ADD(1), SUBTRACT(1), MULTIPLY(2), DIVIDE(2), POWER(3);
    public static Map<String, Operator> operators = new HashMap<>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUBTRACT);
        put("*", Operator.MULTIPLY);
        put("/", Operator.DIVIDE);
        put("^", Operator.POWER);
    }};
    final int precedence;

    Operator(int p) {
        precedence = p;
    }
}
