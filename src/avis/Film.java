package avis;

import exception.BadEntry;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */

public class Film extends Item {

    @Override
    public String toString() {
        return "Film [realisateur=" + realisateur + ", scenariste="
                + scenariste + ", duree=" + duree + ", "
                + super.toString() + "]";
    }


    /**
     * Ajouter un nouveau film au <i>SocialNetwork</i>
     *
     * @param creator le membre qui ajoute le film
     * @param titre le titre du film
     * @param genre son genre (aventure, policier, etc.)
     * @param realisateur le réalisateur
     * @param scenariste le scénariste
     * @param duree sa durée en minutes
     * @throws exception.BadEntry
     */
    public Film(String titre, String genre, Membre creator, String realisateur,
            String scenariste, int duree) throws BadEntry {
        super(titre, genre, creator);
        this.setRealisateur(realisateur);
        this.setScenariste(scenariste);
        this.setDuree(duree);
    }

    /**
     * @uml.property name="realisateur"
     */
    private char[] realisateur;

    /**
     * Getter of the property <tt>realisateur</tt>
     *
     * @return Returns the realisateur.
     * @uml.property name="realisateur"
     */
    public char[] getRealisateur() {
        return realisateur;
    }

    /**
     * Setter of the property <tt>realisateur</tt>
     *
     * @param realisateur The realisateur to set.
     * @throws exception.BadEntry
     * @uml.property name="realisateur"
     */
    public void setRealisateur(String realisateur) throws BadEntry {
        if (!isInstanced(realisateur)) {
            throw new BadEntry("Realisateur not instancied");
        }
        this.realisateur = realisateur.toCharArray();
    }

    /**
     * @uml.property name="scenariste"
     */
    private char[] scenariste;

    /**
     * Getter of the property <tt>scenariste</tt>
     *
     * @return Returns the scenariste.
     * @uml.property name="scenariste"
     */
    public char[] getScenariste() {
        return scenariste;
    }

    /**
     * Setter of the property <tt>scenariste</tt>
     *
     * @param scenariste The scenariste to set.
     * @throws exception.BadEntry si le scénariste n'est pas instancié.
     * @uml.property name="scenariste"
     */
    public void setScenariste(String scenariste) throws BadEntry {
        if (!isInstanced(scenariste)) {
            throw new BadEntry("Scenariste not instancied");
        }
        this.scenariste = scenariste.toCharArray();
    }

    /**
     * @uml.property name="duree"
     */
    private int duree;

    /**
     * Getter of the property <tt>duree</tt>
     *
     * @return Returns the duree.
     * @uml.property name="duree"
     */
    public int getDuree() {
        return duree;
    }


    /**
     * Setter of the property <tt>duree</tt>
     *
     * @param duree The duree to set.
     * @throws exception.BadEntry si la durée du film n'est pas positive.
     * @uml.property name="duree"
     */
    public void setDuree(int duree) throws BadEntry {
        if (!isValidDuree(duree)) {
            throw new BadEntry("Duration invalid");
        }
        this.duree = duree;
    }

    /** Test les paramètres d'entrée
     * @param creator le membre qui ajoute le film
     * @param titre le titre du film
     * @param genre son genre (aventure, policier, etc.)
     * @param realisateur le réalisateur
     * @param scenariste le scénariste
     * @param duree sa durée en minutes
     * @return
     */
    public static boolean isValidFilmInput(String titre, String genre, Membre creator, String realisateur,
            String scenariste, int duree) {
        return isValidTitre(titre) && isInstanced(creator) && isInstanced(genre) && isInstanced(realisateur) && isInstanced(scenariste) && isValidDuree(duree);
    }

    /** Test le paramètre d'entrée duree
     * @param duree sa durée en minutes
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isValidDuree(int duree) {
        return duree > 0;
    }
}
