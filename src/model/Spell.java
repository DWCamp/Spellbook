package model;

import java.io.Serializable;

/**
 * Represents a generic spell of any RPG game edition
 *
 * @author Daniel Campman
 */
public abstract class Spell implements Serializable {
	protected String name;

	/**
	 * Gets the name of the spell
	 * @return The name of the spell
	 */
	public String getName() {
		return name;
	}
	
	public abstract String getCardText();
	public abstract int getLevel();
	public abstract String getSubtitle();
	public abstract String getPopUpText();
	public abstract String getDescription();
	public abstract String getDetails();

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
