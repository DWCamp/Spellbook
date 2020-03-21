package javafx;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import model.CharacterInfo;
import model.Spell;

import java.util.ArrayList;
import java.util.Collection;

/**
 * The primary window of the SpellBrowser application
 *
 * @author Daniel Campman
 */
public class MainWindow extends Stage {

    private static final double BASE_HEIGHT = 400;  // The default height of the window
    private static final double BASE_WIDTH = 640;   // The default width of the window

    /**
     * Creates the main Window of the Spellbook Application
     */
    public MainWindow() {
        super();

        // Window attributes
        setTitle("Spellbook");

        // Window size
        double resolutionScaling = Spellbook.settings.getResolutionScaling();
        setResizable(true);
        BorderPane mainPane = new BorderPane();

        mainPane.setCenter(createSpellList(null));

        // Show window
        Scene scene = new Scene(mainPane,resolutionScaling * BASE_WIDTH,resolutionScaling * BASE_HEIGHT);
        setScene(scene);
    }

    /**
     * Creates the horizontally scrolling window of spell cards
     * @return An HBox containing all of the spell cards
     */
    private HBox createSpellList(Collection<Spell> spells) {
        return new HBox();
    }

}
