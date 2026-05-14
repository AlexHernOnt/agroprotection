package agroprotection.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import agroprotection.application.GetUserUseCase;
import agroprotection.configuration.ApplicationConfig;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserBean {
	
	
	// Configuration
	private final ApplicationConfig config = new ApplicationConfig();
	
	// Connects to application layer
	private final GetUserUseCase app = config.getUserUseCase();

    public String getName() {
        return app.getUser("Paco").getName();
    }

    public String getHectarias() {
    	String String_hec;
   	
    	String_hec = Integer.toString(app.getUser("Paco").getHectarias());
      	return String_hec;
    }
}