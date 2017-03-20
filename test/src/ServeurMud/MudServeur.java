package ServeurMud;

import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject ;
import java.util.ArrayList;
import java.util.HashMap;

import Classe.AlerteInterface;
import Fight.Fight;
import Labyrinthe.Joueur;
import Labyrinthe.Labyrinthe;
import Labyrinthe.Monstre;
import Labyrinthe.Piece;
import Labyrinthe.Porte;

public class MudServeur extends UnicastRemoteObject implements MudInterface {

private static final long serialVersionUID = 1L ;
	

private Thread t;

 private Labyrinthe Labyrinthe1 ;
 private HashMap<String,Joueur> lesjoueurs; 

	
 	
	protected MudServeur() throws RemoteException {
		Labyrinthe1 = new Labyrinthe() ;
		Labyrinthe1.createLabyrinthe();
		lesjoueurs = new HashMap<String, Joueur>();
		// TODO Auto-generated constructor stub
	}

	
	
		
  



/*public boolean authentificationreussi(String nomP,String password) throws RemoteException
{
	// TODO Auto-generated method stub
	
		
		if (joueurexistdeja(nomP,password)){
			Joueur j = new Joueur(nomP, 10, this.Labyrinthe1.getSalleDepart()) ;
			lesjoueurs.put(nomP, j);
			return true ;

		}
	
	
	return false;
}*/



/* Creation du joueur */
	public void creationJoueur(String nomJ, AlerteInterface alerte) throws RemoteException{
		Joueur j = new Joueur(nomJ, 10,0, alerte);
		
		this.lesjoueurs.put(nomJ, j );
	  
	  Piece salle = this.Labyrinthe1.getSalle().get(0);
	  
	  salle.addJoueur(j);
	  
	  System.out.println("Player Created Successsss!");
   }

	
/* Deplacement joueur */
	public int deplacementjoueur(String nomJ, String direction) throws RemoteException {
		
		// TODO Auto-generated method stub
		Joueur j = lesjoueurs.get(nomJ);
		int p = j.getposition(); 
		ArrayList<Porte> listePortes = Labyrinthe1.getSalle().get(p).getListsortie();
		
		if ( j.getCombat() == 0 )
		{
		// pas d'instance de combat
			// parcourt les portes de la salle
			for ( Porte sortie: listePortes ){
			
				// trouve la bonne porte selon la direction 
				if( sortie.getOrientation().equals(direction) )
				{
					
					j.setPposition(j.getposition());
					j.setposition(sortie.getSalle2().getNumero());
					
					//Deplacement du joueur dans la nouvelle salle
					// ajustement des listes des joueurs
					sortie.getSalle1().removeJoueur(j);
					sortie.getSalle2().addJoueur(j);
					
					if ( j.getposition() == this.Labyrinthe1.getSalleFin().getNumero()){
					// fin du jeu
						j.getAlerteJ().alerteMessage("CONGRATULATION, you have broken the legacy of the 9 rings\n"
								+"Thank you for playing..."
								+ "			CREATED by DHRISHTA SEWRAJ");
						return 0;
					}
						System.out.println("Maj Deplacement Joueur succes");
					return 1;
							
				}
			}
		}
		else
		{
			j.getAlerteJ().alerteMessage("You cannot move to this place, you are fighting.");
		}
		
		System.out.print("Cannot move in this direction !");
		return -1;
	
	}


/*
 * nom de la salle ou se trouve le joueur
 */
	public String getPiece(String nomJ) throws RemoteException {
		// TODO Auto-generated method stub
		Joueur j = lesjoueurs.get(nomJ);
		int p = j.getposition(); 
		
		return Labyrinthe1.getSalle().get(p).getIdPiece();
	}


/*
 * salle ou se trouve le joueur
 */
	public Piece getSalle(String nomJ) throws RemoteException {
		// TODO Auto-generated method stub
		Joueur j = lesjoueurs.get(nomJ);
		int p = j.getposition(); 
		
		return Labyrinthe1.getSalle().get(p);
	}
	
	
// retourne la liste des joueurs present
	@Override
	public HashMap<String, Joueur> getListeJoueur(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		Joueur j = lesjoueurs.get(nom);
		
		return Labyrinthe1.getSalle().get(j.getposition()).getListeJoueur();
		
	
	}
	
	
/*
 * combat
 */	
	public void combatMonstre(String nomJ) throws RemoteException {
		
		
		Joueur j = this.lesjoueurs.get(nomJ);
		
		// recuperer la salle et le monstre 
		Piece s = this.Labyrinthe1.getSalle().get(j.getposition());
		Monstre m = s.getM();
		
		if ( j.getCombat() != 0 ){
			j.getAlerteJ().alerteMessage("You are already fighting this Monster.");
		}
		else
		{
			j.setCombat(1);
			j.getAlerteJ().alerteMessage("The monster of "+s.getIdPiece()
					+" is coming at you !!!Fight or flee like a coward!");
			
			t = new Thread( new Fight(j, m) );
			t.start();
		}
		
		
			
	}

/* Fuite du combat */
	public void fuiteCombat( String nom ) throws RemoteException {
		Joueur j = this.lesjoueurs.get(nom);
		
		// recuperer la salle et le monstre 
		Piece s = this.Labyrinthe1.getSalle().get(j.getposition());
		//Monstre m = s.getM();
		
		if ( j.getCombat() == 0 ){
			j.getAlerteJ().alerteMessage("You are not fighting, keep cool");
		}
		else if ( j.getposition() == j.getPposition() ){
			j.getAlerteJ().alerteMessage("You cannot flee 2 times.");
		}
		else
		{
			try{
				t.interrupt();
				System.out.println("Escape ok");
				j.setposition(j.getPposition());
				j.getAlerteJ().alerteMessage("You flee the battle .\n");
				s.removeJoueur(j);
				s = this.Labyrinthe1.getSalle().get(j.getposition());
				s.addJoueur(j);
			}
			catch ( NullPointerException e){
				System.out.println("No escape ");
			}
			
			
		}
		
	}
	
	

/*
 * Verification de combat 
 */
	@Override
	public int verificationCombat(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		Joueur j = this.lesjoueurs.get(nom);
		Piece s = this.Labyrinthe1.getSalle().get(j.getposition());
		
		// si le joueur est dans la premiere salle pas de combat
		if ( j.getposition() == this.Labyrinthe1.getSalleDepart().getNumero() ){
			//peut se deplacer 
			return 1;
		}
		else if ( s.getM().getPoints() > 0 ){
			j.getAlerteJ().alerteMessage("There is a Big Monster, "+s.getM().getNom()+" !\n");
			if ( s.getListeJoueur().size() == 1 ){
				// salle vide 
				// instance de combat automatique avec le monstre
				j.getAlerteJ().alerteMessage("If you don't fight:\n"
						+ "2° Escape... LOOSER\n"
						+ "Or tap enter when battle is finish\n");
				this.combatMonstre(nom);
				return -1;
			}
			else if ( s.getListeJoueur().size() > 1 ){
				// salle non vide 
				j.getAlerteJ().alerteMessage("You are not alone !! But you cannot move from this place.\n"
						+ "If you want to go out of this Dungeon !!\n"
						+ "1° Fight\n"
						+ "2° Escape... LOSER\n");
				return -1;
			}
		}
		else if ( s.getListeJoueur().size() == 1 )
		{
			// on spawn le monstre et instance de combat automatique
			s.getM().setPoints(5);
			j.getAlerteJ().alerteMessage("There is a Big Monster, "+s.getM().getNom()+" !\n");
			j.getAlerteJ().alerteMessage("If you don't fight:\n"
					+ "2° Escape... LOSER\n"
					+ "Or tap enter when battle is finish\n");
			this.combatMonstre(nom);
			return -1;
		}
		return 1;
		
			
	}

	
	
	public void majJoueur(Joueur j, Monstre m) throws RemoteException{
		Joueur player = this.lesjoueurs.get(j.getNom());
		
		Monstre m1 = this.Labyrinthe1.getSalle().get(j.getposition()).getM();
		if( j.getposition() != 0 ){
			m1.setPoints(m.getPoints());
		}
		
		player.setCombat(j.getCombat());
		player.setPoints(j.getPoints());
		player.setPposition(j.getPposition());
		player.setposition(j.getposition());
		
	}


	@Override
	public void finJeu(String nom) throws RemoteException {
		// TODO Auto-generated method stub
		Joueur j = this.lesjoueurs.get(nom);
		Piece s = this.Labyrinthe1.getSalle().get(j.getposition());
		
		s.removeJoueur(j);
		
		lesjoueurs.remove(nom);
		
	}

	
	
	
	
	
	
	
	

	 public static void main(String arg [])  {
		 //Démarrer le rmi registry
		 try {
			LocateRegistry.createRegistry(1099);
			//Gestionnaire de sécurité
			 //System.setSecurityManager(new RMISecurityManager());
			 MudServeur obj = new MudServeur();
			 Naming.rebind("ServeurMUD", obj) ;
			 System.out.println("Serveur est connecté!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }




	
	
	
	
}
