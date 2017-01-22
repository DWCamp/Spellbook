package guiPanels;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import alerts.WordWrapPopUp;
import files.CharacterItems;
import files.FileSystem;
import files.Spell_List;
import gui.MainWindow;
import helperClasses.Spell;
import helperClasses.gameVersion;
import gui.Settings;

import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SpellCard extends JPanel {

	private JButton btnPrepare;
	
	private Spell spell;
	private boolean prepared;
	
	/**
	 * Create the panel.
	 * @param spellName The name of the 
	 * spell the card holds
	 */
	public SpellCard(String spellName) {
		double scaleFactor = Settings.getResizeFactor();
		
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		setPreferredSize(new Dimension((int)(298 * scaleFactor), 0));
		
		if (Settings.getVersion() == gameVersion.FIFTH_EDITION)
		{
			spell = Spell_List.getFESpell(spellName);
		} else if (Settings.getVersion() == gameVersion.PATHFINDER)
		{
			spell = Spell_List.getPFSpell(spellName);
		}
		
		prepared = CharacterItems.getPreparedFESpells().contains(spellName);
		
		JButton btnDetails = new JButton("Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WordWrapPopUp popUp = new WordWrapPopUp
						(spellName, spell.getDetails());
				popUp.setVisible(true);
				popUp.setSize(new Dimension((int)(600 * scaleFactor),
				(int)(400 * scaleFactor)));
			}
		});
		btnDetails.setBounds((int)(176 * scaleFactor),
				(int)(36 * scaleFactor),
				(int)(111 * scaleFactor),
				(int)(20 * scaleFactor));
		btnDetails.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * scaleFactor)));
		add(btnDetails);
		
		btnPrepare = new JButton("Prepare");
		btnPrepare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow.setSpellPrepared(spell, !prepared);
			}
		});
		btnPrepare.setBounds((int)(176 * scaleFactor),
				(int)(268 * scaleFactor),
				(int)(111 * scaleFactor),
				(int)(29 * scaleFactor));
		btnPrepare.setFont(new Font("Tahoma", Font.PLAIN, (int)(16 * scaleFactor)));
		add(btnPrepare);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int remove = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to remove this spell from your spellbook?", 
						"Remove Spell", JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION)
				{
					removeSelf();
				}
			}
		});
		btnRemove.setBounds((int)(10 * scaleFactor),
				(int)(268 * scaleFactor),
				(int)(111 * scaleFactor),
				(int)(29 * scaleFactor));
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, (int)(16 * scaleFactor)));
		add(btnRemove);
		
		JLabel lblSpellName = new JLabel(spellName);
		lblSpellName.setFont(new Font("Tahoma", Font.BOLD, (int)(18 * scaleFactor)));
		lblSpellName.setHorizontalAlignment(SwingConstants.LEFT);
		lblSpellName.setBounds((int)(10 * scaleFactor),
				0, 
				(int)(263 * scaleFactor),
				(int)(43 * scaleFactor));
		add(lblSpellName);
		
		JLabel lblSpellType = new JLabel();
		lblSpellType.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		lblSpellType.setBounds((int)(20 * scaleFactor),
				(int)(38 * scaleFactor),
				(int)(138 * scaleFactor),
				(int)(14 * scaleFactor));
		add(lblSpellType);
	
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * scaleFactor)));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds((int)(10 * scaleFactor),
				(int)(63 * scaleFactor),
				(int)(277 * scaleFactor),
				(int)(194 * scaleFactor));
		add(textArea);
	
		if (spell != null){
			lblSpellType.setText(spell.getSubtitle());
			if (spell.getSubtitle().length() > 30) {
				lblSpellType.setFont(new Font("Tahoma", Font.PLAIN, (int)(8 * scaleFactor)));
			}
			textArea.setText(spell.getCardText());
		} else{
			lblSpellType.setText("ERROR");
			textArea.setText("SPELL NOT FOUND");
		}
		
		if (Settings.getVersion() == gameVersion.FIFTH_EDITION) {
			if (CharacterItems.getPreparedFESpells().contains(spellName)) {
				setBackground(new Color(34, 139, 34));
				btnPrepare.setText("Prepared!");
			} else {
				setBackground(new Color(192, 192, 192));
				btnPrepare.setText("Prepare");
			}
		} else if (Settings.getVersion() == gameVersion.PATHFINDER) {
			if (CharacterItems.getPreparedPFSpells().contains(spellName)) {
				setBackground(new Color(34, 139, 34));
				btnPrepare.setText("Prepared!");
			} else {
				setBackground(new Color(192, 192, 192));
				btnPrepare.setText("Prepare");
			}
		}

		
	}
	
	/**
	 * Returns the spell on the spell card
	 * @return Spell
	 */
	public Spell getSpell(){
		return spell;
	}
	
	/**
	 * Sets this spell's prepared state
	 * @param prep
	 */
	public void setPrepared(boolean prep)
	{
		prepared = prep;
		if (prepared) {
			setBackground(new Color(34, 139, 34));
			btnPrepare.setText("Prepared!");
			if (Settings.getVersion() == gameVersion.FIFTH_EDITION) {
				CharacterItems.prepareFESpell(spell.getName());
			} else if (Settings.getVersion() == gameVersion.PATHFINDER) {
				CharacterItems.preparePFSpell(spell.getName());
			}	
		} else {
			setBackground(new Color(192, 192, 192));
			btnPrepare.setText("Prepare");
			if (Settings.getVersion() == gameVersion.FIFTH_EDITION) {
				CharacterItems.unprepareFESpell(spell.getName());
			} else if (Settings.getVersion() == gameVersion.PATHFINDER) {
				CharacterItems.unpreparePFSpell(spell.getName());
			}	
		}
		FileSystem.saveCharItems();
	}
	
	/**
	 * Removes the spell card from the parent panel
	 */
	public void removeSelf()
	{
		MainWindow.removeSpell(this);
	}
	
	/**
	 * Determines if one spellcard holds the same spell as another,
	 *  as determined by the spells' names
	 * @param card
	 * @return boolean If the cards are equal
	 */
	public boolean equals(SpellCard card)
	{
		if (card.getSpell().getName().equals(spell.getName()))
		{
			return true;
		}
		return false;
	}
}
