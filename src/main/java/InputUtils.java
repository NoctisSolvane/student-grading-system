import java.util.Scanner;

public final class InputUtils {
    /**
     * Validates integer input on scanner and returns the scanner if invalid.
     * <p>
     * The value of the integer should be between the given {@code int min} and {@code int max}.
     * <p> 
     * Repeatedly prompts until a valid integer within the specified range is entered.
     * </p>
     * 
     * @param sc the text {@code Scanner sc} which parses primitive types and breaks its input into tokens
     * @param prompt the additional {@code String} that is provided by the user along with the integer input
     * @param min the minimum value of the integer input (provided by the user)
     * @param max the maximum value of the integer input (provided by the user)
     * @return the integer input of {@code Scanner sc} ({@code int val}) if it is between {@code min} and {@code max}
     * <ul>
     *    <li>"Must be between {@code int min} and {@code int max}. Try again." and another {@code Scanner sc} if the input is an invalid integer</li>
     *    <li>"Invalid input. Input must be a number" and another {@code Scanner sc} if the input is not an integer</li>
     * </ul>
     */    
    public static int getValidInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                int val = sc.nextInt();
                sc.nextLine();
                if (val < min || val > max) {
                    System.out.printf("Must be between %d and %d. Try again. \n", min, max);
                    continue;
                }
                return val;
            }
            catch (Exception e) {
                System.out.println("Invalid input. Input must be number.\n");
                sc.nextLine();
            }
        }
    }
   
    /**
     * Validates double (decimal) input on scanner, and returns the scanner if invalid.
     * <p>
     * The value of the double should be between the given {@code double min} and {@code double max}.
     * <p>
     * Repeatedly prompts until a valid double within the specified range is entered.
     * </p>
     * 
     * @param sc the text {@code Scanner sc} which parses primitive types and breaks its input as tokens
     * @param prompt the additional {@code String} that is provided by the user along with the double input
     * @param min the minimum value of the double input (provided by the user)
     * @param max the maximum value of the double input (provided by the user)
     * @return the double input of {@code Scanner sc} ({@code double val}) if it is between {@code min} and {@code max}
     * <ul>
     *    <li>"Must be between {@code double min} and {@code double max}. Try again." and another {@code Scanner sc} if the input is an invalid double</li>
     *    <li>"Invalid input. Input must be a number." and another {@code Scanner sc} if the input is not a double</li>
     * </ul>
     */
    public static double getValidDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                double val = sc.nextDouble();
                sc.nextLine();
                if (val < min || val > max) {
                    System.out.printf("Must be between %.1f and %.1f. Try again. \n", min, max);
                    continue;
                }
                return val;
            }
            catch (Exception e) {
                System.out.println("Invalid input. Input must be a number.\n");
                sc.nextLine();
            }
        }
    }
   
    /**
     * Validates string input on scanner, and returns the scanner if invalid.
     * <p>
     * The input of {@code Scanner sc} should not be empty.
     * <p>
     * Repeatedly prompts until a valid string is entered.
     * </p>
     *  
     * @param sc the text {@code Scanner sc} which parses primitive types and breaks its input as tokens
     * @param prompt the additional {@code String} that is provided by the user along with the string input
     * @return the string input of {@code Scanner sc} ({@code String input}) if the input is not empty
     * <ul>
     *    <li>"Input cannot be empty." if {@code input.isEmpty()}</li>
     * </ul>
     */
    public static String getValidString(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt + ": ");
            String input = sc.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty.");
        }
    }
}