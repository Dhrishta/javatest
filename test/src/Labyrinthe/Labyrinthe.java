package Labyrinthe;

import java.io.Serializable;
import java.util.ArrayList;

public class Labyrinthe implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<Piece> listeSalle = new ArrayList<Piece>() ;  		
	//private ArrayList<Porte> listePortes = new ArrayList<Porte>();
	private Piece salleDepart ;// initialiser à la creation du labyrinthe
	private Piece salleFin ;// initialiser à la creation du labyrinthe
	
	public Piece getSalleDepart() {
		return salleDepart;
	}

	public void setSalleDepart(Piece salleDepart) {
		this.salleDepart = salleDepart;
	}

	public Labyrinthe() {
		super();
		
	}

	public ArrayList<Piece> getSalle() {
		return listeSalle;
	}

	public void setSalle(ArrayList<Piece> salle) {
		listeSalle = salle;
	}


/* Creation du labyrinthe */
	public void createLabyrinthe(){
	 		
 
        //Création des Pièces du Labyrinthe
 		Piece S0 = new Piece ("Chamber of Mazarbul Moria",0) ;
 		listeSalle.add(S0);
 		//Salle de départ = Salle 0
 		this.setSalleDepart(S0);
		Piece S1 = new Piece ("Red Tooth",1, new Monstre("Nazgul The Black Rider")) ;
		listeSalle.add(S1);
 		Piece S2 = new Piece ("Crack of Doom",2, new Monstre("Nosferatu")) ;
		listeSalle.add(S2);
 		Piece S3 = new Piece ("Dead Marshes",3, new Monstre("Orcs")) ;
		listeSalle.add(S3);
 		Piece S4 = new Piece ("Dimholt",4, new Monstre("Denethor II")) ;
 		
		listeSalle.add(S4);
		Piece S5 = new Piece("Durthtang",5, new Monstre("Saruman"));
		listeSalle.add(S5);
 		Piece S6 = new Piece("Fence of Shadow", 6, new Monstre("Shelob")); 		
		listeSalle.add(S6);	
 		Piece S7 = new Piece("Harondor", 7, new Monstre("Faramir") );
		listeSalle.add(S7);	
 		Piece S8 = new Piece("Khazad Dum", 8, new Monstre("Sauron"));
		listeSalle.add(S8);	
		Piece S9 = new Piece("Farthing Stone... THE SHIRE", 9);
		listeSalle.add(S9);	
		this.setSalleFin(S9);
		
 		//Creation des portes
		//Creation de portes de la piece 0 
 		Porte P0_1 = new Porte(S0,S1,"E"); 		
 		Porte P0_3 = new Porte(S0,S3,"S");
 		S0.addPorte(P0_1);
 		S0.addPorte(P0_3);
 		
 		//Creation de portes de la piece 1
 		Porte P1_0 = new Porte(S1,S0,"W");
 		Porte P1_2 = new Porte(S1,S2,"E");
 		Porte P1_4 = new Porte(S1,S4,"S");
 		S1.addPorte(P1_0);
 		S1.addPorte(P1_2);
 		S1.addPorte(P1_4);
 		
 		//Creation de portes de la piece 3
 		Porte P2_1 = new Porte(S2,S1,"W");
 		Porte P2_5 = new Porte(S2,S5,"S");
 		S2.addPorte(P2_1);
 		S2.addPorte(P2_5);
 		
 		//Creation de portes de la piece 3
 		Porte P3_0 = new Porte(S3,S0,"N");
 		Porte P3_4 = new Porte(S3,S4,"E");
 		Porte P3_6 = new Porte(S3,S6,"S");
 		S3.addPorte(P3_0);
 		S3.addPorte(P3_4);
 		S3.addPorte(P3_6);
 		
        //Creation de portes de la piece 4 
 		Porte P4_3 = new Porte(S4,S3,"W");
 		Porte P4_1 = new Porte(S4,S1,"N");
 		Porte P4_5 = new Porte(S4,S5,"E");
 		Porte P4_7 = new Porte(S4,S7,"S");
		S4.addPorte(P4_3);
 		S4.addPorte(P4_1);
 		S4.addPorte(P4_5);
 		S4.addPorte(P4_7);
 		
 		//Creation de portes de la piece 5 
 		Porte P5_2 = new Porte(S5,S2,"N");
 		Porte P5_4 = new Porte(S5, S4,"W");
 		Porte P5_8 = new Porte(S5,S8,"S");
 		S5.addPorte(P5_2);
 		S5.addPorte(P5_4);
 		S5.addPorte(P5_8);
 		
 		//Creation de portes de la piece 6 
 		Porte P6_3 = new Porte(S6,S3,"N");
 		Porte P6_7 = new Porte(S6,S7,"E");
 		S6.addPorte(P6_3);
 		S6.addPorte(P6_7);
 		
 		//Creation de portes de la piece 7
 		Porte P7_4 = new Porte(S7,S4,"N");
 		Porte P7_6 = new Porte(S7,S6,"W");
 		Porte P7_8 = new Porte(S7,S8,"E");
 		S7.addPorte(P7_4);
 		S7.addPorte(P7_6);
 		S7.addPorte(P7_8);
 		
 		//Creation de portes de la piece 8
 		Porte P8_5 = new Porte(S8,S5,"N");
 		Porte P8_7 = new Porte(S8,S7,"W");
 		Porte P8_9 = new Porte(S8, S9, "E" );
 		S8.addPorte(P8_5);
 		S8.addPorte(P8_7);
 		S8.addPorte(P8_9);
 				
 		
 	}

	public Piece getSalleFin() {
		return salleFin;
	}

	public void setSalleFin(Piece salleFin) {
		this.salleFin = salleFin;
	}

 
 		

	
}
