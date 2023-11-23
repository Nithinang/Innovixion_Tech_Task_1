package model;


public class expression {

    public double evaluateExpression(String expression) throws Exception {

        expression = expression.replaceAll("\\s+", "");

        // Evaluate expression using BODMAS rule
        return evaluateBODMAS(expression);
    }

    private static double evaluateBODMAS(String expression) throws Exception {
        while (expression.contains("(")) {
            int startIndex = expression.lastIndexOf("(");
            int endIndex = expression.indexOf(")", startIndex);

            if (startIndex == -1 || endIndex == -1) {
                throw new Exception("Mismatched parentheses");
            }

            String subExpression = expression.substring(startIndex + 1, endIndex);
            double subResult = evaluateBODMAS(subExpression);

            expression = expression.substring(0, startIndex) + subResult + expression.substring(endIndex + 1);
        }

        // Evaluate multiplication (*) and division (/)
        expression = evaluateMultiplicationAndDivision(expression, '*');
        expression = evaluateMultiplicationAndDivision(expression, '/');

        // Evaluate addition (+) and subtraction (-)
        expression = evaluateAdditionAndSubtraction(expression, '+');
        expression = evaluateAdditionAndSubtraction(expression, '-');

        // The final result after evaluating the entire expression
        return Double.parseDouble(expression);
    }

    private static String evaluateMultiplicationAndDivision(String expression, char Operator) {
        while (expression.contains(Character.toString(Operator))) {
            int operatorIndex = expression.indexOf(Operator);
            int leftIndex = findLeftOperandIndex(expression, operatorIndex);
            int rightIndex = findRightOperandIndex(expression, operatorIndex);

            double leftOperand = Double.parseDouble(expression.substring(leftIndex, operatorIndex));
            double rightOperand = Double.parseDouble(expression.substring(operatorIndex + 1, rightIndex));

            double result;
            if (Operator == '*') {
                result = leftOperand * rightOperand;
            } else { // operator == '/'
                if (rightOperand == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = leftOperand / rightOperand;
            }

            expression = expression.substring(0, leftIndex) + result + expression.substring(rightIndex);
        }
        return expression;
    }

    private static String evaluateAdditionAndSubtraction(String expression, char operator) {
        while (expression.contains(Character.toString(operator))) {
            int operatorIndex = expression.indexOf(operator);
            int leftIndex = findLeftOperandIndex(expression, operatorIndex);
            int rightIndex = findRightOperandIndex(expression, operatorIndex);

            double leftOperand = Double.parseDouble(expression.substring(leftIndex, operatorIndex));
            double rightOperand = Double.parseDouble(expression.substring(operatorIndex + 1, rightIndex));

            double result;
            if (operator == '+') {
                result = leftOperand + rightOperand;
            } else { 
                result = leftOperand - rightOperand;
            }

            expression = expression.substring(0, leftIndex) + result + expression.substring(rightIndex);
        }
        return expression;
    }

    private static int findLeftOperandIndex(String expression, int operatorIndex) {
        for (int i = operatorIndex - 1; i >= 0; i--) {
            if (!Character.isDigit(expression.charAt(i)) && expression.charAt(i) != '.') {
                return i + 1;
            }
        }
        return 0;
    }

    private static int findRightOperandIndex(String expression, int operatorIndex) {
        for (int i = operatorIndex + 1; i < expression.length(); i++) {
            if (!Character.isDigit(expression.charAt(i)) && expression.charAt(i) != '.') {
                return i;
            }
        }
        return expression.length();
    }
}
