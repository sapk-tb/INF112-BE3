package avis;

import java.util.LinkedHashMap;

import exception.BadEntry;
import exception.NotMember;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date may 2015
 * @version V1.0
 */

public class Review {

	private Item item = null;
	private Member member = null;
	private float note;
	private String comment;

	private LinkedHashMap<String, Float> opinions;

	/**
	 * @param item item associé au comment
	 * @param member le member qui ajoute le comment
	 * @param note la note du member associé à l'item
	 * @param comment le comment du member à propos de l'item
	 * @throws exception.BadEntry
	 * <ul>
	 * <li>si le pseudo n'est pas instancié ou a moins de 1 caractère autre que
	 * des espaces .</li>
	 * <li>si le password n'est pas instancié ou a moins de 4 caractères autres
	 * que des leadings or trailing blanks.</li>
	 * <li>si le titre n'est pas instancié ou a moins de 1 caractère autre que
	 * des espaces.</li>
	 * <li>si la note n'est pas comprise entre 0.0 et 5.0.</li>
	 * <li>si le comment n'est pas instancié.</li>
	 * </ul>
	 * <br>
	 */
	public Review(Item item, Member member, float note, String comment)
			throws BadEntry {
		super();
		if (!isValidItem(item)) {
			throw new BadEntry("Item invalid");
		}
		this.item = item;
		if (!isValidMember(member)) {
			throw new BadEntry("Member invalid");
		}
		this.member = member;

		this.setNote(note);
		this.setComment(comment);

		this.opinions = new LinkedHashMap<String, Float>();
	}

	/**
	 * Getter of the property <tt>item</tt>
	 *
	 * @return Returns the item.
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * Getter of the property <tt>member</tt>
	 *
	 * @return Returns the member.
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * Getter of the property <tt>note</tt>
	 *
	 * @return Returns the note.
	 */
	public float getNote() {
		return note;
	}

	/**
	 * Setter of the property <tt>note</tt>
	 *
	 * @param note The note to set.
	 * @throws exception.BadEntry
	 */
	public void setNote(float note) throws BadEntry {
		if (!isValidNote(note)) {
			throw new BadEntry("Note invalid");
		}
		this.note = note;
	}

	/**
	 * Getter of the property <tt>comment</tt>
	 *
	 * @return Returns the comment.
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Setter of the property <tt>comment</tt>
	 *
	 * @param comment The comment to set.
	 * @throws exception.BadEntry si les paramètres sont invalide
	 * @uml.property name="commentaire"
	 */
	public void setComment(String comment) throws BadEntry {
		if (!isValidComment(comment)) {
			throw new BadEntry("Commentaire invalid");
		}
		this.comment = comment;
	}

	/**
	 * @return la liste des opinions
	 */
	public LinkedHashMap<String, Float> getOpinions() {
		return opinions;
	}

	/**
	 * @return le karma "local" de l'opinion
	 */
	public float getLocalKarma() {
		if (opinions.size() == 0) {
			return 2.5f;
		}
		float karma = 0f;
		/*
		 for (Entry<String, Float> opinion : opinions.entrySet()) {
		 karma += opinion.getValue();
		 }
		 */
		for (Float opinion : opinions.values()) {
			karma += opinion;
		}
		return karma / (opinions.size() * 5);
	}

	/**
	 * Ajout une opinion du review
	 *
	 * @param membre le member qui donne son opinion
	 * @param opinion l'opinion à rajouters
	 */
	public void addOpinion(Member membre, float opinion) throws NotMember {
		if (!isValidMember(membre)) {
			throw new NotMember("Le membre n'est pas instancié");
		}

		opinions.put(membre.getNickname().trim().toLowerCase(), opinion);
	}

	/**
	 * Teste si la review du member est valide
	 *
	 * @param item
	 * @param member
	 * @param note
	 * @param comment
	 * @return vrai si la reiview est valide
	 */
	public static boolean isValidReviewInput(Item item, Member member,
			float note, String comment) {
		return isValidItem(item) && isValidMember(member) && isValidNote(note)
				&& isValidComment(comment);
	}

	/**
	 * Test the given param item
	 *
	 * @param item
	 * @return true if the param is correctly instantiated
	 */
	public static boolean isValidItem(Item item) {
		return item != null;
	}

	/**
	 * Test the given param note
	 *
	 * @param note
	 * @return true if the param is correctly instantiated (between 0 and 5)
	 */
	public static boolean isValidNote(float note) {
		return note >= 0 && note <= 5;
	}

	/**
	 * Test the given param member
	 *
	 * @param member
	 * @return true if the param is correctly instantiated
	 */
	public static boolean isValidMember(Member member) {
		return member != null;
	}

	/**
	 * Test the given param comment
	 *
	 * @param comment
	 * @return true if the param is correctly instantiated
	 */
	public static boolean isValidComment(String comment) {
		return comment != null;
	}

	@Override
	public String toString() {
		return "Review{note=" + note + ", comment=" + comment + ", member=" + member + '}';
	}
}
