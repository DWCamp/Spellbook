package gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.SpellList;
import guiPanels.SpellBrowserPanel;
import helperClasses.SpellBrowser;
import model.Spell_PF;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.util.*;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 * The Window for browsing the master spell list. This window is 
 * where the user can look up details of spells and add them to 
 * their own spell list
 *
 * @author Daniel Campman
 */
@SuppressWarnings("serial")
public class SpellBrowserPF extends SpellBrowser {

	// Map sources to their Source Filter Checkbox index
	private static final HashMap<String, Integer> SOURCE_INDEX = new HashMap<>(Map.ofEntries(
			new AbstractMap.SimpleEntry<>("PFRPG Core", 0),
			new AbstractMap.SimpleEntry<>("Advanced Class Guide", 1),
			new AbstractMap.SimpleEntry<>("Advanced Player's Guide", 2),
			new AbstractMap.SimpleEntry<>("Advanced Race Guide", 3),
			new AbstractMap.SimpleEntry<>("Adventure Path", 4),
			new AbstractMap.SimpleEntry<>("Horror Adventures", 5),
			new AbstractMap.SimpleEntry<>("Inner Sea Gods", 6),
			new AbstractMap.SimpleEntry<>("Occult Adventures", 7),
			new AbstractMap.SimpleEntry<>("Ultimate Combat", 8),
			new AbstractMap.SimpleEntry<>("Ultimate Intrigue", 9),
			new AbstractMap.SimpleEntry<>("Ultimate Magic", 10)
	));

	private JScrollPane scrollPane;
	private JTextField searchField;
	private JButton btnSearch;
	private JComboBox<Object> comboBoxLevels;
	private JComboBox<Object> comboBoxOption;
	private JComboBox<Object> comboBoxClasses;
	private JComboBox<Object> comboBoxSchools;
	private JButton btnSelect;
	private JButton btnDeselect;
	private JPanel panelSpells;
	private ArrayList<JCheckBox> sourceCheckBoxes;
	private MainWindow uswParent;

	/**
	 * By making this a field, the school name does 
	 * not need to keep being fetched. This should
	 * save a small amount of time
	 */
	private String schoolSearch;
	
	/**
	 * Create the frame.
	 */
	public SpellBrowserPF(MainWindow parent) {
		super();
		setTitle("Pathfinder spells");
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		double scaleFactor = Settings.getResizeFactor();
		
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
		
		String[] levelOptions = {"All Levels", "Cantrips", "Level 1", 
				"Level 2", "Level 3", "Level 4", "Level 5", "Level 6", 
				"Level 7", "Level 8", "Level 9"};
		
		String[] searchOptions = {"Name", "Description"};
		
		JPanel panelTools = new JPanel();
		panelTools.setBounds(864, 22, 399, 724);
		contentPane.add(panelTools);
		
		ArrayList<String> classList = new ArrayList<>();
		classList.add("All Classes");
		//classList.addAll(Arrays.asList(SpellList.getPFClasses()));
		
		ArrayList<String> schoolList = new ArrayList<>();
		schoolList.add("All Schools");
		//schoolList.addAll(Arrays.asList(SpellList.getPFSchools()));

		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(arg0 -> {
			btnSearch.setText("Wait...");
			btnSearch.setEnabled(false);

			/*
			 * This is a thing I found on StackOverflow. -> http://stackoverflow.com/questions/10372046/swing-jlabel-settext-invoked-too-late
			 * Don't question it. Basically, it somehow
			 * solves the concurrency issue of search()
			 * hanging the thread so Swing can't update
			 */
			new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() {
					search();
					return null;
				}

				@Override
				protected void done() {
					btnSearch.setText("Search");
					btnSearch.setEnabled(true);
				}
			}.execute();
		});
		panelTools.setLayout(new BorderLayout(0, 5));
		
		JPanel panelOptions = new JPanel();
		panelTools.add(panelOptions, BorderLayout.NORTH);
		panelOptions.setLayout(new GridLayout(0, 1, 0, 5));
		
		JPanel panelSearch = new JPanel();
		panelOptions.add(panelSearch);
		panelSearch.setLayout(new BorderLayout(0, 0));
		
		searchField = new JTextField();
		panelSearch.add(searchField);
		searchField.setColumns(10);
		comboBoxOption = new JComboBox<>(searchOptions);
		panelSearch.add(comboBoxOption, BorderLayout.EAST);
		
		comboBoxClasses = new JComboBox<>(classList.toArray(new String[0]));
		panelOptions.add(comboBoxClasses);
		
		comboBoxSchools = new JComboBox<>(schoolList.toArray(new String[0]));
		panelOptions.add(comboBoxSchools);
		
		comboBoxLevels = new JComboBox<>(levelOptions);
		panelOptions.add(comboBoxLevels);
		
		JPanel panelSources = new JPanel();
		panelTools.add(panelSources, BorderLayout.CENTER);
		panelSources.setLayout(new BorderLayout(0, 0));
		
		JPanel panelChoices = new JPanel();
		panelSources.add(panelChoices);
		panelChoices.setLayout(new GridLayout(0, 1, 0, 0));
		
		sourceCheckBoxes = new ArrayList<>();
		
		JCheckBox checkBoxCore = new JCheckBox("PFRPG Core");
		sourceCheckBoxes.add(checkBoxCore);
		JCheckBox checkBoxACG = new JCheckBox("Advanced Class Guide");
		sourceCheckBoxes.add(checkBoxACG);
		JCheckBox checkBoxAPG = new JCheckBox("Advanced Player's Guide");	
		sourceCheckBoxes.add(checkBoxAPG);
		JCheckBox checkBoxARG = new JCheckBox("Advanced Race Guide");
		sourceCheckBoxes.add(checkBoxARG);
		JCheckBox checkBoxAP = new JCheckBox("Adventure Paths");
		sourceCheckBoxes.add(checkBoxAP);
		JCheckBox checkBoxHA = new JCheckBox("Horror Adventures");
		sourceCheckBoxes.add(checkBoxHA);
		JCheckBox checkBoxISG = new JCheckBox("Inner Sea Gods");
		sourceCheckBoxes.add(checkBoxISG);
		JCheckBox checkBoxOA = new JCheckBox("Occult Adventures");
		sourceCheckBoxes.add(checkBoxOA);
		JCheckBox checkBoxUC = new JCheckBox("Ultimate Combat");
		sourceCheckBoxes.add(checkBoxUC);
		JCheckBox checkBoxUI = new JCheckBox("Ultimate Intrigue");
		sourceCheckBoxes.add(checkBoxUI);
		JCheckBox checkBoxUM = new JCheckBox("Ultimate Magic");
		sourceCheckBoxes.add(checkBoxUM);
		JCheckBox checkBoxOther = new JCheckBox("Other");
		sourceCheckBoxes.add(checkBoxOther);
		
		for(JCheckBox cBox : sourceCheckBoxes)
		{
			panelChoices.add(cBox);
			cBox.setFont(new Font("Tagoma", Font.PLAIN, (int)(8 * scaleFactor)));
			cBox.setSelected(true);
		}

		JLabel lblSources = new JLabel("Soruces");
		lblSources.setFont(new Font("Tahoma", Font.BOLD, (int)(11 * scaleFactor)));
		panelSources.add(lblSources, BorderLayout.NORTH);
		
		JPanel panelButtons = new JPanel();
		panelSources.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new GridLayout(0, 2, 0, 0));
		
		btnSelect = new JButton("Select All");
		btnSelect.addActionListener(arg0 -> {
			for(JCheckBox cBox : sourceCheckBoxes){
				cBox.setSelected(true);
			}
		});
		panelButtons.add(btnSelect);
		
		btnDeselect = new JButton("Deselect All");
		btnDeselect.addActionListener(arg0 -> {
			for(JCheckBox cBox : sourceCheckBoxes){
				cBox.setSelected(false);
			}
		});
		panelButtons.add(btnDeselect);
		
		panelTools.add(btnSearch, BorderLayout.SOUTH);
		
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
		//resize all components
		double scaleFactor = Settings.getResizeFactor();
		
		setBounds(getX(), getY(),
				1284,
				806);
		
		scrollPane.setBounds(20,
				22,
				829,
				724);
		scrollPane.getVerticalScrollBar().setUnitIncrement((int)(scaleFactor*(4.8) + 2));
		
		searchField.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		searchField.setText("");
		
		comboBoxOption.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxOption.setSelectedIndex(0);
		
		comboBoxLevels.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxLevels.setSelectedIndex(0);
		
		comboBoxSchools.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxSchools.setSelectedIndex(0);
		
		comboBoxClasses.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		comboBoxClasses.setSelectedIndex(0);
		
		btnSelect.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		
		btnDeselect.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, (int)(15 * scaleFactor)));
		
		//Refill spell search
		panelSpells.removeAll();
		// ArrayList<Spell_PF> spells = SpellList.getAllPFSpells();
		//
		repaint();
		revalidate();
	}
	
	/**
	 * Uses the parameters to search the spell list 
	 * for specific entries and displays them
	 */
	public void search()
	{
		String searchVal = searchField.getText().toUpperCase();
		schoolSearch = (String)comboBoxSchools.getSelectedItem();
		panelSpells.removeAll();
//		ArrayList<Spell_PF> spells = SpellList.getAllPFSpells();
//		if (searchVal.equals("")) {
//			for (Spell_PF spell : spells) {
//				if (hasSearchedClass(spell)
//						&& isSearchedSchool(spell)
//						&& fromSearchedSource(spell)) {
//					panelSpells.add(new SpellBrowserPanel(spell, uswParent));
//				}
//			}
//		} else {
//			for (Spell_PF spell : spells) {
//				String searchedValue;
//				if (comboBoxOption.getSelectedIndex() == 0) {
//					searchedValue = spell.getName();
//				} else {
//					searchedValue = " " + spell.getDescription();
//				}
//				if (searchedValue.toUpperCase().contains(searchVal)
//						&& hasSearchedClass(spell)
//						&& isSearchedSchool(spell)
//						&& fromSearchedSource(spell)) {
//					panelSpells.add(new SpellBrowserPanel(spell, uswParent));
//				}
//			}
//		}
		
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
	private boolean hasSearchedClass(Spell_PF spell)
	{
		if (comboBoxClasses.getSelectedIndex() == 0) {
			return true;
		}
		String searchedClass = (String) comboBoxClasses.getSelectedItem();
		for (String className : spell.getClasses().keySet()) {
			if (className.equals(searchedClass)) {
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
	private boolean isSearchedSchool(Spell_PF spell)
	{
		if (comboBoxSchools.getSelectedIndex() == 0) {
			return true;
		}
		return spell.getSchool().equals(schoolSearch);
	}
	
	/**
	 * Used to determine if the spell in the spell list originated in 
	 * one of the sources selected by the user
	 * @param spell The spell to evaluate
	 * @return Whether the spell comes from allowed sources
	 */
	private boolean fromSearchedSource(Spell_PF spell)
	{
		Integer index = SOURCE_INDEX.get(spell.getSource());
		if (index == null) { index = 11; }  // If source not found, check if "Other" checked
		return sourceCheckBoxes.get(index).isSelected();
	}
}
