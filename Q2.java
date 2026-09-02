public class Q2 {

    static void demonstrate() {

        try {
            System.out.println("Outer try block started.");

            try {
                System.out.println("Inner try block started.");

                // Generates ArithmeticException
                int result = 10 / 0;

                System.out.println(result);

            } catch (NullPointerException e) {
                // This does NOT handle ArithmeticException
                System.out.println("Inner catch: NullPointerException");
            }

            System.out.println("Outer try block continues.");

        } catch (ArithmeticException e) {
            System.out.println("Outer catch: ArithmeticException caught.");
            System.out.println("Exception propagated from inner block to outer block.");
        }

        System.out.println("Program continues after exception handling.");
    }

    public static void main(String[] args) {
        demonstrate();
    }
}