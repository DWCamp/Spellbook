package gui;

import java.awt.Font;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import files.Spell_List;
import guiPanels.SpellBrowserPanel;
import helperClasses.SpellPF;
import gui.Settings;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;

/**
 * The Window for browsing the master spell list. This window is 
 * where the user can look up details of spells and add them to 
 * their own spell list
 * @author Daniel
 *
 */
@SuppressWarnings("serial")
public class SpellBrowserPF extends SpellBrowser {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextField searchField;
	private JButton btnSearch;
	private JComboBox<Object> comboBoxLevels;
	private JComboBox<Object> comboBoxOption;
	private JComboBox<Object> comboBoxClasses;
	private JComboBox<Object> comboBoxSchools;
	private JPanel panelSpells;
	private UserSpellWindow uswParent;

	/**
	 * By making this a field, the school name does 
	 * not need to keep being fetched. This should
	 * save a small amount of time
	 */
	private String schoolSearch;
	
	/**
	 * Create the frame.
	 */
	public SpellBrowserPF(UserSpellWindow parent) {
		super();
		setTitle("Pathfinder spells");
		contentPane = new JPanel();
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
		
		String[] levelOptions = {"All Levels", "Cantrips", "Level 1", 
				"Level 2", "Level 3", "Level 4", "Level 5", "Level 6", 
				"Level 7", "Level 8", "Level 9"};
		
		String[] searchOptions = {"Name", "Description"};
		
		JPanel panelTools = new JPanel();
		panelTools.setBounds(864, 22, 399, 724);
		contentPane.add(panelTools);
		
		ArrayList<String> classList = new ArrayList<String>();
		classList.add("All classes");
		for (String element : Spell_List.getPFClasses()) {
			classList.add(element);
		}
		
		ArrayList<String> schoolList = new ArrayList<String>();
		schoolList.add("All schools");
		for (String element : Spell_List.getPFSchools()) {
			schoolList.add(element);
		}

		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSearch.setText("Wait...");
				btnSearch.setEnabled(false);

				/**
				 * This is a thing I found on StackOverflow. -> http://stackoverflow.com/questions/10372046/swing-jlabel-settext-invoked-too-late
				 * Don't question it. Basically, it somehow
				 * solves the concurrency issue of search()
				 * hanging the thread so Swing can't update
				 */
				new SwingWorker<Void, Void>() {
					@Override
					protected Void doInBackground() throws Exception {
						search();
						return null;
					}

					@Override
					protected void done() {
						btnSearch.setText("Search");
						btnSearch.setEnabled(true);
					}
				}.execute();
			}
		});
		panelTools.setLayout(new BorderLayout(0, 0));
		
		JPanel panelOptions = new JPanel();
		panelTools.add(panelOptions, BorderLayout.NORTH);
		panelOptions.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelSearch = new JPanel();
		panelOptions.add(panelSearch);
		panelSearch.setLayout(new BorderLayout(0, 0));
		
		searchField = new JTextField();
		panelSearch.add(searchField);
		searchField.setColumns(10);
		comboBoxOption = new JComboBox<Object>(searchOptions);
		panelSearch.add(comboBoxOption, BorderLayout.EAST);
		
		JLabel label = new JLabel("");
		panelOptions.add(label);
		
		comboBoxClasses = new JComboBox<Object>(classList.toArray(new String[0]));
		panelOptions.add(comboBoxClasses);
		
		comboBoxSchools = new JComboBox<Object>(schoolList.toArray(new String[0]));
		panelOptions.add(comboBoxSchools);
		
		comboBoxLevels = new JComboBox<Object>(levelOptions);
		panelOptions.add(comboBoxLevels);
		
		JPanel panelSources = new JPanel();
		panelTools.add(panelSources, BorderLayout.CENTER);
		panelSources.setLayout(new BorderLayout(0, 0));
		
		JPanel panelChoices = new JPanel();
		panelSources.add(panelChoices);
		panelChoices.setLayout(new GridLayout(0, 3, 0, 0));
		
		/**
		 * TOO MANY SOURCES
		 */
		JCheckBox checkBoxTemp = new JCheckBox("A");
		for(String source : Spell_List.getPFSources())
		{
			checkBoxTemp = new JCheckBox(source);
			panelChoices.add(checkBoxTemp);
		}
		
		JLabel lblSources = new JLabel("Soruces");
		lblSources.setFont(new Font("Tahoma", Font.BOLD, 22));
		panelSources.add(lblSources, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panelSources.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnSelect = new JButton("Select All");
		panel.add(btnSelect);
		
		JButton btnDeselect = new JButton("Deselect All");
		panel.add(btnDeselect);
		
		panelTools.add(btnSearch, BorderLayout.SOUTH);
		
		self = this;
		refresh();
		
		Point USW = UserSpellWindow.getWindowLocation();
		setLocation(USW.x + 30, USW.y + 30);
	}
	
	/**
	 * Refreshes the contents of the window
	 */
	public void refresh()
	{
		//resize all components
		double scaleFactor = Settings.getResizeFactor();
		scaleFactor = 2;
		
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
		
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, (int)(15 * scaleFactor)));
		
		//Refill spell search
		panelSpells.removeAll();
		ArrayList<SpellPF> spells = Spell_List.getAllPFSpells();
		for (SpellPF spell : spells) {
			panelSpells.add(new SpellBrowserPanel(spell, uswParent));
		}
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
		ArrayList<SpellPF> spells = Spell_List.getAllPFSpells();
		if (searchVal.equals("")) {
			for (SpellPF spell : spells) {
				if (hasSearchedClass(spell)
						&& isSearchedSchool(spell)) {
					panelSpells.add(new SpellBrowserPanel(spell, uswParent));
				}
			}
		} else {
			for (SpellPF spell : spells) {
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
	private boolean hasSearchedClass(SpellPF spell)
	{
		if (comboBoxClasses.getSelectedIndex() == 0) {
			return true;
		}
		boolean hasSearchedClass = false;
		String searchedClass = (String) comboBoxClasses.getSelectedItem();
		for (String className : spell.getClasses().keySet()) {
			if (searchedClass.equals(className)) {
				hasSearchedClass = true;
			}
		}
		return hasSearchedClass;
	}

	/**
	 * Used to determine if the spell in the spell list is of the 
	 * school searched by the user. <br>
	 * If the option is set to show all schools, this method will 
	 * return {@code true} for all spells
	 * @param spell The spell in question
	 * @return Whether to allow the spell to show up in the search
	 */
	private boolean isSearchedSchool(SpellPF spell)
	{
		if (comboBoxSchools.getSelectedIndex() == 0) {
			return true;
		}
		return spell.getSchool().equals(schoolSearch);
	}
}
