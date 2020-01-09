import java.util.List;

/**
 * @author alisher
 * @project Task2
 */
public class ShuntingYard {

    private static boolean isHigherPrecedence(String op, String sub) {
        return (Operator.operators.containsKey(sub) &&
                Operator.operators.get(sub).precedence >= Operator.operators.get(op).precedence);
    }

    // Конвертируем выражение в польскую запись
    public static Stack toPolishNotation(List<String> formattedExpression) {
        Stack<String> output = new Stack<>();
        Stack<String> stack = new Stack<>();

        for (String token : formattedExpression) {
            if (Operator.operators.containsKey(token)) {
                while (!stack.isEmpty() && isHigherPrecedence(token, stack.peek()))
                    output.push(stack.pop());
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.peek().equals("("))
                    output.push(stack.pop());
                stack.pop();
            } else {
                output.push(token);
            }
        }

        while (!stack.isEmpty()) {
            output.push(stack.pop());
        }

        return output;
    }
}
