import java.util.Scanner;

public class MenuSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Implementation of main method
    }

    /**
     * Helper method to get integer input with validation
     */
    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
} 