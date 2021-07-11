package bookshop.ui;

import javax.swing.JPanel;

public interface HasComponents {

    /**
     * Place UI components on the default panel.
     *
     * @param JPanel panel
     *
     * @return void
     */
    public void placeComponents(JPanel panel);
}
