package avis;

import exception.BadEntry;
import exception.NotItem;
import exception.NotReview;
import exception.NotType;

import java.util.LinkedHashMap;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */
import java.util.Map;

public class Membre extends Visiteur {

    /**
     * @param pseudo
     * @param password
     * @param profil
     * @throws exception.BadEntry
     */
    public Membre(String pseudo, String password, String profil)
            throws BadEntry {
        super();
        if (!isValidMemberInput(pseudo, password, profil)) {
            throw new BadEntry("Input invalid !");
        }
        this.pseudo = pseudo.trim();
        this.password = password;
        this.profil = profil;
        this.reviews = new LinkedHashMap<String, Review>();
        this.items = new LinkedHashMap<String, Item>();
    }

    /**
     * @param pseudo
     * @param profil
     * @param password
     * @return
     */
    public static boolean isValidMemberInput(String pseudo, String password,
            String profil) {
        return isValidPseudo(pseudo) && isValidPassword(password) && isValidProfil(profil);
    }

    public static boolean isValidPseudo(String pseudo) {
        if (pseudo == null) {
            return false;
        }
        return pseudo.replaceAll("\\s", "").length() >= 1;
    }

    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.trim().length() >= 4;
    }

    public static boolean isValidProfil(String profil) {
        return profil != null;
    }

    /**
     * @uml.property name="pseudo"
     */
    private String pseudo;

    /**
     * Getter of the property <tt>pseudo</tt>
     *
     * @return Returns the pseudo.
     * @uml.property name="pseudo"
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Setter of the property <tt>pseudo</tt>
     *
     * @param pseudo The pseudo to set.
     * @throws exception.BadEntry
     * @uml.property name="pseudo"
     */
    public void setPseudo(String pseudo) throws BadEntry {
        if (!isValidPseudo(pseudo)) {
            throw new BadEntry("Pseudo invalid");
        }
        this.pseudo = pseudo;
    }

    /**
     * @uml.property name="password"
     */
    private String password;

    /**
     * Getter of the property <tt>password</tt>
     *
     * @return Returns the password.
     * @uml.property name="password"
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter of the property <tt>password</tt>
     *
     * @param password The password to set.
     * @throws exception.BadEntry
     * @uml.property name="password"
     */
    public void setPassword(String password) throws BadEntry {
        if (!isValidPassword(password)) {
            throw new BadEntry("Password invalid");
        }
        this.password = password;
    }

    /**
     * @uml.property name="profil"
     */
    private String profil;

    /**
     * Getter of the property <tt>profil</tt>
     *
     * @return Returns the profil.
     * @uml.property name="profil"
     */
    public String getProfil() {
        return profil;
    }

    /**
     * Setter of the property <tt>profil</tt>
     *
     * @param profil The profil to set.
     * @throws exception.BadEntry
     * @uml.property name="profil"
     */
    public void setProfil(String profil) throws BadEntry {
        if (!isValidProfil(profil)) {
            throw new BadEntry("Profil invalid");
        }
        this.profil = profil;
    }

    /**
     * @uml.property name="reviews"
     * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
     * inverse="membre:avis.Review"
     */
    private LinkedHashMap<String, Review> reviews;

    /**
     * Getter of the property <tt>reviews</tt>
     *
     * @return Returns the reviews.
     * @uml.property name="reviews"
     */
    public LinkedHashMap<String, Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.put(review.getItem().getTitre().trim().toLowerCase(), review);
    }

    public float getKarma() {
        float karma = 0f;
        for (Map.Entry<String, Review> review : reviews.entrySet()) {
        	karma += review.getValue().getLocalKarma();
        }
    	return (reviews.size()==0)?0.5f:karma/reviews.size();
    }
    
    public float addOpinion(String titre,String type,  float opinion) throws NotReview, NotType {
    	//System.out.println("Reviews utilisateurs "+reviews);
    	if(!reviews.containsKey(titre.trim().toLowerCase())){
    		throw new NotReview("L'utilisateur n'a pas donné d'avis sur ce titre");
    	}
    	Review review = reviews.get(titre.trim().toLowerCase());
        switch (type) {
			case "Book":
		        if (!review.getItem().getClass().equals(Book.class)) {
		    		throw new NotReview("L'utilisateur n'a pas donné d'avis sur ce titre de type book");
		        }
				break;
			case "Film":
		        if (!review.getItem().getClass().equals(Film.class)) {
		    		throw new NotReview("L'utilisateur n'a pas donné d'avis sur ce titre de type film");
		        }
				break;
			default:
	            throw new NotType("Type invalid !");
		}
        
        review.addOpinion(this, opinion);
		return getKarma();
    }
    @Override
    public String toString() {
        return "Membre{" + "pseudo=" + pseudo + ", profil=" + profil + ", reviews=" + reviews.size() + ", items=" + items.size() + '}';
    }

    /**
     * @uml.property name="items"
     * @uml.associationEnd multiplicity="(0 -1)" dimension="1" ordering="true"
     * inverse="creator:avis.Item"
     */
    private LinkedHashMap<String, Item> items;

    /**
     * Getter of the property <tt>items</tt>
     *
     * @return Returns the items.
     * @uml.property name="items"
     */
    public LinkedHashMap<String, Item> getItems() {
        return items;
    }

    /**
     * @param item
     */
    public void addItem(Item item) {
        items.put(item.getTitre().trim().toLowerCase(), item);
    }

    /**
     * @param password
     * @return Return true if the password is good
     */
    protected boolean auth(String password) {
        return this.password.equals(password);
    }

}
