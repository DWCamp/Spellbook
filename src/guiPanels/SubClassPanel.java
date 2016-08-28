package guiPanels;

import javax.swing.JPanel;
import javax.swing.JTextField;

import files.Feat;
import files.PClass;
import files.SubClass;

import java.awt.GridLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class SubClassPanel extends JPanel {
	private JTextField textFieldName;
	private JComboBox<Object> comboBoxCaster;
	private JButton btnEditFeats;
	private PClass parent;

	/**
	 * Create the panel.
	 */
	public SubClassPanel(SubClass classParam) {
		setLayout(new GridLayout(0, 4, 2, 2));
		
		parent = classParam.getParent();
		
		textFieldName = new JTextField(classParam.getName());
		add(textFieldName);
		textFieldName.setColumns(10);
		
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
		
		btnEditFeats = new JButton("Edit Feats");
		add(btnEditFeats);
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
		return new SubClass(parent, 
				textFieldName.getText(),
				spellLevel,
				new Feat[20][0]);
	}
	
	/**
	 * Deletes this panel from the parent container
	 */
	private void deleteSelf()
	{
		this.getParent().remove(this);
	}

}
