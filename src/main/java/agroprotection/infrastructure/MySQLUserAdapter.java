package agroprotection.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import agroprotection.application.UserPort;
import agroprotection.domain.Terreno;
import agroprotection.domain.User;

public class MySQLUserAdapter implements UserPort {

	private String pass;

	/*
	 ** CONSTRUCTORS
	 */

	public MySQLUserAdapter() {

		pass = getPassword();
	}

	/*
	 ** GETERS
	 */

	@Override
	public User getUser(String name) {

		ArrayList<Terreno> terrenos = loadTerrenos(name);

		return loadUser(name, terrenos);
	}

	@Override
	public void addTerreno(String userName, Terreno terreno) {

		String sql =
			"INSERT INTO terrenos (comunidad, hectarias, precio, user) VALUES (?, ?, ?, ?)";

		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, terreno.getComunidad());
			statement.setInt(2, terreno.getHectarias());
			statement.setInt(3, terreno.getPrecio());
			statement.setString(4, userName);

			statement.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	** USER QUERY
	*/

	private User loadUser(String name, ArrayList<Terreno> terrenos) {

		String sql =
			"SELECT name, hectarias FROM users WHERE name= ? LIMIT 1";

		try (Connection connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)) {
				
					
				statement.setString(1, name);
				
				ResultSet result = statement.executeQuery();
				
				if (result.next()) {
					return new User(
						result.getString("name"),
						result.getInt("hectarias"),
					 	terrenos
					);
				}		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
	/*
	** TERRENOS QUERY
	*/
	
	private ArrayList<Terreno> loadTerrenos(String name) {
		String sql =
			"SELECT comunidad, hectarias, precio FROM terrenos WHERE user=\"" + name + "\"";

		ArrayList<Terreno> terrenos = new ArrayList<>();
		
		try (Connection connection = getConnection();
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql)) {

			while (result.next()) {

				terrenos.add(new Terreno(
					result.getString("comunidad"),
					result.getInt("hectarias"),
					result.getInt("precio")
					));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return terrenos;
	}	
			
	/*
	 ** CONNECTION
	 */

	private Connection getConnection() throws Exception {
		return DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/agroprotection",
			"root",
			pass
		);
	}

	/*
	** PASSWORD
	*/

	private String getPassword() {

		try (BufferedReader reader = new BufferedReader(new FileReader(".env"))) {
			return reader.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}
}
