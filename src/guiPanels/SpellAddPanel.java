package guiPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;

import alerts.WordWrapPopUp;
import files.Spell;
import mainGUI.UserSpellWindow;
import userData.CharacterItems;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;

@SuppressWarnings("serial")
public class SpellAddPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public SpellAddPanel(Spell spell, UserSpellWindow parent) {
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("   " + spell.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnDetails = new JButton("Details");
		panel.add(btnDetails);
		btnDetails.setPreferredSize(new Dimension(0, 20));
		
		JButton btnAdd = new JButton("Add Spell");
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CharacterItems.learnSpell(spell.getName());
				parent.refresh();
			}
		});
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordWrapPopUp popUp = new WordWrapPopUp("");
				popUp.setText(spell.toString());
				popUp.setVisible(true);
				popUp.setSize(new Dimension(600, 400));
				popUp.setTitle(spell.getName());
			}
		});
	}
}