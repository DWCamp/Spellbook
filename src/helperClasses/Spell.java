package helperClasses;

public abstract class Spell {
	protected String name;
	
	public String getName()
	{
		return name;
	}
	
	public abstract String getCardText();
	public abstract int getLevel();
	public abstract String getSubtitle();
}
