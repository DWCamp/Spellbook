package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Defines a collection of spells
 *
 * @author Daniel Campman
 */
public class SpellList implements Serializable {

    public ArrayList<Spell>[] spells;
    public ArrayList<Spell>[] customSpells;
    public ArrayList<String> classes;
    public ArrayList<String> schools;

    /**
     * Basic constructor for SpellList
     * Takes a list of spells
     * @param spells The spells stored in the list
     */
    public SpellList(ArrayList<Spell>[] spells,
                     ArrayList<Spell>[] customSpells,
                     ArrayList<String> classes,
                     ArrayList<String> schools) {
        this.spells = spells;
        this.customSpells = customSpells;
        this.classes = classes;
        this.schools = schools;
    }

}
