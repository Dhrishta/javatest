package Labyrinthe;

import java.io.Serializable;

import Classe.AlerteInterface;

public class Joueur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private String nom ;
	private int points ;
	private int position ;
	private int pposition=0;
	private int combat = 0;
	private AlerteInterface alerteJ;
	
	public Joueur(String nom, int points, int position, AlerteInterface alerte) {
		super();
		this.nom = nom;
		this.points = points;
		this.position = position;
		this.setAlerteJ(alerte);
	}
	
/* Getteurs && Setteurs */
	public int getposition() {
		return position;
	}
	public void setposition(int position) {
		this.position = position;
	}
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
	public AlerteInterface getAlerteJ() {
		return alerteJ;
	}
	public void setAlerteJ(AlerteInterface alerteJ) {
		this.alerteJ = alerteJ;
	}

	public int getCombat() {
		return combat;
	}

	public void setCombat(int combat) {
		this.combat = combat;
	}

	public int getPposition() {
		return pposition;
	}

	public void setPposition(int pposition) {
		this.pposition = pposition;
	}
	


}
 


