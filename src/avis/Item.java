package avis;

import java.util.LinkedHashMap;
import java.util.Map;

import exception.BadEntry;

public abstract class Item {

	@Override
	public String toString() {
		return " titre=" + titre + ", genre=" + genre + ", moyenne=" + getMoyenne() + ", reviews="
				+ reviews + ", creator=" + creator;
	}

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
		this.reviews = new LinkedHashMap<String, Review>();
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
	private LinkedHashMap<String, Review> reviews;
	/**
	 * Getter of the property <tt>reviews</tt>
	 * 
	 * @return Returns the reviews.
	 * @uml.property name="reviews"
	 */
	public LinkedHashMap<String, Review> getReviews() {
		return reviews;
	}

	/**
	 * TODO
	 */
	public float addReview(Review review) {
		reviews.put(review.getMembre().getPseudo().trim().toLowerCase(), review);
                review.getMembre().addReview(review);
		return this.getMoyenne();
	}
	/**
	 * TODO
		 */
	public float getMoyenne() {
		float moyenne = 0;
		for (Map.Entry<String, Review> review : reviews.entrySet()) {
			moyenne += review.getValue().getNote();
		}
		return moyenne/(float)reviews.size();
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
