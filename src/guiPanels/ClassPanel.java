package guiPanels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import helperClasses.Feat;
import helperClasses.PClass;

import java.awt.GridLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ClassPanel extends JPanel {
	private JTextField textFieldName;
	private JComboBox<Object> comboBoxHD;
	private JComboBox<Object> comboBoxCaster;
	private JButton btnEditFeats;
	private JButton btnNewButton;
	private JComboBox<Object> comboBoxSubClassAt;

	/**
	 * Create the panel.
	 */
	public ClassPanel(PClass classParam) {
		setLayout(new GridLayout(0, 4, 2, 2));
		
		textFieldName = new JTextField(classParam.getName());
		add(textFieldName);
		textFieldName.setColumns(10);
		
		comboBoxHD = new JComboBox<Object>(new String[]{
				"Non-caster",
				"Partial caster",
				"Half caster",
				"Full caster"});
		add(comboBoxHD);
		if(classParam.getMaxSpellLevel() == 4) {
			comboBoxHD.setSelectedIndex(1);
		} else if(classParam.getMaxSpellLevel() == 5) {
			comboBoxHD.setSelectedIndex(2);
		} else if(classParam.getMaxSpellLevel() == 9) {
			comboBoxHD.setSelectedIndex(3);
		}
		
		comboBoxCaster = new JComboBox<Object>(new Integer[]{6,8,10,12});
		add(comboBoxCaster);
		if(classParam.getHitDie() == 8) {
			comboBoxCaster.setSelectedIndex(1);
		} else if(classParam.getHitDie() == 10) {
			comboBoxCaster.setSelectedIndex(2);
		} else if(classParam.getHitDie() == 12) {
			comboBoxCaster.setSelectedIndex(3);
		}
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelf();
			}
		});
		
		
		comboBoxSubClassAt = new JComboBox<Object>(new Integer[]{1,2,3,4,5});
		add(comboBoxSubClassAt);
		
		btnEditFeats = new JButton("Edit Feats");
		add(btnEditFeats);
		
		btnNewButton = new JButton("Edit Subs");
		add(btnNewButton);
		add(btnDelete);
	}

	/**
	 * Returns the name of this class
	 */
	public String getName()
	{
		return textFieldName.getText();
	}
	
	/**
	 * Returns the data of the panel
	 * @return
	 */
	public PClass getClassData()
	{
		int spellLevel = 0;
		switch (comboBoxCaster.getSelectedIndex()) {
		case 1: {
			spellLevel = 4;
			break; }
		case 2: {
			spellLevel = 5;
			break; }
		case 3: {
			spellLevel = 9; }
		}
		return new PClass(textFieldName.getText(),
				(comboBoxHD.getSelectedIndex()*2 + 6),
				spellLevel,
				new Feat[20][0],
				(comboBoxSubClassAt.getSelectedIndex() + 1),
				new ArrayList<PClass>());
	}
	
	/**
	 * Deletes this panel from the parent container
	 */
	private void deleteSelf()
	{
		this.getParent().remove(this);
	}

}
