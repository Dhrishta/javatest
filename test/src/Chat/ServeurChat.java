package Chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import Labyrinthe.Joueur;
import ServeurMud.MudInterface;

public class ServeurChat extends UnicastRemoteObject implements ChatInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private MudInterface mud;
	
	public MudInterface getMud() {
		return mud;
	}


	public void setMud(MudInterface mud) {
		this.mud = mud;
	}


	// constructor
	protected ServeurChat() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void envoyerMessages( String nom, String msg ) throws RemoteException {
		
		HashMap<String, Joueur> joueursPresents = mud.getListeJoueur(nom);
		for(Joueur j : joueursPresents.values()) {
			//System.out.println("Tentative   "+);
			//if ( !j.getNom().equals(joueursPresents.get(nom).getNom())){
				try{
					j.getAlerteJ().alerteMessage("\n"+joueursPresents.get(nom).getNom()+" says : "+msg+'"');
				}
				catch ( Exception e){
					System.out.println("One of the players is in this room at this instant !");
				}
			//}
		}
		
	}

	
	
	
	public static void main(String arg []){
		try {
			LocateRegistry.createRegistry(1098);
			ServeurChat ob = new ServeurChat();
			
			Naming.rebind("ServeurChat", ob); 
			System.out.println("Serveur chat est connecté !!");
			
			ob.setMud( (MudInterface)Naming.lookup("//localhost/ServeurMUD") );
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
