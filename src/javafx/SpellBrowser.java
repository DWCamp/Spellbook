package javafx;

import javafx.stage.Window;
import model.Settings;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The Window for browsing and searching spells
 * Intended to support all implemented versions of the game,
 * but currently is only designed to support FE in its current
 * implementation. Full version support is intended in a later
 * release.
 */
public class SpellBrowser extends Window {

    /**
     * Creates a SpellBrowser Window
     */
    public SpellBrowser() {
        super();
        System.out.println("SpellBrowser - Start.");
    }
}
