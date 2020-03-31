package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class for storing a user's information 
 * (e.g. spells prepared/learned, etc.)
 * @author Daniel Campman
 */
public class CharacterInfo implements Serializable {
	
	private ArrayList<Spell> learnedSpells;
	private ArrayList<Spell> preparedSpells;

    /**
     * Creates the model object which stores character data
	 *
	 * @param learnedSpells The spells the character has learned
	 * @param preparedSpells The spells the character currently has prepared
	 *
     * @throws IOException If there was an error reading the file
     */
	public CharacterInfo(ArrayList<Spell> learnedSpells, ArrayList<Spell> preparedSpells) {
	    this.learnedSpells = learnedSpells;
		this.preparedSpells = preparedSpells;
    }

    /**
     * Sets the list of prepared spells
     * @param spells The prepared spells
     */
    public void setPreparedSpells(ArrayList<Spell> spells) {
		preparedSpells = spells;
    }

    /**
     * Adds a spell to the player's known spells list
     * @param spell The spell being prepared
     */
    public void prepareSpell(Spell spell) {
        if (!preparedSpells.contains(spell)) {
			preparedSpells.add(spell);
        }
    }

	/**
	 * Returns the spells prepared by the player
	 * @return {@code ArrayList<Spell>} The prepared spells
	 */
	public ArrayList<Spell> getPreparedSpells() {
		return preparedSpells;
	}
	
	/**
	 * Returns the spells prepared by the player of a specific level
	 * @return {@code ArrayList<Spell>} The prepared spells
	 */
	public ArrayList<Spell> getPreparedSpellsOfLevel(int level) {
		return preparedSpells;
	}

    /**
     * Returns the spells known by the player
     * @return {@code ArrayList<Spell>} The known spells
     */
    public ArrayList<Spell> getLearnedSpells() {
        return learnedSpells;
    }

	/**
	 * Removes a spell to the player's known spells list
	 * @param spell The spell being unprepared
	 */
	public void unprepareSpell(Spell spell) {
		preparedSpells.remove(spell);
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spell The spell being learned
	 */
	public void learnSpell(Spell spell) {
		if (!learnedSpells.contains(spell)) {
			learnedSpells.add(spell);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spell The spell being unlearned
	 */
	public void unlearnSpell(Spell spell) {
		learnedSpells.remove(spell);
	}
	
	/**
	 * Empties the list of prepared spells
	 */
	public void clearPreparedSpells() {
		preparedSpells.clear();
	}
}
