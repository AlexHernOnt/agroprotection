package agroprotection.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import agroprotection.application.GetUserUseCase;
import agroprotection.configuration.ApplicationConfig;
import agroprotection.domain.User;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
	
	private User user;
	
	// Configuration
	private final ApplicationConfig config = new ApplicationConfig();
	
	// Connects to application layer
	private final GetUserUseCase app = config.getUserUseCase();

	
	public UserBean () {
		user = app.getUser("Juan");
	}
	
	public User getUser() {
		System.out.println(user.getTerrenos().get(1).getComunidad());
		return user;
	}
	


    
   // public List<Terreno> getTerreno
}