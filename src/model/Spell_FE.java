package model;

public class Spell_FE extends Spell implements Comparable<Spell_FE>{
	private String name;
	private String[] classes;
	private String school;
	private String type;
	private int level;
	private String castingTime;
	private String range;
	private String components;
	private String duration;
	private String desc;
	
	/**
	 * Blank constructor for a spell object
	 */
	public Spell_FE()
	{
		name = "";
		classes = new String[0];
		type = "";
		school = "";
		level = 0;
		castingTime = "";
		range = "";
		components = "";
		duration = "";
		desc  = "";
	}

	/**
	 * Constructor for a spell object
	 * @param name The name of the spell
	 * @param classes The classes that know the spell
	 * @param school The wizard school the spell belongs to
	 * @param level The spell slot level
	 * @param castingTime The casting time for the spell
	 * @param range The range of the spell
	 * @param components The components of the spell
	 * @param duration The duration of the spell
	 * @param desc The description of the spell
	 */
	public Spell_FE(String name, String[] classes, String school,
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
		
		switch (level){
		case 0:
			type = school + " cantrip";
			break;
		case 1: 
			type = "1st-level " + school;
			break;
		case 2: 
			type = "2nd-level " + school;
		case 3:
			type = "3rd-level " + school;
		default:
			type = level + "th-level " + school;
		}
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
	 * Returns the type of the spell
	 * @return {@code String}
	 */
	public String getType()
	{
		return type;
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
	public boolean equals(Spell_FE spell)
	{
		return spell.getName().equals(name);
	}

	@Override
	public String getSubtitle()
	{
		return type;
	}
	
	@Override
	public int compareTo(Spell_FE other) {
		return name.toLowerCase().compareTo(other.getName().toLowerCase());
	}

	@Override
	public String getCardText() {
		return "Casting Time: " + castingTime 
		+ "\n\nRange: " + range
		+ "\n\nComponents: " + components
		+ "\n\nDuration: " + duration;
	}
	
	@Override
	public String getPopUpText()
	{
		return name + "\n" + type + "\n\nCasting time: " + castingTime
			+ "\n\nRange: " + range + "\n\nComponents: " + components
			+ "\n\nDuration: " + duration + "\n\n" + desc;
	}

	@Override
	public String getDetails() {
		return type + "\n\nCasting time: " + castingTime
				+ "\n\nRange: " + range + "\n\nComponents: " + components
				+ "\n\nDuration: " + duration + "\n\n" + desc;
	}
}
