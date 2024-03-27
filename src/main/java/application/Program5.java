package application;

import db.DB;
import db.DbException;
import db.DbIntegretyException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program5 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        Statement statement = null;
        try {
            conn = DB.getConnection();
            conn.setAutoCommit(false);


            statement = conn.createStatement();

            int rows1 = statement.executeUpdate("UPDATE SELLER " +
                    "SET " +
                    "BaseSalary = 2090 " +
                    "WHERE DepartmentId = 1");

//            int x = 1;
//            if(x < 2){
//                throw new SQLException("Fake error");
//            }

            int rows2 = statement.executeUpdate("UPDATE SELLER " +
                    "SET " +
                    "BaseSalary = 3090 " +
                    "WHERE DepartmentId = 2");
            System.out.println("Rows affected " + rows1 + " rows2 " + rows2);

            conn.commit();

        } catch (SQLException e) {
            try{
                conn.rollback();
                throw new DbException("Nao concluiu a transacao!" + e.getMessage());
            }catch (SQLException f){
                throw new DbException("Erro ao realizar rollback" + "Caused By:" + f.getMessage());
            }
        }
        finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }
}


