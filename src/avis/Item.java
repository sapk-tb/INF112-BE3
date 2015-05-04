package avis;

import java.util.LinkedList;

import exception.BadEntry;

public abstract class Item {

	/**
	 * @param titre
	 * @param genre
	 * @param reviews
	 * @param membre
	 */
	public Item(String titre, String genre, Membre creator) throws BadEntry{
		super();
		this.setTitre(titre);
		this.setGenre(genre);
		this.reviews = new LinkedList<Review>();
		if(!isInstanced(creator))
			throw new BadEntry("Creator invalid");
		this.creator = creator;
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
	 * Setter of the property <tt>titre</tt>
	 * 
	 * @param titre
	 *            The titre to set.
	 * @uml.property name="titre"
	 */
	public void setTitre(String titre) throws BadEntry{
		if(!isValidTitre(titre))
			throw new BadEntry("Titre invalid");
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
	 * @param genre
	 *            The genre to set.
	 * @uml.property name="genre"
	 */
	public void setGenre(String genre) throws BadEntry{
		if(!isInstanced(genre))
			throw new BadEntry("");
		this.genre = genre;
	}

	/**
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
	 *                     inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;

	/**
	 * Getter of the property <tt>reviews</tt>
	 * 
	 * @return Returns the reviews.
	 * @uml.property name="reviews"
	 */
	public LinkedList<Review> getReviews() {
		return reviews;
	}

	/**
	 */
	public float addReview(Review review ) {
		return 0;
	}
	/**
		 */
	public void getMoyenne() {
	}

	/** 
	 * @uml.property name="creator"
	 * @uml.associationEnd multiplicity="(1 1)" inverse="items:avis.Membre"
	 */
	private Membre creator = null;

	/** 
	 * Getter of the property <tt>membre</tt>
	 * @return Returns the membre.
	 * @uml.property  name="creator"
	 */
	public Membre getCreator() {
		return creator;
	}

	public static boolean isValidTitre(String titre) {
		if (titre == null)
			return false;
		if (titre.replaceAll("\\s", "").length() < 1)
			return false;
		return true;
	}
	public static boolean isInstanced(Object s) {
		if (s == null)
			return false;
		return true;
	}
}
