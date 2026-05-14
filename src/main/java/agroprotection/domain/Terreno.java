package agroprotection.domain;

public class Terreno {
	private String comunidad;
	private int hectarias;
	private int precio;
	
	
	public Terreno(String comunidad, int hectarias, int precio) {
		this.comunidad = comunidad;
		this.hectarias = hectarias;
		this.precio = precio;
	}
	
	public String getComunidad() {
		return this.comunidad;
	}
	
	public int getHectarias () {
		return this.hectarias;
	}
	
	public int getPrecio () {
		return this.precio;
	}
}
