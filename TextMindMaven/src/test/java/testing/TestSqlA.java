/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author hoanl
 */
public class TestSqlA {
    public static void main(String[] args) {
    String JDBC_URL = "jdbc:sqlserver://hka.database.windows.net:1433;database=TextMind;user=s1;password=Ducanh123;encrypt=true;trustServerCertificate=false;loginTimeout=30;";
    String USERNAME = "s1";
    String PASSWORD = "Ducanh123";
    TestSqlA azure = new TestSqlA();
    String selectSql = "SELECT * FROM Username;";
    String insertSql = "INSERT INTO Username VALUES ('G');";
    azure.SelectAzureSQL(USERNAME, PASSWORD, selectSql, JDBC_URL, insertSql);
}

private void SelectAzureSQL(String userName, String userPassword, String sql, String cnnStr, String insertSql) {
    ResultSet resultSet = null;
    try(Connection cnn = DriverManager.getConnection(cnnStr, userName, userPassword);
        Statement statement = cnn.createStatement()) {
        resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
//        statement.executeUpdate(insertSql);
    } catch (SQLException ex) {
        ex.printStackTrace();
    } finally {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
}
