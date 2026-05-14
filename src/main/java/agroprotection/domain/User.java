package agroprotection.domain;

public class User {
	
	private String name;
	private int hectarias;
	
	public String getName() {
		return name;
	}
	
	public int getHectarias() {
		return hectarias;
	}
	

	public User(String name, int hectarias) {
		this.name = name;
		this.hectarias = hectarias;
	}
}