package javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import model.CharacterInfo;
import model.FileSystem;
import model.Settings;
import model.SpellList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;


/**
 * The core element of the application. This class stores all of the state
 * objects that the operation relies on as well as the references to each
 * of the windows that the application uses.
 *
 * @author Daniel Campman
 */
public class Spellbook extends Application implements PropertyChangeListener {

    // Models
    public static Settings settings;
    public static SpellList spellList;
    public static CharacterInfo characterInfo;

    // Views
    public static MainWindow mainWindow;

    /**
     * Initializes the program.
     * Called at the start of launch before start()
     */
    @Override
    public void init() {
        System.out.println("Spellbook - init");
        try {
            // Load settings
            settings = FileSystem.loadSettings();
            settings.addPropertyChangeListener(this);

            // Load Spells
            spellList = FileSystem.loadSpellList();

            // Load classes
            characterInfo = FileSystem.loadCharacterInfo();

        } catch (Exception e) {
            System.out.println("Well shit.");
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Starts the application
     * @param primaryStage The stage of the main application. This is provided
     *                     by the code of JavaFX but is ignored, as the Spellbook
     *                     class does not produce any UI
     */
    @Override
    public void start(Stage primaryStage) {
        System.out.println("Spellbook - start");
        mainWindow = new MainWindow();
        mainWindow.show();
    }

    /**
     * The main method of the program. Called at start to launch the application
     *
     * @param args The program arguments. No arguments are supported at this time
     */
    public static void main(String[] args) {
        System.out.println("Spellbook - main");
        Application.launch(args);
        System.out.println("Done.");
    }

    /**
     * Handle a property change on one of the objects being viewed
     *
     * @param evt The PropertyChangeEvent that fired. Contains the source of
     *            the event and the old/new values
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Spellbook - propertyChange");
        switch (evt.getPropertyName()) {
            default:
                System.out.println("\nPROPERTY CHANGE\n-----------------\n");
                System.out.println("Source Class: " + evt.getSource().getClass().getName());
                System.out.println("Property change: " + evt.getPropertyName());
                System.out.println("Old Value: " + evt.getOldValue());
                System.out.println("New Value: " + evt.getNewValue());
                System.out.println();
        }
    }

}
