package agroprotection.application;

import agroprotection.domain.User;
import agroprotection.domain.Terreno;

public interface UserPort {

	public User getUser(String name);

	public void addTerreno(String userName, Terreno terreno);
}
