package files;

import java.util.ArrayList;

public class PClass {
	private String name;
	private int hitDie;
	private int maxSpellLevel;
	private Feat[][] feats;
	private int subClassAt;
	private ArrayList<PClass> subClasses;
	
	/**
	 * Creates a class
	 * @param name The name of the class
	 * @param hitDie The class's hit die
	 * @param maxSpellLevel The maximum spell slot for the class
	 * @param feats The feats this class can learn organized 
	 * in a [X, Y] array, with each X index representing the 
	 * level at which the feats along the Y indices are gained 
	 * @param subClassAt The level at which the 
	 * @param subClasses The subclasses of this class
	 */
	public PClass(String name, 
			int hitDie, int maxSpellLevel, 
			Feat[][] feats, int subClassAt,
			ArrayList<PClass> subClasses)
	{
		this.name = name;
		this.hitDie = hitDie;
		this.maxSpellLevel = maxSpellLevel; 
		this.feats = feats;
		this.subClassAt = subClassAt;
		this.subClasses = subClasses;
	}
	
	/**
	 * Gets the name of the class
	 * @return The name of the class
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the number of sides the given classes's hit dice have
	 * @param className The name of the class
	 * @return class hit die
	 */
	public int getHitDie()
	{
		return hitDie;
	}
	
	/**
	 * Gets the average roll for a class's hit die. 
	 * This is used when adding HP for leveling up
	 * @param className Name of the class
	 * @return average hit die
	 */
	public int getAvgHitDie()
	{
		return hitDie / 2 + 1;
	}
	
	/**
	 * Gets the highest level of spell the class can cast
	 * @return Max spell level
	 */
	public int getMaxSpellLevel()
	{
		return maxSpellLevel;
	}
	
	/**
	 * Returns the highest spell slot for 
	 * the level at a passed level
	 * @param level The level
	 * @return The highest level spell 
	 * slot for that level
	 */
	public int getMaxSpellSlot(int level) {
		if (maxSpellLevel == 9) {
			return (level - 1) / 2 + 1;
		} else if (maxSpellLevel == 5) {
			if (level > 1) {
				return (level - 1) / 4 + 1;
			}
		} else if (maxSpellLevel == 4) {
			if (level > 2) {
				return (level + 5) / 6;
			}
		}
		return 0;
	}
	
	/**
	 * Returns an array of the class's feats
	 * @return 2-dimensional feat array
	 */
	public Feat[][] getFeats(){
		return feats;
	}
	
	/**
	 * Returns the level at which the class develops subclasses. These 
	 * "subclasses" can include archetypes, domains, or oaths,
	 * @return subclass level
	 */
	public int getSubClassAt()
	{
		return subClassAt;
	}
	
	/**
	 * Returns an arrayList of the class's subclasses.
	 * @return subclass list
	 */
	public ArrayList<PClass> getSubClasses()
	{
		return subClasses;
	}
	
	/**
	 * Adds a subclass to the class's list
	 * @param subClass
	 */
	public void addSubClass(PClass subClass)
	{
		subClasses.add(subClass);
	}
	
	/**
	 * Prints the class's feats
	 * @param feats
	 * @return String representation of the 
	 * class's feats
	 */
	public String printFeats()
	{
		String forReturn = "";
		boolean firstFeat = true;
		for (int i = 0; i < 20; i++) {
			Feat[] featArray = feats[i];
			firstFeat = true;
			forReturn += "[";
			for (Feat feat : featArray) {
				if (firstFeat) {
					firstFeat = false;
				} else {
					forReturn += "/";
				}
				forReturn += feat;
			}
			forReturn += "]";
		}
		return forReturn;
	}
	
	/**
	 * Prints the class's feats
	 * @param feats
	 * @return String representation of the 
	 * class's feats
	 */
	public String printSubClasses()
	{
		String forReturn = "[";
		boolean first = true;
		for(PClass pclass : subClasses)
		{
			if (first) {
				first = false;
			} else {
				forReturn += "/";
			}
			forReturn += pclass.getName();
		}
		return forReturn + ']';
	}
	
	/**
	 * Returns a string representation of the class
	 * @return {@code String} 
	 */
	public String toString()
	{
		return name;
	}
}
