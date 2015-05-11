package test;

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


        //On ajout un utilisateur qui nous servira pour les reviews
        try {
            sn.addMember("UtilisateurConsultTest", "password", "grand amoureux de critique de critique");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de la création de l'utilisateur servant au test. " + e);
            e.printStackTrace();
        }

        
        //On ajout des opinions qui nous serviront pour les reviews
        try {
            sn.addItemBook("UtilisateurConsultTest", "password", "Pulp Fiction", "Policier", "Quentin Tarantino", 168);
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de opinion servant au test. " + e);
            e.printStackTrace();
        }
        

        Moyenne moyenne = new Moyenne();
        //On ajout des opinions qui nous serviront pour les reviews
        try {
        	moyenne.value = sn.reviewItemBook("UtilisateurConsultTest", "password", "Pulp Fiction", 5.0f, "Un commentaire");
        } catch (Exception e) {
            System.out.println("Exception non prévue lors de l'ajout de opinion servant au test. " + e);
            e.printStackTrace();
        }
		System.out.println(" Moyenne du livre au début "+ moyenne.value);


        Karma karma = new Karma();
        karma.value = 0.5f;
		
        int nbFilms = sn.nbFilms();
        int nbLivres = sn.nbBooks();
        
        
        //TODO add all tests
        
     
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

}
