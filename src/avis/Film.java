package avis;

import exception.BadEntry;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date may 2015
 * @version V1.0
 */

public class Film extends Item {

	private int duration;
	private String director;
	private String scenarist;

	
	
	/**
	 * Add a new film to <i>SocialNetwork</i>
	 *
	 * @param creator the member who add the film
	 * @param title the title of the book
	 * @param genre the genre (novels, essays, etc.)
	 * @param director the director
	 * @param scenarist the scenarist
	 * @param duration the duration in minutes
	 * @throws exception.BadEntry :
	 * <ul>
	 * <li> if the creator is not instantiated.</li>
	 * <li>if the title is not instantiated or less than 1 character other than
	 * spaces.</li>
	 * <li>if genre is not instantiated.</li>
	 * <li>if the director is not instantiated.</li>
	 * <li>if the scenarist is not instantiated.</li>
	 * <li>if the duration is not positive.</li>
	 * </ul>
	 * <br>
	 */
	public Film(String title, String genre, Member creator, String director,
			String scenarist, int duration) throws BadEntry {
		super(title, genre, creator);
		this.setDirector(director);
		this.setScenarist(scenarist);
		this.setDuration(duration);
	}

	/**
	 * Getter of the property <tt>director</tt>
	 *
	 * @return Returns the director.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Setter of the property <tt>director</tt>
	 *
	 * @param director The director to set.
	 * @throws exception.BadEntry if the director is not instantiated
	 */
	public void setDirector(String director) throws BadEntry {
		if (!isInstanced(director)) {
			throw new BadEntry("Bad director entry");
		}
		this.director = director;
	}

	/**
	 * Getter of the property <tt>scenarist</tt>
	 *
	 * @return Returns the scenarist.
	 */
	public String getScenarist() {
		return scenarist;
	}

	/**
	 * Setter of the property <tt>scenarist</tt>
	 *
	 * @param scenarist The scenarist to set.
	 * @throws exception.BadEntry if the scenarist is not instantiated
	 */
	public void setScenarist(String scenarist) throws BadEntry {
		if (!isInstanced(scenarist)) {
			throw new BadEntry("Bad scenarist entry");
		}
		this.scenarist = scenarist;
	}


	/**
	 * Getter of the property <tt>duration</tt>
	 *
	 * @return Returns the duration.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Setter of the property <tt>duration</tt>
	 *
	 * @param duration The duration to set.
	 * @throws exception.BadEntry if the duration is not positive.
	 */
	public void setDuration(int duration) throws BadEntry {
		if (!isValidDuration(duration)) {
			throw new BadEntry("Duration invalid");
		}
		this.duration = duration;
	}

	/**
	 * Test the given parameters
	 *
	 * @param creator the member who add the film
	 * @param title the title of the book
	 * @param genre the genre (novels, essays, etc)
	 * @param director the director
	 * @param scenarist the scenarist
	 * @param duration the duration in minutes
	 * @return true if all the params are correctly instantiated
	 */
	public static boolean isValidFilmInput(String title, String genre, Member creator, String director,
			String scenarist, int duration) {
		return isValidTitle(title) && isInstanced(creator) && isInstanced(genre) && isInstanced(director) && isInstanced(scenarist) && isValidDuration(duration);
	}

	/**
	 * Test the given param duration
	 *
	 * @param duration the duration in minutes
	 * @return true if the param is correctly instantiated
	 */
	public static boolean isValidDuration(int duration) {
		return duration > 0;
	}

	@Override
	public String toString() {
		return "Film [realisateur=" + director + ", scenariste="
				+ scenarist + ", duration=" + duration + ", "
				+ super.toString() + "]";
	}
}
