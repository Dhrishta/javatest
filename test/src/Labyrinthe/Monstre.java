package Labyrinthe;

import java.io.Serializable;

public class Monstre implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6L;
	
	
	private String nom ;
	private int points=5; 
	
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	
	public Monstre(String nom) {
		super();
		this.nom = nom;
	}
	
}
