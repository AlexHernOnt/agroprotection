package agroprotection.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import agroprotection.application.UserPort;
import agroprotection.domain.Terreno;
import agroprotection.domain.User;

public class MySQLUserAdapter implements UserPort {

	private String pass;

	@Override
	public User getUser(String name) {
				
		String sqlTerrenos = "SELECT comunidad, hectarias, precio FROM terrenos WHERE user=\"" + name + "\"";
		String sql = "SELECT name, hectarias FROM users WHERE name=\"" + name + "\" LIMIT 1";

		ArrayList<Terreno> terrenos = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/agroprotection", "root", pass);
				Statement statement = connection.createStatement()) {

			// Load Terrenos Query
			ResultSet resultT = statement.executeQuery(sqlTerrenos);
			
			while (resultT.next()) {
 
				terrenos.add(new Terreno(
						resultT.getString("comunidad"),
						resultT.getInt("hectarias"),
						resultT.getInt("precio")
						));
			}
			
			// Load User
			ResultSet result = statement.executeQuery(sql);

			if (result.next()) {	
				return new User(result.getString("name"), result.getInt("hectarias"), terrenos);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
}