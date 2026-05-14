package agroprotection.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import agroprotection.application.UserPort;
import agroprotection.domain.User;

public class MySQLUserAdapter implements UserPort {

	
	private String pass;
	
	@Override
	public User getUser(String name) {
				
		String sql = "SELECT name, hectarias FROM users WHERE name=\"" + name + "\" LIMIT 1";

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agroprotection", "root", pass);
				Statement statement = connection.createStatement();
				ResultSet result = statement.executeQuery(sql)) {

			if (result.next()) {
				
				User u = new User(
					result.getString("name"),
					result.getInt("hectarias")
						);
				
				return u;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public MySQLUserAdapter() {
		
		pass = getPassword();
	}
	
	
	private String getPassword() {

	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(".env"));
	        String password = reader.readLine();
	        reader.close();
	        return password;
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
}