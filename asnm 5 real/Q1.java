public class Q1 {

    public static <T extends Number> double sumArray(T[] array) {
        if (array == null) {
            return 0.0;
        }
        double sum = 0.0;
        for (T element : array) {
            if (element != null) {
                sum += element.doubleValue();
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        Integer[] intArray = {10, 20, 30, 40};
        Float[] floatArray = {1.5f, 2.5f, 3.0f};
        Double[] doubleArray = {12.75, 8.25, 4.50};

        System.out.println("Sum of Integer array: " + sumArray(intArray));
        System.out.println("Sum of Float array:   " + sumArray(floatArray));
        System.out.println("Sum of Double array:  " + sumArray(doubleArray));
    }
}