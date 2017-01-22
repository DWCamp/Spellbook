package helperClasses;

import java.util.HashMap;

public class SpellPF extends Spell implements Comparable<SpellPF>{
	String name;
	String school;
	String[] subschools;
	String[] descriptors;
	HashMap<String, Integer> classes;
	String castingTime;
	String components;
	boolean costlyComponents;
	String range;
	String area;
	String effect;
	String target;
	String duration;
	boolean dismissible;
	boolean shapable;
	String savingThrow;
	String spellResistance;
	String description;
	String source;
	String deity;
	int SLA_Level;
	HashMap<String, Integer> domains;
	String shortDescription;
	int materialCost;
	HashMap<String, Integer> bloodline;
	HashMap<String, Integer> patrons;
	String mythicText;
	String augmented;
	String hauntStatistics;
	boolean ruse;
	boolean draconic;
	boolean meditative;

	public SpellPF() {
		name = "NULL";
		school = "NULL";
		subschools = new String[0];
		descriptors = new String[0];
		classes = new HashMap<String, Integer>();
		castingTime = "NULL";
		components = "NULL";
		costlyComponents = false;
		range = "NULL";
		area = "NULL";
		effect = "NULL";
		target = "NULL";
		duration = "NULL";
		dismissible = false;
		shapable = false;
		savingThrow = "none";
		spellResistance = "NULL";
		description = "NULL";
		source = "NULL";
		deity = "NULL";
		SLA_Level = 0;
		domains = new HashMap<String, Integer>();
		shortDescription = "NULL";
		materialCost = 0;
		bloodline = new HashMap<String, Integer>();
		patrons = new HashMap<String, Integer>();
		mythicText = "NULL";
		augmented = "NULL";
		hauntStatistics = "NULL";
		ruse = false;
		draconic = false;
		meditative = false;
	}
	/**
	 * A Pathfinder Spell <br><br>
	 * Good god. Look at all of those parameters
	 * @param name The name of the spell
	 * @param school The spell's school of magic
	 * @param subschools The subschools
	 * @param descriptors The spell's descriptors
	 * @param classes The classes that can use this spell
	 * and the level at which they gain the ability
	 * @param castingTime The time it takes to cast the spell
	 * @param components The components required to cast
	 * @param materialCosts The gp value of the materials
	 * @param range The range of the spell
	 * @param area The area affected
	 * @param effect Any physical result of the spell
	 * @param target The target(s) of the spell
	 * @param duration How long the spell lasts
	 * @param dismissible Whether the spell can be prematurely dismissed
	 * @param shapable Whether the spell is shapable (I don't really know)
	 * @param savingThrow What (if any) saving throw this spell has
	 * @param spellResistance Spell resistances
	 * @param shortDescription An abridged description of the spell's effect
	 * @param description A full description of the spell's effect
	 * @param source The official text that introduced this spell
	 * @param deity The deity related to this spell
	 * @param SLA_Level I have no idea what this is
	 * @param domains The domains of the spell
	 * @param bloodline Bloodline. Not sure
	 * @param patrons Don't know about this either.
	 * @param mythicText The text for a mythic version of the spell
	 * @param augmented Augmented text
	 * @param hauntStatistics Haunt details
	 * @param ruse Whether the spell is a ruse
	 * @param draconic Whether the spell is draconic
	 * @param meditative Whether the spell is meditative
	 */
	public SpellPF(String name, String school, String[] subschools, String[] descriptors, HashMap<String,Integer> classes, 
			String castingTime, String components, int materialCosts, String range, String area, String effect, String target,
			String duration, boolean dismissible, boolean shapable, String savingThrow, String spellResistance, 
			String shortDescription, String description, String source, String deity, int SLA_Level, 
			HashMap<String,Integer> domains, HashMap<String, Integer> bloodline, HashMap<String, Integer> patrons, 
			String mythicText, String augmented, String hauntStatistics, boolean ruse, boolean draconic, 
			boolean meditative) {
		this.name = name;
		this.school = school;
		this.subschools = subschools;
		this.descriptors = descriptors;
		this.classes = classes;
		this.castingTime = castingTime;
		this.components = components;
		this.materialCost = materialCosts;
		costlyComponents = materialCosts > 0;
		this.range = range;
		this.area = area;
		this.effect = effect;
		this.target = target;
		this.duration = duration;
		this.dismissible = dismissible;
		this.shapable = shapable;
		this.savingThrow = savingThrow;
		this.spellResistance = spellResistance;
		this.shortDescription = shortDescription;
		this.description = description;
		this.source = source;
		this.deity = deity;
		this.SLA_Level = SLA_Level;
		this.domains = domains;
		this.bloodline = bloodline;
		this.patrons = patrons;
		this.mythicText = mythicText;
		this.augmented = augmented;
		this.hauntStatistics = hauntStatistics;
		this.ruse = ruse;
		this.draconic = draconic;
		this.meditative = meditative;
	}

	public String getName() {
		return name;
	}
	
	public String getSchool() {
		return school;
	}
	
	public String[] getSubschools()
	{
		return subschools;
	}
	
	public String printSubschools() {
		if (subschools.length == 0) {
			return "";
		}
		String forReturn = "";
		for (String str : subschools) {
			forReturn += str + ", ";
		}
		return forReturn.substring(0, forReturn.length() - 2);
	}
	
	public String[] getDescriptors()
	{
		return descriptors;
	}
	
	public String printDescriptors() {
		if (descriptors.length == 0) {
			return "";
		}
		String forReturn = "";
		for (String str : descriptors) {
			forReturn += str + ", ";
		}
		return forReturn.substring(0, forReturn.length() - 2);
	}

	public HashMap<String, Integer> getClasses()
	{
		return classes;
	}
	
	public String printClasses() {
		String forReturn = "";
		for (String str : classes.keySet().toArray(new String[0])) {
			forReturn += str + " " + classes.get(str) + ", ";
		}
		return forReturn.substring(0, forReturn.length() - 2);
	}
	
	public String getCastingTime()
	{
		return castingTime;
	}
	
	public String getComponents()
	{
		return components;
	}

	public boolean hasCostlyComponents()
	{
		return costlyComponents;
	}
	
	public int getMaterialsCost()
	{
		return materialCost;
	}
	
	public String getRange()
	{
		return range;
	}
	
	public String getArea()
	{
		return area;
	}

	public String getEffect()
	{
		return effect;
	}
	
	public String getTarget()
	{
		return target;
	}
	
	public String getDuration()
	{
		return duration;
	}
	
	public boolean isDismissible()
	{
		return dismissible;
	}
	
	public boolean isShapable()
	{
		return shapable;
	}

	public String getSpellResistance()
	{
		return spellResistance;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getSource()
	{
		return source;
	}

	public String getDeity()
	{
		return deity;
	}
	
	/**
	 * Returns the SLA Level of the spell
	 */
	@Override
	public int getLevel()
	{
		return SLA_Level;
	}

	public HashMap<String, Integer> getDomains()
	{
		return domains;
	}
	
	public String printDomains() {
		if (domains.isEmpty()) {
			return "";
		}
		String forReturn = "";
		for (String str : domains.keySet().toArray(new String[0])) {
			forReturn += str + " " + domains.get(str) + ", ";
		}
		return forReturn.substring(0, forReturn.length() - 2);
	}

	public String getShortDescription()
	{
		return shortDescription;
	}
	
	public HashMap<String, Integer> getBloodline()
	{
		return bloodline;
	}
	
	public String printBloodline() {
		if (bloodline.isEmpty()) {
			return "";
		}

		String forReturn = "";
		for (String str : bloodline.keySet().toArray(new String[0])) {
			forReturn += str + " " + bloodline.get(str) + ", ";
		}
		return forReturn.substring(0, forReturn.length() - 2);
	}

	public HashMap<String, Integer> getPatrons()
	{
		return patrons;
	}
	
	public String printPatrons() {
		if (patrons.isEmpty()) {
			return "";
		}

		String forReturn = "";
		for (String str : patrons.keySet().toArray(new String[0])) {
			forReturn += str + " " + patrons.get(str) + ", ";
		}
		return forReturn.substring(0, forReturn.length() - 2);
	}

	public String getMythicText()
	{
		return mythicText;
	}
	
	public String getAugmented()
	{
		return augmented;
	}
	
	public boolean isMythic()
	{
		return !mythicText.equals("NULL");
	}
	
	public String getHauntStatistics()
	{
		return hauntStatistics;
	}
	
	public boolean isRuse()
	{
		return ruse;
	}
	
	public boolean isDraconic()
	{
		return draconic;
	}
	
	public boolean isMeditative()
	{
		return meditative;
	}

	/**
	 * Returns a string representation of the spell<br>
	 * <strong>name</strong> : <strong>short description</strong>
	 */
	public String toString() {
		return name + ": " + shortDescription;
	}
	
	/**
	 * Determines if two spells are equal based on their names
	 * @param spell Other spell
	 * @return boolean Returns {@code true} if they are equal
	 */
	public boolean equals(SpellFE spell)
	{
		return spell.getName().equals(name);
	}
	
	/**
	 * Returns the type of spell this is
	 */
	@Override
	public String getSubtitle()
	{
		String subList = "";
		if (subschools.length > 0)
		{
			for (String subschool : subschools)
			{
				subList += ", " + subschool;
			}
			subList = " (" + subList.substring(2) + ")";
		}
		return school + subList;
	}
	
	/**
	 * Compares the names of the spells for alphabetical sorting
	 */
	@Override
	public int compareTo(SpellPF other) {
		return name.toLowerCase().compareTo(other.getName().toLowerCase());
	}
	
	/**
	 * Returns a simplified text readout for use on SpellCards
	 * @return {@code String}
	 */
	@Override
	public String getCardText() {
		StringBuffer forReturn = new StringBuffer();
		if (!shortDescription.equals("NULL")) {
			forReturn.append(shortDescription + "\n\n");
		}
		if (!components.equals("NULL"))
		{
			forReturn.append("Components: " + components + "\n");
		}
		if (!castingTime.equals("NULL"))
		{
			forReturn.append("Casting Time: " + castingTime + "\n");
		}
		if (!duration.equals("NULL"))
		{
			forReturn.append("Duration: " + duration + "\n");
		}
		if (!range.equals("NULL"))
		{
			forReturn.append("Range: " + range + "\n");
		}
		if (!area.equals("NULL"))
		{
			forReturn.append("Area: " + area + "\n");
		}
		return forReturn.toString();
	}
	
	/**
	 * Returns a descriptive text readout for use with WordWrapPopUp
	 * @return {@code String}
	 */
	@Override
	public String getPopUpText() {
		
		return name + "\n" + school + "\n\nCasting time: " + castingTime
				+ "\n\nRange: " + range + "\n\nComponents: " + components
				+ "\n\nDuration: " + duration + "\n\n" + description + "\n\n\nSource: " + source;
	}
	
	/**
	 * Returns the full details of the spell for the Details 
	 * window on a spell card
	 * @return {@code String} full text
	 */
	@Override
	public String getDetails() {
		StringBuffer forReturn = new StringBuffer();
		if (!components.equals("NULL"))
		{
			forReturn.append("Components: " + components + "\n");
		}
		if (costlyComponents)
		{
			forReturn.append("Component Cost: " + materialCost);
		}
		if (!castingTime.equals("NULL"))
		{
			forReturn.append("Casting Time: " + castingTime + "\n");
		}
		if (!duration.equals("NULL"))
		{
			forReturn.append("Duration: " + duration + "\n");
		}
		if (!range.equals("NULL"))
		{
			forReturn.append("Range: " + range + "\n");
		}
		if (!area.equals("NULL"))
		{
			forReturn.append("Area: " + area + "\n");
		}
		
		
		if(!mythicText.equals("NULL"))
		{
			forReturn.append("\nMythic " + name +": " + mythicText);
		}
		return forReturn.toString();
		
		/**	
		school = "NULL";
		subschools = new String[0];
		descriptors = new String[0];
		effect = "NULL";
		target = "NULL";
		duration = "NULL";
		dismissible = false;
		shapable = false;
		savingThrow = "none";
		spellResistance = "NULL";
		description = "NULL";
		source = "NULL";
		deity = "NULL";
		SLA_Level = 0;
		domains = new HashMap<String, Integer>();
		shortDescription = "NULL";
		materialCosts = 0;
		bloodline = new HashMap<String, Integer>();
		patrons = new HashMap<String, Integer>();
		mythicText = "NULL";
		augmented = "NULL";
		hauntStatistics = "NULL";
		ruse = false;
		draconic = false;
		meditative = false;
		*/
	}
}
