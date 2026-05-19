package agroprotection.application;

import agroprotection.domain.User;
import agroprotection.domain.Terreno;

public class GetUserUseCase {


	// Instance of the Port / Adapter
	
	final UserPort userPort;
	
	
	public User getUser(String name) {
		return this.userPort.getUser(name);
	}

	public void addTerreno(String userName, Terreno terreno) {
		this.userPort.addTerreno(userName, terreno);
	}
	
	
	// Constructor
	public GetUserUseCase(UserPort userPort) {
		this.userPort = userPort;
	}
}
