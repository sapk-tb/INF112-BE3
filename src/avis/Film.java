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
     * @param titre
     * @param genre
     * @param creator
     * @param realisateur
     * @param scenariste
     * @param duree
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
    private String realisateur;

    /**
     * Getter of the property <tt>realisateur</tt>
     *
     * @return Returns the realisateur.
     * @uml.property name="realisateur"
     */
    public String getRealisateur() {
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
            throw new BadEntry("");
        }
        this.realisateur = realisateur;
    }

    /**
     * @uml.property name="scenariste"
     */
    private String scenariste;

    /**
     * Getter of the property <tt>scenariste</tt>
     *
     * @return Returns the scenariste.
     * @uml.property name="scenariste"
     */
    public String getScenariste() {
        return scenariste;
    }

    /**
     * Setter of the property <tt>scenariste</tt>
     *
     * @param scenariste The scenariste to set.
     * @throws exception.BadEntry
     * @uml.property name="scenariste"
     */
    public void setScenariste(String scenariste) throws BadEntry {
        if (!isInstanced(scenariste)) {
            throw new BadEntry("");
        }
        this.scenariste = scenariste;
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
     * @throws exception.BadEntry
     * @uml.property name="duree"
     */
    public void setDuree(int duree) throws BadEntry {
        if (!isValidDuree(duree)) {
            throw new BadEntry("Duration invalid");
        }
        this.duree = duree;
    }

    /**
     * @param titre
     * @param genre
     * @param creator
     * @param realisateur
     * @param scenariste
     * @param duree
     * @return
     */
    public static boolean isValidFilmInput(String titre, String genre, Membre creator, String realisateur,
            String scenariste, int duree) {
        return isValidTitre(titre) && isInstanced(creator) && isInstanced(genre) && isInstanced(realisateur) && isInstanced(scenariste) && isValidDuree(duree);
    }

    public static boolean isValidDuree(int duree) {
        return duree > 0;
    }
}
