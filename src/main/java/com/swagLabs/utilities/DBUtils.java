package com.swagLabs.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtils 
{
	public List<List<String>> getDataFromTable(String tableName) 
	{
        String url = "jdbc:mysql://localhost:3306/your_database";
        String username = "your_username";
        String password = "your_password";

        List<List<String>> data = new ArrayList<>();

        try (
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName)
            ) 
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();

            // Header row
            List<String> headerRow = new ArrayList<>();
            for (int i = 1; i <= colCount; i++) {
                headerRow.add(rsmd.getColumnName(i));
            }
            data.add(headerRow);

            // Data rows
            while (rs.next()) 
            {
                List<String> row = new ArrayList<>();
                for (int i = 1; i <= colCount; i++) {
                    row.add(rs.getString(i));
                }
                data.add(row);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return data;
    }
	

    public static void executeQuery(String query) 
    {
    	 String url = "jdbc:mysql://localhost:3306/your_database";
         String username = "your_username";
         String password = "your_password";
    	
        // try-with-resources will auto-close all resources
        try (
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query)
        	) 
        {
	        while (rs.next()) 
	        {
	            
	        }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

}
