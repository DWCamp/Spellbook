package javafx;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Settings;

public class MainWindow extends Stage {

    private static final double BASE_HEIGHT = 400;  // The default height of the window
    private static final double BASE_WIDTH = 640;   // The default width of the window

    private BorderPane mainPane;

    /**
     * Creates the main Window of the Spellbook Application
     */
    public MainWindow() {
        super();

        // Window attributes
        setTitle("Spellbook");

        // Window size
        Double resolutionScaling = Spellbook.settings.getResolutionScaling();
        setResizable(true);
        mainPane = new BorderPane();
        Scene scene = new Scene(mainPane, resolutionScaling * BASE_WIDTH, resolutionScaling * BASE_HEIGHT);

        // Show window
        setScene(scene);
    }

}
