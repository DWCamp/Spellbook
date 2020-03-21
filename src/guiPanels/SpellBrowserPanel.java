package guiPanels;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;

import alerts.WordWrapPopUp;
import model.CharacterInfo;
import model.Spell;
import helperClasses.gameVersion;
import gui.MainWindow;
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
	public SpellBrowserPanel(Spell spell, MainWindow parent) {
		double scaleFactor = Settings.getResizeFactor();
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblName = new JLabel("   " + spell.getName());
		lblName.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		add(lblName, BorderLayout.CENTER);
		
		if(Settings.getSBColor())
		{
			int color = 255 - (spell.getLevel() * 28);
			if (spell.getLevel() > -1 && spell.getLevel() < 10)
			{
				setBackground(new Color(color, color, color));
			} else{
				setBackground(new Color(240,200,200));
			}
			
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
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordWrapPopUp popUp = new WordWrapPopUp(spell.getName(), spell.getPopUpText());
				popUp.setVisible(true);
				popUp.setSize(new Dimension((int)(600 * scaleFactor),
						(int)(400 * scaleFactor)));
			}
		});
	}
}