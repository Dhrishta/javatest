package Classe;

import java.rmi.RemoteException;
import java.util.Scanner;

import Chat.ChatInterface;
import ServeurMud.MudInterface;



public class Menu {
	 private String nom;
	 private MudInterface mud;
	 private ChatInterface chat;
	 private boolean finJeu= false;
	 
//Menu pour démarrer le jeu	

public Menu(MudInterface mud, ChatInterface chat) {
		// TODO Auto-generated constructor stub
	this.mud = mud;
	this.chat = chat;
	}


public String getNom() {
	return nom;
}


public void setNom(String nom) {
	this.nom = nom;
}


public MudInterface getMud() {
	return mud;
}


public void setMud(MudInterface mud) {
	this.mud = mud;
}

public ChatInterface getChat() {
	return chat;
}


public void setChat(ChatInterface chat) {
	this.chat = chat;
}


/**
 * 
 */

/*
 * Menu PRINCIPAL
 */
	public void affichageMenuPrincipal(){
		
		Scanner input = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
	
		System.out.println("*****************************************************");
		System.out.println("*****************************************************");
		System.out.println("*****************************************************");
		System.out.println("*****************************************************");	
		System.out.println("*****************************************************");	
		System.out.println("************************ **  ************************");	
		System.out.println("***********************  **   ***********************");	
		System.out.println("**********************   **    **********************");	
		System.out.println("*********************    **     *********************");	
		System.out.println("********************     **      ********************");	
		System.out.println("*******************      **       *******************");	
		System.out.println("******************       **        ******************");
		System.out.println("*****************        **         *****************");
		System.out.println("****************      DUNGEONS       ****************");
		System.out.println("***************          of           ***************");
		System.out.println("**************         MORDOR          **************");
		System.out.println("*************            **             *************");
		System.out.println("************             **              ************");
		System.out.println("***********              **               ***********");
		System.out.println("**********               **                **********");
		System.out.println("*********                **                 *********");
		System.out.println("********                 **                  ********");
		System.out.println("*******                  **                   *******");
		System.out.println("******                   **                    ******");
		System.out.println("*****************************************************");
		System.out.println("*****************************************************");
	
	
		System.out.println("Are you ready to enter this perillious mission?");	
		System.out.println("YOUR DESTINY LIES WITHIN THESE OPTIONS...CHOOSE WISELY");
		do
		{	
			System.out.println("MENU");
			System.out.println("1° START ADVENTURE\n"
					         + "2° QUIT              ");	
		
			String selection = input.nextLine();
			while ( selection.length() <= 0 )
			{
				System.out.println("Error!! Enter 1 or 2, please Retry !");	
				selection = input.nextLine();
			}

			char choix = selection.charAt(0);
			
			switch(choix){
				case '1':
					
				try {
					System.out.println("WHAT IS YOUR NAME BRAVE HOBBIT?");
					nom = sc.nextLine();
					mud.creationJoueur(nom, new AlerteImpl() );
					System.out.println("Welcome to the Dungeons of Mordor "+nom);
			        System.out.println("You are now entering the Dungeon by the first room") ;
			        System.out.println("You have 10 life points or PV");
				    System.out.println("May the will of the Shire and Elves be with you !\n");
				    affichageMenuDeplacement();
				       
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				       
				       	break;
			case '2' : 
					System.out.println("Farewell");
					System.exit(0);
					break; 
			
			default:
				System.out.println("Error!! Enter 1 or 2, please Retry !");	
				break;
				
				
			}
			
				
			
		}while ( true );
	
	}
	

/*
 * Menu DEPLACEMENT
 */
	public void affichageMenuDeplacement(){
		String selection=null;
		Scanner input = new Scanner(System.in);
		int res;
		finJeu = false;
try {			
	
		do {
			
			System.out.println("You are now in "+mud.getPiece(nom));	
			System.out.println("Select a direction move in the Dungeon");	
			System.out.println("N : To move north\n"
					         + "S : To move south\n"             
							 + "E : To move east\n"
					         + "W: To move west \n"
							 + "Or, if you want to chat just write \"+ your text.\n"
							 + "/exit, if you want to exit the game.\n" );	
		
			// SAISIE
			selection = input.nextLine();
			if ( selection.startsWith("\"") ){
				chat.envoyerMessages(nom, selection);
			}
			else if ( selection.equals("/exit") )
			{
				mud.finJeu(nom);
				finJeu = true; 
			}
			// deplacement
			else if ( (res = mud.deplacementjoueur(nom, selection) )< 0 ){
					System.out.println("Cannot move in this direction ! Retry");
			}
			else if ( res > 0 )
			{
				if ( mud.verificationCombat(nom) < 0 ){
					affichageMenuCombat();
				}
			}else if ( res == 0 ){
				mud.finJeu(nom);
				finJeu=true;
			}
			
		}while( !finJeu );
			
				
} catch (RemoteException e) {
	// TODO Auto-generated catch block
	System.out.println("Connexion lost !"+e);
}
		
		
		

	}
	
/*
 * Menu COMBAT	
 */
	public void affichageMenuCombat() throws RemoteException{
		String choix;
		Scanner input = new Scanner(System.in);
			
		choix = input.nextLine();
		if ( choix.equals("1") ){
			try{
				mud.combatMonstre(nom);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if ( choix.equals("2") ){
		//	System.out.println("You are a looser, you will stay in this room !!"
				//	+ "To go out, you need to fight !\n");
			mud.fuiteCombat(nom);
		}
		else
		{
			while ( ( choix.equals("1") ) && (choix.equals("2")) ){
				System.out.println("Error!! Enter 1 or 2, please Retry !");
				choix = input.nextLine();
			}
		}
		
		
	}


	
}	
	
	
	
	
	
	
	
	
	

