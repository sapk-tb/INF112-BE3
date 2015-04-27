package avis;

import java.util.LinkedList;


public class Membre extends Visiteur {

	/**
	 * @param pseudo
	 * @param password
	 * @param profil
	 */
	public Membre(String pseudo, String password, String profil) {
		super();
		this.pseudo = pseudo;
		this.password = password;
		this.profil = profil;
		this.reviews = new LinkedList<Review>();
	}

	/**
	 * @uml.property  name="pseudo"
	 */
	private String pseudo;

	/**
	 * Getter of the property <tt>pseudo</tt>
	 * @return  Returns the pseudo.
	 * @uml.property  name="pseudo"
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Setter of the property <tt>pseudo</tt>
	 * @param pseudo  The pseudo to set.
	 * @uml.property  name="pseudo"
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @uml.property  name="password"
	 */
	private String password;

	/**
	 * Getter of the property <tt>password</tt>
	 * @return  Returns the password.
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter of the property <tt>password</tt>
	 * @param password  The password to set.
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @uml.property  name="profil"
	 */
	private String profil;

	/**
	 * Getter of the property <tt>profil</tt>
	 * @return  Returns the profil.
	 * @uml.property  name="profil"
	 */
	public String getProfil() {
		return profil;
	}

	/**
	 * Setter of the property <tt>profil</tt>
	 * @param profil  The profil to set.
	 * @uml.property  name="profil"
	 */
	public void setProfil(String profil) {
		this.profil = profil;
	}

	/**
	 * @uml.property  name="reviews"
	 * @uml.associationEnd  multiplicity="(0 -1)" dimension="1" ordering="true" inverse="membre:avis.Review"
	 */
	private  LinkedList<Review> reviews;

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



}
