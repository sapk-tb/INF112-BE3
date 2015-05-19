package test;

import exception.BadEntry;
import exception.NotMember;
import exception.NotReview;
import exception.NotType;
import avis.SocialNetwork;
import exception.NotItem;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */

//TODO verify that the moyenne doesn't change during the faulting test
public class TestsReviewOpinion {

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


        //On ajout un utilisateur qui nous servira pour les reviews
        try {
            sn.addMember("UtilisateurReviewTest", "password", "grand amoureux de critique de critique");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de la création de l'utilisateur servant au test. " + e);
            e.printStackTrace();
        }

        //On ajout un utilisateur qui nous servira pour les reviews
        try {
            sn.addMember("UtilisateurOpinionTest", "password", "grand amoureux de critique de critique");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de la création de l'utilisateur servant au test. " + e);
            e.printStackTrace();
        }
        
        //On ajout des opinions qui nous serviront pour les reviews
        try {
            sn.addItemBook("UtilisateurReviewTest", "password", "Pulp Fiction", "Policier", "Quentin Tarantino", 168);
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de opinion servant au test. " + e);
            e.printStackTrace();
        }
        //On ajout u livre qui nous serviront pour les reviews 
        try {
            sn.addItemBook("UtilisateurReviewTest", "password", "Pulp Fiction 2", "Policier", "Quentin Tarantino", 168);
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de opinion servant au test. " + e);
            e.printStackTrace();
        }       

        Moyenne moyenne = new Moyenne();
        //On ajout des opinions qui nous serviront pour les reviews
        try {
        	moyenne.value = sn.reviewItemBook("UtilisateurReviewTest", "password", "Pulp Fiction", 5.0f, "Un commentaire");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de opinion servant au test. " + e);
            e.printStackTrace();
        }
		//System.out.println(" Moyenne du livre au début "+ moyenne.value);


        Karma karma = new Karma();
        karma.value = 0.5f;
		
        int nbFilms = sn.nbFilms();
        int nbLivres = sn.nbBooks();
        
        
        //TODO add all tests

        //nbTests++;
        //nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"1.1","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma, null, "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"1.1","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"   ", "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"1.2","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"UtilisateurOpinionTest", null, "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"1.3","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"UtilisateurOpinionTest", "123", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"1.4","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", null, "Book", 5,"1.5","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "  ", "Book", 5,"1.6","");
        nbTests++;
        nbErreurs += reviewOpinionBadEntry(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 15,"1.7","");

        
        
        nbTests++;
        nbErreurs += reviewOpinionNotMember(sn,moyenne,karma,"UtilisateurNot", "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"2.1","");
        nbTests++;
        nbErreurs += reviewOpinionNotMember(sn,moyenne,karma,"UtilisateurOpinionTest", "notpassword", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"2.2","");
        nbTests++;
        nbErreurs += reviewOpinionNotMember(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurNot", "Pulp Fiction", "Book", 5,"2.3","");

        nbTests++;
        nbErreurs += reviewOpinionNotItem(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Not Fiction", "Book", 5,"3.1","");
        nbTests++;
        nbErreurs += reviewOpinionNotItem(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction", "Film", 5,"3.1","");       
        
        
        nbTests++;
        nbErreurs += reviewOpinionNotReview(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction 2", "Book", 5,"4.2","");
        
        nbTests++;
        nbErreurs += reviewOpinionNotType(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction", "Not Book", 5,"5.1","");

        nbTests++;
        nbErreurs += reviewOpinionOK(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 5,"6.1");
        nbTests++;
        nbErreurs += reviewOpinionOK(sn,moyenne,karma,"UtilisateurOpinionTest", "password", "UtilisateurReviewTest", "Pulp Fiction", "Book", 3,"6.2");
     
        nbTests++;
        if (nbFilms != sn.nbFilms()) {
            System.out.println("Erreur  :  le nombre de film après utilisation de reviewOpinion a été modifié");
            nbErreurs++;
        }
        nbTests++;
        if (nbLivres != sn.nbBooks()) {
            System.out.println("Erreur  :  le nombre de livres après utilisation de reviewOpinion a été modifié");
            nbErreurs++;
        }

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);
        // bilan du test de ReviewOpinion
        System.out.println("TestsReviewOpinion :   " + nbErreurs + " erreur(s) / " + nbTests + " tests effectués");

        // ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {
            nbTests = nbTests + new Integer(args[0]);
            nbErreurs = nbErreurs + new Integer(args[1]);
            args[0] = "" + nbTests;
            args[1] = "" + nbErreurs;
        }
    }

	private static int reviewOpinionOK(SocialNetwork sn, Moyenne moyenne,
			Karma karma, String pseudo, String password, String user, String titre,
			String type, int note, String idTest) {
        int nbBooks = sn.nbBooks();
        try {
        	karma.value = sn.reviewOpinion(pseudo, password, user, titre, type, note);
            if (karma.value != note/5f) {
                System.out.println("Test " + idTest + " : Le opinion semble etre ajouté mais la karma n'a pas été correctement modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : Le opinion semble etre ajouté mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
		
	}

	private static int reviewOpinionNotType(SocialNetwork sn, Moyenne moyenne,
			Karma karma, String pseudo, String password, String user, String titre,
			String type, int note, String idTest, String messErreur) {
        float kar = karma.value;
        int nbBooks = sn.nbBooks();
        try {
        	kar = sn.reviewOpinion(pseudo, password, user, titre, type, note);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotType e) {
            if (karma.value != kar) {
                System.out.println("Test " + idTest + " : l'exception NotType a bien été levée mais la karma a été modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : l'exception NotType a bien été levée mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
	}

	private static int reviewOpinionNotReview(SocialNetwork sn, Moyenne moyenne,
			Karma karma, String pseudo, String password, String user, String titre,
			String type, int note, String idTest, String messErreur) {
        float kar = karma.value;
        int nbBooks = sn.nbBooks();
        try {
        	kar = sn.reviewOpinion(pseudo, password, user, titre, type, note);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotReview e) {
            if (karma.value != kar) {
                System.out.println("Test " + idTest + " : l'exception NotReview a bien été levée mais la karma a été modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : l'exception NotReview a bien été levée mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
	}

	private static int reviewOpinionNotItem(SocialNetwork sn, Moyenne moyenne,
			Karma karma, String pseudo, String password, String user, String titre,
			String type, int note, String idTest, String messErreur) {
        float kar = karma.value;
        int nbBooks = sn.nbBooks();
        try {
        	kar = sn.reviewOpinion(pseudo, password, user, titre, type, note);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotItem e) {
            if (karma.value != kar) {
                System.out.println("Test " + idTest + " : l'exception NotItem a bien été levée mais la karma a été modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : l'exception NotItem a bien été levée mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
	}

	private static int reviewOpinionNotMember(SocialNetwork sn, Moyenne moyenne,
			Karma karma, String pseudo, String password, String user, String titre,
			String type, int note, String idTest, String messErreur) {
        float kar = karma.value;
        int nbBooks = sn.nbBooks();
        try {
        	kar = sn.reviewOpinion(pseudo, password, user, titre, type, note);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotMember e) {
            if (karma.value != kar) {
                System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais la karma a été modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
	}

	private static int reviewOpinionBadEntry(SocialNetwork sn, Moyenne moyenne,
			Karma karma, String pseudo, String password, String user, String titre,
			String type, int note, String idTest, String messErreur) {
        float kar = karma.value;
        int nbBooks = sn.nbBooks();
        try {
        	kar = sn.reviewOpinion(pseudo, password, user, titre, type, note);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (BadEntry e) {
            if (karma.value != kar) {
                System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais la karma a été modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
            e.printStackTrace();
            return 1;
        }
	}

}
