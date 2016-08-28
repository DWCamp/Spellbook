package files;

import java.util.ArrayList;

public class SubClass extends PClass {

	private PClass parentClass;
	
	public SubClass(PClass parentClass, String name, int maxSpellLevel, Feat[][] feats) {
		super(name, parentClass.getHitDie(), maxSpellLevel, feats, 100, null);
		this.parentClass = parentClass;
	}
	
	/**
	 * Inherited class to return subclasses
	 * <br>DO NOT USE
	 */
	@Override
	public ArrayList<PClass> getSubClasses()
	{
		return super.getSubClasses();
	}
	
	/**
	 * Gets the parent class
	 * @return
	 */
	public PClass getParent()
	{
		return parentClass;
	}

}
