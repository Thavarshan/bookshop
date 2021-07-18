package bookshop.ui;

import bookshop.Application;

public class Builder {

    /**
     * The application instance.
     *
     * @var Application
     */
    protected Application app = null;

    /**
     * Set the default application instance.
     *
     * @param Application app
     *
     * @return void
     */
    public void setApplication(Application app) {
        this.app = app;
    }

    /**
     * Build the main application insterface.
     *
     * @return void
     */
    public void build() {
        GUI ui = new GUI();

        this.make(ui);
    }

    /**
     * Make the given UI instance.
     *
     * @param GUI ui
     *
     * @return void
     */
    public void make(GUI ui) {
        ui.setApplication(this.app);

        ui.setDefaultPanel(ui.loginPanel());

        ui.build();
    }
}
