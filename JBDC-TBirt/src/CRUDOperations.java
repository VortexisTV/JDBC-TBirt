import java.sql.*;

public class CRUDOperations {
    private static final String URL = "jdbc:mysql://localhost:3306/sampledb";
    private static final String USERNAME = "t_birt";
    private static final String PASSWORD = "test";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            // Create operation
            String createQuery = "INSERT INTO users (name, email) VALUES (?, ?)";
            try (PreparedStatement createStatement = connection.prepareStatement(createQuery)) {
                // Set values for placeholders in the prepared statement
                createStatement.setString(1, "Mario"); // Set Name
                createStatement.setString(2, "Mario@gmail.com"); // Set Email
                // Execute the update operation
                createStatement.executeUpdate();
                System.out.println("Record created successfully");
            } catch (SQLException e) {
                // Handle any SQL exceptions
                e.printStackTrace();
            }

            // Read operation
            String readQuery = "SELECT * FROM users";
            try (Statement readStatement = connection.createStatement();
                 ResultSet resultSet = readStatement.executeQuery(readQuery)) {
                // Iterate over the result set and print user information
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("id"));
                    System.out.println("Name: " + resultSet.getString("name"));
                    System.out.println("Email: " + resultSet.getString("email"));
                }
            } catch (SQLException e) {
                // Handle any SQL exceptions
                e.printStackTrace();
            }

            // Update operation
            String updateQuery = "UPDATE users SET email = ? WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, "updated.email@example.com");
                updateStatement.setInt(2, 1);
                updateStatement.executeUpdate();
                System.out.println("Record updated successfully");
            } catch (SQLException e) {
                e.printStackTrace();
            }


            // Delete operation
            String deleteQuery = "DELETE FROM users WHERE id = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                // Set the ID of the record to be deleted
                deleteStatement.setInt(1, 13);
                // Execute the delete operation
                deleteStatement.executeUpdate();
                System.out.println("Record deleted successfully");
            } catch (SQLException e) {
                // Handle any SQL exceptions
                e.printStackTrace();
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions related to connection establishment
            e.printStackTrace();
        }
    }
}
