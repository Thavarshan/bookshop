package bookshop.db.models;

public abstract class Model {

    /**
     * Model attributes.
     *
     * @var String[]
     */
    protected String[] attributes;

    /**
     * Set the user attributes.
     *
     * @param String[] attributes
     *
     * @return void
     */
    public abstract void setAttributes(String[] attributes);

    /**
     * Get the user's set attributes.
     *
     * @erturn String[]
     */
    public String[] getAttributes() {
        return this.attributes;
    }
}
