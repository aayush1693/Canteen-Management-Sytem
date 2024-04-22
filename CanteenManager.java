import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CanteenManager {
    private Connection connection;

    public CanteenManager() {
        try {
            connection = Database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new menu item
    public void addMenuItem(MenuItem item) {
        String query = "INSERT INTO menu (item_name, price) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, item.getItemName());
            statement.setDouble(2, item.getPrice());
            statement.executeUpdate();
            System.out.println("Menu item added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to remove a menu item
    public void removeMenuItem(int itemId) {
        String query = "DELETE FROM menu WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, itemId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Menu item removed successfully.");
            } else {
                System.out.println("Menu item not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all menu items
    public List<MenuItem> getMenu() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                MenuItem menuItem = new MenuItem();
                menuItem.setId(resultSet.getInt("id"));
                menuItem.setItemName(resultSet.getString("item_name"));
                menuItem.setPrice(resultSet.getDouble("price"));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

    // Method to place an order
    public void placeOrder(Order order) {
        String query = "INSERT INTO orders (user_id, item_id, quantity, total_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getItemId());
            statement.setInt(3, order.getQuantity());
            statement.setDouble(4, order.getTotalPrice());
            statement.executeUpdate();
            System.out.println("Order placed successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all orders
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt("id"));
                order.setUserId(resultSet.getInt("user_id"));
                order.setItemId(resultSet.getInt("item_id"));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setTotalPrice(resultSet.getDouble("total_price"));
                order.setOrderDate(resultSet.getDate("order_date"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
