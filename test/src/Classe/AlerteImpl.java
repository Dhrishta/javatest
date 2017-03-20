package Classe;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



public class AlerteImpl extends UnicastRemoteObject implements AlerteInterface{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public AlerteImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	// 
	public void alerteMessage(String msg){
		System.out.println(msg);
	}

	
}