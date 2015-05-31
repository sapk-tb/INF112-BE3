package avis;

import exception.BadEntry;
import exception.NotMember;
import exception.NotReview;
import exception.NotType;

import java.util.LinkedHashMap;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date may 2015
 * @version V1.0
 */

public class Member extends Visiteur {

    private String nickname;
    private String password;
    private String profil;

    private LinkedHashMap<String, Item> items;
    private LinkedHashMap<String, Review> reviews;

    /**
     * Constructor of <i>Member</i>
     *
     * @param nickname his nickname
     * @param password his password
     * @param profil a slogan chosen by the member to be described
     *
     * @throws BadEntry :
     * <ul>
     * <li> if the nickname is not instantiated or less than 1 character other
     * than spaces.</li>
     * <li> if the password is not instantiated or less than 4 character other
     * than spaces.</li>
     * <li> if the profil is not instantiated </li>
     * </ul>
     * <br>
     */
    public Member(String nickname, String password, String profil)
            throws BadEntry {
        super();
        if (!isValidMemberInput(nickname, password, profil)) {
            throw new BadEntry("Input invalid !");
        }
        this.nickname = nickname.trim();
        this.password = password;
        this.profil = profil;
        this.reviews = new LinkedHashMap<String, Review>();
        this.items = new LinkedHashMap<String, Item>();
    }

    /**
     * Verify the authentification of the member
     *
     * @param password
     * @return true if the password is good
     */
    protected boolean auth(String password) {
        return this.password.equals(password);
    }

    /**
     * Getter of the property <tt>nickname</tt>
     *
     * @return Returns the nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Setter of the property <tt>nickname</tt>
     *
     * @param nickname The nickname to set.
     * @throws exception.BadEntry if the nickname is not instantiated or less
     * than 1 character other than spaces.
     */
    public void setNickname(String nickname) throws BadEntry {
        if (!isValidNickname(nickname)) {
            throw new BadEntry("Pseudo invalid");
        }
        this.nickname = nickname;
    }

    /**
     * Setter of the property <tt>password</tt>
     *
     * @param password The password to set.
     * @throws exception.BadEntry if the nickname is not instantiated or less
     * than 4 character other than spaces.
     */
    public void setPassword(String password) throws BadEntry {
        if (!isValidPassword(password)) {
            throw new BadEntry("Password invalid");
        }
        this.password = password;
    }

    /**
     * Getter of the property <tt>profil</tt>
     *
     * @return Returns the profil.
     */
    public String getProfil() {
        return profil;
    }

    /**
     * Setter of the property <tt>profil</tt>
     *
     * @param profil The profil to set.
     * @throws exception.BadEntry if the profil is not instantiated.
     */
    public void setProfil(String profil) throws BadEntry {
        if (!isValidProfil(profil)) {
            throw new BadEntry("Profil invalid");
        }
        this.profil = profil;
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
     * Getter of the property <tt>items</tt>
     *
     * @return Returns the items.
     */
    public LinkedHashMap<String, Item> getItems() {
        return items;
    }

    /**
     * Add a review from the member
     *
     * @param review
     */
    public void addReview(Review review) {
        reviews.put(review.getItem().getTitle().trim().toLowerCase(), review);
    }

    /**
     * Calculate the karma of the member
     *
     * @return Return the karma.
     */
    public float getKarma() {
        //TODO consider caching
        if (reviews.size() == 0) {
            return 0.5f;
        }
        float karma = 0f;
        for (Review review : reviews.values()) {
            karma += review.getLocalKarma();
        }

        return karma / reviews.size();
    }

    /**
     * Add a opinion of a review given by a member to this member
     *
     * @param member the member giving the opinion
     * @param title the title of the item
     * @param type the type of the item
     * @param opinion the opinion to register
     * @return the new karma of this member
     * @throws NotReview if this member haven't gave a review of this title
     * @throws NotType if the type is differnt of Book or Film
     * @throws NotMember if the member giving the opinion is not instantiated
     */
    public float addOpinion(Member member, String title, String type, float opinion) throws NotReview, NotType, NotMember {
        if (!reviews.containsKey(title.trim().toLowerCase())) {
            throw new NotReview("The member haven't gave a review of this title");
        }
        Review review = reviews.get(title.trim().toLowerCase());
        try {
            switch (Item.Types.valueOf(type)) {
                case Book:
                    if (!review.getItem().getClass().equals(Book.class)) {
                        throw new NotReview("The member haven't gave a review of this title of type book");
                    }
                    break;
                case Film:
                    if (!review.getItem().getClass().equals(Film.class)) {
                        throw new NotReview("The member haven't gave a review of this title of type film");
                    }
                    break;
            }
        } catch (IllegalArgumentException e) {
            //If this exception is thrown it means that the type is not in the enum of type allowed
            throw new NotType("Type invalid !");
        }

        review.addOpinion(member, opinion);
        return getKarma();
    }

    /**
     * Add a item of the member
     *
     * @param item
     */
    public void addItem(Item item) {
        items.put(item.getTitle().trim().toLowerCase(), item);
    }

    /**
     * Test the given parameters
     *
     * @param nickname his nickname
     * @param password his password
     * @param profil un slogan choisi par le membre pour se définir
     * @return vrai si les paramètres sont valides
     */
    public static boolean isValidMemberInput(String nickname, String password,
            String profil) {
        return isValidNickname(nickname) && isValidPassword(password) && isValidProfil(profil);
    }

    /**
     * Test the given param nickname
     *
     * @param nickname nickname to validate
     * @return true if the param is correctly instantiated
     */
    public static boolean isValidNickname(String nickname) {
        return nickname != null && nickname.trim().length() >= 1;
    }

    /**
     * Test the given param password
     *
     * @param password password to validate
     * @return true if the param is correctly instantiated
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.trim().length() >= 4;
    }

    /**
     * Test the given param profil
     *
     * @param profil profil to validate
     * @return true if the param is correctly instantiated
     */
    public static boolean isValidProfil(String profil) {
        return profil != null;
    }

    @Override
    public String toString() {
        return "Membre{" + "nickname=" + nickname + ", profil=" + profil + ", len(reviews)=" + reviews.size() + ", len(items)=" + items.size() + '}';
    }
}
