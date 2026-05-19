package agroprotection.ui;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import agroprotection.application.GetUserUseCase;
import agroprotection.configuration.ApplicationConfig;
import agroprotection.domain.Terreno;
import agroprotection.domain.User;

@ManagedBean(name = "addTerrenoBean")
@RequestScoped
public class AddTerrenoBean {

	private String comunidad;
	private int hectarias;
	private int precio;

	// Injects UserBean
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	// Configuration
	private final ApplicationConfig config = new ApplicationConfig();

	// Application layer
	private final GetUserUseCase app = config.getUserUseCase();

	public String continuar() {

		User user = userBean.getUser();

		if (user == null) {
			return "index.xhtml?faces-redirect=true";
		}

		Terreno terreno = new Terreno(comunidad, hectarias, precio);

		app.addTerreno(user.getName(), terreno);

		userBean.setUser(app.getUser(user.getName()));

		return "home.xhtml?faces-redirect=true";
	}

	// REQUIRED for ManagedProperty injection
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public String getComunidad() {
		return comunidad;
	}

	public void setComunidad(String comunidad) {
		this.comunidad = comunidad;
	}

	public int getHectarias() {
		return hectarias;
	}

	public void setHectarias(int hectarias) {
		this.hectarias = hectarias;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
}
