package avis;

import exception.BadEntry;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */
public class Book extends Item {

    @Override
    public String toString() {
        return "Book [auteur=" + auteur + ", nbPages=" + nbPages
                + ", " + super.toString() + "]";
    }

    /**
     * Ajouter un nouveau livre au <i>SocialNetwork</i>
     *
     * @param titre le titre du livre
     * @param genre son genre (roman, essai, etc.)
     * @param auteur l'auteur
     * @param nbPages le nombre de pages
     * @param creator le membre qui ajoute le livre 
     * @throws exception.BadEntry :
     * <ul>
     * <li>si le pseudo n'est pas instancié ou a moins de 1 caractère autre que
     * des espaces .</li>
     * <li>si le password n'est pas instancié ou a moins de 4 caractères autres
     * que des leadings or trailing blanks.</li>
     * <li>si le titre n'est pas instancié ou a moins de 1 caractère autre que
     * des espaces.</li>
     * <li>si le genre n'est pas instancié.</li>
     * <li>si l'auteur n'est pas instancié.</li>
     * <li>si le nombre de pages n'est pas positif.</li>
     * </ul>
     * <br>
     */
    public Book(String titre, String genre, Membre creator, String auteur,
            int nbPages) throws BadEntry {
        super(titre, genre, creator);
        this.setAuteur(auteur);
        this.setNbPages(nbPages);
    }

    /**
     * @uml.property name="auteur"
     */
    private String auteur;

    /**
     * Getter of the property <tt>auteur</tt>
     *
     * @return Retourne l'auteur de l'item livre.
     * @uml.property name="auteur"
     */
    public String getAuteur() {
        return auteur;
    }

    /**
     * Setter of the property <tt>auteur</tt>
     *
     * @param auteur The auteur to set.
     * @throws exception.BadEntry si l'auteur n'est pas instancié
     * @uml.property name="auteur"
     */
    public void setAuteur(String auteur) throws BadEntry {
        if (!isInstanced(auteur)) {
            throw new BadEntry("");
        }
        this.auteur = auteur;
    }

    /**
     * @uml.property name="nbPages"
     */
    private int nbPages;


    /**
     * Getter of the property <tt>nbPages</tt>
     *
     * @return Retourne le nombre de pages de l'item livre concerné.
     * @uml.property name="nbPages"
     */
    public int getNbPages() {
        return nbPages;
    }


    /**
     * Setter of the property <tt>nbPages</tt>
     *
     * @param nbPages The nbPages to set.
     * @throws exception.BadEntry si le nombre de pages n'est pas positif.
     * @uml.property name="nbPages"
     */
    public void setNbPages(int nbPages) throws BadEntry {
        if (!isValidnbPages(nbPages)) {
            throw new BadEntry("");
        }
        this.nbPages = nbPages;
    }


    /** Test les paramètres d'entrée
     * @param titre le titre du livre
     * @param genre son genre (roman, essai, etc.)
     * @param auteur l'auteur
     * @param nbPages le nombre de pages
     * @param creator le membre qui ajoute le livre 
     * @return vrai si les paramètres sont correctement instanciés
     */
    public static boolean isValidBookInput(Membre creator, String titre, String genre, String auteur, int nbPages) {
        return isValidTitre(titre) && isInstanced(creator) && isInstanced(genre) && isInstanced(auteur) && isValidnbPages(nbPages);
    }
    /** Test le paramètre d'entrée nbPages
     * @param nbPages le nombre de pages
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isValidnbPages(int nbPages) {
        return nbPages > 0;
    }
}
