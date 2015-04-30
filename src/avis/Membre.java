package avis;

import java.util.LinkedList;

import exception.BadAuth;
import exception.BadEntry;
import exception.NotMember;

public class Membre extends Visiteur {

	/**
	 * @param pseudo
	 * @param password
	 * @param profil
	 */
	public Membre(String pseudo, String password, String profil)
			throws BadEntry {
		super();
		if (!isValidMemberInput(pseudo, password, profil))
			throw new BadEntry("Input invalid !");
		this.pseudo = pseudo.trim();
		this.password = password;
		this.profil = profil;
		this.reviews = new LinkedList<Review>();
	}

	/**
		 */
	private boolean isValidMemberInput(String pseudo, String password,
			String profil) {
		if (pseudo == null)
			return false;
		if (pseudo.replaceAll("\\s", "").length() < 1)
			return false;
		if (pseudo.length() < 4)
			return false;
		if (profil == null)
			return false;

		return true;
	}

	/**
	 * @uml.property name="pseudo"
	 */
	private String pseudo;

	/**
	 * Getter of the property <tt>pseudo</tt>
	 * 
	 * @return Returns the pseudo.
	 * @uml.property name="pseudo"
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * Setter of the property <tt>pseudo</tt>
	 * 
	 * @param pseudo
	 *            The pseudo to set.
	 * @uml.property name="pseudo"
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @uml.property name="password"
	 */
	private String password;

	/**
	 * Getter of the property <tt>password</tt>
	 * 
	 * @return Returns the password.
	 * @uml.property name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter of the property <tt>password</tt>
	 * 
	 * @param password
	 *            The password to set.
	 * @uml.property name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @uml.property name="profil"
	 */
	private String profil;

	/**
	 * Getter of the property <tt>profil</tt>
	 * 
	 * @return Returns the profil.
	 * @uml.property name="profil"
	 */
	public String getProfil() {
		return profil;
	}

	/**
	 * Setter of the property <tt>profil</tt>
	 * 
	 * @param profil
	 *            The profil to set.
	 * @uml.property name="profil"
	 */
	public void setProfil(String profil) {
		this.profil = profil;
	}

	/**
	 * @uml.property name="reviews"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
	 *                     inverse="membre:avis.Review"
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
	public void addReview(Review review) {

	}

	/**
	 * @uml.property name="items"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
	 *                     inverse="creator:avis.Item"
	 */
	private LinkedList<Item> items;

	/**
	 * Getter of the property <tt>items</tt>
	 * 
	 * @return Returns the items.
	 * @uml.property name="items"
	 */
	public LinkedList<Item> getItems() {
		return items;
	}

	/**
	 */
	public void addItem(Item item) {

	}

	/**
		 */
	protected Membre auth(String password) throws NotMember, BadAuth {
		return null;
	}

}
