package files;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import helperClasses.SortedStringList;

public class Class_List {
	
	/**
	 * Contains a list of each class's information <br>
	 * Index 0 - Name (yes, that's just the key again, 
	 * but it was easier to implement this way. Bite me.) <br>
	 * Index 1 - hit die
	 * Index 2 - Max spell slot level
	 */
	private static Map<String, PClass> classes = new HashMap<String, PClass>();
	
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
	 * Returns a string array of all available classes
	 * @return {@code String[]}
	 */
	public static String[] getClassNames()
	{
		SortedStringList classList = new SortedStringList
				(classes.keySet().toArray(new String[0]));
		return classList.toArray();
	}
	
	/**
	 * Returns an alphabetically sorted list of classes
	 * @return class arrayList
	 */
	public static ArrayList<PClass> getClasses()
	{
		SortedStringList classNames = new SortedStringList(
				classes.keySet().toArray(new String[0]));
		ArrayList<PClass> classList = new ArrayList<PClass>();
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
