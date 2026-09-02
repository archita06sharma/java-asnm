public class Q1 {

    public static void main(String[] args) {

        System.out.println("Exception Hierarchy:");
        System.out.println();
        System.out.println("Throwable");
        System.out.println("   |");
        System.out.println(" Exception");
        System.out.println("   |");
        System.out.println(" RuntimeException");
        System.out.println("   |-- ArithmeticException");
        System.out.println("   |-- NullPointerException");
        System.out.println("   |-- IndexOutOfBoundsException");
        System.out.println("   |      |-- ArrayIndexOutOfBoundsException");
        System.out.println("   |-- IllegalArgumentException");
        System.out.println("          |-- NumberFormatException");

        System.out.println("\nDemonstrating Exceptions:\n");

        // ArithmeticException
        try {
            int a = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException caught: " + e.getMessage());
        }

        // NullPointerException
        try {
            String str = null;
            System.out.println(str.length());
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
        }

        // ArrayIndexOutOfBoundsException
        try {
            int[] arr = {10, 20, 30};
            System.out.println(arr[5]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBoundsException caught");
        }

        // NumberFormatException
        try {
            int number = Integer.parseInt("ABC");
            System.out.println(number);
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException caught");
        }
    }
}