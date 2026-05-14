package agroprotection.application;

import agroprotection.domain.User;

public class GetUserUseCase {


	// Instance of the Port / Adapter
	
	final UserPort userPort;
	
	
	public User getUser(String name) {
		return this.userPort.getUser(name);
	}
	
	
	// Constructor
	public GetUserUseCase(UserPort userPort) {
		this.userPort = userPort;
	}
}