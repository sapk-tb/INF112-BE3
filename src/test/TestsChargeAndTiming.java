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

public class TestsChargeAndTiming {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int nbTests = 0;
		int nbErreurs = 0;

		System.out.println("Tests de charge et de timing au réseau social  ");
		SocialNetwork sn = new SocialNetwork();

		nbTests++;
		nbErreurs += addNMember(sn, 500, 2000, "1.1");
		nbTests++;
		nbErreurs += addNBook(sn, 2500, 2000, "1.2");
		nbTests++;
		nbErreurs += addNFilm(sn, 2500, 2000, "1.3");
		// TODO add moyenne des opération
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

	private static int addNMember(SocialNetwork sn, int nb_user, int max_ms_op,
			String idTest) {
		int nbMembers = sn.nbMembers();
		try {
			for (int i = 1; i <= nb_user; i++) {
				// TODO add timing
				sn.addMember("Utilisateur_" + i, "password_" + i,
						"Description de l'utitilisateur n°" + i);
			}
			if (sn.nbMembers() != nbMembers + nb_user){
				System.out.println("Test " + idTest + " : les utilisateurs semblent avoir été ajoutés mais le nb de membres final est invalide.");
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
	private static int addNBook(SocialNetwork sn, int nb_book, int max_ms_op,
			String idTest) {
		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		try {
			for (int i = 1; i <= nb_book; i++) {
				// TODO add timing
				int rnd = (int)(Math.random() * nbMembers) + 1;
	            sn.addItemBook("Utilisateur_" + rnd, "password_" + rnd, "Livre "+i, "Genre du livre "+i, "Auteur"+i, 10+i);
			}
			if (sn.nbBooks() != nbBooks + nb_book){
				System.out.println("Test " + idTest + " : les books semblent avoir été ajoutés mais le nb de books final est invalide.");
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
	private static int addNFilm(SocialNetwork sn, int nb_film, int max_ms_op,
			String idTest) {
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();
		try {
			for (int i = 1; i <= nb_film; i++) {
				// TODO add timing
				int rnd = (int)(Math.random() * nbMembers) + 1;
	            sn.addItemFilm("Utilisateur_" + rnd, "password_" + rnd, "Film "+i, "Genre du film "+i, "Realisateur"+i, "Scenariste"+i, 10+i);
			}
			if (sn.nbFilms() != nbFilms + nb_film){
				System.out.println("Test " + idTest + " : les films semblent avoir été ajoutés mais le nb de films final est invalide.");
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
