package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program3 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DB.getConnection();

            statement = conn.prepareStatement(
                    "UPDATE seller SET BaseSalary = BaseSalary + ? " +
                            " WHERE " +
                            "(DepartmentId = ?)");

            statement.setDouble(1, 350.0);
            statement.setInt(2, 2);

            int rowAffected = statement.executeUpdate();
            statement.executeUpdate();
            System.out.println("Done! Rows affected: " + rowAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DB.closeStatement(statement);
            DB.closeConnection();

        }
    }
}
