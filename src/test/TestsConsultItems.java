package test;

import java.util.LinkedList;

import test.TestsReviewOpinion.Karma;
import test.TestsReviewOpinion.Moyenne;
import exception.BadEntry;
import avis.SocialNetwork;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */

//TODO verify that the moyenne doesn't change during the faulting test
public class TestsConsultItems {

	static class Moyenne {
		public float value;
	}

	static class Karma {
		public float value;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests de review des opinions au réseau social  ");
		SocialNetwork sn = new SocialNetwork();

		// On ajout un utilisateur qui nous servira pour les reviews
		try {
			sn.addMember("UtilisateurConsultTest", "password",
					"grand amoureux de critique de critique");
		} catch (Exception e) {
			System.out
					.println("Exception non prévue lors de la création de l'utilisateur servant au test. "
							+ e);
			e.printStackTrace();
		}

		// On ajout des items qui nous serviront pour les reviews
		try {
			sn.addItemBook("UtilisateurConsultTest", "password",
					"Pulp Fiction", "Policier", "Quentin Tarantino", 168);
		} catch (Exception e) {
			System.out
					.println("Exception non prévue lors de l'ajout de opinion servant au test. "
							+ e);
			e.printStackTrace();
		}

		int nbFilms = sn.nbFilms();
		int nbLivres = sn.nbBooks();

		nbTests++;
		nbErreurs += consulItemBadEntry(sn, null, "1.1",
				"consulItem avec un titre non instancié");
		nbTests++;
		nbErreurs += consulItemBadEntry(sn, "  ", "1.2",
				"consulItem avec un titre contenant que des espaces");

		nbTests++;
		nbErreurs += consulItemOKTest(sn, "Pulp Fiction", 1, "1.1");
		nbTests++;
		nbErreurs += consulItemOKTest(sn, "Pas Pulp Fiction", 0, "1.1");

		nbTests++;
		if (nbFilms != sn.nbFilms()) {
			System.out
					.println("Erreur  :  le nombre de film après utilisation de reviewOpinion a été modifié");
			nbErreurs++;
		}
		nbTests++;
		if (nbLivres != sn.nbBooks()) {
			System.out
					.println("Erreur  :  le nombre de livres après utilisation de reviewOpinion a été modifié");
			nbErreurs++;
		}

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);
		// bilan du test de ReviewOpinion
		System.out.println("TestsReviewOpinion :   " + nbErreurs
				+ " erreur(s) / " + nbTests + " tests effectués");

		// ajouts au bilan en cours si le bilan est passé en paramètre
		if ((args != null) && (args.length == 2)) {
			nbTests = nbTests + new Integer(args[0]);
			nbErreurs = nbErreurs + new Integer(args[1]);
			args[0] = "" + nbTests;
			args[1] = "" + nbErreurs;
		}
	}

	private static int consulItemBadEntry(SocialNetwork sn, String titre,
			String idTest, String messErreur) {
		LinkedList<String> list;
		try {
			list = sn.consultItems(titre);
			System.out.println("Test " + idTest + " : " + messErreur);
			return 1;
		} catch (BadEntry e) {
			return 0;
		} catch (Exception e) {
			System.out.println("Test " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int consulItemOKTest(SocialNetwork sn, String titre,
			int size_expected, String idTest) {
		LinkedList<String> list;
		try {
			list = sn.consultItems(titre);
			if (list.size() != size_expected) {
				System.out
						.println("Test "
								+ idTest
								+ " : consultItem n'a pas renvoyer le nombre de résultats expecté");
				return 1;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("Test " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}

	}
}
