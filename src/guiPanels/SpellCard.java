package guiPanels;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import alerts.WordWrapPopUp;
import files.FileSystem;
import files.Spell;
import files.Spell_List;
import mainGUI.UserSpellWindow;
import userData.CharacterItems;

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
		
		setBackground(new Color(192, 192, 192));
		setLayout(null);
		setPreferredSize(new Dimension(298, 300));
		
		spell = Spell_List.getSpell(spellName);
		prepared = CharacterItems.getPreparedSpells().contains(spellName);
		
		JButton btnDetails = new JButton("Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WordWrapPopUp popUp = new WordWrapPopUp
						(Spell_List.getSpell(spellName).getDescription());
				popUp.setVisible(true);
				popUp.setSize(new Dimension(600,400));
				popUp.setTitle(spellName);
			}
		});
		btnDetails.setBounds(176, 32, 111, 20);
		btnDetails.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add(btnDetails);
		
		btnPrepare = new JButton("Prepare");
		btnPrepare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserSpellWindow.setSpellPrepared(spell, !prepared);
			}
		});
		btnPrepare.setBounds(186, 268, 101, 29);
		btnPrepare.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		btnRemove.setBounds(10, 268, 101, 29);
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 16));
		add(btnRemove);
		
		JLabel lblSpellName = new JLabel(spellName);
		lblSpellName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSpellName.setHorizontalAlignment(SwingConstants.LEFT);
		lblSpellName.setBounds(10, 0, 263, 43);
		add(lblSpellName);
		
		JLabel lblSpellType = new JLabel();
		lblSpellType.setBounds(20, 38, 138, 14);
		add(lblSpellType);
	
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBounds(10, 63, 277, 194);
		add(textArea);
	
		if (spell != null){
			lblSpellType.setText(spell.getType());
			textArea.setText("Casting Time: " + spell.getCastingTime() 
			+ "\n\nRange: " + spell.getRange() 
				+ "\n\nComponents: " + spell.getComponents()
				+ "\n\nDuration: " + spell.getDuration());
		} else{
			lblSpellType.setText("ERROR");
			textArea.setText("SPELL NOT FOUND");
		}
		
		if (CharacterItems.getPreparedSpells().contains(spellName)) {
			setBackground(new Color(34, 139, 34));
			btnPrepare.setText("Prepared!");
		} else {
			setBackground(new Color(192, 192, 192));
			btnPrepare.setText("Prepare");
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
			CharacterItems.prepareSpell(spell.getName());
		} else {
			setBackground(new Color(192, 192, 192));
			btnPrepare.setText("Prepare");
			CharacterItems.unprepareSpell(spell.getName());
		}
		FileSystem.saveCharItems();
	}
	
	/**
	 * Removes the spell card from the parent panel
	 */
	public void removeSelf()
	{
		UserSpellWindow.removeSpell(this);
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
