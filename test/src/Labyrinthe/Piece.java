package Labyrinthe;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Piece implements Serializable {
	   /**
	 * 
	 */
	private static final long serialVersionUID = 3L;
		// variables
		private String idPiece;
		private int numero;
		private Monstre m;
		
		// Liste des portes d'une salle
		private ArrayList <Porte> listsortie = new ArrayList <Porte>() ;
		private HashMap<String, Joueur> listeJoueur = new HashMap<String, Joueur>();
		
		
	public Piece(String idPiece, int numero, Monstre m) {
		super();
		this.numero = numero;
		this.idPiece = idPiece ;
		this.setM(m);
	}

		
	public Piece(String idPiece, int i) {
		// TODO Auto-generated constructor stub
		super();
		this.numero = i;
		this.idPiece = idPiece ;
	}


	public String getIdPiece() {
		return idPiece;
	}


	public void setIdPiece(String idPiece) {
		this.idPiece = idPiece;
	}


	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public ArrayList<Porte> getListsortie() {
		return listsortie;
	}


	public void setListsortie(ArrayList<Porte> listsortie) {
		this.listsortie = listsortie;
	}


	public void addPorte(Porte sortie){
		
		listsortie.add(sortie);
		
	}


	public Monstre getM() {
		return m;
	}


	public void setM(Monstre m) {
		this.m = m;
	}


	public HashMap<String, Joueur> getListeJoueur() {
		return listeJoueur;
	}


	public void setListeJoueur(HashMap<String, Joueur> listeJoueur) {
		this.listeJoueur = listeJoueur;
	}
	
	public void addJoueur(Joueur j ){
		this.listeJoueur.put(j.getNom(), j);
	}
	
	public void removeJoueur(Joueur j ){
		this.listeJoueur.remove(j.getNom(), j);
	}
}
		
