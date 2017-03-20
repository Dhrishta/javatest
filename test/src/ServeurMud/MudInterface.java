package ServeurMud;

import java.rmi.*;
import java.util.HashMap;

import Classe.AlerteInterface;
import Labyrinthe.Joueur;
import Labyrinthe.Monstre;
import Labyrinthe.Piece;

public interface MudInterface extends java.rmi.Remote {


	
/*public boolean authentificationreussi (String nomP , String password ) throws RemoteException ;
public boolean creationreussi (String nomP ,String password ) throws RemoteException ;
public boolean joueurexistdeja  (String nomP , String password) throws RemoteException ;
public void affichage() throws RemoteException ;*/
	
/* deplacement avec maj du joueur */
	int deplacementjoueur(String nomJ, String Direction) throws RemoteException ;

/* recueperer le nom de la piece ou se trouve le joueur */
	String getPiece(String nomJ )throws RemoteException;

/* creation d'un nouveau joueur  */
	void creationJoueur(String nomJ, AlerteInterface alerte )throws RemoteException;

/* recupere la salle ou se trouve le joueur */
	Piece getSalle(String nomJ) throws RemoteException;

/* recuperer la liste des joueur d'une salle */
	HashMap<String,Joueur> getListeJoueur(String nom ) throws RemoteException;

/* combat vs monstre */
	void combatMonstre(String nomJ) throws RemoteException;
	
/* fuire le combat */
	void fuiteCombat( String nom ) throws RemoteException;

/* maj joueur suite à un combat */
	void majJoueur(Joueur j, Monstre m) throws RemoteException;
	
/* fermeture du client propre */
	void finJeu(String nom) throws RemoteException;

	
/* verifie si il ya lieu d'avoir un combat ou non */
	int verificationCombat(String nom) throws RemoteException;
	
}
