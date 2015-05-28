package avis;

import exception.BadEntry;
import exception.NotMember;
import java.util.HashMap;

/**
 * @author agirar01
 *
 */
public class Review {

    @Override
    public String toString() {
        return "Review{note=" + note + ", commentaire=" + commentaire + ", membre=" + membre + '}';
    }

    /**
     * @param item item associé au commentaire
     * @param membre le membre qui ajoute le commentaire
     * @param note la note du membre associé à l'item
     * @param commentaire le commentaire du membre à propos de l'item
     * @throws exception.BadEntry
     * <ul>
     * <li>si le pseudo n'est pas instancié ou a moins de 1 caractère autre que
     * des espaces .</li>
     * <li>si le password n'est pas instancié ou a moins de 4 caractères autres
     * que des leadings or trailing blanks.</li>
     * <li>si le titre n'est pas instancié ou a moins de 1 caractère autre que
     * des espaces.</li>
     * <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
     * <li>si le commentaire n'est pas instancié.</li>
     * </ul>
     * <br>
     */
    public Review(Item item, Membre membre, float note, String commentaire)
            throws BadEntry {
        super();
        if (!isValidItem(item)) {
            throw new BadEntry("Item invalid");
        }
        this.item = item;
        if (!isValidMembre(membre)) {
            throw new BadEntry("Member invalid");
        }
        this.membre = membre.getUID();

        this.setNote(note);
        this.setCommentaire(commentaire);

        this.opinions = new HashMap<String, Float>();
    }

    private HashMap<String, Float> opinions;

    /**
     * @return la liste des opinions
     */
    public HashMap<String, Float> getOpinions() {
        return opinions;
    }

    private Float localKarma = -1f;

    public Float getLocalKarma() {
        //Si la karma est pas instancié on le calcul en brut
        if (localKarma == -1f) {
            localKarma = calcLocalKarma();
        }
        return localKarma;
    }

    /**
     * @return le karma "local" de l'opinion 0 à 1
     */
    public float calcLocalKarma() {
        if (opinions.isEmpty()) {
            return .5f;
        }
        float karma = 0f;
        /*
         for (Entry<String, Float> opinion : opinions.entrySet()) {
         karma += opinion.getValue();
         }
         */
        for (Float opinion : opinions.values()) {
            karma += opinion;
        }
        return karma / (opinions.size() * 5);
    }

    /**
     * Ajout une opinion du review
     *
     * @param membre le membre qui donne son opinion
     * @param opinion l'opinion à rajouters
     * @return The last opinion of the member noting if it exist else null
     * @throws exception.NotMember
     */
    public Float addOpinion(Membre membre, float opinion) throws NotMember {
        if (!isValidMembre(membre)) {
            throw new NotMember("Le membre n'est pas instancié");
        }

        Float lastOpinion = opinions.put(membre.getUID(), opinion);
        if (lastOpinion == null) {
            //l'utilisateur n'avais pas encore donné son opinion sur la review
            this.localKarma = (this.localKarma * opinions.size() + opinion) / (opinions.size() + 1);
        } else {
            // Ici il la déjà donné
            this.localKarma = (this.localKarma * opinions.size() - lastOpinion + opinion) / opinions.size();
        }
        return lastOpinion;
    }

    /**
     * @uml.property name="item"
     * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Item"
     */
    private Item item = null;

    /**
     * Getter of the property <tt>item</tt>
     *
     * @return Returns the item.
     * @uml.property name="item"
     */
    public Item getItem() {
        return item;
    }

    /**
     * The UID of the member
     */
    private String membre = null;

    /**
     * Getter of the property <tt>membre</tt>
     *
     * @return Returns the membre.
     * @uml.property name="membre"
     */
    public Membre getMembre() {
        return SocialNetwork.membres.get(this.membre);
    }

    /**
     * @uml.property name="note"
     */
    private float note;

    /**
     * Getter of the property <tt>note</tt>
     *
     * @return Returns the note.
     * @uml.property name="note"
     */
    public float getNote() {
        return note;
    }

    /**
     * Setter of the property <tt>note</tt>
     *
     * @param note The note to set.
     * @throws exception.BadEntry
     * @uml.property name="note"
     */
    public void setNote(float note) throws BadEntry {
        if (!isValidNote(note)) {
            throw new BadEntry("Note invalid");
        }
        this.note = note;
    }

    /**
     * @uml.property name="commentaire"
     */
    private String commentaire;

    /**
     * Getter of the property <tt>commentaire</tt>
     *
     * @return Returns the commentaire.
     * @uml.property name="commentaire"
     */
    public String getCommentaire() {
        return commentaire;
    }

    /**
     * Setter of the property <tt>commentaire</tt>
     *
     * @param commentaire The commentaire to set.
     * @throws exception.BadEntry si les paramètres sont invalide
     * @uml.property name="commentaire"
     */
    public void setCommentaire(String commentaire) throws BadEntry {
        if (!isValidCommentaire(commentaire)) {
            throw new BadEntry("Commentaire invalid");
        }
        this.commentaire = commentaire;
    }

    /**
     * Teste si la review du membre est valide
     *
     * @param item
     * @param membre
     * @param note
     * @param commentaire
     * @return vrai si la reiview est valide
     */
    public static boolean isValidReviewInput(Item item, Membre membre,
            float note, String commentaire) {
        return isValidItem(item) && isValidMembre(membre) && isValidNote(note)
                && isValidCommentaire(commentaire);
    }

    /**
     * Vérifie la validité de l'item
     *
     * @param item
     * @return vrai si l'item est valide
     */
    public static boolean isValidItem(Item item) {
        return item != null;
    }

    /**
     * Vérifie la validité de la note associée au commentaire du membre
     *
     * @param note
     * @return vrai si la note est comrpise entre 0 et 5
     */
    public static boolean isValidNote(float note) {
        return note >= 0 && note <= 5;
    }

    /**
     * Vérifie la validité du membre
     *
     * @param membre
     * @return vrai si le membre est instancié
     */
    public static boolean isValidMembre(Membre membre) {
        return membre != null;
    }

    /**
     * Vérifie la validité du commentaire
     *
     * @param commentaire
     * @return vrai si le commentaire est instancié
     */
    public static boolean isValidCommentaire(String commentaire) {
        return commentaire != null;
    }

}
