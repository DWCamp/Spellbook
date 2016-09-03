package guiPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;

import alerts.WordWrapPopUp;
import files.Spell;
import mainGUI.SpellBookLauncher;
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
		double scaleFactor = SpellBookLauncher.getScaleFactor();
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("   " + spell.getName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		add(lblName, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton btnDetails = new JButton("Details");
		btnDetails.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		panel.add(btnDetails);
		btnDetails.setPreferredSize(new Dimension(0, (int)(20 * scaleFactor)));
		
		JButton btnAdd = new JButton("Add Spell");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		panel.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CharacterItems.learnSpell(spell.getName());
				parent.refresh();
			}
		});
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordWrapPopUp popUp = new WordWrapPopUp(spell.getName(), spell.toString());
				popUp.setVisible(true);
				popUp.setSize(new Dimension((int)(600 * scaleFactor),
						(int)(400 * scaleFactor)));
			}
		});
	}
}