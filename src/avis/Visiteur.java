package avis;
/*
 * @author Antoine GIRARD
 * @author Simon LILLE
 * @date mai 2015
 * @version V1.0
 */

public class Visiteur {

    /**
     *
     */
    public Visiteur() {
        super();
    }

    /**
     * @uml.property name="id_session"
     */
    private String id_session;

    /**
     * Getter of the property <tt>id_session</tt>
     *
     * @return Returns the id_session.
     * @uml.property name="id_session"
     */
    public String getId_session() {
        return id_session;
    }

    /**
     * Setter of the property <tt>id_session</tt>
     *
     * @param id_session The id_session to set.
     * @uml.property name="id_session"
     */
    public void setId_session(String id_session) {
        this.id_session = id_session;
    }

}
