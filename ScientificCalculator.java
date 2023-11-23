import java.util.Scanner;

import service.operator;
import model.expression;

public class ScientificCalculator {
    private static operator Operator = new operator();
    private static expression Expression = new expression();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter an expression (e.g., 2 + 3 * 4), 'square', 'sqrt', 'sin', 'cos', 'tan', 'log', or type 'exit' to quit:");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the calculator. Goodbye!");
                break; 
            }

            try {
                double result;
                if (input.equalsIgnoreCase("square")) {
                    result = Operator.squareOperation(scanner);
                } else if (input.equalsIgnoreCase("sqrt")) {
                    result = Operator.sqrtOperation(scanner);
                } else if (input.equalsIgnoreCase("sin") || input.equalsIgnoreCase("cos") || input.equalsIgnoreCase("tan")) {
                    result = Operator.trigonometricOperation(scanner, input);
                } else if (input.equalsIgnoreCase("log")) {
                    result = Operator.logarithmOperation(scanner);
                } else {
                    result = Expression.evaluateExpression(input);
                }

                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }


}
