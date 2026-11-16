import java.util.Objects;
import java.util.Optional;

class GenericUtils {

    // 1. Checks whether two objects are equal (null-safe)
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    // 2. Returns the contained value or a default
    public static <T> T getDefault(Optional<T> value, T defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        return value.orElse(defaultValue);
    }

    // 3. Returns the larger of two comparable objects
    public static <T extends Comparable<T>> T max(T a, T b) {
        Objects.requireNonNull(a, "First argument cannot be null");
        Objects.requireNonNull(b, "Second argument cannot be null");
        return (a.compareTo(b) >= 0) ? a : b;
    }

    // 4. Checks whether an object is null using Objects class
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5 {
    public static void main(String[] args) {
        // 1. Testing isEqual()
        System.out.println("--- isEqual() ---");
        System.out.println("Strings equal ('Java', 'Java'): " + GenericUtils.isEqual("Java", "Java"));
        System.out.println("Integers equal (42, 99):        " + GenericUtils.isEqual(42, 99));
        System.out.println("Null comparison (null, null):   " + GenericUtils.isEqual(null, null));

        // 2. Testing getDefault()
        System.out.println("\n--- getDefault() ---");
        Optional<String> presentCity = Optional.of("Chandigarh");
        Optional<String> emptyCity = Optional.empty();
        System.out.println("Present Optional: " + GenericUtils.getDefault(presentCity, "New Delhi"));
        System.out.println("Empty Optional:   " + GenericUtils.getDefault(emptyCity, "New Delhi"));

        // 3. Testing max()
        System.out.println("\n--- max() ---");
        System.out.println("Max Integer (25, 78):          " + GenericUtils.max(25, 78));
        System.out.println("Max Double (19.99, 19.89):     " + GenericUtils.max(19.99, 19.89));
        System.out.println("Max String ('Apple', 'Mango'): " + GenericUtils.max("Apple", "Mango"));

        // 4. Testing isNull()
        System.out.println("\n--- isNull() ---");
        String nullStr = null;
        Integer validNum = 100;
        System.out.println("Is nullStr null?  " + GenericUtils.isNull(nullStr));
        System.out.println("Is validNum null? " + GenericUtils.isNull(validNum));
    }
}