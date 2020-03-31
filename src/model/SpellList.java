package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Defines a collection of spells
 *
 * @author Daniel Campman
 */
public class SpellList implements Serializable {

    private static final long serialVersionUID = 1441098673L;

    public ArrayList<ArrayList<Spell>> spells;
    public ArrayList<ArrayList<Spell>> customSpells;
    public ArrayList<String> classes;
    public ArrayList<String> schools;

    /**
     * Basic constructor for SpellList
     * Takes a list of spells
     * @param spells The spells stored in the list
     */
    public SpellList(ArrayList<ArrayList<Spell>> spells,
                     ArrayList<ArrayList<Spell>> customSpells,
                     ArrayList<String> classes,
                     ArrayList<String> schools) {
        this.spells = spells;
        this.customSpells = customSpells;
        this.classes = classes;
        this.schools = schools;
    }

}
