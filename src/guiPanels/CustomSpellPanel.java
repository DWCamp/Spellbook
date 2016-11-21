package guiPanels;

import javax.swing.JTextField;

import alerts.DescriptionPopUp;
import files.Spell;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class CustomSpellPanel extends EditorSpellPanel {
	private JPanel panelLabels;
	private JLabel lblName;
	private JLabel lblLevel;
	private JLabel lblSchool;
	private JPanel panelFields;
	private JLabel lblClasses;
	private JLabel lblCastingTime;
	private JLabel lblRange;
	private JLabel lblComponents;
	private JLabel lblDuration;
	private JTextField textFieldName;
	private JTextField textFieldClasses;
	private JTextField textFieldTime;
	private JTextField textFieldRange;
	private JTextField textFieldComp;
	private JTextField textFieldDuration;
	private JPanel panelButtons;
	private JButton btnDetails;
	private JButton btnDelete;
	private JButton btnSanitize;
	private JComboBox<Object> comboBoxLevel;
	private JComboBox comboBoxSchool;

	/**
	 * Create the panel.
	 * @param spell The spell the panel represents
	 */
	public CustomSpellPanel(Spell spell) {
		level = spell.getLevel();
		popUp = new DescriptionPopUp(this);
		
		setLayout(new BorderLayout(2, 0));
		
		panelLabels = new JPanel();
		add(panelLabels, BorderLayout.WEST);
		panelLabels.setLayout(new GridLayout(0, 1, 0, 2));
		
		lblName = new JLabel("Name");
		panelLabels.add(lblName);
		
		lblLevel = new JLabel("Level");
		panelLabels.add(lblLevel);
		
		lblSchool = new JLabel("School");
		panelLabels.add(lblSchool);
		
		lblClasses = new JLabel("Classes");
		panelLabels.add(lblClasses);
		
		lblCastingTime = new JLabel("Casting Time");
		panelLabels.add(lblCastingTime);
		
		lblRange = new JLabel("Range");
		panelLabels.add(lblRange);
		
		lblComponents = new JLabel("Components");
		panelLabels.add(lblComponents);
		
		lblDuration = new JLabel("Duration");
		panelLabels.add(lblDuration);
		
		panelFields = new JPanel();
		add(panelFields, BorderLayout.CENTER);
		panelFields.setLayout(new GridLayout(0, 1, 0, 2));
		
		textFieldName = new JTextField(spell.getName());
		panelFields.add(textFieldName);
		textFieldName.setColumns(10);
		
		String[] levels = {"Cantrip", "Level 1",
				"Level 2", "Level 3", "Level 4",
				"Level 5", "Level 6", "Level 7",
				"Level 8", "Level 9"};
		comboBoxLevel = new JComboBox<Object>(levels);
		comboBoxLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level = comboBoxLevel.getSelectedIndex();
			}
		});
		comboBoxLevel.setSelectedIndex(spell.getLevel());
		panelFields.add(comboBoxLevel);
		
		String[] schools = {"Abjuration", "Conjuration", 
				"Divination", "Enchantment", "Evocation", 
				"Illusion", "Necromancy", "Transmutation"};
		comboBoxSchool = new JComboBox<Object>(schools);
		panelFields.add(comboBoxSchool);
		
		String classes = "";
		for (String className : spell.getClasses()) {
			classes += "," + className;
		}
		if (classes.length() > 1) {
			textFieldClasses = new JTextField(classes.substring(1));
		} else {
			textFieldClasses = new JTextField();
		}
		textFieldClasses.setColumns(10);
		panelFields.add(textFieldClasses);
		
		textFieldTime = new JTextField(spell.getCastingTime());
		textFieldTime.setColumns(10);
		panelFields.add(textFieldTime);
		
		textFieldRange = new JTextField(spell.getRange());
		textFieldRange.setColumns(10);
		panelFields.add(textFieldRange);
		
		textFieldComp = new JTextField(spell.getComponents());
		textFieldComp.setColumns(10);
		panelFields.add(textFieldComp);
		
		textFieldDuration = new JTextField(spell.getDuration());
		textFieldDuration.setColumns(10);
		panelFields.add(textFieldDuration);
		
		panelButtons = new JPanel();
		add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new GridLayout(1, 1, 0, 0));
		
		btnDetails = new JButton("Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDescription();
			}
		});
		panelButtons.add(btnDetails);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelf();
			}
		});
		
		btnSanitize = new JButton("Sanitize");
		btnSanitize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sanitize();
			}
		});
		panelButtons.add(btnSanitize);
		panelButtons.add(btnDelete);
		
		description = spell.getDescription();
	}
	
	/**
	 * Converts the contents of the pane into a spell
	 * @return {@code Spell}
	 */
	public Spell toSpell()
	{
		String type = "";
		switch (level){
		case 0:
			type = comboBoxSchool.getSelectedItem() + " catrip";
			break;
		case 1:
			type = "1st-level " + comboBoxSchool.getSelectedItem(); 
			break;
		case 2:
			type = "2nd-level " + comboBoxSchool.getSelectedItem(); 
			break;
		case 3:
			type = "3rd-level " + comboBoxSchool.getSelectedItem(); 
			break;
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			type = level + "th-level " + comboBoxSchool.getSelectedItem(); 
			break;
		default:
			type = "Spell";
		}
		
		return new Spell(textFieldName.getText(),
				textFieldClasses.getText().split(","),
				type,
				level,
				textFieldTime.getText(),
				textFieldRange.getText(),
				textFieldComp.getText(),
				textFieldDuration.getText(),
				description);
	}

}