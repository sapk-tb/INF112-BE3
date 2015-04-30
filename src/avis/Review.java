package avis;

import exception.BadEntry;


public class Review {

	/**
	 * @param item
	 * @param membre
	 * @param note
	 * @param commentaire
	 */
	public Review(Item item, Membre membre, float note, String commentaire) throws BadEntry {
		super();
		this.item = item;
		this.membre = membre;
		this.note = note;
		this.commentaire = commentaire;
	}

	/**
	 * @uml.property  name="item"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="reviews:avis.Item"
	 */
	private Item item = null;

	/**
	 * Getter of the property <tt>item</tt>
	 * @return  Returns the item.
	 * @uml.property  name="item"
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @uml.property  name="membre"
	 * @uml.associationEnd  multiplicity="(1 1)" inverse="reviews:avis.Membre"
	 */
	private Membre membre = null;

	/**
	 * Getter of the property <tt>membre</tt>
	 * @return  Returns the membre.
	 * @uml.property  name="membre"
	 */
	public Membre getMembre() {
		return membre;
	}

	/**
	 * @uml.property  name="note"
	 */
	private float note;

	/**
	 * Getter of the property <tt>note</tt>
	 * @return  Returns the note.
	 * @uml.property  name="note"
	 */
	public float getNote() {
		return note;
	}

	/**
	 * Setter of the property <tt>note</tt>
	 * @param note  The note to set.
	 * @uml.property  name="note"
	 */
	public void setNote(float note) {
		this.note = note;
	}

	/**
	 * @uml.property  name="commentaire"
	 */
	private String commentaire;

	/**
	 * Getter of the property <tt>commentaire</tt>
	 * @return  Returns the commentaire.
	 * @uml.property  name="commentaire"
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * Setter of the property <tt>commentaire</tt>
	 * @param commentaire  The commentaire to set.
	 * @uml.property  name="commentaire"
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}


	/**
		 */
	private boolean isValidReviewInput(String pseudo, String password,
			String titre, float note, String commentaire) {
		return false;
	}

}
