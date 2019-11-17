/**
 * @author alisher
 * @project Task2
 */
public class InversePolishNotation {

    public static double calculate(Stack<String> tokens) {
        double returnValue = 0;

        Stack<String> stack = new Stack<String>();

        while (!tokens.isEmpty()) {
            String token = tokens.pop();

            if (!Operator.operators.containsKey(token)) {
                stack.push(token);
            } else {
                double a = Double.valueOf(stack.pop());
                double b = Double.valueOf(stack.pop());
                switch (token) {
                    case "+":
                        stack.push(String.valueOf(a + b));
                        break;
                    case "-":
                        stack.push(String.valueOf(b - a));
                        break;
                    case "*":
                        stack.push(String.valueOf(a * b));
                        break;
                    case "/":
                        stack.push(String.valueOf(b / a));
                        break;
                    case "^":
                        stack.push(String.valueOf(Math.pow(b, a)));
                        break;
                }
            }
        }

        returnValue = Double.valueOf(stack.pop());

        return returnValue;
    }
}
