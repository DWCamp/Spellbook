package model;

import model.FileSystem;

import java.io.IOException;
import java.util.ArrayList;

/**
 * It's 1:10 AM and I would love to be doing anything else right now
 *
 * @author Wouldn't you like to know
 */
public class SerializeEverythingAgain {

    /**
     * Fuck you
     * @param args eat me
     */
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Spell_FE>> FE_Spells = FileSystem.loadFESpellList();
        ArrayList<Spell_FE> FE_customSpells = FileSystem.loadCustomFESpellList();
    }
}


