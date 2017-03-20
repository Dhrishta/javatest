package Fight;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FightInterface extends Remote  {

	int combatMonstre(String nomJ) throws RemoteException;
	
	

}
