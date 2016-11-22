package helperClasses;

public class Feat {
	private String name;
	private String description;

	/**
	 * Public constructor for the feat
	 * @param name The feat's name
	 * @param description The feat's description
	 */
	public Feat(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	/**
	 * Returns the name of the feat
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the description of the feat
	 * @return description
	 */
	public String getDescription()
	{
		return description;
	}
}
