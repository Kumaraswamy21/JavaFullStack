package com.virtusa.eg.dao;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class EGDatabaseConnection {
	static Logger log = Logger.getLogger(EGDatabaseConnection.class);
	static Scanner scanner = new Scanner(System.in);
	private static EGDatabaseConnection egDatabaseConnection = null;

	private EGDatabaseConnection() {
	}

	public static EGDatabaseConnection getInstance() {
		if (egDatabaseConnection == null) {
			egDatabaseConnection = new EGDatabaseConnection();
		}

		return egDatabaseConnection;
	}
	private static String userName="root";
	private static String pass="1234";
	

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/virtusa", userName, pass);
		} catch (Exception e) {

			log.info(e);
		}
		return null;

	}

	public static void releaseConnection(Connection connection) {

		try {
			connection.close();
		} catch (SQLException e) {

			log.info(e);
		}
	}

}
