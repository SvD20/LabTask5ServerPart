package edu.bsuir.jdbcserver.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnectorDB {

    private static final String url = "jdbc:mysql://localhost:3306/labtask5";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public void create_connection() throws SQLException {

        con = DriverManager.getConnection(url, user, password);

    }


    public String select_from_table(String query) throws SQLException {

        stmt = con.createStatement();

        rs = stmt.executeQuery(query);

        StringBuilder result = new StringBuilder();

        while (rs.next()) {
            int id = rs.getInt(1);
            String title = rs.getString(2);
            String country = rs.getString(3);
            int price = rs.getInt(4);
            result.append("id: " + id + " title: " + title + " country: " + country + " price: " + price + " ||| ");
            //System.out.printf("id: %d, title: %s, country: %s, price: %d %n", id, title, country, price);
        }

        String answer = result.toString();

        return answer;

    }


    public void close() throws SQLException {

        con.close();;

        stmt.close();

        rs.close();

    }




}
