package agroprotection.domain;

import java.util.ArrayList;

public class User {
	
	private String name;
	private int hectarias;
	private ArrayList<Terreno> terrenos;

	public String getName() {
		return name;
	}

	public int getHectarias() {
		int h = 0;
		
		for (int i = 0; i < terrenos.size(); ++i)
			h += terrenos.get(i).getHectarias();
		return h;
	}

	public ArrayList<Terreno> getTerrenos() {
		return terrenos;
	}

	public User(String name, int hectarias, ArrayList<Terreno> terrenos) {
		this.name = name;
		this.hectarias = hectarias;
		this.terrenos = terrenos;
	}
}