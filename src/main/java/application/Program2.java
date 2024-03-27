package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program2 {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DB.getConnection();
            statement = conn.prepareStatement(
                                    "INSERT INTO SELLER "+
                                        "(Name, Email, BirthDate, BaseSalary, DepartmentId)" +
                                        "VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, "Marcos Filho");
            statement.setString(2, "mf@gmail.com");
            statement.setDate(3, new java.sql.Date(sdf.parse("02/06/1985").getTime()));
            statement.setDouble(4, 3300.0);
            statement.setInt(5, 2);

            int rowAffected = statement.executeUpdate(); //

            if(rowAffected > 0){
                ResultSet generatedKeys = statement.getGeneratedKeys();
                while(generatedKeys.next()){
                    System.out.println(generatedKeys.getInt(1));
                    System.out.println("Done! " + rowAffected);
                }

            } else {
                System.out.println("Nenhuma linha afetada");
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}
