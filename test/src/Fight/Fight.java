
package Fight;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

import Labyrinthe.Joueur;
import Labyrinthe.Monstre;
import ServeurMud.MudInterface;

public class Fight implements Runnable{

	Joueur j;
	Monstre m;
	
	
	
	public Fight(Joueur j, Monstre m) {
		super();
		this.j = j;
		this.m = m;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		int max=3,min=1; // fonction du random permet d'avoir soit 0 soit 1 
		Random rand = new Random();
		int nb;
		
		do 
		{

try{
	
				Thread.sleep(1000);
			
			nb = min + rand.nextInt(max-1);
			
			if ( nb == 1 ){
				j.setPoints(j.getPoints()-1);
				try {
					j.getAlerteJ().alerteMessage("You lose 1 pV");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else 
			{
				m.setPoints(m.getPoints()-1);
				try {
					j.getAlerteJ().alerteMessage(m.getNom()+" lose 1 pv");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
} catch ( InterruptedException i) {
	// On arrete le thread 
	System.out.println("The player has fled !\n");
	Thread.currentThread().interrupt();
	break;
}	
		}while( (j.getPoints() >0) && (m.getPoints() >0) && ( !Thread.interrupted() ));
		
		if ( (j.getPoints() > 0) && ( m.getPoints() == 0) ){
			// victoire
			j.setPoints(10);
			j.setCombat(0);
			try {
				j.getAlerteJ().alerteMessage("You win the battle .\n"
						+ "\n		Hit enter\n");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ( (j.getPoints() == 0) && ( m.getPoints() > 0) )
		{//defaite
			m.setPoints(5);
			j.setCombat(0);
			j.setPposition(0);
			j.setposition(0);
			j.setPoints(10);
			try {
				j.getAlerteJ().alerteMessage("You lose the battle .\n"
						+ "You shall return to the first room. Better Luck this time Warrior\n"
						+ "\n		Hit enter\n");
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ( (j.getPoints() > 0) && (m.getPoints() > 0) )
		{
			// fuite
			j.setCombat(0);
		}
		maj();
		
	}
	
	public void maj(){
		MudInterface mud;
		try {
			mud = (MudInterface)Naming.lookup("//localhost/ServeurMUD");
			mud.majJoueur(j, m);
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
