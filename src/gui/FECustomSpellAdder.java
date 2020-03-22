package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import alerts.ClassPicker;
import model.FileSystem;
import guiPanels.FECustomSpellPanel;
import helperClasses.SortedObjectList;
import model.Spell_FE;

import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * A window for creating custom spells 
 * @author Daniel Campman
 */
public class FECustomSpellAdder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSpells;
	
	private JButton btnAddSpell;
	private JButton btnSave;
	
	private boolean deleted;
	private static ClassPicker pickerWindow;

	/**
	 * Create the frame.
	 */
	public FECustomSpellAdder() {
		setResizable(false);
		setTitle("Custom Spells");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		deleted = false;
		pickerWindow = null;
		
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
				deleted = true;
			}
		});

		Spell_FE[] bs_list = {};
		for (Spell_FE spell : bs_list)
		{
			panelSpells.add(new FECustomSpellPanel(spell));
		}
		
		String[] blank = {""};
		Spell_FE blankSpell = new Spell_FE("", blank,
				"", 0, "", "", "", "", "");
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		btnAddSpell = new JButton("Add Spell");
		panelButtons.add(btnAddSpell, BorderLayout.WEST);
		btnAddSpell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panelSpells.add(new FECustomSpellPanel(blankSpell));
				repaint();
				revalidate();
			}
		});
		
		btnSave = new JButton("Save");
		panelButtons.add(btnSave, BorderLayout.EAST);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveSpells();
				for (Component component : panelSpells.getComponents()) {
					FECustomSpellPanel spell = (FECustomSpellPanel)(component);
					spell.setSaved(true);
				}
			}
		});

		refresh();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				boolean unsaved = false;
				for (Component component : panelSpells.getComponents()) {
					FECustomSpellPanel spell = (FECustomSpellPanel)(component);
					if (!spell.isSaved()) {
						unsaved = true;
					}
				}
				if (unsaved || deleted) {
					int remove = JOptionPane.showConfirmDialog(null, 
							"You have made changes to this spell list which "
							+ "have not been saved. "
							+ "Would you like to save them now?", 
							"Save New Spells", JOptionPane.YES_NO_OPTION);
					if (remove == JOptionPane.YES_OPTION) {
						saveSpells();
					}
				}
				if (pickerWindow != null) {
					pickerWindow.dispose();
				}
				
			}
		});
		
		Point USW = MainWindow.getWindowLocation();
		setLocation(USW.x + 30, USW.y + 30);
	}
	
	/**
	 * Refreshes the scaling and content of the window
	 */
	public void refresh() {
		double scaleFactor = Settings.getResizeFactor();
		
		setBounds(getX(), getY(), 
				(int)(331 * scaleFactor), 
				(int)(300 * scaleFactor));
		
		btnAddSpell.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		
		for(Component comp : panelSpells.getComponents())
		{
			FECustomSpellPanel spellPanel = (FECustomSpellPanel)(comp);
			spellPanel.refresh();
		}
		
		if(pickerWindow != null)
		{
			pickerWindow.refresh();
		}
	}
	
	/**
	 * Saves the spells in the window to file
	 */
	public void saveSpells() {
		ArrayList<SortedObjectList<Spell_FE>> sortedSpellArrayList = new ArrayList<SortedObjectList<Spell_FE>>();
		for(int i = 0; i < 10; i++)
		{
			sortedSpellArrayList.add(new SortedObjectList<Spell_FE>());
		}
		
		for(Component element : panelSpells.getComponents())
		{
			Spell_FE spell = ((FECustomSpellPanel)element).toSpell();
			sortedSpellArrayList.get(spell.getLevel()).add(spell);
		}
		
		ArrayList<ArrayList<Spell_FE>> spellList = new ArrayList<ArrayList<Spell_FE>>();
		for(int i = 0; i < 10; i++)
		{
			spellList.add(new ArrayList<Spell_FE>());
			spellList.add(sortedSpellArrayList.get(i).toArrayList());
		}
		FileSystem.saveCustomFESpellList(spellList);
		//SpellList.updateFESpells();
		SpellBrowserFE.windowRefresh();
		deleted = false;
	}
	
	/**
	 * Refreshes the contents of the window
	 */
	public void reset()
	{
		panelSpells.removeAll();
		deleted = false;
		refresh();
		Spell_FE[] bs_list = {};
		for (Spell_FE spell : bs_list)
		{
			panelSpells.add(new FECustomSpellPanel(spell));
		}
	}
	
	/**
	 * Closes the current class picker window 
	 * and sets a new one as the current window
	 * @param cP The new classPicker window to be opened
	 */
	public static void newClassPicker(ClassPicker cP)
	{
		if (pickerWindow != null)
		{
			pickerWindow.dispose();
		}
		pickerWindow = cP;
	}
}
