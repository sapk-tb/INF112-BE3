package avis;

import java.util.LinkedHashMap;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date may 2015
 * @version V1.0
 */
import exception.BadEntry;

public abstract class Item {

	public static enum Types {

		Book, Film
	}
	private Member creator = null;
	private String title;
	private String genre;

	private LinkedHashMap<String, Review> reviews;

	/**
	 * Creation of one Item (generic) by one member
	 *
	 * @param title
	 * @param genre
	 * @param creator
	 * @throws exception.BadEntry
	 */
	public Item(String title, String genre, Member creator) throws BadEntry {
		super();
		this.setTitle(title);
		this.setGenre(genre);
		this.reviews = new LinkedHashMap<String, Review>();
		if (!isInstanced(creator)) {
			throw new BadEntry("Creator invalid");
		}
		this.creator = creator;
	}

	/**
	 * Getter of the property <tt>title</tt>
	 *
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter of the property <tt>title</tt>
	 *
	 * @param title The title to set.
	 * @throws exception.BadEntry if the title is not instantiated or less than
	 * 1 character other than spaces
	 */
	public void setTitle(String title) throws BadEntry {
		if (!isValidTitle(title)) {
			throw new BadEntry("Title invalid");
		}
		this.title = title;
	}

	/**
	 * Getter of the property <tt>genre</tt>
	 *
	 * @return Returns the genre.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Setter of the property <tt>genre</tt>
	 *
	 * @param genre The genre to set.
	 * @throws exception.BadEntry if genre is not instantiated.
	 */
	public void setGenre(String genre) throws BadEntry {
		if (!isInstanced(genre)) {
			throw new BadEntry("Genre is not instantiated");
		}
		this.genre = genre;
	}

	/**
	 * Getter of the property <tt>reviews</tt>
	 *
	 * @return Returns the reviews.
	 */
	public LinkedHashMap<String, Review> getReviews() {
		return reviews;
	}

	/**
	 * Add a review of the Item by a member
	 *
	 * @param review
	 * @return the average of the item.
	 */
	public float addReview(Review review) {
		reviews.put(review.getMember().getNickname().trim().toLowerCase(), review);
		review.getMember().addReview(review);
		return this.getAverage();
	}

	/**
	 * Getter of the property <tt>membre</tt>
	 *
	 * @return Returns the membre.
	 */
	public Member getCreator() {
		return creator;
	}

	/**
	 * Calculate and return the average of all the review.
	 *
	 * @return the average of the item.
	 */
	public float getAverage() {
		if (reviews.size() == 0) {
			return -1f;
		}
		float moyenne = 0;
		float total_karma = 0;
		// Not compatible with  Java 1.7 :   moyenne = reviews.entrySet().stream().map((review) -> review.getValue().getNote()).reduce(moyenne, (accumulator, _item) -> accumulator + _item);
		for (Review review : reviews.values()) {
			float user_karma = review.getMember().getKarma();
			moyenne += review.getNote() * user_karma;
			total_karma += user_karma;
		}
		return (total_karma == 0) ? -1f : moyenne / total_karma;
	}

	/**
	 * Test the given param title
	 *
	 * @param title the title to validate
	 * @return true if the title is correctly instantiated
	 */
	public static boolean isValidTitle(String title) {
		return title != null && title.trim().length() >= 1;
	}

	/**
	 * Test the given param is instantiated
	 *
	 * @param o the object to validate
	 * @return true if the param is correctly instantiated
	 */
	public static boolean isInstanced(Object o) {
		return o != null;
	}

	@Override
	public String toString() {
		return " title=" + title + ", genre=" + genre + ", average=" + getAverage() + ", reviews="
				+ reviews + ", creator=" + creator;
	}

}
