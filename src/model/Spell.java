package model;

public class Spell implements Comparable<Spell>{
    private String name;
    private String[] classes;
    private String school;
    private int level;
    private String castingTime;
    private String range;
    private String components;
    private String duration;
    private String desc;

    /**
     * Constructor for a spell object
     * @param name The name of the spell
     * @param classes The classes the spell belongs to
     * @param school The wizard school of the spell
     * @param castingTime The casting time for the spell
     * @param range The range of the spell
     * @param components The components of the spell
     * @param duration The duration of the spell
     * @param desc The description of the spell
     */
    public Spell(String name, String[] classes, String school,
                 int level, String castingTime, String range,
                 String components, String duration, String desc)
    {
        this.name = name;
        this.classes = classes;
        this.school = school;
        this.level = level;
        this.castingTime = castingTime;
        this.range = range;
        this.components = components;
        this.duration = duration;
        this.desc = desc;
    }

    /**
     * Gets the name of the spell
     * @return {@code String}
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets a String array of all of the
     * classes that this spell belongs to
     * @return {@code String[]}
     */
    public String[] getClasses()
    {
        return classes;
    }

    public String getSchool()
    {
        return school;
    }

    /**
     * Returns the level of the spell as a string (e.g. '1st-level')
     * @return {@code String}
     */
    public String getLevelString()
    {
        if (level == 0) {
            return "Cantrip";
        } else if (level == 1) {
            return "1st-level";
        } else if (level == 2) {
            return "2nd-level";
        } else if (level == 3) {
            return "3rd-level";
        }
        return level + "th-level";
    }

    /**
     * Returns the level of the spell
     * @return {@code int} level
     */
    public int getLevel()
    {
        return level;
    }

    /**
     * Returns the Casting Time of the spell
     * @return {@code String}
     */
    public String getCastingTime()
    {
        return castingTime;
    }

    /**
     * Returns the range of the spell
     * @return {@code String}
     */
    public String getRange()
    {
        return range;
    }

    /**
     * Returns the components of the spell
     * @return {@code String}
     */
    public String getComponents()
    {
        return components;
    }

    /**
     * Returns the duration of the spell
     * @return {@code String}
     */
    public String getDuration()
    {
        return duration;
    }

    /**
     * Returns the description of the spell, including it's <br>
     * type, casting time, range, components, duration, <br>
     * and effect
     * @return {@code String}
     */
    public String getDescription()
    {
        return desc;
    }

    /**
     * Returns a string representation of the object
     * @return {@code String}
     */
    @Override
    public String toString()
    {
        return name;
    }

    /**
     * Determines if two spells are equal based on their names
     * @param spell Other spell
     * @return boolean Returns {@code true} if they are equal
     */
    public boolean equals(Spell spell)
    {
        return spell.getName().equals(name);
    }

    /**
     * Provides a quick description of the spell, including its level and school
     * @return The level as a String
     */
    public String getSubtitle()
    {
        if (level == 0) {
            return school + " cantrip";
        }
        return getLevelString() + " " + school;
    }

    @Override
    public int compareTo(Spell other) {
        return name.toLowerCase().compareTo(other.getName().toLowerCase());
    }


    public String getCardText() {
        return "Casting Time: " + castingTime
                + "\n\nRange: " + range
                + "\n\nComponents: " + components
                + "\n\nDuration: " + duration;
    }


    public String getPopUpText()
    {
        return name + "\n" + getLevelString() + "\n\nCasting time: " + castingTime
                + "\n\nRange: " + range + "\n\nComponents: " + components
                + "\n\nDuration: " + duration + "\n\n" + desc;
    }


    public String getDetails() {
        return getLevelString() + "\n\nCasting time: " + castingTime
                + "\n\nRange: " + range + "\n\nComponents: " + components
                + "\n\nDuration: " + duration + "\n\n" + desc;
    }

    /**
     * Checks if spells are equal
     * Two spells are considered equal if they possess the same name
     *
     * @param other The other object being compared
     * @return `true` if the spells are equal
     */
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (other instanceof Spell) {
            Spell otherSpell = (Spell) other;
            return otherSpell.getName().equals(name);
        }

        return false;
    }
}