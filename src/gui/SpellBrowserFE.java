package gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.SpellList;
import guiPanels.SpellBrowserPanel;
import helperClasses.SpellBrowser;
import model.Spell_FE;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;

/**
 * The Window for browsing the master spell list. This window is 
 * where the user can look up details of spells and add them to 
 * their own spell list
 *
 * @author Daniel Campman
 */
@SuppressWarnings("serial")
public class SpellBrowserFE extends SpellBrowser {

	private JScrollPane scrollPane;
	private JTextField searchField;
	private JLabel lblSearch;
	private JComboBox<Object> comboBoxLevels;
	private JComboBox<Object> comboBoxOption;
	private JComboBox<Object> comboBoxClasses;
	private JComboBox<Object> comboBoxSchools;
	private JPanel panelSpells;
	private MainWindow uswParent;

	/**
	 * Create the frame.
	 */
	public SpellBrowserFE(MainWindow parent) {
		super();

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		uswParent = parent;
		
		try {
			Image image = new ImageIcon("Resources/browser.PNG").getImage();
			setIconImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane);
		
		panelSpells = new JPanel();
		scrollPane.setViewportView(panelSpells);
		panelSpells.setLayout(new BoxLayout(panelSpells, BoxLayout.Y_AXIS));
		
		searchField = new JTextField();
		searchField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				System.out.println("How did you find this (SpellBrowser: "
						+ "textField Document Listener)");
			}
			@Override
			public void insertUpdate(DocumentEvent arg0) {
				refresh();
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				refresh();
			}
		});
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		lblSearch = new JLabel("Search:");
		contentPane.add(lblSearch);
		
		String[] levelOptions = {"All Levels", "Cantrips", "Level 1", 
				"Level 2", "Level 3", "Level 4", "Level 5", "Level 6", 
				"Level 7", "Level 8", "Level 9"};
		comboBoxLevels = new JComboBox<>(levelOptions);
		comboBoxLevels.addActionListener(e -> refresh());
		contentPane.add(comboBoxLevels);
		
		String[] searchOptions = {"Name", "Description"};
		comboBoxOption = new JComboBox<>(searchOptions);
		comboBoxOption.addActionListener(e -> refresh());
		contentPane.add(comboBoxOption);
		
		ArrayList<String> classList = new ArrayList<>();
		classList.add("All classes");
		classList.addAll(Arrays.asList(SpellList.getFEClasses()));
		
		comboBoxClasses = new JComboBox<>(classList.toArray(new String[0]));
		comboBoxClasses.addActionListener(e -> refresh());
		contentPane.add(comboBoxClasses);
		
		ArrayList<String> schoolList = new ArrayList<>();
		schoolList.add("All schools");
		schoolList.addAll(Arrays.asList(SpellList.getFESchools()));
		
		comboBoxSchools = new JComboBox<>(schoolList.toArray(new String[0]));
		comboBoxSchools.addActionListener(e -> refresh());
		contentPane.add(comboBoxSchools);
		
		self = this;
		refresh();
		
		Point USW = MainWindow.getWindowLocation();
		setLocation(USW.x + 30, USW.y + 30);
	}
	
	/**
	 * Refreshes the contents of the window
	 */
	public void refresh()
	{
		String searchVal = searchField.getText().toUpperCase();
		panelSpells.removeAll();
		ArrayList<Spell_FE> spells = SpellList.getFESpellsOfLevel(comboBoxLevels.getSelectedIndex() - 1); // OPTIMIZE THIS SHIT
		if (searchVal.equals("")) {
			for (Spell_FE spell : spells) {
				if (hasSearchedClass(spell)
						&& isSearchedSchool(spell)) {
					panelSpells.add(new SpellBrowserPanel(spell, uswParent));
				}
			}
		} else {
			for (Spell_FE spell : spells) {
				String searchedValue;
				if (comboBoxOption.getSelectedIndex() == 0) {
					searchedValue = spell.getName();
				} else {
					searchedValue = " " + spell.getDescription();
				}
				if (searchedValue.toUpperCase().contains(searchVal) 
						&& hasSearchedClass(spell) 
						&& isSearchedSchool(spell)) {
					panelSpells.add(new SpellBrowserPanel(spell, uswParent));
				}
			}
		}
		//resize all components
		double scaleFactor = Settings.getResizeFactor();
		//scaleFactor = 1;
		
		setBounds(getX(), getY(),
				(int)(384 * scaleFactor),
				(int)(413 * scaleFactor) - (int)(20 * (scaleFactor - 1)));
		
		scrollPane.setBounds((int)(10 * scaleFactor),
				(int)(69 * scaleFactor),
				(int)(358 * scaleFactor),
				(int)(304 * scaleFactor));
		scrollPane.getVerticalScrollBar().setUnitIncrement((int)(scaleFactor*(24.0/5.0) + 2));
		
		searchField.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		searchField.setBounds((int)(51 * scaleFactor),
				(int)(11 * scaleFactor),
				(int)(197 * scaleFactor),
				(int)(20 * scaleFactor));
		
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		lblSearch.setBounds((int)(10 * scaleFactor),
				(int)(14 * scaleFactor),
				(int)(46 * scaleFactor),
				(int)(14 * scaleFactor));
		
		comboBoxOption.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxOption.setBounds((int)(258 * scaleFactor),
				(int)(11 * scaleFactor),
				(int)(110 * scaleFactor),
				(int)(20 * scaleFactor));
		
		comboBoxLevels.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxLevels.setBounds((int)(12 * scaleFactor),
				(int)(38 * scaleFactor),
				(int)(110 * scaleFactor),
				(int)(20 * scaleFactor));
		
		comboBoxSchools.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxSchools.setBounds((int)(256 * scaleFactor),
				(int)(38 * scaleFactor),
				(int)(110 * scaleFactor),
				(int)(20 * scaleFactor));
		
		comboBoxClasses.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxClasses.setBounds((int)(134 * scaleFactor),
				(int)(38 * scaleFactor),
				(int)(110 * scaleFactor),
				(int)(20 * scaleFactor));
		
		repaint();
		revalidate();
	}
	
	/**
	 * Resets the state of the window
	 */
	public static void windowRefresh(){
		self.refresh();
	}
	
	/**
	 * Resets the state of the window to be like when first appeared
	 */
	public void reset()
	{
		searchField.setText("");
		comboBoxOption.setSelectedIndex(0);
		comboBoxLevels.setSelectedIndex(0);
		comboBoxClasses.setSelectedIndex(0);
		comboBoxSchools.setSelectedIndex(0);
		refresh();
	}
	
	/**
	 * Used to determine if the spell in the spell list can be 
	 * learned by the class searched by the user. <br>
	 * If the option is set to show all classes, this method will 
	 * return {@code true} for all spells
	 * @param spell The spell in question
	 * @return Whether to allow the spell to show up in the search
	 */
	private boolean hasSearchedClass(Spell_FE spell)
	{
		if (comboBoxClasses.getSelectedIndex() == 0) {
			return true;
		}

		String searchedClass = (String) comboBoxClasses.getSelectedItem();
		if (searchedClass == null) { return false; }

		for (String className : spell.getClasses()) {
			if (searchedClass.equals(className)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Used to determine if the spell in the spell list is of the 
	 * school searched by the user. <br>
	 * If the option is set to show all schools, this method will 
	 * return {@code true} for all spells
	 * @param spell The spell in question
	 * @return Whether to allow the spell to show up in the search
	 */
	private boolean isSearchedSchool(Spell_FE spell)
	{
		if (comboBoxSchools.getSelectedIndex() == 0) {
			return true;
		}
		String selectedItem = (String) comboBoxSchools.getSelectedItem();
		if (selectedItem == null) { return false; }

		return spell.getSchool().contains(selectedItem);
	}
}
