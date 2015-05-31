package avis;

import exception.BadEntry;

/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date may 2015
 * @version V1.0
 */
public class Book extends Item {

	private String author;
	private int nbPages;

	/**
	 * Add a new book to <i>SocialNetwork</i>
	 *
	 * @param title the title of th book
	 * @param genre the genre (novels, essays, etc.)
	 * @param author the author
	 * @param nbPages the number of pages
	 * @param creator the member who add the book
	 * @throws exception.BadEntry :
	 * <ul>
	 * <li> if the creator is not instantiated.</li>
	 * <li>if the title is not instantiated or less than 1 character other than
	 * spaces.</li>
	 * <li>if genre is not instantiated.</li>
	 * <li>if the author is not instantiated.</li>
	 * <li>if the number of pages is not positive.</li>
	 * </ul>
	 * <br>
	 */
	public Book(String title, String genre, Member creator, String author,
			int nbPages) throws BadEntry {
		super(title, genre, creator);
		this.setAuthor(author);
		this.setNbPages(nbPages);
	}

	/**
	 * Getter of the property <tt>author</tt>
	 *
	 * @return return the author of the book.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Setter of the property <tt>author</tt>
	 *
	 * @param author The author to set.
	 * @throws exception.BadEntry if the author is not instantiated
	 */
	public void setAuthor(String author) throws BadEntry {
		if (!isInstanced(author)) {
			throw new BadEntry("");
		}
		this.author = author;
	}

	/**
	 * Getter of the property <tt>nbPages</tt>
	 *
	 * @return the number of pages of the book.
	 */
	public int getNbPages() {
		return nbPages;
	}

	/**
	 * Setter of the property <tt>nbPages</tt>
	 *
	 * @param nbPages The nbPages to set.
	 * @throws exception.BadEntry if the number of pages is not positive.
	 */
	public void setNbPages(int nbPages) throws BadEntry {
		if (!isValidnbPages(nbPages)) {
			throw new BadEntry("");
		}
		this.nbPages = nbPages;
	}

	/**
	 * Test the given parameters
	 *
	 * @param title the title of the book
	 * @param genre the genre (novels, essays, etc)
	 * @param author the author
	 * @param nbPages the number of pages
	 * @param creator the member who add the book
	 * @return true if all the params are correctly instantiated
	 */
	public static boolean isValidBookInput(Member creator, String title, String genre, String author, int nbPages) {
		return isValidTitle(title) && isInstanced(creator) && isInstanced(genre) && isInstanced(author) && isValidnbPages(nbPages);
	}

	/**
	 * Test the given paramater nbPages
	 *
	 * @param nbPages the number of pages
	 * @return true if the params is correctly instantiated
	 */
	public static boolean isValidnbPages(int nbPages) {
		return nbPages > 0;
	}

	@Override
	public String toString() {
		return "Book [author=" + author + ", nbPages=" + nbPages
				+ ", " + super.toString() + "]";
	}
}
