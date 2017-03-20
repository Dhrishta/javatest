package Chat;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatInterface extends Remote {
	
	
	void envoyerMessages( String nom, String msg ) throws RemoteException;
		
}