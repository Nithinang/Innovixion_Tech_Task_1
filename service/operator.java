package service;
import java.util.Scanner;

public class operator {
    public double squareOperation(Scanner scanner) {
        System.out.println("Enter the number to square:");
        double number = scanner.nextDouble();
        scanner.nextLine();
        return Math.pow(number, 2);
    }

    public double sqrtOperation(Scanner scanner) {
        System.out.println("Enter the number for square root:");
        double number = scanner.nextDouble();
        scanner.nextLine();
        return Math.sqrt(number);
    }

    public double trigonometricOperation(Scanner scanner, String operation) {
        System.out.println("Enter the angle in radians for " + operation + ":");
        double angle = scanner.nextDouble();
        scanner.nextLine(); 

        switch (operation.toLowerCase()) {
            case "sin":
                return Math.sin(angle);
            case "cos":
                return Math.cos(angle);
            case "tan":
                return Math.tan(angle);
            default:
                throw new IllegalArgumentException("Invalid trigonometric operation: " + operation);
        }
    }

    public double logarithmOperation(Scanner scanner) {
        System.out.println("Enter the number for logarithm:");
        double number = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter the base for logarithm:");
        double base = scanner.nextDouble();
        scanner.nextLine(); 

        return Math.log(number) / Math.log(base);
    }
}
