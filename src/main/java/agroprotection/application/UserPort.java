package agroprotection.application;

import agroprotection.domain.User;

public interface UserPort {

	public User getUser(String name);
}
