package files;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guiPanels.SpellPanel;

import javax.swing.JScrollPane;
import java.awt.GridLayout;

public class CustomSpellAdder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CustomSpellAdder() {
		setTitle("Custom Spells");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 477, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelSpells = new JPanel();
		scrollPane.setViewportView(panelSpells);
		panelSpells.setLayout(new GridLayout(0, 1, 0, 10));
		String[] blank = {"Classes"};
		Spell blankSpell = new Spell("Name", blank, "Type", 0, 
				"Casting Time", "Range", "Components", 
				"Duration", "Description");
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
		panelSpells.add(new SpellPanel(blankSpell));
	}

	/**
	 * Refreshes the contents of the window
	 */
	public void reset()
	{
		
	}
}
