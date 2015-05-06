package avis;

import exception.BadEntry;

public class Review {

    @Override
    public String toString() {
        return "Review{note=" + note + ", commentaire=" + commentaire + ", membre=" + membre + '}';
    }

    /**
     * @param item
     * @param membre
     * @param note
     * @param commentaire
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
        this.membre = membre;

        this.setNote(note);
        this.setCommentaire(commentaire);
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
     * @uml.property name="membre"
     * @uml.associationEnd multiplicity="(1 1)" inverse="reviews:avis.Membre"
     */
    private Membre membre = null;

    /**
     * Getter of the property <tt>membre</tt>
     *
     * @return Returns the membre.
     * @uml.property name="membre"
     */
    public Membre getMembre() {
        return membre;
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
     * @uml.property name="commentaire"
     */
    public void setCommentaire(String commentaire) throws BadEntry {
        if (!isValidCommentaire(commentaire)) {
            throw new BadEntry("Commentaire invalid");
        }
        this.commentaire = commentaire;
    }

    /**
     */
    public static boolean isValidReviewInput(Item item, Membre membre,
            float note, String commentaire) {
        return isValidItem(item) && isValidMembre(membre) && isValidNote(note)
                && isValidCommentaire(commentaire);
    }

    public static boolean isValidItem(Item item) {
        return item != null;
    }

    public static boolean isValidNote(float note) {
        return note >= 0 && note <= 5;
    }

    public static boolean isValidMembre(Membre membre) {
        return membre != null;
    }

    public static boolean isValidCommentaire(String commentaire) {
        return commentaire != null;
    }

}
