package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperClasses.Feat;
import helperClasses.PClass;
import helperClasses.SortedStringList;

/**
 * A class containing information on all classes recognized 
 * by the software, pulled on load from a .txt resource
 * @author Daniel Campman
 * @version September 3, 2016
 */
public class Class_List {
	
	/**
	 * Contains a list of each class's information <br>
	 * Index 0 - Name (yes, that's just the key again, 
	 * but it was easier to implement this way. Bite me.) <br>
	 * Index 1 - hit die
	 * Index 2 - Max spell slot level
	 */
	private static Map<String, PClass> classes = new HashMap<String, PClass>();
	
	/**
	 * Loads the data in from the .txt file
	 */
	public static void load()
	{
		try {
			ArrayList<String[]> classInfo = FileSystem.loadClassList();
			for (int i = 0; i < classInfo.size(); i++) {
				PClass newClass = new PClass(classInfo.get(i)[0], //Name
						Integer.parseInt(classInfo.get(i)[1]),  //Hit die
						Integer.parseInt(classInfo.get(i)[2]),  //MaxSpellLevel
						new Feat[20][1], 						//Feat list
						Integer.parseInt(classInfo.get(i)[2]), 	//subClassAt
						new ArrayList<PClass>());	   			//subClasses	
				classes.put(classInfo.get(i)[0], newClass);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a string array of all available classes <br>
	 * This array is alphabetized
	 * @return {@code String[]}
	 */
	public static String[] getClassNames()
	{
		//By placing the key set into a sorted list and then exporting 
		//it to an array, the key set is alphabetized. 
		SortedStringList classList = new SortedStringList
				(classes.keySet().toArray(new String[0]));
		return classList.toArray();
	}
	
	/**
	 * Returns an alphabetically sorted ArrayList of classes
	 * @return class arrayList
	 */
	public static ArrayList<PClass> getClasses()
	{
		//The SortedStringList alphabetizes the names
		SortedStringList classNames = new SortedStringList(
				classes.keySet().toArray(new String[0]));
		ArrayList<PClass> classList = new ArrayList<PClass>();
		
		//Grabs the class objects for each String name
		for (String name : classNames.toArrayList()) {
			classList.add(classes.get(name));
		}
		return classList;
	}
	
	/**
	 * Returns the class object of the given name
	 * @param className
	 * @return the named class
	 */
	public static PClass getClass(String className)
	{
		return classes.get(className);
	}
	
	/**
	 * Returns the map containing all classes and their information
	 * @return {@code Map<String, PClass[]>}
	 */
	protected static Map<String, PClass> getClassesMap()
	{
		return classes;
	}
}
