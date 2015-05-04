/**
 * 
 */
package test;

import exception.BadEntry;
import exception.ItemBookAlreadyExists;
import exception.NotMember;
import avis.SocialNetwork;

/**
 * @author Antoine GIRARD, Simon LILLE
 *
 */
public class TestsAddItemBook {

	public static int addItemBookBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de livre a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}

	public static int addItemBookItemBookAlreadyExistsTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest, String messErreur) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemBookAlreadyExists e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Test " + idTest + " : l'exception ItemBookAlreadyExists a bien été levée mais le nombre de livres a été modifié");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	
	public static int addItemBookOKTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String auteur, int nbPages, String idTest) {
		int nbBooks = sn.nbBooks();
		try{
			sn.addItemBook(pseudo, password, titre, genre, auteur, nbPages);
			if (sn.nbBooks() != nbBooks+1) {
				System.out.println("Test " + idTest + " : le nombre de livre n'a pas été correctement incrémenté");
				return 1;
			}
			else 
				return 0;
		}
		catch (Exception e) {
			System.out.println ("Test " + idTest + " : exception non prévue. " + e); 
			e.printStackTrace();
			return 1;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;
		
		System.out.println("Tests ajouter des livres au réseau social  ");

		SocialNetwork sn = new SocialNetwork();

		int nbFilms = sn.nbFilms();

		//On ajout un utilisateur qui nous servira pour les ajout de livre
		try {
			sn.addMember("UtilisateurAddItemBookTest", "password", "grand amoureux de livre");
		} catch (Exception e) {
			System.out.println ("Exception non prévue lors de la création de l'utilisateur servant au test. " + e); 
			e.printStackTrace();
		} 

		// <=> fiche numéro 3
		// tentative d'ajout de livre avec entrées "incorrectes"
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, null, "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", 145, "1.1", "L'ajout d'un livre dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, " ", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", 145, "1.2", "L'ajout d'un livre dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", null, "Forrest Gump", "Comédie dramatique", "Robert Zemeckis",  145, "1.3", "L'ajout d'un livre dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", "   qwd ", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis",  145, "1.4", "L'ajout d'un livre dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", "password", null, "Comédie dramatique", "Robert Zemeckis",  145, "1.5", "L'ajout d'un livre dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", "password", " ", "Comédie dramatique", "Robert Zemeckis",  145, "1.6", "L'ajout d'un livre dont le titre ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", "password", "Forrest Gump", null, "Robert Zemeckis",  145, "1.7", "L'ajout d'un livre dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", "password", "Forrest Gump", "Comédie dramatique", null,  145, "1.8", "L'ajout d'un livre dont l'auteur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemBookBadEntryTest(sn, "UtilisateurAddItemBookTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", -1, "1.10", "L'ajout d'un livre dont le nb de page est négative est accepté");

		//Tentative d'ajout de livre avec des informations utilisateurs invalides (pseudo, password)
		nbTests++;
		nbErreurs += addItemBookNotMemberTest(sn, "NotAMenber", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", 145, "2.1", "L'ajout d'un livre dont l'utilisateur n'existe pas est accepté");
		nbTests++;
		nbErreurs += addItemBookNotMemberTest(sn, "UtilisateurAddItemBookTest", "badpassword", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", 145, "2.2", "L'ajout d'un livre dont le mot de passe ne correspond pas à celui de l'utilisateur n'existe pas est accepté");


		// <=> fiche numéro 4
		//Ajout de 3 livres corrects
		//On utilise les utilisateurs crée lors du test de AddMember
		nbTests++;
		nbErreurs += addItemBookOKTest(sn, "UtilisateurAddItemBookTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", 145, "3.1a");
		nbTests++;
		nbErreurs += addItemBookOKTest(sn, "UtilisateurAddItemBookTest", "password", "The Dark Knight, Le Chevalier Noir", "Action", "Christopher Nolan", 153, "3.1b");
		nbTests++;
		nbErreurs += addItemBookOKTest(sn, "UtilisateurAddItemBookTest", "password", "Le Parrain", "Policier", "Francis Ford Coppola", 178, "3.1c");
	
		
		//Tentative d'ajout de livre déjà existant
		nbTests++;
		nbErreurs += addItemBookItemBookAlreadyExistsTest(sn, "UtilisateurAddItemBookTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", 145, "4.1", "L'ajout d'un livre existant avec le titre identique est accepté");
		nbTests++;
		nbErreurs += addItemBookItemBookAlreadyExistsTest(sn, "UtilisateurAddItemBookTest", "password", " Forrest Gump  ", "Comédie dramatique", "Robert Zemeckis", 145, "4.2", "L'ajout d'un livre existant (avec leading et trailing blanks) est accepté");
		nbTests++;
		nbErreurs += addItemBookItemBookAlreadyExistsTest(sn, "UtilisateurAddItemBookTest", "password", "forrest gump", "Comédie dramatique", "Robert Zemeckis", 145, "4.3", "L'ajout d'un livre existant avec le titre indifférent à la casse (en minuscule) est accepté");
		nbTests++;
		nbErreurs += addItemBookItemBookAlreadyExistsTest(sn, "UtilisateurAddItemBookTest", "password", "foRRest gumP", "Comédie dramatique", "Robert Zemeckis", 145, "4.4", "L'ajout d'un livre existant avec le titre indifférent à la casse (aléatoire) est accepté");

		//On vérifie que le nombre de film n'a pas changé
		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Erreur  :  le nombre de films après utilisation de addItemBook a été modifié");	
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);
		// bilan du test de addItemBook
		System.out.println("TestsAddItemBook :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");
	    
		// ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
	}

}
