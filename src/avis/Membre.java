package avis;

import exception.BadEntry;
import exception.NotMember;
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
     * Ajouter un nouveau membre au <i>SocialNetwork</i>
     *
     * @param pseudo son pseudo
     * @param password son mot de passe
     * @param profil un slogan choisi par le membre pour se définir
     *
     * @throws BadEntry :
     * <ul>
     * <li>si le pseudo n'est pas instancié ou a moins de 1 caractère autre que
     * des espaces .</li>
     * <li>si le password n'est pas instancié ou a moins de 4 caractères autres
     * que des leadings or trailing blanks.</li>
     * <li>si le profil n'est pas instancié.</li>
     * </ul>
     * <br>
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


    /** Test les paramètres d'entrée
     * @param pseudo son pseudo
     * @param password son mot de passe
     * @param profil un slogan choisi par le membre pour se définir
     * @return vrai si les paramètres sont valides
     */
    public static boolean isValidMemberInput(String pseudo, String password,
            String profil) {
        return isValidPseudo(pseudo) && isValidPassword(password) && isValidProfil(profil);
    }


    /** Test le paramètre d'entrée pseudo
     * @param pseudo pseudo à valider
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isValidPseudo(String pseudo) {
        if (pseudo == null) {
            return false;
        }
        return pseudo.replaceAll("\\s", "").length() >= 1;
    }

    /** Test le paramètre d'entrée password
     * @param password password à valider
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        return password.trim().length() >= 4;
    }

    /** Test le paramètre d'entrée profil
     * @param profil profil à valider
     * @return vrai si le paramètre est correctement instancié
     */
    public static boolean isValidProfil(String profil) {
        return profil != null;
    }

    /**
     * @uml.property name="pseudo"
     */
    private String pseudo;


    /**
     * Récupérer le pseudo du membre 
     * 
     * Getter of the property <tt>pseudo</tt>
     *
     * @return Returns the pseudo.
     * @uml.property name="pseudo"
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Changer le pseudo d'un membre
     * Setter of the property <tt>pseudo</tt>
     *
     * @param pseudo The pseudo to set.
     * @throws exception.BadEntry si le pseudo n'est pas instancié ou a moins de 1 caractère autre que
     * des espaces
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
     * Récupérer le password d'un membre
     * Getter of the property <tt>password</tt>
     *
     * @return Returns the password.
     * @uml.property name="password"
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changer le password d'un membre
     * Setter of the property <tt>password</tt>
     *
     * @param password The password to set.
     * @throws exception.BadEntry si le password n'est pas instancié ou a moins de 4 caractères autres
     * que des leadings or trailing blanks.
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
     * Récupérer le profil d'un membre
     * Getter of the property <tt>profil</tt>
     *
     * @return Returns the profil.
     * @uml.property name="profil"
     */
    public String getProfil() {
        return profil;
    }

    /**
     * Changer le profil d'un membre
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
     * Récupérer la liste de reviews d'un membre
     * Getter of the property <tt>reviews</tt>
     *
     * @return Returns the reviews.
     * @uml.property name="reviews"
     */
    public LinkedHashMap<String, Review> getReviews() {
        return reviews;
    }

    /**
     * Permet à un membre d'ajouter une review
     * 
     * @param review
     */
    public void addReview(Review review) {
        reviews.put(review.getItem().getTitre().trim().toLowerCase(), review);
    }
    /**
     * Récupérer le karma d'un membre
     *
     * @return Return the karma.
     */
    public float getKarma() {
        float karma = 0f;
        for (Map.Entry<String, Review> review : reviews.entrySet()) {
        	karma += review.getValue().getLocalKarma();
        }
    	return (reviews.size()==0)?0.5f:karma/reviews.size();
    }

    /**
     * Permet à un membre d'ajouter une opinion
     * @param membre 
     * 
     * @param membre le membre qui donne son opinion
     * @param titre le titre de l'item
     * @param type le type de l'item
     * @param opinion la nouvelel opinion à enregistrer
     * @return the new karma of the user 
     * @throws NotReview si l'utilisateur n'as pas donner de review de l'élément
     * @throws NotType si le type est différent de Book ou Film
     * @throws NotMember si le memebre qui note n'est pas instancié
     */
    public float addOpinion(Membre membre, String titre,String type,  float opinion) throws NotReview, NotType, NotMember {
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
        
        review.addOpinion(membre, opinion);
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
     * Permet à un membre d'ajouter un item
     * 
     * @param item
     */
    public void addItem(Item item) {
        items.put(item.getTitre().trim().toLowerCase(), item);
    }


    /**
     * Permet d'authentifier un membre 
     * 
     * @param password
     * @return Return true if the password is good
     */
    protected boolean auth(String password) {
        return this.password.equals(password);
    }

}
