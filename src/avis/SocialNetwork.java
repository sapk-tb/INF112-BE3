package avis;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import exception.BadEntry;
import exception.ItemFilmAlreadyExists;
import exception.ItemBookAlreadyExists;
import exception.MemberAlreadyExists;
import exception.NotItem;
import exception.NotMember;

/** 
 * @author A. Beugnard, 
 * @author G. Ouvradou
 * @author B. Prou
 * @date février - mars 2011
 * @version V0.6
 */

/**
 * <p>
 * <b>Système de mutualisation d'opinions portant sur des domaines variés
 * (littérature, cinéma, art, gastronomie, etc.) et non limités.</b>
 * </p>
 * <p>
 * L'accès aux items et aux opinions qui leurs sont associées est public. La
 * création d'item et le dépôt d'opinion nécessite en revanche que l'utilisateur
 * crée son profil au préalable.
 * </p>
 * <p>
 * Lorsqu'une méthode peut lever deux types d'exception, et que les conditions
 * sont réunies pour lever l'une et l'autre, rien ne permet de dire laquelle des
 * deux sera effectivement levée.
 * </p>
 * <p>
 * Dans une version avancée (version 2), une opinion peut également être
 * évaluée. Chaque membre se voit dans cette version décerner un "karma" qui
 * mesure la moyenne des notes portant sur les opinions qu'il a émises. L'impact
 * des opinions entrant dans le calcul de la note moyenne attribuée à un item
 * est pondéré par le karma des membres qui les émettent.
 * </p>
 */

public class SocialNetwork {

	/**
	 * constructeur de <i>SocialNetwok</i>
	 * 
	 */

	public SocialNetwork() {
		membres = new LinkedHashMap<String, Membre>();
		books = new LinkedHashMap<String, Book>();
		films = new LinkedHashMap<String, Film>();
	}

	/**
	 * Obtenir le nombre de membres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de membres
	 */
	public int nbMembers() {
		return membres.size();
	}

	/**
	 * Obtenir le nombre de films du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de films
	 */
	public int nbFilms() {
		return films.size();
	}

	/**
	 * Obtenir le nombre de livres du <i>SocialNetwork</i>
	 * 
	 * @return le nombre de livres
	 */
	public int nbBooks() {
		return books.size();
	}

	/**
	 * Ajouter un nouveau membre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo
	 *            son pseudo
	 * @param password
	 *            son mot de passe
	 * @param profil
	 *            un slogan choisi par le membre pour se définir
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le profil n'est pas instancié.</li>
	 *             </ul>
	 * <br>
	 * 
	 * @throws MemberAlreadyExists
	 *             membre de même pseudo déjà présent dans le
	 *             <i>SocialNetwork</i> (même pseudo : indifférent à la casse et
	 *             aux leadings et trailings blanks)
	 * 
	 */
	public void addMember(String pseudo, String password, String profil)
			throws BadEntry, MemberAlreadyExists {
		if (memberAlreadyExists(pseudo)) {
			throw new MemberAlreadyExists();
		}
		Membre membre = new Membre(pseudo, password, profil);
		membres.put(membre.getPseudo().toLowerCase().trim(), membre);
	}

	/**
	 * Ajouter un nouvel item de film au <i>SocialNetwork</i>
	 * 
	 * @param pseudo
	 *            le pseudo du membre
	 * @param password
	 *            le password du membre
	 * @param titre
	 *            le titre du film
	 * @param genre
	 *            son genre (aventure, policier, etc.)
	 * @param realisateur
	 *            le réalisateur
	 * @param scenariste
	 *            le scénariste
	 * @param duree
	 *            sa durée en minutes
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si le genre n'est pas instancié.</li>
	 *             <li>si le réalisateur n'est pas instancié.</li>
	 *             <li>si le scénariste n'est pas instancié.</li>
	 *             <li>si la durée n'est pas positive.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws ItemFilmAlreadyExists
	 *             : item film de même titre déjà présent (même titre :
	 *             indifférent à la casse et aux leadings et trailings blanks)
	 * 
	 */
	public void addItemFilm(String pseudo, String password, String titre,
			String genre, String realisateur, String scenariste, int duree)
			throws BadEntry, NotMember, ItemFilmAlreadyExists {

		if (!(Membre.isValidPseudo(pseudo) && Membre.isValidPassword(password))) {
			throw new BadEntry("Utilisateur incorrect");
		}
		if (!memberAlreadyExists(pseudo)) {
			throw new NotMember(pseudo + " is not a member");
		}
		Membre creator = membres.get(pseudo.trim().toLowerCase());
		if (!creator.auth(password)) {
			throw new NotMember("Password incorrect");
		}

		if (!Film.isValidFilmInput(titre, genre, creator, realisateur,
				scenariste, duree)) {
			throw new BadEntry("Film data incorrect");
		}

		if (filmAlreadyExists(titre)) {
			throw new ItemFilmAlreadyExists();
		}

		films.put(titre.trim().toLowerCase(), new Film(titre, genre, creator,
				realisateur, scenariste, duree));

	}

	/**
	 * Ajouter un nouvel item de livre au <i>SocialNetwork</i>
	 * 
	 * @param pseudo
	 *            le pseudo du membre
	 * @param password
	 *            le password du membre
	 * @param titre
	 *            le titre du livre
	 * @param genre
	 *            son genre (roman, essai, etc.)
	 * @param auteur
	 *            l'auteur
	 * @param nbPages
	 *            le nombre de pages
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si le genre n'est pas instancié.</li>
	 *             <li>si l'auteur n'est pas instancié.</li>
	 *             <li>si le nombre de pages n'est pas positif.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws ItemBookAlreadyExists
	 *             item livre de même titre déjà présent (même titre :
	 *             indifférent à la casse et aux leadings et trailings blanks)
	 * 
	 * 
	 */
	public void addItemBook(String pseudo, String password, String titre,
			String genre, String auteur, int nbPages) throws BadEntry,
			NotMember, ItemBookAlreadyExists {

		if (!(Membre.isValidPseudo(pseudo) && Membre.isValidPassword(password))) {
			throw new BadEntry("Utilisateur incorrect");
		}
		if (!memberAlreadyExists(pseudo)) {
			throw new NotMember(pseudo + " is not a member");
		}
		Membre creator = membres.get(pseudo.trim().toLowerCase());
		if (!creator.auth(password)) {
			throw new NotMember("Password incorrect");
		}

		if (!Book.isValidBookInput(creator, titre, genre, auteur, nbPages)) {
			throw new BadEntry("Film data incorrect");
		}

		if (bookAlreadyExists(titre)) {
			throw new ItemBookAlreadyExists();
		}

		books.put(titre.trim().toLowerCase(), new Book(titre, genre, creator,
				auteur, nbPages));

	}

	/**
	 * Consulter les items du <i>SocialNetwork</i> par nom
	 * 
	 * @param nom
	 *            son nom (eg. titre d'un film, d'un livre, etc.)
	 * 
	 * @throws BadEntry
	 *             : si le nom n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces. </li>
	 * 
	 * @return LinkedList <String> : la liste des représentations de tous les
	 *         items ayant ce nom Cette représentation contiendra la note de
	 *         l'item s'il a été noté. (une liste vide si aucun item ne
	 *         correspond)
	 */
	public LinkedList<String> consultItems(String nom) throws BadEntry {
		LinkedList<String> results = new LinkedList<String>();
		for (Entry<String, Film> film : films.entrySet()) {
			if (film.getValue().getTitre().contains(nom)) {
				results.add(film.getValue().toString());
			}
		}
		for (Entry<String, Book> book : books.entrySet()) {
			if (book.getValue().getTitre().contains(nom)) {
				results.add(book.getValue().toString());
			}
		}
		return results;
	}

	/**
	 * Donner son opinion sur un item film. Ajoute l'opinion de ce membre sur ce
	 * film au <i>SocialNetwork</i> Si une opinion de ce membre sur ce film
	 * préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo
	 *            pseudo du membre émettant l'opinion
	 * @param password
	 *            son mot de passe
	 * @param titre
	 *            titre du film concerné
	 * @param note
	 *            la note qu'il donne au film
	 * @param commentaire
	 *            ses commentaires
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 *             <li>si le commentaire n'est pas instancié.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws NotItem
	 *             : si le titre n'est pas le titre d'un film.
	 * 
	 * @return la note moyenne des notes sur ce film
	 */
	public float reviewItemFilm(String pseudo, String password, String titre,
			float note, String commentaire) throws BadEntry, NotMember, NotItem {

		if (!(Membre.isValidPseudo(pseudo) && Membre.isValidPassword(password))) {
			throw new BadEntry("User invalid");
		}
		if (!(Item.isValidTitre(titre))) {
			throw new BadEntry("Titre invalid");
		}
		if (!(Review.isValidNote(note) && Review
				.isValidCommentaire(commentaire))) {
			throw new BadEntry("Review data invalid");
		}

		if (!memberAlreadyExists(pseudo)) {
			throw new NotMember(pseudo + " is not a member");
		}

		Membre membre = membres.get(pseudo.trim().toLowerCase());
		if (!membre.auth(password)) {
			throw new NotMember("Password incorrect");
		}
		if (!filmAlreadyExists(titre)) {
			throw new NotItem("Le film n'existe pas");
		}

		Film film = films.get(titre.trim().toLowerCase());

		return film.addReview(new Review(film, membre, note, commentaire));

	}

	/**
	 * Donner son opinion sur un item livre. Ajoute l'opinion de ce membre sur
	 * ce livre au <i>SocialNetwork</i> Si une opinion de ce membre sur ce livre
	 * préexiste, elle est mise à jour avec ces nouvelles valeurs.
	 * 
	 * @param pseudo
	 *            pseudo du membre émettant l'opinion
	 * @param password
	 *            son mot de passe
	 * @param titre
	 *            titre du livre concerné
	 * @param note
	 *            la note qu'il donne au livre
	 * @param commentaire
	 *            ses commentaires
	 * 
	 * @throws BadEntry
	 *             :
	 *             <ul>
	 *             <li>si le pseudo n'est pas instancié ou a moins de 1
	 *             caractère autre que des espaces .</li>
	 *             <li>si le password n'est pas instancié ou a moins de 4
	 *             caractères autres que des leadings or trailing blanks.</li>
	 *             <li>si le titre n'est pas instancié ou a moins de 1 caractère
	 *             autre que des espaces.</li>
	 *             <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 *             <li>si le commentaire n'est pas instancié.</li>
	 *             </ul>
	 * <br>
	 * @throws NotMember
	 *             : si le pseudo n'est pas celui d'un membre ou si le pseudo et
	 *             le password ne correspondent pas.
	 * @throws NotItem
	 *             : si le titre n'est pas le titre d'un livre.
	 * 
	 * @return la note moyenne des notes sur ce livre
	 */
	public float reviewItemBook(String pseudo, String password, String titre,
			float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		if (!(Membre.isValidPseudo(pseudo) && Membre.isValidPassword(password))) {
			throw new BadEntry("User invalid");
		}
		if (!(Item.isValidTitre(titre))) {
			throw new BadEntry("Titre invalid");
		}
		if (!(Review.isValidNote(note) && Review
				.isValidCommentaire(commentaire))) {
			throw new BadEntry("Review data invalid");
		}

		if (!memberAlreadyExists(pseudo)) {
			throw new NotMember(pseudo + " is not a member");
		}

		Membre membre = membres.get(pseudo.trim().toLowerCase());
		if (!membre.auth(password)) {
			throw new NotMember("Password incorrect");
		}
		if (!bookAlreadyExists(titre)) {
			throw new NotItem("Le book n'existe pas");
		}

		Book book = books.get(titre.trim().toLowerCase());

		return book.addReview(new Review(book, membre, note, commentaire));
	}
	/* TODO analyze cast on LinkedHashMap
	private float reviewItemOf(LinkedHashMap<String, Item> items, String pseudo, String password, String titre,
			float note, String commentaire) throws BadEntry, NotMember, NotItem {
		
		if (!(Membre.isValidPseudo(pseudo) && Membre.isValidPassword(password))) {
			throw new BadEntry("User invalid");
		}
		if (!(Item.isValidTitre(titre))) {
			throw new BadEntry("Titre invalid");
		}
		if (!(Review.isValidNote(note) && Review
				.isValidCommentaire(commentaire))) {
			throw new BadEntry("Review data invalid");
		}

		if (!memberAlreadyExists(pseudo)) {
			throw new NotMember(pseudo + " is not a member");
		}

		Membre membre = membres.get(pseudo.trim().toLowerCase());
		if (!membre.auth(password)) {
			throw new NotMember("Password incorrect");
		}
		if (!filmAlreadyExists(titre)) {
			throw new NotItem("Le film n'existe pas");
		}

		Item item = items.get(titre.trim().toLowerCase());

		return item.addReview(new Review(item, membre, note, commentaire));
	}
	//*/
	
	/**
	 * Obtenir une représentation textuelle du <i>SocialNetwork</i>.
	 * 
	 * @return la chaîne de caractères représentation textuelle du
	 *         <i>SocialNetwork</i>
	 */
	public String toString() {
		return "Social Network contains : " + this.nbMembers() + " Membres, " + this.nbBooks() + " Books, " + this.nbFilms() + " Films" ;
	}

	/**
	 * @uml.property name="books"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
	 *                     inverse="socialNetwork:avis.Book"
	 */
	private LinkedHashMap<String, Book> books;
	/**
	 * @uml.property name="films"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
	 *                     inverse="socialNetwork:avis.Film"
	 */
	private LinkedHashMap<String, Film> films;
	/**
	 * @uml.property name="membres"
	 * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
	 *                     inverse="socialNetwork:avis.Membre"
	 */
	private LinkedHashMap<String, Membre> membres;

	/**
		 */
	protected boolean memberAlreadyExists(String pseudo) throws BadEntry {
		if (!Membre.isValidPseudo(pseudo)) {
			throw new BadEntry("Pseudo invalid");
		}
		return membres.containsKey(pseudo.toLowerCase().trim());
	}

	/**
		 */
	protected boolean bookAlreadyExists(String titre) throws BadEntry {
		if (!Book.isValidTitre(titre)) {
			throw new BadEntry("Titre invalid");
		}
		return books.containsKey(titre.toLowerCase().trim());
	}

	/**
		 */
	protected boolean filmAlreadyExists(String titre) throws BadEntry {
		if (!Film.isValidTitre(titre)) {
			throw new BadEntry("Titre invalid");
		}
		return films.containsKey(titre.toLowerCase().trim());
	}
}
