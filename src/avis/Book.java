package avis;

import exception.BadEntry;
//TODO
public class Book extends Item {

	@Override
	public String toString() {
		return "Book [auteur=" + auteur + ", nbPages=" + nbPages
				+ ", " + super.toString() + "]";
	}

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
		this.setAuteur(auteur);
		this.setNbPages(nbPages);
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
	public void setAuteur(String auteur) throws BadEntry{
		if(!isInstanced(auteur))
			throw new BadEntry("");
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
	public void setNbPages(int nbPages) throws BadEntry{
		if(!isValidnbPages(nbPages))
			throw new BadEntry("");
		this.nbPages = nbPages;
	}

	

	/**
		 */
	public static boolean isValidBookInput(Membre creator, String titre, String genre, String auteur, int nbPages) {
		return isValidTitre(titre) && isInstanced(creator) && isInstanced(genre)&& isInstanced(auteur)  && isValidnbPages(nbPages);
	}


	public static boolean isValidnbPages(int nbPages) {
		if (nbPages <=0)
			return false;
		
		return true;
	}
}
