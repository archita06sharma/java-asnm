import java.util.ArrayList;
import java.util.List;

// Base Exception
class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);
    }
}

// Product Exception
class ProductException extends ApplicationException {

    public ProductException(String message) {
        super(message);
    }
}

// Product Not Found
class ProductNotFoundException extends ProductException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}

// Out of Stock
class OutOfStockException extends ProductException {

    public OutOfStockException(String message) {
        super(message);
    }
}

// Payment Exception
class PaymentException extends ApplicationException {

    public PaymentException(String message) {
        super(message);
    }
}

// Invalid Payment
class InvalidPaymentException extends PaymentException {

    public InvalidPaymentException(String message) {
        super(message);
    }
}

// Insufficient Funds
class InsufficientFundsException extends PaymentException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}

// Order Exception
class OrderException extends ApplicationException {

    public OrderException(String message) {
        super(message);
    }
}

// Empty Cart
class EmptyCartException extends OrderException {

    public EmptyCartException(String message) {
        super(message);
    }
}

// Product class
class Product {

    String name;
    double price;
    int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}

// Shopping Cart
class ShoppingCart {

    private List<Product> cart = new ArrayList<>();

    // Search product
    public Product searchProduct(String name)
            throws ProductNotFoundException {

        Product[] products = {
                new Product("Laptop", 60000, 5),
                new Product("Phone", 30000, 10),
                new Product("Headphones", 2000, 0)
        };

        for (Product p : products) {

            if (p.name.equalsIgnoreCase(name)) {
                return p;
            }
        }

        throw new ProductNotFoundException(
                "Product '" + name + "' not found."
        );
    }

    // Add product
    public void addProduct(String name)
            throws ProductNotFoundException,
                   OutOfStockException {

        Product product = searchProduct(name);

        if (product.stock <= 0) {
            throw new OutOfStockException(
                    "Product '" + name + "' is out of stock."
            );
        }

        cart.add(product);

        System.out.println(
                product.name + " added to cart."
        );
    }

    // Remove product
    public void removeProduct(String name)
            throws ProductNotFoundException {

        for (Product p : cart) {

            if (p.name.equalsIgnoreCase(name)) {
                cart.remove(p);

                System.out.println(
                        p.name + " removed from cart."
                );

                return;
            }
        }

        throw new ProductNotFoundException(
                "Product '" + name + "' is not present in cart."
        );
    }

    // Display cart
    public void displayCart()
            throws EmptyCartException {

        if (cart.isEmpty()) {
            throw new EmptyCartException(
                    "Cart is empty."
            );
        }

        double total = 0;

        System.out.println("\n----- SHOPPING CART -----");

        for (Product p : cart) {

            System.out.println(
                    p.name + " - Rs. " + p.price
            );

            total += p.price;
        }

        System.out.println("Total = Rs. " + total);
    }

    // Calculate total
    public double calculateTotal()
            throws EmptyCartException {

        if (cart.isEmpty()) {
            throw new EmptyCartException(
                    "Cannot calculate total. Cart is empty."
            );
        }

        double total = 0;

        for (Product p : cart) {
            total += p.price;
        }

        return total;
    }

    // Payment
    public void makePayment(
            String paymentMethod,
            double availableFunds)
            throws EmptyCartException,
                   InvalidPaymentException,
                   InsufficientFundsException {

        double total = calculateTotal();

        if (!paymentMethod.equalsIgnoreCase("UPI")
                && !paymentMethod.equalsIgnoreCase("CARD")) {

            throw new InvalidPaymentException(
                    "Invalid payment method."
            );
        }

        if (availableFunds < total) {

            throw new InsufficientFundsException(
                    "Insufficient funds. Required: Rs. "
                    + total
                    + ", Available: Rs. "
                    + availableFunds
            );
        }

        System.out.println(
                "Payment successful using " + paymentMethod
        );

        System.out.println(
                "Amount paid = Rs. " + total
        );
    }
}

public class Q7 {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();

        // Add products
        try {

            cart.addProduct("Laptop");
            cart.addProduct("Phone");

        } catch (ProductNotFoundException e) {

            System.out.println(
                    "Product Error: " + e.getMessage()
            );

        } catch (OutOfStockException e) {

            System.out.println(
                    "Stock Error: " + e.getMessage()
            );
        }

        // Display cart
        try {

            cart.displayCart();

        } catch (EmptyCartException e) {

            System.out.println(
                    "Order Error: " + e.getMessage()
            );
        }

        // Remove product
        try {

            cart.removeProduct("Phone");

        } catch (ProductNotFoundException e) {

            System.out.println(
                    "Remove Error: " + e.getMessage()
            );
        }

        // Payment
        try {

            cart.makePayment("UPI", 70000);

        } catch (EmptyCartException e) {

            System.out.println(
                    "Order Error: " + e.getMessage()
            );

        } catch (InvalidPaymentException e) {

            System.out.println(
                    "Payment Error: " + e.getMessage()
            );

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Payment Error: " + e.getMessage()
            );
        }

        // Demonstrate product not found
        try {

            cart.addProduct("Tablet");

        } catch (ProductNotFoundException e) {

            System.out.println(
                    "Product Error: " + e.getMessage()
            );

        } catch (OutOfStockException e) {

            System.out.println(
                    "Stock Error: " + e.getMessage()
            );
        }

        // Demonstrate out of stock
        try {

            cart.addProduct("Headphones");

        } catch (ProductNotFoundException e) {

            System.out.println(
                    "Product Error: " + e.getMessage()
            );

        } catch (OutOfStockException e) {

            System.out.println(
                    "Stock Error: " + e.getMessage()
            );
        }
    }
}