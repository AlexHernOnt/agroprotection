package agroprotection.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import agroprotection.application.GetUserUseCase;
import agroprotection.configuration.ApplicationConfig;
import agroprotection.domain.User;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean {

    private String username;
    private String password;

    // Injects UserBean
    @ManagedProperty(value = "#{userBean}")
    private UserBean userBean;

    // Configuration
    private final ApplicationConfig config = new ApplicationConfig();

    // Application layer
    private final GetUserUseCase app = config.getUserUseCase();

    public String login() {

        User user = app.getUser(username);

        if (user != null) {

            // Save logged user in session bean
            userBean.setUser(user);

            return "home.xhtml?faces-redirect=true";
        }

        System.out.println("User not found");

        return null;
    }

    // REQUIRED for ManagedProperty injection
    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}