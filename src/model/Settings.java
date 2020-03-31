package model;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * Stores the current settings for the application
 * All values should be watched through PropertyChangeListeners
 *
 * @author Daniel Campman
 */
public class Settings implements Serializable {

    /**
     * Defines FrameCentering behavior
     * These behaviors are as follows:
     * - NO_CENTERING: Never center the window automatically
     * - CENTER_ON_VISIBLE: Center the window whenever it is unhidden
     * - CENTER_ON_REQUEST: Center the window whenever another window summons it
     */
    public enum CenteringBehavior {
        NO_CENTERING, CENTER_ON_VISIBLE, CENTER_ON_REQUEST
    }

    /**
     * The PropertyChangeSupport object
     */
    private PropertyChangeSupport support;


    // The private fields

    /**
     * The mainwindow's behavior of centering (see getter)
     */
    private CenteringBehavior centeringBehavior;
    /**
     * User determined window scaling multiplier
     */
    private double scalingMultiplier;
    /**
     * Whether spell browser panels should be colored to reflect spell level
     */
    private boolean browserUseColor;

    /**
     * Value-accepting constructor for the Settings object
     * @param centeringBehavior The window centering behavior
     * @param scalingMultiplier The window size scaling factor
     * @param browserUseColor Whether the SpellBrowser should use colors to distinguish spell level
     */
    public Settings(CenteringBehavior centeringBehavior,
                    Double scalingMultiplier,
                    boolean browserUseColor) {
        this.centeringBehavior = centeringBehavior;
        this.scalingMultiplier = scalingMultiplier;
        this.browserUseColor = browserUseColor;
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a PropertyChangeListener to the Settings class
     * This allows other classes to listen for changes to different
     * settings and react to their new values
     * @param pcl A PropertyChangeListener
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Removes a PropertyChangeListener from the application
     * @param listener The PropertyChangeListener to remove from the list
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Sets the window centering behavior for the application
     * See Settings.CenteringBehavior for the description of each option
     * @param behavior The CenteringBehavior enum value used to set the window centering
     */
    public void setCenteringBehavior(CenteringBehavior behavior) {
        System.out.println("Spellbook - setCenteringBehavior");
        CenteringBehavior oldBehavior = centeringBehavior;
        centeringBehavior = behavior;
        support.firePropertyChange("centeringBehavior", centeringBehavior, oldBehavior);
    }

    /**
     * Returns the window centering behavior for the application
     * @return The current GameVersion
     */
    public CenteringBehavior getCenteringBehavior() {
        return centeringBehavior;
    }

    /**
     * Sets the window size scaling multiplier
     * 1.0 is the default scaling. 2.0 would make the window twice as large
     * Values are capped at a minimum of 0.1
     * @param multiplier The scaling multiplier value
     */
    public void setScalingMultiplier(Double multiplier) {
        System.out.println("Spellbook - setScalingMultiplier");
        // Floor the multiplier at 0.1
        if (multiplier < 0.1) {
            multiplier = 0.1;
        }
        Double oldMultiplier = scalingMultiplier;
        scalingMultiplier = multiplier;
        support.firePropertyChange("scalingMultiplier", scalingMultiplier, oldMultiplier);
    }

    /**
     * Returns the window size scaling multiplier
     * @return The current GameVersion
     */
    public Double getScalingMultiplier() {
        return scalingMultiplier;
    }

    /**
     * Gets the resolution scaling factor for each window
     * This value is computed using the resolution of the monitor and
     * scalingFactor (the window size scaling factor)
     *
     * @return The resolution scaling as a multiplier from the default values
     */
    public double getResolutionScaling() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // If the screen is wider than normal, scale to the height
        if (screenSize.getWidth() * 9 > screenSize.getHeight() * 16) {
            return (screenSize.getHeight() / 1080.0) * scalingMultiplier;
        }
        // Otherwise, scale to the width
        return (screenSize.getWidth() / 1920.0) * scalingMultiplier;
    }

    /**
     * Whether the SpellBrowser should use coloration in search results to display the
     * level of each spell
     * @return Returns `true` if the SpellBrowser should use colors
     */
    public boolean useColorInBrowser() {
        return browserUseColor;
    }

    /**
     * Set the coloration behavior of SpellBrowser
     * If `true`, search results in SpellBrowser will be set to different colors to denote their level
     * @param useColor Returns `true` if the SpellBrowser should use colors
     */
    public void setBrowserUseColor(boolean useColor) {
        System.out.println("Spellbook - setBrowserUseColor");
        boolean oldValue = browserUseColor;
        browserUseColor = useColor;
        support.firePropertyChange("browserUseColor", browserUseColor, oldValue);
    }
}
