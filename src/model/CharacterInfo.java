package model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import helperClasses.SortedStringList;

/**
 * The class for storing a user's information 
 * (e.g. spells prepared/learned, etc.)
 * @author Daniel Campman
 */
public class CharacterInfo implements Serializable {
	
	private ArrayList<Spell> FEspellsLearned;
	private ArrayList<Spell> FEspellsPrepared;
	
	private ArrayList<Spell> PFspellsLearned;
	private ArrayList<Spell> PFspellsPrepared;

    /**
     * Creates the model object which stores character data
	 *
	 * @param FEspellsLearned The 5e spells the character has learned
	 * @param FEspellsPrepared The 5e spells the character currently has prepared
	 * @param PFspellsLearned The Pathfinder spells the character has learned
	 * @param PFspellsPrepared The Pathfinder spells the character currently has prepared
	 *
     * @throws IOException If there was an error reading the file
     */
	public CharacterInfo(ArrayList<Spell> FEspellsLearned,
						 ArrayList<Spell> FEspellsPrepared,
						 ArrayList<Spell> PFspellsLearned,
						 ArrayList<Spell> PFspellsPrepared) throws IOException {
	    this.FEspellsLearned = FEspellsLearned;
		this.FEspellsPrepared = FEspellsPrepared;
		this.PFspellsLearned = PFspellsLearned;
		this.PFspellsPrepared = PFspellsPrepared;
    }

    /**
     * Sets the list of prepared Fifth Edition spells
     * @param feSpells The prepared spells
     */
    public void setPreparedFESpells(ArrayList<Spell> feSpells) {
        FEspellsPrepared = feSpells;
    }

    /**
     * Adds a spell to the player's known spells list
     * @param spell The spell being prepared
     */
    public void prepareFESpell(Spell spell) {
        if (!FEspellsPrepared.contains(spell)) {
            FEspellsPrepared.add(spell);
        }
    }

	/**
	 * Returns the spells prepared by the player
	 * @return {@code ArrayList<Spell>} The prepared spells
	 */
	public ArrayList<Spell> getPreparedFESpells() {
		return FEspellsPrepared;
	}
	
	/**
	 * Returns the spells prepared by the player of a specific level
	 * @return {@code ArrayList<Spell>} The prepared spells
	 */
	public ArrayList<Spell> getPreparedFESpellsOfLevel(int level) {
		return FEspellsPrepared;
	}

    /**
     * Returns the spells known by the player
     * @return {@code ArrayList<Spell>} The known spells
     */
    public ArrayList<Spell> getLearnedFESpells() {
        return FEspellsLearned;
    }

	/**
	 * Removes a spell to the player's known spells list
	 * @param spell The spell being unprepared
	 */
	public void unprepareFESpell(Spell spell) {
		FEspellsPrepared.remove(spell);
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spell The spell being learned
	 */
	public void learnFESpell(Spell spell) {
		if (!FEspellsLearned.contains(spell)) {
			FEspellsLearned.add(spell);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spell The spell being unlearned
	 */
	public void unlearnFESpell(Spell spell) {
		FEspellsLearned.remove(spell);
	}
	
	/**
	 * Empties the list of prepared spells
	 */
	public void clearFEPreparedSpells() {
		FEspellsPrepared.clear();
	}
	
	/**
	 * Returns the spells prepared by the player
	 * @return {@code ArrayList<Spell>} The prepared spells
	 */
	public ArrayList<Spell> getPreparedPFSpells() {
		return PFspellsPrepared;
	}
	
	/**
	 * Returns the spells prepared by the player of a specific level
	 * @return {@code ArrayList<Spell>} The prepared spells
	 */
	public ArrayList<Spell> getPreparedPFSpellsOfLevel(int level) {
		return PFspellsPrepared;
	}
	
	/**
	 * Returns the spells known by the player
	 * @return {@code ArrayList<Spell>} The known spells
	 */
	public ArrayList<Spell> getLearnedPFSpells() {
		return PFspellsLearned;
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spell The spell being prepared
	 */
	public void preparePFSpell(Spell spell) {
		if (!PFspellsPrepared.contains(spell))
		{
			PFspellsPrepared.add(spell);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spell The spell being unprepared
	 */
	public void unpreparePFSpell(Spell spell) {
		PFspellsPrepared.remove(spell);
	}
	
	/**
	 * Adds a spell to the player's known spells list
	 * @param spell The spell being learned
	 */
	public void learnPFSpell(Spell spell) {
		if (!PFspellsLearned.contains(spell)) {
			PFspellsLearned.add(spell);
		}
	}
	
	/**
	 * Removes a spell to the player's known spells list
	 * @param spell The spell being unlearned
	 */
	public void unlearnPFSpell(Spell spell) {
		PFspellsLearned.remove(spell);
	}
	
	/**
	 * Empties the list of prepared spells
	 */
	public void clearPFPreparedSpells() {
		PFspellsPrepared.clear();
	}
}
