import java.util.List;

/**
 * @author alisher
 * @project Task2
 */
public class Calculator {
    public static double calculate(String expression) {
        List<String> formattedExpression = FormatEquation.format(expression);

        Stack<String> polishNotation = ShuntingYard.toPolishNotation(formattedExpression);
        Stack<String> inversePolishNotation = new Stack<>();

        while (!polishNotation.isEmpty()) {
            inversePolishNotation.push(polishNotation.pop());
        }

        return InversePolishNotation.calculate(inversePolishNotation);
    }
}
