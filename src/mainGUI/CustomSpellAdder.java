package mainGUI;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import files.FileSystem;
import files.Spell_List;
import guiPanels.CustomSpellPanel;
import helperClasses.SortedSpellList;
import helperClasses.Spell;
import userData.Settings;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class CustomSpellAdder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSpells;

	/**
	 * Create the frame.
	 */
	public CustomSpellAdder() {
		double scaleFactor = Settings.getResizeFactor();
		//double scaleFactor = 1;
		
		setResizable(false);
		setTitle("Custom Spells");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 
				(int)(331 * scaleFactor), 
				(int)(300 * scaleFactor));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 5));
		setContentPane(contentPane);
		
		try {
			Image image = new ImageIcon("Resources/custom.PNG").getImage();
			setIconImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
        panelSpells = new JPanel();
		panelSpells.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(panelSpells);
		panelSpells.setLayout(new GridLayout(0, 1, 0, 10));
		
		panelSpells.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				repaint();
				revalidate();
				saveSpells();
			}
		});
		
		for (Spell spell : Spell_List.getCustomSpells())
		{
			panelSpells.add(new CustomSpellPanel(spell));
		}
		
		String[] blank = {""};
		Spell blankSpell = new Spell("", blank, 
				"", 0, "", "", "", "", "");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnAddSpell = new JButton("Add Spell");
		btnAddSpell.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		panel.add(btnAddSpell, BorderLayout.EAST);
		btnAddSpell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelSpells.add(new CustomSpellPanel(blankSpell));
				repaint();
				revalidate();
			}
		});
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		panel.add(btnNewButton, BorderLayout.WEST);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSpells();
			}
		});

	}
	
	/**
	 * Saves the spells in the window to file
	 */
	public void saveSpells() {
		ArrayList<SortedSpellList> sortedSpellList = new ArrayList<SortedSpellList>();
		for(int i = 0; i < 10; i++)
		{
			sortedSpellList.add(new SortedSpellList());
		}
		
		for(Component element : panelSpells.getComponents())
		{
			Spell spell = ((CustomSpellPanel)element).toSpell();
			sortedSpellList.get(spell.getLevel()).add(spell);
		}
		
		ArrayList<ArrayList<Spell>> spellList = new ArrayList<ArrayList<Spell>>();
		for(int i = 0; i < 10; i++)
		{
			spellList.add(sortedSpellList.get(i).toArrayList());
		}
		FileSystem.saveCustomSpellList(spellList);
	}
	
	/**
	 * Refreshes the contents of the window
	 */
	public void reset()
	{
		panelSpells.removeAll();
		
		for (Spell spell : Spell_List.getCustomSpells())
		{
			panelSpells.add(new CustomSpellPanel(spell));
		}
	}
}
