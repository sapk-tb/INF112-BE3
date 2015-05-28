package avis;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */
import exception.BadEntry;
import java.util.HashMap;

public abstract class Item{

    @Override
    public String toString() {
        return " titre=" + titre + ", genre=" + genre + ", moyenne=" + getMoyenne() + ", reviews="
                + reviews + ", creator=" + creator;
    }

    public static enum Types {Book, Film}

    /**
     * Création d'un Item par un membre
     *
     * @param titre
     * @param genre
     * @param creator
     * @throws exception.BadEntry
     */
    public Item(String titre, String genre, Membre creator) throws BadEntry {
        super();
        this.setTitre(titre);
        this.setGenre(genre);
        this.reviews = new HashMap<>();
        if (!isInstanced(creator)) {
            throw new BadEntry("Creator invalid");
        }
        this.creator = creator.getUID();
    }

    /**
     * @uml.property name="titre"
     */
    private String titre;

    /**
     * Getter of the property <tt>titre</tt>
     *
     * @return Returns the titre.
     * @uml.property name="titre"
     */
    public String getTitre() {
        return titre;
    }
    /**
     * Getter of the property <tt>titre</tt>
     *
     * @return Returns the titre.
     * @uml.property name="titre"
     */
    public String getUID() {
        return titre.toLowerCase().trim();
    }
    /**
     * Setter of the property <tt>titre</tt>
     *
     * @param titre The titre to set.
     * @throws exception.BadEntry si le titre de l'item est invalide
     * @uml.property name="titre"
     */
    public void setTitre(String titre) throws BadEntry {
        if (!isValidTitre(titre)) {
            throw new BadEntry("Titre invalid");
        }
        this.titre = titre;
    }

    /**
     * @uml.property name="genre"
     */
    private String genre;

    /**
     * Getter of the property <tt>genre</tt>
     *
     * @return Returns the genre.
     * @uml.property name="genre"
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Setter of the property <tt>genre</tt>
     *
     * @param genre The genre to set.
     * @throws exception.BadEntry si le genre n'est pas instancié
     * @uml.property name="genre"
     */
    public void setGenre(String genre) throws BadEntry {
        if (!isInstanced(genre)) {
            throw new BadEntry("");
        }
        this.genre = genre;
    }

    /**
     * @uml.property name="reviews"
     * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
     * inverse="item:avis.Review"
     */
    private final HashMap<String, Review> reviews;

    /**
     * Getter of the property <tt>reviews</tt>
     *
     * @return Returns the reviews.
     * @uml.property name="reviews"
     */
    public HashMap<String, Review> getReviews() {
        return reviews;
    }

    /**
     * Permet au membre d'ajouter une review
     *
     * @param review
     * @return la note moyenne associé à l'item
     */
    public float addReview(Review review) {
        Membre membre = review.getMembre();
        reviews.put(membre.getUID(), review);
        membre.addReview(review);
        return this.getMoyenne();
    }

    /**
     *
     * @return la note moyenne associé à l'item
     */
    public float getMoyenne() {
        //TODO consider caching
        if (reviews.isEmpty()) {
            return -1f;
        }
        float moyenne = 0;
        float total_karma = 0;
        //        moyenne = reviews.entrySet().stream().map((review) -> review.getValue().getNote()).reduce(moyenne, (accumulator, _item) -> accumulator + _item);
        /*
        for (Map.Entry<String, Review> review : reviews.entrySet()) {
            float user_karma = review.getValue().getMembre().getKarma();
            moyenne += review.getValue().getNote() * user_karma;
            total_karma += user_karma;
        }
        */
        for (Review review : reviews.values()) {
            float user_karma = review.getMembre().getKarma();
            moyenne += review.getNote() * user_karma;
            total_karma += user_karma;
        }
        return (total_karma == 0) ? -1f : moyenne / total_karma;
    }

    /**
     * @uml.property name="creator"
     * @uml.associationEnd multiplicity="(1 1)" inverse="items:avis.Membre"
     */
    private String creator = null;

    /**
     * Getter of the property <tt>membre</tt>
     *
     * @return Returns the membre.
     * @uml.property name="creator"
     */
    public Membre getCreator() {
        return SocialNetwork.membres.get(this.creator);
    }

    /**
     * Test le paramètre d'entrée titre
     *
     * @param titre titre à valider
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isValidTitre(String titre) {
        return titre != null && titre.trim().length() >= 1;
    }

    /**
     * Test si le paramètre est instancié
     *
     * @param o l'objet à verifier
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isInstanced(Object o) {
        return o != null;
    }
}
