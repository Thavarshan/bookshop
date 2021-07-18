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
     * Create a new UI builder instance.
     *
     * @param Application
     *
     * @return void
     */
    public Builder(Application app) {
        this.app = app;
    }

    /**
     * Get the application instance.
     *
     * @return Application
     */
    public Application getApplication() {
        return this.app;
    }

    /**
     * Build the main application insterface.
     *
     * @return GUI
     */
    public GUI build() {
        return this.make(new GUI());
    }

    /**
     * Make the given UI instance.
     *
     * @param GUI ui
     *
     * @return GUI
     */
    public GUI make(GUI ui) {
        ui.setApplication(this.app);
        ui.setDefaultPanel(ui.loginPanel());
        ui.build();

        return ui;
    }
}
