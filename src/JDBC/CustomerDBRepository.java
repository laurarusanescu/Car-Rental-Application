package JDBC;

import domain.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDBRepository extends DatabaseRepository<Customer, String> {
    public CustomerDBRepository(String tableName) {
        super(tableName);
        getData();
    }

    @Override
    public void getData() {
        try {
            openConnection();

            if (conn == null || conn.isClosed()) {
                System.out.println("CustomerDBRepository - Connection is null or closed");
                // Add code to handle the situation, e.g., reopen the connection
            } else {
                String selectString = "SELECT * FROM " + tableName + ";";
                System.out.println("CustomerDBRepository - Executing query: " + selectString);
                try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                    ResultSet resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int phoneNumber = resultSet.getInt("phone number");
                        Customer customer= new Customer(id, name, phoneNumber);
                        listOfItems.add(customer);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void addItem(Customer c){
        try {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?,?,?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setInt(1, c.getId());
                ps.setString(2, c.getName());
                ps.setInt(3, c.getPhoneNumber());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void deleteItem(int id){
        try {
            openConnection();
            String deleteString = "DELETE FROM " + tableName + " WHERE ID = " + id;
            try (PreparedStatement ps = conn.prepareStatement(deleteString)) {
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void updateItem(int id, String newName, int newPhone){
        try {
            openConnection();
            String updateString = "UPDATE " + tableName + " SET name=?, phone_number=? WHERE ID=?";
            try (PreparedStatement ps = conn.prepareStatement(updateString)) {
                ps.setString(1, newName);
                ps.setInt(2, newPhone);
                ps.setInt(3, id);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ArrayList<Customer> getAllItems() {
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName;
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet resultSet = ps.executeQuery();
                ArrayList<Customer> customers = new ArrayList<>();
                while (resultSet.next()) {
                    // Populate Customer objects from the result set
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int phoneNumber = resultSet.getInt("phone_number");
                    Customer customer = new Customer(id, name, phoneNumber);
                    customers.add(customer);
                }
                return customers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                closeConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
