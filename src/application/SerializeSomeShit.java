package application;

import model.Spell;
import model.SpellList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class SerializeSomeShit {

    public static void main(String[] args) throws IOException {
        FileReader file = new FileReader("Resources/FESpellList.txt");
        BufferedReader reader = new BufferedReader(file);

        String name;
        String[] classes;
        String school;
        String castingTime;
        String range;
        String components;
        String duration;
        StringBuilder description;

        int level = 0;
        String nextLine = reader.readLine();
        Set<String> classSet = new HashSet<>();
        Set<String> schoolSet = new HashSet<>();
        ArrayList<ArrayList<Spell>> spells = new ArrayList<>();
        spells.add(new ArrayList<>());

        while (nextLine != null) {
            // search for level tag
            if (nextLine.startsWith("<LEVEL>")) {
                level = Integer.parseInt(nextLine.substring(7));
                spells.add(new ArrayList<>());
                nextLine = reader.readLine();
            }

            // Get primary spell attributes
            name = nextLine.substring(6);
            classes = reader.readLine().substring(6).split(",");
            school = reader.readLine().substring(6);
            castingTime = reader.readLine().substring(6);
            range = reader.readLine().substring(6);
            components = reader.readLine().substring(6);
            duration = reader.readLine().substring(6);

            // Record classes and school
            classSet.addAll(Arrays.asList(classes));
            schoolSet.add(school);

            // Loop over rest of spell and add it to the description
            description = new StringBuilder();
            while (!(nextLine = reader.readLine()).equals("<END>")) {
                description.append(nextLine).append("\n");
            }

            Spell spell = new Spell(
                    name,
                    classes,
                    school,
                    level,
                    castingTime,
                    range,
                    components,
                    duration,
                    description.toString()
            );

            spells.get(level).add(spell);

            nextLine = reader.readLine();
        }

        ArrayList<String> classList = new ArrayList<>(classSet);
        Collections.sort(classList);
        ArrayList<String> schoolList = new ArrayList<>(schoolSet);
        Collections.sort(schoolList);

        SpellList spellList = new SpellList(spells, new ArrayList<>(), classList, schoolList);

        FileSystem.saveSpellList(spellList);

        reader.close();
        System.out.println("Done.");
    }

}
