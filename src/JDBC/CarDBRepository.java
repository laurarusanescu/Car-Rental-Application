package JDBC;

import domain.Car;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarDBRepository extends DatabaseRepository<Car, Integer>{
    public CarDBRepository(String tableName) throws SQLException {
        super(tableName);
        getData();
    }

    // In CarDBRepository class
    @Override
    public void getData() {
        try {
            openConnection();

            if (conn == null || conn.isClosed()) {
                System.out.println("CarDBRepository - Connection is null or closed");
                // Add code to handle the situation, e.g., reopen the connection
            } else {
                String selectString = "SELECT * FROM " + tableName + ";";
                System.out.println("CarDBRepository - Executing query: " + selectString);
                try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                    ResultSet resultSet = ps.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int numberOFStars = resultSet.getInt("number_stars");
                        int yearOfFabrication = resultSet.getInt("year_of_fabrication");
                        int pricePerDay = resultSet.getInt("price_per_day");
                        Car car = new Car(id, name, numberOFStars, yearOfFabrication, pricePerDay);
                        listOfItems.add(car);
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


    public void addItem(Car c) {
        try {
            openConnection();
            String insertString = "INSERT INTO " + tableName + " VALUES (?,?,?,?,?);";
            try (PreparedStatement ps = conn.prepareStatement(insertString)) {
                ps.setInt(1, c.getID());
                ps.setString(2, c.getName());
                ps.setInt(3, c.getNumberStars());
                ps.setInt(4, c.getYearOfFabrication());
                ps.setInt(5, c.getPricePerDay());
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
    public void removeItem(int id){
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

    public void updateItem(int id, String newName, int newNumberStars, int newYear, int newPrice) {
        try {
            openConnection();
            String updateString = "UPDATE " + tableName + " SET name=?, number_stars=?, year_of_fabrication=?, price_per_day=? WHERE ID=?";
            try (PreparedStatement ps = conn.prepareStatement(updateString)) {
                ps.setString(1, newName);
                ps.setInt(2, newNumberStars);
                ps.setInt(3, newYear);
                ps.setInt(4, newPrice);
                ps.setInt(5, id);
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

    public ArrayList<Car> getAllItems() {
        try {
            openConnection();
            String selectString = "SELECT * FROM " + tableName;
            try (PreparedStatement ps = conn.prepareStatement(selectString)) {
                ResultSet resultSet = ps.executeQuery();
                ArrayList<Car> cars = new ArrayList<>();
                while (resultSet.next()) {
                    // Populate Car objects from the result set
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int numberOFStars = resultSet.getInt("number_stars");
                    int yearOfFabrication = resultSet.getInt("year_of_fabrication");
                    int pricePerDay = resultSet.getInt("price_per_day");
                    Car car = new Car(id, name, numberOFStars, yearOfFabrication, pricePerDay);
                    cars.add(car);
                }
                return cars;
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
