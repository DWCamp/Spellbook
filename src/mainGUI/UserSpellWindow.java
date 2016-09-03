package mainGUI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import files.FileSystem;
import files.Spell;
import files.Spell_List;
import guiPanels.SpellCard;
import userData.CharacterItems;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Font;

@SuppressWarnings("serial")
public class UserSpellWindow extends JFrame {

	private JPanel contentPane;
	private static JPanel panelAllSpells;
	private static JPanel[] spellPanels;
	private static JTabbedPane tabbedPane;
	
	private static UserSpellWindow window;
	private static SpellBrowser browser;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//					Spell_List.load();
//					CharacterItems.loadItems();
//					window = new UserSpellWindow();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public UserSpellWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				FileSystem.saveCharItems();
			}
		});
		
		double scaleFactor = SpellBookLauncher.getScaleFactor();
		
		browser = new SpellBrowser(this);
		
//		try {
//			Image image = new ImageIcon(UserSpellWindow.class.getResource("icon.PNG")).getImage();
//			setIconImage(image);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		setResizable(false);
		setTitle("Spellbook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int)(100 * scaleFactor),
				(int)(100 * scaleFactor),
				(int)(641 * scaleFactor),
				(int)(431 * scaleFactor) - (int)(65 * (scaleFactor-1)));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds((int)(10 * scaleFactor),
				(int)(11 * scaleFactor),
				(int)(615 * scaleFactor),
				(int)(355 * scaleFactor) - (int)(30 * (scaleFactor - 1)));
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPaneAllSpells = new JScrollPane();
		JScrollPane scrollPaneCantrips = new JScrollPane();
		JScrollPane scrollPaneLevel1 = new JScrollPane();
		JScrollPane scrollPaneLevel2 = new JScrollPane();
		JScrollPane scrollPaneLevel3 = new JScrollPane();
		JScrollPane scrollPaneLevel4 = new JScrollPane();
		JScrollPane scrollPaneLevel5 = new JScrollPane();
		JScrollPane scrollPaneLevel6 = new JScrollPane();
		JScrollPane scrollPaneLevel7 = new JScrollPane();
		JScrollPane scrollPaneLevel8 = new JScrollPane();
		JScrollPane scrollPaneLevel9 = new JScrollPane();
		
		tabbedPane.addTab("All spells", null, scrollPaneAllSpells, null);
		tabbedPane.addTab("Cantrips", null, scrollPaneCantrips, null);
		tabbedPane.addTab("Level 1", null, scrollPaneLevel1, null);
		tabbedPane.addTab("Level 2", null, scrollPaneLevel2, null);
		tabbedPane.addTab("Level 3", null, scrollPaneLevel3, null);
		tabbedPane.addTab("Level 4", null, scrollPaneLevel4, null);
		tabbedPane.addTab("Level 5", null, scrollPaneLevel5, null);
		tabbedPane.addTab("Level 6", null, scrollPaneLevel6, null);
		tabbedPane.addTab("Level 7", null, scrollPaneLevel7, null);
		tabbedPane.addTab("Level 8", null, scrollPaneLevel8, null);
		tabbedPane.addTab("Level 9", null, scrollPaneLevel9, null);
		
		JLabel lab = new JLabel("All Spells");
		Font tabFont = new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor));
		lab.setFont(tabFont);
		lab.setPreferredSize(new Dimension((int)(50 * scaleFactor),
	    		(int)(20 * scaleFactor)));
	    tabbedPane.setTabComponentAt(0, lab);
	    
		String[] tabNames = {"All Spells", "Cantrips", "Level 1", "Level 2",
				"Level 3", "Level 4", "Level 5", "Level 6", "Level 7", 
				"Level 8", "Level 9"};
		
		for (int i = 1; i <= 10; i++) {
			lab = new JLabel(tabNames[i]);
			lab.setFont(tabFont);
			lab.setEnabled(false);
		    lab.setPreferredSize(new Dimension((int)(40 * scaleFactor),
		    		(int)(20 * scaleFactor)));
		    tabbedPane.setTabComponentAt(i, lab);
		    tabbedPane.setEnabledAt(i, false);
		}
		
		panelAllSpells = new JPanel();
		scrollPaneAllSpells.setViewportView(panelAllSpells);
		JPanel panelCantrips = new JPanel();
		scrollPaneCantrips.setViewportView(panelCantrips);
		JPanel panelLevel1 = new JPanel();
		scrollPaneLevel1.setViewportView(panelLevel1);
		JPanel panelLevel2 = new JPanel();
		scrollPaneLevel2.setViewportView(panelLevel2);
		JPanel panelLevel3 = new JPanel();
		scrollPaneLevel3.setViewportView(panelLevel3);
		JPanel panelLevel4 = new JPanel();
		scrollPaneLevel4.setViewportView(panelLevel4);
		JPanel panelLevel5 = new JPanel();
		scrollPaneLevel5.setViewportView(panelLevel5);
		JPanel panelLevel6 = new JPanel();
		scrollPaneLevel6.setViewportView(panelLevel6);
		JPanel panelLevel7 = new JPanel();
		scrollPaneLevel7.setViewportView(panelLevel7);
		JPanel panelLevel8 = new JPanel();
		scrollPaneLevel8.setViewportView(panelLevel8);
		JPanel panelLevel9 = new JPanel();
		scrollPaneLevel9.setViewportView(panelLevel9);
		
		JPanel[] tempArray = {panelCantrips, 
				panelLevel1, panelLevel2, panelLevel3, 
				panelLevel4, panelLevel5, panelLevel6, 
				panelLevel7, panelLevel8, panelLevel9};
		spellPanels = tempArray;
		
		JButton btnBrowseSpells = new JButton("Browse Spells");
		btnBrowseSpells.setFont(new Font("Tahoma", Font.PLAIN, (int)(13 * scaleFactor)));
		btnBrowseSpells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!browser.isVisible()) {
					browser.reset();
					browser.setVisible(true);
				}
				browser.toFront();
			}
		});
		btnBrowseSpells.setBounds((int)(447 * scaleFactor),
				(int)(370 * scaleFactor) - (int)(35 * (scaleFactor - 1)),
				(int)(178 * scaleFactor),
				(int)(27 * scaleFactor));
		btnBrowseSpells.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		contentPane.add(btnBrowseSpells);
		panelAllSpells.setLayout(new GridLayout(1, 2, 15, 0));
		
		JLabel lblClickbrowseSpells = new JLabel("Click \"Browse Spells\" "
				+ "to add spells to your spellbook");
		lblClickbrowseSpells.setForeground(SystemColor.controlShadow);
		lblClickbrowseSpells.setHorizontalAlignment(SwingConstants.CENTER);
		panelAllSpells.add(lblClickbrowseSpells);
		for (JPanel panel : spellPanels) {
			panel.setLayout(new GridLayout(1, 2, 15, 0));
		}

		ArrayList<String> spellsLearned = CharacterItems.getLearnedSpells();
		if (spellsLearned.size() > 0) {  //Removes the placeholder message if there are spells to add
			panelAllSpells.remove(0);
		}
		for (String spell : spellsLearned) {
			int level = Spell_List.getSpellLevel(spell);
			tabbedPane.setEnabledAt(level + 1, true);
			tabbedPane.getTabComponentAt(level + 1).setEnabled(true);
			panelAllSpells.add(new SpellCard(spell));
			spellPanels[level].add(new SpellCard(spell));
		}
		
		window = this;
	}
	
	/**
	 * Refreshes the cards in all panels
	 */
	public void refresh()
	{
		panelAllSpells.removeAll();
		for (JPanel panel : spellPanels) {
			panel.removeAll();
		}
		ArrayList<String> spellsLearned = CharacterItems.getLearnedSpells();
		for (String spell : spellsLearned) {
			int level = Spell_List.getSpell(spell).getLevel();
			tabbedPane.setEnabledAt(level + 1, true);
			tabbedPane.getTabComponentAt(level + 1).setEnabled(true);
			panelAllSpells.add(new SpellCard(spell));
			spellPanels[level].add(new SpellCard(spell));
		}
	}
	
	/**
	 * Removes a SpellCard from the menu and the User's spellbook
	 * @param card
	 */
	public static void removeSpell(SpellCard card)
	{
		Spell spell = card.getSpell();
		int spellLevel = spell.getLevel();
		for (Component comp : panelAllSpells.getComponents()) {
			if (((SpellCard) comp).equals(card)) {
				panelAllSpells.remove(comp);
			}
		}
		for (Component comp : spellPanels[card.getSpell().getLevel()]
				.getComponents()) {
			if (((SpellCard) comp).equals(card)) {
				spellPanels[spellLevel].remove(comp);
			}
		}
		CharacterItems.unlearnSpell(spell.getName());
		if (panelAllSpells.getComponents().length == 0) {
			JLabel lblClickbrowseSpells = new JLabel("Click \"Browse Spells\" " 
					+ "to add spells to your spellbook");
			lblClickbrowseSpells.setForeground(SystemColor.controlShadow);
			lblClickbrowseSpells.setHorizontalAlignment(SwingConstants.CENTER);
			panelAllSpells.add(lblClickbrowseSpells);
		}
		if (spellPanels[spellLevel].getComponentCount() == 0) {
			tabbedPane.setEnabledAt(spellLevel + 1, false);
			tabbedPane.getTabComponentAt(spell.getLevel() + 1).setEnabled(false);;
			if (tabbedPane.getSelectedIndex() == spellLevel + 1) {
				tabbedPane.setSelectedIndex(0);
			}
		}
		window.repaint();
	}
	
	/**
	 * Sets the prepared trait of a SpellCard across all menus
	 * @param card The card to be set
	 * @param state The cards new prepared state
	 */
	public static void setSpellPrepared(Spell spell, boolean state)
	{
		for (Component comp : panelAllSpells.getComponents()) {
			SpellCard compCard = (SpellCard)comp;
			if (compCard.getSpell().equals(spell)) {
				compCard.setPrepared(state);
			}
		}
		for (Component comp : spellPanels[spell.getLevel()]
				.getComponents()) {
			SpellCard compCard = (SpellCard)comp;
			if (compCard.getSpell().equals(spell)) {
				compCard.setPrepared(state);
			}
		}
		window.repaint();
	}
}
