package bookshop.ui;

import javax.swing.JFrame;
import bookshop.Application;
import bookshop.db.Manager;

public abstract class GUI {

    /**
     * The application database instance.
     *
     * @var Manager
     */
    Application app = null;

    /**
     * The main UI frame.
     *
     * @var JFrame
     */
    JFrame frame = null;

    /**
     * Set the default application instance.
     *
     * @param Appliation app
     *
     * @return void
     */
    public void setApplication(Application app) {
        this.app = app;
    }

    /**
     * Build the UI.
     *
     * @return void
     */
    public abstract void build();

    /**
     * Get the application database manager instance.
     *
     * @return Manager
     */
    public Manager db() {
        return this.app.db;
    }

    /**
     * The default close operation on GUIs.
     *
     * @return int
     */
    public int defaultCloseOperation() {
        this.app.terminate();

        return JFrame.EXIT_ON_CLOSE;
    }

    /**
     * Get the main UI frame of the current UI.
     *
     * @return JFrame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Set the current UI frame to given visibility.
     *
     * @param boolean visible
     *
     * @return void
     */
    public void setJFrameVisible(boolean visible) {
        this.frame.setVisible(visible);
    }

    /**
     * Dispose of this UI frame.
     *
     * @return void
     */
    public void dispose() {
        this.frame.dispose();
    }
}
