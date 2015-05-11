package avis;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import exception.BadEntry;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */

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
     * @throws exception.BadEntry
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

        this.opinions = new LinkedHashMap<String, Float>();
    }

    private LinkedHashMap<String, Float> opinions;
    
    /**
	 * @return the opinions
	 */
	public LinkedHashMap<String, Float> getOpinions() {
		return opinions;
	}
    public float getLocalKarma() {
        float karma = 0f;
        for (Entry<String, Float> opinion : opinions.entrySet()) {
        	karma += opinion.getValue();
        }
    	return opinions.size()==0 ? 2.5f:karma/(opinions.size()*5);
    }
    public void addOpinion(Membre membre, float opinion) {
    	opinions.put(membre.getPseudo().trim().toLowerCase(), opinion);
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
     * @throws exception.BadEntry
     * @uml.property name="commentaire"
     */
    public void setCommentaire(String commentaire) throws BadEntry {
        if (!isValidCommentaire(commentaire)) {
            throw new BadEntry("Commentaire invalid");
        }
        this.commentaire = commentaire;
    }

    /**
     * @param item
     * @param membre
     * @param note
     * @param commentaire
     * @return
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
