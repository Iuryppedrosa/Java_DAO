package application;

import db.DB;
import db.DbIntegretyException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program4 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = DB.getConnection();

            statement = conn.prepareStatement(
                    "DELETE FROM department WHERE Id" +
                            " = ?");

            statement.setInt(1, 5);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected);

        } catch (SQLException e) {
            throw new DbIntegretyException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}

