package avis;

import java.util.LinkedList;

import exception.BadEntry;

public class Film extends Item {

	/**
	 * @param titre
	 * @param genre
	 * @param reviews
	 * @param membre
	 * @param realisateur
	 * @param scenariste
	 * @param duree
	 */
	public Film(String titre, String genre, Membre creator, String realisateur,
			String scenariste, int duree) throws BadEntry {
		super(titre, genre, creator);
		this.realisateur = realisateur;
		this.scenariste = scenariste;
		this.duree = duree;
	}

	/**
	 * @uml.property name="realisateur"
	 */
	private String realisateur;

	/**
	 * Getter of the property <tt>realisateur</tt>
	 * 
	 * @return Returns the realisateur.
	 * @uml.property name="realisateur"
	 */
	public String getRealisateur() {
		return realisateur;
	}

	/**
	 * Setter of the property <tt>realisateur</tt>
	 * 
	 * @param realisateur
	 *            The realisateur to set.
	 * @uml.property name="realisateur"
	 */
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}

	/**
	 * @uml.property name="scenariste"
	 */
	private String scenariste;

	/**
	 * Getter of the property <tt>scenariste</tt>
	 * 
	 * @return Returns the scenariste.
	 * @uml.property name="scenariste"
	 */
	public String getScenariste() {
		return scenariste;
	}

	/**
	 * Setter of the property <tt>scenariste</tt>
	 * 
	 * @param scenariste
	 *            The scenariste to set.
	 * @uml.property name="scenariste"
	 */
	public void setScenariste(String scenariste) {
		this.scenariste = scenariste;
	}

	/**
	 * @uml.property name="duree"
	 */
	private int duree;

	/**
	 * Getter of the property <tt>duree</tt>
	 * 
	 * @return Returns the duree.
	 * @uml.property name="duree"
	 */
	public int getDuree() {
		return duree;
	}

	/**
	 * Setter of the property <tt>duree</tt>
	 * 
	 * @param duree
	 *            The duree to set.
	 * @uml.property name="duree"
	 */
	public void setDuree(int duree) {
		this.duree = duree;
	}

	/**
		 */
	private boolean isValidFilmInput(String titre, String genre, Membre creator, String realisateur,
			String scenariste, int duree) {
		return false;
	}

}
