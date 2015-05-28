package test;

import avis.SocialNetwork;
import java.lang.ref.WeakReference;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */
//TODO starting with -XX:MaxGCPauseMillis=n so no need to clear the gc will not old to many time by cycle
public class TestsChargeAndTiming {

	private static Integer nbErreurs;
	private static int nbTests;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		nbTests = 0;
		nbErreurs = 0;

		System.out.println("Tests de charge et de timing au réseau social  ");
		SocialNetwork sn = new SocialNetwork();

		doTest(sn, 2000, 2000, 2500, 2500, 12500, 12500, 500, true, "1");

		doTest(sn, 2000, 2000 * 10, 2500 * 10, 2500 * 10, 12500 * 10, 12500 * 10, 500 * 10, false, "2");

		doTest(sn, 2000, 2000 * 100, 2500 * 100, 2500 * 100, 12500 * 100, 12500 * 100, 500 * 100, false, "3");

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);
		// bilan du test de ReviewOpinion
		System.out.println("TestChargeAndTiming :   " + nbErreurs
				+ " erreur(s) / " + nbTests + " tests effectués");

		// ajouts au bilan en cours si le bilan est passé en paramètre
		if ((args != null) && (args.length == 2)) {
			nbTests = nbTests + new Integer(args[0]);
			nbErreurs = nbErreurs + new Integer(args[1]);
			args[0] = "" + nbTests;
			args[1] = "" + nbErreurs;
		}
	}

	/**
	 * This method guarantees that garbage collection is done unlike
	 * <code>{@link System#gc()}</code> from
	 * http://stackoverflow.com/questions/1481178/forcing-garbage-collection-in-java
	 */
	public static void gc() {
		Object obj = new Object();
		WeakReference ref = new WeakReference<Object>(obj);
		obj = null;
		while (ref.get() != null) {
			System.gc();
		}
	}

	private static SocialNetwork doTest(SocialNetwork sn, int maxTime, int nbMember, int nbBook, int nbFilm, int nbReviewBook, int nbReviewFilm, int nbItemsToConsults, boolean blocking, String numTest) {
		nbTests++;
		nbErreurs += addNMember(sn, nbMember, maxTime, blocking, numTest + ".1");
		nbTests++;
		nbErreurs += addNBook(sn, nbBook, maxTime, blocking, numTest + ".2");
		nbTests++;
		nbErreurs += addNFilm(sn, nbFilm, maxTime, blocking, numTest + ".3");
		nbTests++;
		nbErreurs += addNReviewBook(sn, nbReviewBook, maxTime, blocking, numTest + ".4");
		nbTests++;
		nbErreurs += addNReviewFilm(sn, nbReviewFilm, maxTime, blocking, numTest + ".5");
		nbTests++;
		nbErreurs += addNConsultItems(sn, nbItemsToConsults, maxTime, blocking, numTest + ".6");
		nbTests++;
		nbErreurs += memoryTest(sn, numTest + ".6");

		//TODO test consultItems
		//TODO test reviewOpinion
		return sn;
	}

	private static int memoryTest(SocialNetwork sn, String idTest) {
		gc();
		System.out.println("Memory used : "
				+ Runtime.getRuntime().totalMemory() / 1024 / 1024 + "Mo");
		if (Runtime.getRuntime().totalMemory() / 1024 / 1024 / 1024 > 100 + 10 * (sn
				.nbMembers() + sn.nbBooks() + sn.nbFilms()) / 1000) {
			System.out
					.println("\nTest " + idTest + "Too much memory used!");
			return 1;
		}
		return 0;
	}

	private static int addNConsultItems(SocialNetwork sn, int nbItemsToConsults, int max_ms_op, boolean blocking,
			String idTest) {

		System.out.println("Testing to consult " + nbItemsToConsults + " Items");

		long max_ns_op = max_ms_op * 1000 * 1000;
		int range = Math.min(sn.nbFilms(), sn.nbBooks());
		long[] timings = new long[nbItemsToConsults];
		try {
			for (int i = 0; i < nbItemsToConsults; i++) {
				int rnd = (int) (Math.random() * range);
				long startTime = System.nanoTime();
				sn.consultItems(" "+rnd);
				long endTime = System.nanoTime();
				timings[i] = (endTime - startTime);
				if (i % 5000 == 0 || i + 1 == nbItemsToConsults) {
					System.out.print("\rAvancement " + (float) (i + 1) / (float) nbItemsToConsults * 100 + "%");
					System.gc();
				}
				if (timings[i] > max_ns_op) {
					System.out
							.println("\nTest "
									+ idTest
									+ " : l'operation " + (i) + " a pris plus de temps que le temps maximum. "
									+ timings[i] / (1000 * 1000) + "ms");
					if (blocking) {
						return 1;
					} else {
						System.out.println(" mais n'est pas bloquant pour le cachier des charges");
					}
				}
			}
			
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			long max = -1;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
				max = (timings[j] > max) ? timings[j] : max;
			}
			System.out.println("\nTemps total pour consult de " + nbItemsToConsults
					+ " membre(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");
			System.out.println("Temps max : " + max / (1000 * 1000) + "ms");
		} catch (Exception e) {
			System.out.println("\nTest " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	private static int addNMember(SocialNetwork sn, int nb_user, int max_ms_op, boolean blocking,
			String idTest) {
		System.out.println("Testing to add " + nb_user + " Users");
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_user];
		try {
			for (int i = nbMembers; i < nb_user + nbMembers; i++) {
				long startTime = System.nanoTime();
				sn.addMember("Utilisateur_" + i, "password_" + i,
						"Description de l'utitilisateur n°" + i);
				long endTime = System.nanoTime();
				timings[i - nbMembers] = (endTime - startTime);
				if ((i - nbMembers) % 5000 == 0 || (i - nbMembers) == nb_user - 1) {
					System.out.print("\rAvancement " + (float) (i + 1 - nbMembers) / (float) nb_user * 100 + "%");
					System.gc();
				}
				if (timings[i - nbMembers] > max_ns_op) {
					System.out
							.println("\nTest "
									+ idTest
									+ " : l'operation " + (i - nbMembers) + " a pris plus de temps que le temps maximum. "
									+ timings[i - nbMembers] / (1000 * 1000) + "ms");
					if (blocking) {
						return 1;
					} else {
						System.out.println(" mais n'est pas bloquant pour le cachier des charges");
					}
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			long max = -1;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
				max = (timings[j] > max) ? timings[j] : max;
			}
			System.out.println("\nTemps total pour l'ajout de " + nb_user
					+ " membre(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");
			System.out.println("Temps max : " + max / (1000 * 1000) + "ms");

			// On vérifie que l'on a le bon nombre de membre
			if (sn.nbMembers() != nbMembers + nb_user) {
				System.out
						.println("\nTest "
								+ idTest
								+ " : les utilisateurs semblent avoir été ajoutés mais le nb de membres final est invalide.");
				return 1;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("\nTest " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int addNBook(SocialNetwork sn, int nb_book, int max_ms_op, boolean blocking,
			String idTest) {
		System.out.println("Testing to add " + nb_book + " Books");
		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_book];
		try {
			for (int i = nbBooks; i < nb_book + nbBooks; i++) {
				int rnd = (int) (Math.random() * nbMembers);
				long startTime = System.nanoTime();
				sn.addItemBook("Utilisateur_" + rnd, "password_" + rnd,
						"Livre " + i, "Genre du livre " + i, "Auteur" + i,
						10 + i);
				long endTime = System.nanoTime();
				timings[i - nbBooks] = (endTime - startTime);
				if ((i - nbBooks) % 5000 == 0 || (i - nbBooks) == nb_book - 1) {
					System.out.print("\rAvancement " + (float) (i + 1 - nbBooks) / (float) nb_book * 100 + "%");
					System.gc();
				}
				if (timings[i - nbBooks] > max_ns_op) {
					System.out
							.println("\nTest "
									+ idTest
									+ " : l'operation " + (i - nbBooks) + " a pris plus de temps que le temps maximum. "
									+ timings[i - nbBooks] / (1000 * 1000) + "ms");
					if (blocking) {
						return 1;
					} else {
						System.out.println(" mais n'est pas bloquant pour le cachier des charges");
					}
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			long max = -1;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
				max = (timings[j] > max) ? timings[j] : max;
			}
			System.out.println("\nTemps total pour l'ajout de " + nb_book
					+ " book(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");
			System.out.println("Temps max : " + max / (1000 * 1000) + "ms");

			// On vérifie que l'on a le bon nombre de livre
			if (sn.nbBooks() != nbBooks + nb_book) {
				System.out
						.println("\nTest "
								+ idTest
								+ " : les books semblent avoir été ajoutés mais le nb de books final est invalide.");
				return 1;
			}

			return 0;
		} catch (Exception e) {
			System.out.println("\nTest " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int addNReviewBook(SocialNetwork sn, int nb_review, int max_ms_op, boolean blocking,
			String idTest) {
		System.out.println("Testing to add " + nb_review + " ReviewBook");
		int nbBooks = sn.nbBooks();
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_review];
		try {
			for (int i = 0; i < nb_review; i++) {
				int rnd = (int) (Math.random() * nbMembers);
				int rnd2 = (int) (Math.random() * nbBooks);
				float note = (float) (Math.random() * 5);
				long startTime = System.nanoTime();
				sn.reviewItemBook("Utilisateur_" + rnd, "password_" + rnd, "Livre " + rnd2, note, "Commentaire n°" + i);
				long endTime = System.nanoTime();
				timings[i] = (endTime - startTime);
				if (i % 5000 == 0 || i == nb_review - 1) {
					System.out.print("\rAvancement " + (float) (i + 1) / (float) nb_review * 100 + "%");
					System.gc();
				}
				if (timings[i] > max_ns_op) {
					System.out
							.println("\nTest "
									+ idTest
									+ " : l'operation " + (i) + " a pris plus de temps que le temps maximum. "
									+ timings[i] / (1000 * 1000) + "ms");
					if (blocking) {
						return 1;
					} else {
						System.out.println(" mais n'est pas bloquant pour le cachier des charges");
					}
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			long max = -1;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
				max = (timings[j] > max) ? timings[j] : max;
			}
			System.out.println("\nTemps total pour l'ajout de " + nb_review
					+ " reviewBook(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");
			System.out.println("Temps max : " + max / (1000 * 1000) + "ms");

			// On vérifie que  nombre de livre n'a pas changé
			if (sn.nbBooks() != nbBooks) {
				System.out
						.println("\nTest "
								+ idTest
								+ " : les reviews semblent avoir été ajoutés mais le nb de books à changé.");
				return 1;
			}

			return 0;
		} catch (Exception e) {
			System.out.println("\nTest " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int addNFilm(SocialNetwork sn, int nb_film, int max_ms_op, boolean blocking,
			String idTest) {
		System.out.println("Testing to add " + nb_film + " Films");
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_film];

		try {
			for (int i = nbFilms; i < nb_film + nbFilms; i++) {
				int rnd = (int) (Math.random() * nbMembers);
				long startTime = System.nanoTime();
				sn.addItemFilm("Utilisateur_" + rnd, "password_" + rnd, "Film "
						+ i, "Genre du film " + i, "Realisateur" + i,
						"Scenariste" + i, 10 + i);
				long endTime = System.nanoTime();
				timings[i - nbFilms] = (endTime - startTime);
				if ((i - nbFilms) % 5000 == 0 || (i - nbFilms) == nb_film - 1) {
					System.out.print("\rAvancement " + (float) (i + 1 - nbFilms) / (float) nb_film * 100 + "%");
					System.gc();
				}
				if (timings[i - nbFilms] > max_ns_op) {
					System.out
							.println("\nTest "
									+ idTest
									+ " : l'operation " + (i - nbFilms) + " a pris plus de temps que le temps maximum. "
									+ timings[i - nbFilms] / (1000 * 1000) + "ms");
					if (blocking) {
						return 1;
					} else {
						System.out.println(" mais n'est pas bloquant pour le cachier des charges");
					}
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			long max = -1;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
				max = (timings[j] > max) ? timings[j] : max;
			}
			System.out.println("\nTemps total pour l'ajout de " + nb_film
					+ " films(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");
			System.out.println("Temps max : " + max / (1000 * 1000) + "ms");

			// On vérifie que l'on a le bon nombre de film
			if (sn.nbFilms() != nbFilms + nb_film) {
				System.out
						.println("\nTest "
								+ idTest
								+ " : les films semblent avoir été ajoutés mais le nb de films final est invalide.");
				return 1;
			}
			return 0;
		} catch (Exception e) {
			System.out.println("\nTest " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}

	private static int addNReviewFilm(SocialNetwork sn, int nb_review, int max_ms_op, boolean blocking,
			String idTest) {
		System.out.println("Testing to add " + nb_review + " ReviewFilm");
		int nbFilms = sn.nbFilms();
		int nbMembers = sn.nbMembers();
		long max_ns_op = max_ms_op * 1000 * 1000;
		long[] timings = new long[nb_review];
		try {
			for (int i = 0; i < nb_review; i++) {
				int rnd = (int) (Math.random() * nbMembers);
				int rnd2 = (int) (Math.random() * nbFilms);
				float note = (float) (Math.random() * 5);
				long startTime = System.nanoTime();
				sn.reviewItemFilm("Utilisateur_" + rnd, "password_" + rnd, "Film " + rnd2, note, "Commentaire n°" + i);
				long endTime = System.nanoTime();
				timings[i] = (endTime - startTime);
				if (i % 5000 == 0 || i == nb_review - 1) {
					System.out.print("\rAvancement " + (float) (i + 1) / (float) nb_review * 100 + "%");
					System.gc();
				}
				if (timings[i] > max_ns_op) {
					System.out
							.println("\nTest "
									+ idTest
									+ " : l'operation " + (i) + " a pris plus de temps que le temps maximum. "
									+ timings[i] / (1000 * 1000) + "ms");
					if (blocking) {
						return 1;
					} else {
						System.out.println(" mais n'est pas bloquant pour le cachier des charges");
					}
				}
			}
			// Calcul du temps moyens de toutes les opréations
			long tot = 0;
			long max = -1;
			for (int j = 0; j < timings.length; j++) {
				tot += timings[j];
				max = (timings[j] > max) ? timings[j] : max;
			}
			System.out.println("\nTemps total pour l'ajout de " + nb_review
					+ " reviewFilm(s) : " + tot / (1000 * 1000) + "ms");
			System.out.println("Soit une moyenne de : "
					+ (tot / timings.length) / (1000) + "us");
			System.out.println("Temps max : " + max / (1000 * 1000) + "ms");

			// On vérifie que  nombre de livre n'a pas changé
			if (sn.nbFilms() != nbFilms) {
				System.out
						.println("\nTest "
								+ idTest
								+ " : les reviews semblent avoir été ajoutés mais le nb de films à changé.");
				return 1;
			}

			return 0;
		} catch (Exception e) {
			System.out.println("\nTest " + idTest + " : exception non prévue. "
					+ e);
			e.printStackTrace();
			return 1;
		}
	}
}
