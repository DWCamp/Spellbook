package files;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guiPanels.SpellPanel;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;

import javax.swing.JButton;

public class CustomSpellAdder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CustomSpellAdder() {
		setTitle("Custom Spells");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 477, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panelSpells = new JPanel();
		scrollPane.setViewportView(panelSpells);
		panelSpells.setLayout(new GridLayout(0, 1, 0, 10));
		
		panelSpells.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				repaint();
				revalidate();
			}
		});
		
		String[] blank = {"Classes"};
		Spell blankSpell = new Spell("Name", blank, "Type", 0, 
				"Casting Time", "Range", "Components", 
				"Duration", "Description");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddSpell = new JButton("Add Spell");
		panel.add(btnAddSpell, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Save");
		panel.add(btnNewButton, BorderLayout.WEST);
		btnAddSpell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelSpells.add(new SpellPanel(blankSpell));
				repaint();
				revalidate();
			}
		});
	}
	
	/**
	 * Refreshes the contents of the window
	 */
	public void reset()
	{
		
	}
}
