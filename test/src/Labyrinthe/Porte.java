package Labyrinthe;

public class Porte {
	private Piece salle1 ; //La sortie fait référence à 2 pièces
	private Piece salle2 ;
	private String orientation ;
	
	//Constructor
	public Porte(Piece salle1, Piece salle2, String orientation) {
		super();
		this.salle1 = salle1;
		this.salle2 = salle2;
		this.orientation = orientation;

	}
	public Piece getSalle1() {
		return salle1;
	}
	public void setSalle1(Piece salle1) {
		this.salle1 = salle1;
	}
	public Piece getSalle2() {
		return salle2;
	}
	public String getOrientation() {
		return orientation;
	}
	public void setOrientation(String orientation) {
		this.orientation = orientation;
	}
	public void setSalle2(Piece salle2) {
		this.salle2 = salle2;
	}


}