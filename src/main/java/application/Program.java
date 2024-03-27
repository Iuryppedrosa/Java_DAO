package application;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import db.DB;

public class Program {

    public static void main(String[] args) {

        Connection conn = DB.getConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM seller");

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<String[]> resultSetData = new ArrayList<>();

            while (rs.next()) {
                // Array para armazenar os dados de uma linha
                String[] rowData = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getString(i);
                }
                resultSetData.add(rowData);
            }

            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Dados");

            Row headerRow = sheet.createRow(0);
            for (int i = 1; i <= columnCount; i++) {
                Cell cell = headerRow.createCell(i - 1);
                cell.setCellValue(metaData.getColumnName(i));
            }

            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                for (int i = 1; i <= columnCount; i++) {
                    Cell cell = row.createCell(i - 1);
                    String value = rs.getString(i);

                    if(value != null){
                        cell.setCellValue(value);
                    }
                    else{
                        cell.setCellValue("");
                    }
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream("dados.xlsx")) {
                workbook.write(outputStream);
                System.out.println("Dados exportados para dados.xlsx");
            } catch (IOException e) {
                e.printStackTrace();
            }

            for(String[] rowData : resultSetData){
                IntStream.range(1, columnCount + 1)
                        .mapToObj(i -> {
                            try {
                                return metaData.getColumnName(i) + ": " + rs.getString(i);
                            } catch (SQLException e) {
                                e.printStackTrace();
                                return null;
                            }
                        })
                        .forEach(System.out::println);
            }
            DB.closeConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
