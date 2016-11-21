package files;

public class Spell {
	private String name;
	private String[] classes;
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
	public Spell()
	{
		name = "";
		classes = new String[0];
		type = "";
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
	 * @param classes The classes the spell belongs to
	 * @param type The type of spell
	 * @param castingTime The casting time for the spell
	 * @param range The range of the spell
	 * @param components The components of the spell
	 * @param duration The duration of the spell
	 * @param desc The description of the spell
	 */
	public Spell(String name, String[] classes, String type, 
			int level, String castingTime, String range, 
			String components, String duration, String desc)
	{
		this.name = name;
		this.classes = classes;
		this.type = type;
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
		return name + "\n" + type + "\n\nCasting time: " + castingTime
				+ "\n\nRange: " + range + "\n\nComponents: " + components
				+ "\n\nDuration: " + duration + "\n\n" + desc;
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
}
