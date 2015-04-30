/**
 * 
 */
package test;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotMember;
import avis.SocialNetwork;

/**
 * @author Antoine GIRARD, Simon LILLE
 *
 */
public class TestsAddItemFilm {

	public static int addItemFilmBadEntryTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur) {
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (BadEntry e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de films a été modifié");
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

	public static int addItemFilmNotMemberTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur) {
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (NotMember e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de films a été modifié");
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

	public static int addItemFilmItemFilmAlreadyExistsTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest, String messErreur) {
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			System.out.println ("Test " + idTest + " : " + messErreur);
			return 1;
		}
		catch (ItemFilmAlreadyExists e) {
			if (sn.nbFilms() != nbFilms) {
				System.out.println("Test " + idTest + " : l'exception ItemFilmAlreadyExists a bien été levée mais le nombre de films a été modifié");
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
	
	public static int addItemFilmOKTest(SocialNetwork sn, String pseudo, String password, String titre, String genre, String realisateur, String scenariste, int duree, String idTest) {
		int nbFilms = sn.nbFilms();
		try{
			sn.addItemFilm(pseudo, password, titre, genre, realisateur, scenariste, duree);
			if (sn.nbFilms() != nbFilms+1) {
				System.out.println("Test " + idTest + " : le nombre de film n'a pas été correctement incrémenté");
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
		
		System.out.println("Tests ajouter des films au réseau social  ");

		SocialNetwork sn = new SocialNetwork();

		int nbLivres = sn.nbBooks();

		//On ajout un utilisateur qui nous servira pour les ajout de film
		try {
			sn.addMember("UtilisateurAddItemFilmTest", "password", "grand amoureux de film");
		} catch (Exception e) {
			System.out.println ("Exception non prévue lors de la création de l'utilisateur servant au test. " + e); 
			e.printStackTrace();
		} 

		// <=> fiche numéro 3
		// tentative d'ajout de films avec entrées "incorrectes"
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, null, "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "1.1", "L'ajout d'un film dont le pseudo n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, " ", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "1.2", "L'ajout d'un film dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", null, "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "1.3", "L'ajout d'un film dont le password n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "   qwd ", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "1.4", "L'ajout d'un film dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "password", null, "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "1.5", "L'ajout d'un film dont le titre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "password", " ", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "1.6", "L'ajout d'un film dont le titre ne contient pas un caracteres, autre que des espaces, est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "password", "Forrest Gump", null, "Robert Zemeckis", "Eric Roth", 145, "1.7", "L'ajout d'un film dont le genre n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "password", "Forrest Gump", "Comédie dramatique", null, "Eric Roth", 145, "1.8", "L'ajout d'un film dont le réalisateur n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", null, 145, "1.9", "L'ajout d'un film dont le scénariste n'est pas instancié est accepté");
		nbTests++;
		nbErreurs += addItemFilmBadEntryTest(sn, "UtilisateurAddItemFilmTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", -1, "1.10", "L'ajout d'un film dont la durée est négative est accepté");

		//Tentative d'ajout de film avec des informations utilisateurs invalides (pseudo, password)
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest(sn, "NotAMenber", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "2.1", "L'ajout d'un film dont l'utilisateur n'existe pas est accepté");
		nbTests++;
		nbErreurs += addItemFilmNotMemberTest(sn, "UtilisateurAddItemFilmTest", "badpassword", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "2.2", "L'ajout d'un film dont le mot de passe ne correspond pas à celui de l'utilisateur n'existe pas est accepté");


		// <=> fiche numéro 4
		//Ajout de 3 films corrects
		//On utilise les utilisateur crée lors ud test de AddMember
		nbTests++;
		nbErreurs += addItemFilmOKTest(sn, "UtilisateurAddItemFilmTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "3.1a");
		nbTests++;
		nbErreurs += addItemFilmOKTest(sn, "UtilisateurAddItemFilmTest", "password", "The Dark Knight, Le Chevalier Noir", "Action", "Christopher Nolan", "Jonathan Nolan,Christopher Nolan", 153, "3.1b");
		nbTests++;
		nbErreurs += addItemFilmOKTest(sn, "UtilisateurAddItemFilmTest", "password", "Le Parrain", "Policier", "Francis Ford Coppola", "Mario Puzo,Francis Ford Coppola", 178, "3.1c");
	
		
		//Tentative d'ajout de film déjà existant
		nbTests++;
		nbErreurs += addItemFilmItemFilmAlreadyExistsTest(sn, "UtilisateurAddItemFilmTest", "password", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "4.1", "L'ajout d'un film existant avec le titre identique est accepté");
		nbTests++;
		nbErreurs += addItemFilmItemFilmAlreadyExistsTest(sn, "UtilisateurAddItemFilmTest", "password", " Forrest Gump  ", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "4.2", "L'ajout d'un film existant (avec leading et trailing blanks) est accepté");
		nbTests++;
		nbErreurs += addItemFilmItemFilmAlreadyExistsTest(sn, "UtilisateurAddItemFilmTest", "password", "forrest gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "4.3", "L'ajout d'un film existant avec le titre indifférent à la casse (en minuscule) est accepté");
		nbTests++;
		nbErreurs += addItemFilmItemFilmAlreadyExistsTest(sn, "UtilisateurAddItemFilmTest", "password", "foRRest gumP", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "4.4", "L'ajout d'un film existant avec le titre indifférent à la casse (aléatoire) est accepté");

		//On vérifie que le nombre de livre n'a pas changé
		nbTests++;
		if (nbLivres != sn.nbBooks()) {
			System.out.println("Erreur  :  le nombre de livres après utilisation de addItemFilm a été modifié");	
			nbErreurs++;
		}
//		nbErreurs += addItemFilmBadEntryTest(sn, "Antoine", "antoine", "Forrest Gump", "Comédie dramatique", "Robert Zemeckis", "Eric Roth", 145, "0.0", "Nothing");
		// bilan du test de addItemFilm
		System.out.println("TestsAddItemFilm :   " + nbErreurs + " erreur(s) / " +  nbTests + " tests effectués");
	    
		// ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {        
           nbTests = nbTests + new Integer(args[0]);
           nbErreurs = nbErreurs + new Integer(args[1]);       
           args[0] = "" + nbTests;
           args[1] = "" + nbErreurs;
        }
	}

}
