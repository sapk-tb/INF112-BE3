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
		
		nbTests++;
		nbErreurs += addNReviewBook(sn, 12500, 2000, "1.4");
		nbTests++;
		nbErreurs += addNReviewFilm(sn, 12500, 2000, "1.4");

		nbTests++;
		System.out.println("Memory used : "
				+ Runtime.getRuntime().totalMemory() / 1024 / 1024 + "Mo");
		if (Runtime.getRuntime().totalMemory() / 1024 / 1024 / 1024  > 100 + 10 * (sn
				.nbMembers() + sn.nbBooks() + sn.nbFilms()) / 1000) {
			nbErreurs++;
			System.out.println("Too much memory used!");

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

	private static int addNMember(SocialNetwork sn, int nb_user, int max_ms_op,
			String idTest) {
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_user];
		try {
			for (int i = 1; i <= nb_user; i++) {
				long startTime = System.nanoTime();
				sn.addMember("Utilisateur_" + i, "password_" + i,
						"Description de l'utitilisateur n°" + i);
				long endTime = System.nanoTime();
				timings[i - 1] = (endTime - startTime);
				if (timings[i - 1] > max_ns_op) {
					System.out
							.println("Test "
									+ idTest
									+ " : L'operation a pris plus de temps que le temps maximum. "
									+ timings[i - 1] / (1000 * 1000) + "ms");
					return 1;
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
			}
			System.out.println("Temps total pour l'ajout de " + nb_user
					+ " membre(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");

			// On vérifie que l'on a le bon nombre de membre
			if (sn.nbMembers() != nbMembers + nb_user) {
				System.out
						.println("Test "
								+ idTest
								+ " : les utilisateurs semblent avoir été ajoutés mais le nb de membres final est invalide.");
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
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_book];
		try {
			for (int i = 1; i <= nb_book; i++) {
				int rnd = (int) (Math.random() * nbMembers) + 1;
				long startTime = System.nanoTime();
				sn.addItemBook("Utilisateur_" + rnd, "password_" + rnd,
						"Livre " + i, "Genre du livre " + i, "Auteur" + i,
						10 + i);
				long endTime = System.nanoTime();
				timings[i - 1] = (endTime - startTime);
				if (timings[i - 1] > max_ns_op) {
					System.out
							.println("Test "
									+ idTest
									+ " : L'operation a pris plus de temps que le temps maximum. "
									+ timings[i - 1] / (1000 * 1000) + "ms");
					return 1;
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
			}
			System.out.println("Temps total pour l'ajout de " + nb_book
					+ " book(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");

			// On vérifie que l'on a le bon nombre de livre
			if (sn.nbBooks() != nbBooks + nb_book) {
				System.out
						.println("Test "
								+ idTest
								+ " : les books semblent avoir été ajoutés mais le nb de books final est invalide.");
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

	private static int addNReviewBook(SocialNetwork sn, int nb_review, int max_ms_op,
			String idTest) {
		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_review];
		try {
			for (int i = 1; i <= nb_review; i++) {
				int rnd = (int) (Math.random() * nbMembers) + 1;
				int rnd2 = (int) (Math.random() * nbBooks) + 1;
				float note = (float) (Math.random() * 5);
				long startTime = System.nanoTime();
				sn.reviewItemBook("Utilisateur_" + rnd, "password_" + rnd, "Livre " + rnd2, note, "Commentaire n°"+i);
				long endTime = System.nanoTime();
				timings[i - 1] = (endTime - startTime);
				if (timings[i - 1] > max_ns_op) {
					System.out
							.println("Test "
									+ idTest
									+ " : L'operation a pris plus de temps que le temps maximum. "
									+ timings[i - 1] / (1000 * 1000) + "ms");
					return 1;
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
			}
			System.out.println("Temps total pour l'ajout de " + nb_review
					+ " reviewBook(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");

			// On vérifie que  nombre de livre n'a pas changé
			if (sn.nbBooks() != nbBooks ) {
				System.out
						.println("Test "
								+ idTest
								+ " : les reviews semblent avoir été ajoutés mais le nb de books à changé.");
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
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_film];

		try {
			for (int i = 1; i <= nb_film; i++) {
				int rnd = (int) (Math.random() * nbMembers) + 1;
				long startTime = System.nanoTime();
				sn.addItemFilm("Utilisateur_" + rnd, "password_" + rnd, "Film "
						+ i, "Genre du film " + i, "Realisateur" + i,
						"Scenariste" + i, 10 + i);
				long endTime = System.nanoTime();
				timings[i - 1] = (endTime - startTime);
				if (timings[i - 1] > max_ns_op) {
					System.out
							.println("Test "
									+ idTest
									+ " : L'operation a pris plus de temps que le temps maximum. "
									+ timings[i - 1] / (1000 * 1000) + "ms");
					return 1;
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
			}
			System.out.println("Temps total pour l'ajout de " + nb_film
					+ " films(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");

			// On vérifie que l'on a le bon nombre de film
			if (sn.nbFilms() != nbFilms + nb_film) {
				System.out
						.println("Test "
								+ idTest
								+ " : les films semblent avoir été ajoutés mais le nb de films final est invalide.");
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

	private static int addNReviewFilm(SocialNetwork sn, int nb_review, int max_ms_op,
			String idTest) {
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_review];
		try {
			for (int i = 1; i <= nb_review; i++) {
				int rnd = (int) (Math.random() * nbMembers) + 1;
				int rnd2 = (int) (Math.random() * nbFilms) + 1;
				float note = (float) (Math.random() * 5);
				long startTime = System.nanoTime();
				sn.reviewItemFilm("Utilisateur_" + rnd, "password_" + rnd, "Film " + rnd2, note, "Commentaire n°"+i);
				long endTime = System.nanoTime();
				timings[i - 1] = (endTime - startTime);
				if (timings[i - 1] > max_ns_op) {
					System.out
							.println("Test "
									+ idTest
									+ " : L'operation a pris plus de temps que le temps maximum. "
									+ timings[i - 1] / (1000 * 1000) + "ms");
					return 1;
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
			}
			System.out.println("Temps total pour l'ajout de " + nb_review
					+ " reviewFilm(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");

			// On vérifie que  nombre de livre n'a pas changé
			if (sn.nbFilms() != nbFilms ) {
				System.out
						.println("Test "
								+ idTest
								+ " : les reviews semblent avoir été ajoutés mais le nb de films à changé.");
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
