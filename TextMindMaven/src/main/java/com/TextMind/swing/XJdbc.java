/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.swing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

/**
 *
 * @author hoanl
 */
public class XJdbc {
	static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String dburl = "jdbc:sqlserver://hka.database.windows.net:1433;database=TextMind;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
	static String user = "s1@hka";
	static String pass = "Ducanh123"; // Replace with your actual password

	static {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
		Connection conn = DriverManager.getConnection(dburl, user, pass);
		PreparedStatement stmt;
		if (sql.trim().startsWith("{")) {
			stmt = conn.prepareCall(sql);
		} else {
			stmt = conn.prepareStatement(sql);
		}
		for (int i = 0; i < args.length; i++) {
			stmt.setObject(i + 1, args[i]);
		}
		return stmt;
	}

	public static ResultSet query(String sql, Object... args) {
		try {
			PreparedStatement stmt = getStmt(sql, args);
			return stmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Object value(String sql, Object... args) {
		try {

			ResultSet rs = query(sql, args);

			if (rs.next()) {
				return rs.getObject(0);
			}
			rs.getStatement().getConnection().close();
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static int update(String sql, Object... args) {
		try {
			PreparedStatement stmt = getStmt(sql, args);
			try {
				return stmt.executeUpdate();
			} finally {
				stmt.getConnection().close();
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
