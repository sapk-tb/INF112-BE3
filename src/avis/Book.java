package avis;

import java.util.LinkedList;

import exception.BadEntry;

public class Book extends Item {

	/**
	 * @param titre
	 * @param genre
	 * @param reviews
	 * @param membre
	 * @param auteur
	 * @param nbPages
	 */
	public Book(String titre, String genre, Membre creator, String auteur,
			int nbPages) throws BadEntry {
		super(titre, genre, creator);
		this.auteur = auteur;
		this.nbPages = nbPages;
	}

	/**
	 * @uml.property name="auteur"
	 */
	private String auteur;

	/**
	 * Getter of the property <tt>auteur</tt>
	 * 
	 * @return Returns the auteur.
	 * @uml.property name="auteur"
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * Setter of the property <tt>auteur</tt>
	 * 
	 * @param auteur
	 *            The auteur to set.
	 * @uml.property name="auteur"
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @uml.property name="nbPages"
	 */
	private int nbPages;

	/**
	 * Getter of the property <tt>nbPages</tt>
	 * 
	 * @return Returns the nbPages.
	 * @uml.property name="nbPages"
	 */
	public int getNbPages() {
		return nbPages;
	}

	/**
	 * Setter of the property <tt>nbPages</tt>
	 * 
	 * @param nbPages
	 *            The nbPages to set.
	 * @uml.property name="nbPages"
	 */
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}

	

	/**
		 */
	private boolean isValidBookInput(String pseudo, String password,
			String titre, String genre, String auteur, int nbPages) {
		return false;
	}

}
