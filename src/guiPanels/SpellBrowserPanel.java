package guiPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;

import alerts.WordWrapPopUp;
import files.CharacterItems;
import helperClasses.Spell;
import helperClasses.SpellFE;
import helperClasses.SpellPF;
import helperClasses.gameVersion;
import gui.UserSpellWindow;
import gui.Settings;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;

/**
 * The panel which displays a given spell in the 
 * scroll pane of the SpellBrowser
 * @author Daniel Campman
 */
@SuppressWarnings("serial")
public class SpellBrowserPanel extends JPanel {
	
	/**
	 * Create the panel.
	 */
	public SpellBrowserPanel(Spell spell, UserSpellWindow parent) {
		double scaleFactor = Settings.getResizeFactor();
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("   " + spell.getName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		add(lblName, BorderLayout.CENTER);
		
		if(Settings.getSBColor())
		{
			int color = 255 - (spell.getLevel() * 28);
			setBackground(new Color(color, color, color));
			if (color < 100)
			{
				lblName.setForeground(Color.WHITE);
			}
			
		}
		
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
				if (Settings.getVersion().equals(gameVersion.FIFTH_EDITION))
				{
					CharacterItems.learnFESpell(spell.getName());
				} else if (Settings.getVersion().equals(gameVersion.PATHFINDER))
				{
					CharacterItems.learnPFSpell(spell.getName());
				}
				parent.refreshSpells();
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