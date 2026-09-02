package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.utility.OrderUtility;

public class Main {

    public static void main(String[] args) {

        // Create an array of FoodOrder
        FoodOrder[] orders = new FoodOrder[6];

        // Create 6 orders
        orders[0] = new RegularOrder(101, "Archita", 1000);
        orders[1] = new PremiumOrder(102, "Rahul", 2000);
        orders[2] = new RegularOrder(103, "Simran", 1500);
        orders[3] = new PremiumOrder(104, "Karan", 2500);
        orders[4] = new RegularOrder(105, "Priya", 800);
        orders[5] = new PremiumOrder(106, "Ananya", 3000);

        // Display restaurant
        System.out.println("========================================");
        System.out.println("          " + FoodOrder.getRestaurantName());
        System.out.println("========================================");

        // Display all orders
        for (FoodOrder order : orders) {

            // Validate amount
            if (!OrderUtility.validateAmount(order.getAmount())) {
                System.out.println(
                        "Invalid amount for Order ID: "
                                + order.getOrderId()
                );
                continue;
            }

            // Validate customer name
            if (!OrderUtility.validateCustomerName(order.getCustomerName())) {
                System.out.println(
                        "Invalid customer name for Order ID: "
                                + order.getOrderId()
                );
                continue;
            }

            // Display order summary
            OrderUtility.generateOrderSummary(order);
        }

        // Display total number of orders
        FoodOrder.displayTotalOrders();
    }
}