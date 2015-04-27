package avis;

import java.util.LinkedList;


public abstract class Item {

	/**
	 * @uml.property  name="titre"
	 */
	private String titre;

	/**
	 * Getter of the property <tt>titre</tt>
	 * @return  Returns the titre.
	 * @uml.property  name="titre"
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Setter of the property <tt>titre</tt>
	 * @param titre  The titre to set.
	 * @uml.property  name="titre"
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * @uml.property  name="genre"
	 */
	private String genre;

	/**
	 * Getter of the property <tt>genre</tt>
	 * @return  Returns the genre.
	 * @uml.property  name="genre"
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Setter of the property <tt>genre</tt>
	 * @param genre  The genre to set.
	 * @uml.property  name="genre"
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" dimension="1" ordering="true" inverse="item:avis.Review"
	 */
	private LinkedList<Review> reviews;

	/**
	 * Getter of the property <tt>reviews</tt>
	 * @return  Returns the reviews.
	 * @uml.property  name="reviews"
	 */
	public LinkedList<Review> getReviews() {
		return reviews;
	}

	/**
	 * Setter of the property <tt>reviews</tt>
	 * @param reviews  The reviews to set.
	 * @uml.property  name="reviews"
	 */
	public void setReviews(LinkedList<Review> reviews) {
		this.reviews = reviews;
	}

		
		/**
		 */
		public void getMoyenne(){
		}



}