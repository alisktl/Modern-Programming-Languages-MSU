import exception.NoOperatorCanStandTogetherException;
import exception.UnknownSymbolException;
import exception.WrongCountParenthesesException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alisher
 * @project Task2
 */
public class FormatEquation {

    public static List<String> format(String equation) {

        List<String> formattedEquation = new ArrayList<>();

        // Удаляем все whitespaces
        equation = equation.replaceAll("\\s+", "");

        int openParenthesisCount = 0;
        int closeParenthesisCount = 0;

        String prevNumber = null;
        boolean prevOperator = false;

        for (int i = 0; i < equation.length(); i++) {
            char token = equation.charAt(i);

            if (token >= 48 && token <= 57) {
                prevOperator = false;

                if (prevNumber == null) {
                    prevNumber = String.valueOf(token);
                    formattedEquation.add(prevNumber);
                } else {
                    formattedEquation.remove(formattedEquation.size() - 1);
                    prevNumber = prevNumber + String.valueOf(token);
                    formattedEquation.add(prevNumber);
                }
            } else if (isOperator(token)) {
                prevNumber = null;

                if (prevOperator) {
                    // Операторы не могут находиться друг за другом
                    throw new NoOperatorCanStandTogetherException("No operators can stand together");
                }

                formattedEquation.add(String.valueOf(token));

                prevOperator = true;
            } else if (isParenthesis(token)) {
                formattedEquation.add(String.valueOf(token));

                if (token == '(') {
                    ++openParenthesisCount;
                } else if (token == ')') {
                    ++closeParenthesisCount;
                }
            } else {
                // Найден неизвестный символ
                throw new UnknownSymbolException("Unknown symbol found: " + token);
            }
        }

        if (openParenthesisCount != closeParenthesisCount) {
            // Не равное количество открывающихся и закрывающихся скобок
            throw new WrongCountParenthesesException("Count of open and close parentheses are not equal");
        }

        return formattedEquation;
    }

    private static boolean isOperator(char token) {
        return token == '+' || token == '-' || token == '*' || token == '/' || token == '^';
    }

    private static boolean isParenthesis(char token) {
        return token == '(' || token == ')';
    }
}
