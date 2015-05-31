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
     * Constructor of <i>Review</i>
     *
     * @param item item associted to the comment
     * @param member the member who add the review
     * @param note the note of the review
     * @param comment the comment of the review
     * @throws exception.BadEntry
     * <ul>
     * <li> if the member is not instantiated.</li>
     * <li>if the title is not instantiated or less than 1 character other than
     * spaces.</li>
     * <li>if the note is not instantiated or between 0.0 and 5.0</li>
     * <li>if the comment is not instantiated</li>
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
        for (Float opinion : opinions.values()) {
            karma += opinion;
        }
        return karma / (opinions.size() * 5);
    }

    /**
     * add a opinion of the review
     *
     * @param member the member giving a opinion
     * @param opinion the opinion to add
     */
    public void addOpinion(Member member, float opinion) throws NotMember {
        if (!isValidMember(member)) {
            throw new NotMember("The member is not instantiated");
        }

        opinions.put(member.getNickname().trim().toLowerCase(), opinion);
    }

    /**
     * Test the given parameters
     *
     * @param item
     * @param member
     * @param note
     * @param comment
     * @return true if all the params are correctly instantiated
     */
    public static boolean isValidReviewInput(Item item, Member member,
            float note, String comment) {
        return isValidItem(item) && isValidMember(member) && isValidNote(note)
                && isValidComment(comment);
    }

    /**
     * Test the given param itam
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
