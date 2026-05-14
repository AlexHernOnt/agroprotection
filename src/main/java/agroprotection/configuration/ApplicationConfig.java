package agroprotection.configuration;

import agroprotection.application.GetUserUseCase;
import agroprotection.application.UserPort;
import agroprotection.infrastructure.MySQLUserAdapter;

public class ApplicationConfig {

	
	// Adapter
	
	private final UserPort adapter = new MySQLUserAdapter();
	
	// Connects to application layer
	private final GetUserUseCase app = new GetUserUseCase(adapter);

	
	
	public ApplicationConfig() {
		
	}
	
	
	public UserPort getAdapter() {
		return adapter;
	}
	
	public GetUserUseCase getUserUseCase() {
		return app;
	}
}

