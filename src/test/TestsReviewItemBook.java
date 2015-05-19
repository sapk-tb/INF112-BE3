package test;

import exception.BadEntry;
import exception.NotMember;
import avis.SocialNetwork;
import exception.NotItem;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */
public class TestsReviewItemBook {

    static class Moyenne {
        public float value;
    }

    public static int reviewItemBookBadEntryTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
        float moy = moyenne.value;
        int nbBooks = sn.nbBooks();
        try {
            moy = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (BadEntry e) {
            if (moyenne.value != moy) {
                System.out.println("Test " + idTest + " : l'exception BadEntry a bien été levée mais la moyenne a été modifié");
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

    public static int reviewItemBookNotMemberTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
        float moy = moyenne.value;
        int nbBooks = sn.nbBooks();
        try {
            moy = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotMember e) {
            if (moyenne.value != moy) {
                System.out.println("Test " + idTest + " : l'exception NotMember a bien été levée mais la moyenne a été modifié");
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

    public static int reviewItemBookNotItemTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest, String messErreur) {
        float moy = moyenne.value;
        int nbBooks = sn.nbBooks();
        try {
            moy = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
            System.out.println("Test " + idTest + " : " + messErreur);
            return 1;
        } catch (NotItem e) {
            if (moyenne.value != moy) {
                System.out.println("Test " + idTest + " : l'exception NotItem a bien été levée mais la moyenne a été modifié");
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

    public static int reviewItemBookOKTest(SocialNetwork sn, Moyenne moyenne, String pseudo, String password, String titre, float note, String commentaire, String idTest) {
        int nbBooks = sn.nbBooks();
        try {
            moyenne.value = sn.reviewItemBook(pseudo, password, titre, note, commentaire);
            if (moyenne.value != note) {
                System.out.println("Test " + idTest + " : Le review semble etre ajouté mais la moyenne n'a pas été correctement modifié");
                return 1;
            }
            if (sn.nbBooks() != nbBooks) {
                System.out.println("Test " + idTest + " : Le review semble etre ajouté mais le nombre de books a été modifié");
                return 1;
            }
            return 0;
        } catch (Exception e) {
            System.out.println("Test " + idTest + " : exception non prévue. " + e);
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

        System.out.println("Tests de review des books au réseau social  ");
        SocialNetwork sn = new SocialNetwork();


        //On ajout un utilisateur qui nous servira pour les reviews
        try {
            sn.addMember("UtilisateurReviewTest", "password", "grand amoureux de critique");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de la création de l'utilisateur servant au test. " + e);
            e.printStackTrace();
        }

        //On ajout des books qui nous serviront pour les reviews
        try {
            sn.addItemBook("UtilisateurReviewTest", "password", "Pulp Fiction", "Policier", "Quentin Tarantino", 168);
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de book servant au test. " + e);
            e.printStackTrace();
        }

        int nbLivres = sn.nbBooks();
        int nbFilms = sn.nbFilms();
        Moyenne moyenne = new Moyenne();
        moyenne.value = 0;
        

        // <=> fiche numéro 6
        // Tentative d'ajout de review avec entrées "correctes"
        //Aillant un seul utilisateru de test la moyenne des reviwe doivent etre la note du dernier review de ceet utilisateur
        nbTests++;
        nbErreurs += reviewItemBookOKTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 5, "un commentaire", "1.1a");
        //System.out.printf("%f\n", moyenne.value);
        nbTests++;
        nbErreurs += reviewItemBookOKTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 3, "un commentaire", "1.1b");
        //System.out.printf("%f\n", moyenne.value);


        // <=> fiche numéro 5
        // tentative d'ajout de review avec entrées "incorrectes"
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, null, "password", "Pulp Fiction", 0, "un commentaire", "2.1", "L'ajout d'une review dont le pseudo n'est pas instancié est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, " ", "password", "Pulp Fiction", 0, "un commentaire", "2.2", "L'ajout d'une review  dont le pseudo ne contient pas un caracteres, autre que des espaces, est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, "UtilisateurReviewTest", null, "Pulp Fiction", 0, "un commentaire", "2.3", "L'ajout d'une review dont le password n'est pas instancié est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "   qwd ", "Pulp Fiction", 0, "un commentaire", "2.4", "L'ajout d'un review dont le password ne contient pas au moins 4 caracteres, autre que des espaces de début ou de fin, est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", null, 0, "un commentaire", "2.5", "L'ajout d'une review dont le titre n'est pas instancié est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", " ", 0, "un commentaire", "2.6", "L'ajout d'une review  dont le titre ne contient pas un caracteres, autre que des espaces, est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 10, "un commentaire", "2.7", "L'ajout d'une review  dont la note n'est pas comprise entre 0.0 et 5.0 est accepté");
        nbTests++;
        nbErreurs += reviewItemBookBadEntryTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pulp Fiction", 0, null, "2.8", "L'ajout d'une review dont le commentaire n'est pas instancié est accepté");

        //Tentative d'ajout de de review avec des informations utilisateurs invalides (pseudo, password)
        nbTests++;
        nbErreurs += reviewItemBookNotMemberTest(sn, moyenne, "NotAMenber", "password", "Pulp Fiction", 0, "un commentaire", "3.1", "L'ajout d'une review dont l'utilisateur n'existe pas est accepté");
        nbTests++;
        nbErreurs += reviewItemBookNotMemberTest(sn, moyenne, "UtilisateurAddItemBookTest", "badpassword", "Pulp Fiction", 0, "un commentaire", "3.2", "L'ajout d'une review dont le mot de passe ne correspond pas à celui de l'utilisateur est accepté");

        //Tentative d'ajout de review avec des informations book invalides (titre)
        nbTests++;
        nbErreurs += reviewItemBookNotItemTest(sn, moyenne, "UtilisateurReviewTest", "password", "Pas Fiction", 0, "un commentaire", "4.1", "L'ajout d'une review dont le book n'existe pas est accepté");

        nbTests++;
        if (nbFilms != sn.nbFilms()) {
            System.out.println("Erreur  :  le nombre de books après utilisation de reviewItemBook a été modifié");
            nbErreurs++;
        }
        nbTests++;
        if (nbLivres != sn.nbBooks()) {
            System.out.println("Erreur  :  le nombre de livres après utilisation de reviewItemBook a été modifié");
            nbErreurs++;
        }

		// ce n'est pas du test, mais cela peut "rassurer"...
		System.out.println(sn);
        // bilan du test de ReviewItemBook
        System.out.println("TestsReviewItemBook :   " + nbErreurs + " erreur(s) / " + nbTests + " tests effectués");

        // ajouts au bilan en cours si le bilan est passé en paramètre
        if ((args != null) && (args.length == 2)) {
            nbTests = nbTests + new Integer(args[0]);
            nbErreurs = nbErreurs + new Integer(args[1]);
            args[0] = "" + nbTests;
            args[1] = "" + nbErreurs;
        }
    }

}
