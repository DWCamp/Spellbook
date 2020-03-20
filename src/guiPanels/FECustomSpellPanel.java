package guiPanels;

import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import alerts.ClassPicker;
import alerts.DescriptionPopUp;
import model.Spell_FE;
import gui.Settings;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class FECustomSpellPanel extends EditorSpellPanel {
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
	private JComboBox<Object> comboBoxLevel;
	private JComboBox<Object> comboBoxSchool;

	private boolean saved;

	/**
	 * Create the panel.
	 * @param spell The spell the panel represents
	 */
	public FECustomSpellPanel(Spell_FE spell) {
		double scaleFactor = Settings.getResizeFactor();

		level = spell.getLevel();
		popUp = new DescriptionPopUp(this);
		popUp.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				saved = false;
			}
		});

		setLayout(new BorderLayout((int) (10 * scaleFactor), 0));

		panelLabels = new JPanel();
		add(panelLabels, BorderLayout.WEST);
		panelLabels.setLayout(new GridLayout(0, 1, 0, 2));

		lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblName);

		lblLevel = new JLabel("Level");
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblLevel);

		lblSchool = new JLabel("School");
		lblSchool.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblSchool);

		lblClasses = new JLabel("Classes");
		lblClasses.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblClasses);

		lblCastingTime = new JLabel("Casting Time");
		lblCastingTime.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblCastingTime);

		lblRange = new JLabel("Range");
		lblRange.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblRange);

		lblComponents = new JLabel("Components");
		lblComponents.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblComponents);

		lblDuration = new JLabel("Duration");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelLabels.add(lblDuration);

		panelFields = new JPanel();
		add(panelFields, BorderLayout.CENTER);
		panelFields.setLayout(new GridLayout(0, 1, 0, 2));

		textFieldName = new JTextField(spell.getName());
		textFieldName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				saved = false;
			}
		});
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelFields.add(textFieldName);
		textFieldName.setColumns(10);

		String[] levels = { "Cantrip", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5", "Level 6", "Level 7",
				"Level 8", "Level 9" };
		comboBoxLevel = new JComboBox<Object>(levels);
		comboBoxLevel.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		comboBoxLevel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				level = comboBoxLevel.getSelectedIndex();
				saved = false;
			}
		});
		comboBoxLevel.setSelectedIndex(spell.getLevel());
		panelFields.add(comboBoxLevel);

		String[] schools = { "Abjuration", "Conjuration", "Divination", "Enchantment", "Evocation", "Illusion",
				"Necromancy", "Transmutation" };
		comboBoxSchool = new JComboBox<Object>(schools);
		comboBoxSchool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saved = false;
			}
		});
		comboBoxSchool.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		panelFields.add(comboBoxSchool);

		JPanel classPickerPanel = new JPanel();
		panelFields.add(classPickerPanel);
		classPickerPanel.setLayout(new BorderLayout(0, 0));
		
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
		textFieldClasses.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		classPickerPanel.add(textFieldClasses, BorderLayout.CENTER);
		textFieldClasses.setEditable(false);
		
		JButton addClass = new JButton("+");
		classPickerPanel.add(addClass, BorderLayout.EAST);
		addClass.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClassPicker picker;
				if (textFieldClasses.getText().equals("")) {
					String[] emptyArray = {};
					picker = new ClassPicker(emptyArray);
				}
				else {
					picker = new ClassPicker(textFieldClasses.getText().split(", "));
				}
				picker.setVisible(true);
				picker.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						textFieldClasses.setText(picker
								.getSelectedClasses().contentsToString());
						saved = false;
					}	
				});
			}
		});

		textFieldTime = new JTextField(spell.getCastingTime());
		textFieldTime.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				saved = false;
			}
		});
		textFieldTime.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldTime.setColumns(10);
		panelFields.add(textFieldTime);

		textFieldRange = new JTextField(spell.getRange());
		textFieldRange.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				saved = false;
			}
		});
		textFieldRange.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldRange.setColumns(10);
		panelFields.add(textFieldRange);

		textFieldComp = new JTextField(spell.getComponents());
		textFieldComp.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				saved = false;
			}
		});
		textFieldComp.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldComp.setColumns(10);
		panelFields.add(textFieldComp);

		textFieldDuration = new JTextField(spell.getDuration());
		textFieldDuration.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				saved = false;
			}
		});
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldDuration.setColumns(10);
		panelFields.add(textFieldDuration);

		panelButtons = new JPanel();
		add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new GridLayout(1, 1, 0, 0));

		btnDetails = new JButton("Details");
		btnDetails.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDescription();
			}
		});
		panelButtons.add(btnDetails);

		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelf();
			}
		});
		panelButtons.add(btnDelete);

		saved = true;

		description = spell.getDescription();
	}

	/**
	 * Returns whether the contents of this spell have been changed since the
	 * last save
	 * 
	 * @return {@code boolean} returns <em>false</em> if there are unsaved
	 *         changes
	 */
	public boolean isSaved() {
		return saved;
	}

	/**
	 * Sets whether the spell is saved or unsaved. Used for alerting the panel
	 * of a save event from the parent window
	 * 
	 * @param state
	 *            {@code boolean} The new state of the panel
	 */
	public void setSaved(boolean state) {
		saved = state;
	}

	void deleteSelf() {
		this.getParent().remove(this);
	}

	/**
	 * Converts the contents of the pane into a spell
	 * 
	 * @return {@code Spell}
	 */
	public Spell_FE toSpell() {
		String type = "";
		switch (level) {
		case 0:
			type = comboBoxSchool.getSelectedItem() + " cantrip";
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

		return new Spell_FE(textFieldName.getText(), textFieldClasses.getText().split(","), type, level,
				textFieldTime.getText(), textFieldRange.getText(), textFieldComp.getText(), textFieldDuration.getText(),
				description);
	}
	
	/**
	 * Updates the size of the components
	 */
	public void refresh()
	{
		double scaleFactor = Settings.getResizeFactor();
		
		btnDetails.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldComp.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldRange.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldTime.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldClasses.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		
		comboBoxSchool.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		comboBoxLevel.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		
		lblComponents.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblRange.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblCastingTime.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblClasses.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblSchool.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblName.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, (int) (11 * scaleFactor)));
	}

}