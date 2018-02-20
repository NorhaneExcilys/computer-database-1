package main.java.com.excilys.computer.database.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Connection;

public class Connect {	
	private static Properties prop = new Properties();
	private static InputStream input = null;
	
	private static String url;
	private static String password;
	private static String username;
	
	private static Connection connect = null;
	final private static Logger logger = Logger.getLogger(Connect.class);
	
	
	private Connect() {
		try {
			input = new FileInputStream("src/main/resources/connect.properties");	
			prop.load(input);
			url = prop.getProperty("url");
			username = prop.getProperty("username");
			password = prop.getProperty("password");
			
			connect = (Connection) DriverManager.getConnection(url, username,  password);
		} catch (SQLException e1) {
			logger.error("Erreur de connexion a la BDD! erreur:" + e1);
		} catch (IOException e) {
			logger.error("Erreur d'ouverture du 'connect.properties' erreur:" + e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Erreur de fermeture du 'connect.properties' erreur:" + e);
				}
			}
		}
	}
	
	public static Connection getConnection()
    {           
        if (connect == null) {   
        	new Connect(); 
        }
        return connect;
    }

	public static void closeConnection()
    {           
        if (connect != null) {   
        	try {
        		connect.close();
        		connect = null;
	        } catch (SQLException e1) {
	        	logger.error("Erreur de fermeture de la connexion a la BDD! erreur:" + e1);
			}
        }
    }
}