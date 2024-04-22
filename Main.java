import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Create a CanteenManager instance
        CanteenManager canteenManager = new CanteenManager();

        // Adding a new menu item
        MenuItem newItem = new MenuItem();
        newItem.setItemName("Burger");
        newItem.setPrice(5.99);
        canteenManager.addMenuItem(newItem);

        // Displaying the menu
        System.out.println("Current Menu:");
        List<MenuItem> menuItems = canteenManager.getMenu();
        for (MenuItem menuItem : menuItems) {
            System.out.println(menuItem.getItemName() + " - $" + menuItem.getPrice());
        }

        // Placing an order
        Order newOrder = new Order();
        newOrder.setUserId(1); // Assuming user ID 1
        newOrder.setItemId(1); // Assuming the ID of the item just added is 1
        newOrder.setQuantity(2);
        newOrder.setTotalPrice(newOrder.getQuantity() * menuItems.get(0).getPrice()); // Assuming only one item in menu
        canteenManager.placeOrder(newOrder);

        // Displaying all orders
        System.out.println("\nAll Orders:");
        List<Order> orders = canteenManager.getAllOrders();
        for (Order order : orders) {
            System.out.println("Order ID: " + order.getId() + ", User ID: " + order.getUserId() +
                    ", Item ID: " + order.getItemId() + ", Quantity: " + order.getQuantity() +
                    ", Total Price: $" + order.getTotalPrice() + ", Order Date: " + order.getOrderDate());
        }

        // Closing the connection
        canteenManager.closeConnection();
    }
}
