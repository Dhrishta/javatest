package Classe;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AlerteInterface extends Remote {
	
	
	void alerteMessage(String msg) throws RemoteException;
	
}