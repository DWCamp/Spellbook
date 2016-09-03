package mainGUI;

import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import files.Class_List;
import files.Spell;
import files.Spell_List;
import guiPanels.SpellAddPanel;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class SpellBrowser extends JFrame {

	private JPanel contentPane;
	private JTextField searchField;
	private JComboBox<Object> comboBoxLevels;
	private JComboBox<Object> comboBoxOption;
	private JComboBox<Object> comboBoxClasses;
	private JComboBox<Object> comboBoxSchools;
	private JPanel panelSpells;
	private UserSpellWindow uswParent;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Spell_List.load();
					Class_List.load();
					SpellBrowser frame = new SpellBrowser(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpellBrowser(UserSpellWindow parent) {
		double scaleFactor = SpellBookLauncher.getScaleFactor();
		setResizable(false);
		setTitle("Add spell");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((int)(100 * scaleFactor),
				(int)(100 * scaleFactor),
				(int)(384 * scaleFactor),
				(int)(413 * scaleFactor));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		uswParent = parent;
		
//		try {
//			Image image = new ImageIcon(UserSpellWindow.class.getResource("icon.PNG")).getImage();
//			setIconImage(image);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((int)(10 * scaleFactor),
				(int)(69 * scaleFactor),
				(int)(358 * scaleFactor),
				(int)(304 * scaleFactor));
		scrollPane.getVerticalScrollBar().setUnitIncrement(23);
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
		searchField.setBounds(51, 11, 197, 20);
		contentPane.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Search:");
		lblNewLabel.setBounds(10, 14, 46, 14);
		contentPane.add(lblNewLabel);
		
		String[] levelOptions = {"All Levels", "Cantrips", "Level 1", 
				"Level 2", "Level 3", "Level 4", "Level 5", "Level 6", 
				"Level 7", "Level 8", "Level 9"};
		comboBoxLevels = new JComboBox<Object>(levelOptions);
		comboBoxLevels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		comboBoxLevels.setBounds(12, 38, 110, 20);
		contentPane.add(comboBoxLevels);
		
		String[] searchOptions = {"Name", "Description"};
		comboBoxOption = new JComboBox<Object>(searchOptions);
		comboBoxOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		comboBoxOption.setBounds(258, 11, 110, 20);
		contentPane.add(comboBoxOption);
		
		ArrayList<String> classOptions = new ArrayList<String>();
		classOptions.add("All classes");
		for (String className : Class_List.getClassNames()) {
			classOptions.add(className);
		}
		comboBoxClasses = new JComboBox<Object>(classOptions
				.toArray(new String[0]));
		comboBoxClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		comboBoxClasses.setBounds(134, 38, 110, 20);
		contentPane.add(comboBoxClasses);
		
		comboBoxSchools = new JComboBox<Object>(new String[]
				{"All Schools", "Abjuration", "Conjuration", "Divination", "Enchantment", 
						"Evocation", "Illusion", "Necromancy", "Transmutation"});
		comboBoxSchools.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		comboBoxSchools.setBounds(256, 38, 110, 20);
		contentPane.add(comboBoxSchools);
		
		refresh();
	}
	
	/**
	 * Refreshes the contents of the window
	 */
	public void refresh()
	{
		String searchVal = searchField.getText().toUpperCase();
		panelSpells.removeAll();
		Spell[] spells = Spell_List.getSpellsOfLevel(comboBoxLevels.getSelectedIndex() - 1); // OPTIMIZE THIS SHIT
		if (searchVal.equals("")) {
			for (Spell spell : spells) {
				if (hasSearchedClass(spell)
						&& isSearchedSchool(spell)) {
					panelSpells.add(new SpellAddPanel(spell, uswParent));
				}
			}
		} else {
			for (Spell spell : spells) {
				String searchedValue;
				if (comboBoxOption.getSelectedIndex() == 0) {
					searchedValue = spell.getName();
				} else {
					searchedValue = " " + spell.getDescription();
				}
				if (searchedValue.toUpperCase().contains(searchVal) 
						&& hasSearchedClass(spell) 
						&& isSearchedSchool(spell)) {
					panelSpells.add(new SpellAddPanel(spell, uswParent));
				}
			}
		}
		repaint();
		revalidate();
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
	private boolean hasSearchedClass(Spell spell)
	{
		if (comboBoxClasses.getSelectedIndex() == 0) {
			return true;
		}
		boolean hasSearchedClass = false;
		String searchedClass = (String) comboBoxClasses.getSelectedItem();
		for (String className : spell.getClasses()) {
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
	private boolean isSearchedSchool(Spell spell)
	{
		if (comboBoxSchools.getSelectedIndex() == 0) {
			return true;
		}
		return spell.getType().contains(((String)comboBoxSchools.getSelectedItem()).toLowerCase());
	}
}
