package Fight;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import ServeurMud.MudInterface;


public class ServeurFight extends UnicastRemoteObject implements FightInterface{



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private MudInterface mud;
	
	
	
	//constructor
	protected ServeurFight() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	public void connexionServeur(){
		try {
			setMud((MudInterface)Naming.lookup("//localhost/ServeurMUD"));
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	@Override
	public int combatMonstre(String nomJ) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(1119);
			//Gestionnaire de sécurité
			 //System.setSecurityManager(new RMISecurityManager());
			 ServeurFight obj = new ServeurFight();
			 Naming.rebind("ServeurFight", obj) ;
			 System.out.println("Serveur de combat est connecté!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}






	public MudInterface getMud() {
		return mud;
	}






	public void setMud(MudInterface mud) {
		this.mud = mud;
	}





}
