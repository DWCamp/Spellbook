package gui;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import files.CharacterItems;
import files.FileSystem;
import files.Spell_List;
import guiPanels.SpellCard;
import helperClasses.Spell;
import helperClasses.SpellBrowser;
import helperClasses.gameVersion;
import gui.Settings;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Point;
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
import java.awt.Frame;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.Color;

/**
 * The main window of the application. Displays saved spells and 
 * has a menu for the accessing the functions and menus of the 
 * application.
 * @author Daniel Campman
 */
@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private static JPanel panelAllSpells;
	private static JPanel[] spellPanels;
	private static JTabbedPane tabbedPane;
	
	private static MainWindow window;
	private static Settings settings;
	private static FECustomSpellAdder customSpells;
	
	private static SpellBrowserFE browserFE;
	private static SpellBrowserPF browserPF;
	private static SpellBrowser activeBrowser;
	
	private JMenuItem mntmPreferences;
	private JMenuBar menuBar;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				FileSystem.saveCharItems();
				System.exit(0);
			}
		});
		
		window = this;
		this.setLocation(100, 100);
		
		Settings.addResizeListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				draw();
				browserFE.refresh();
				browserPF.refresh();
				settings.refresh();
				customSpells.refresh();
			}
		});
		
		settings = new Settings();
		browserFE = new SpellBrowserFE(this);
		browserPF = new SpellBrowserPF(this);
		 if(Settings.getVersion().equals(gameVersion.FIFTH_EDITION))
		 {
			 activeBrowser = browserFE;
		 } else {
			 activeBrowser = browserPF;
		 }
		customSpells = new FECustomSpellAdder();
		
		try {
			Image image = new ImageIcon("Resources/main.PNG").getImage();
			setIconImage(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setResizable(false);
		setTitle("Spellbook");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230,230,230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		draw(); //draws the elements of the window
	}
	
	/**
	 * Draws the window
	 */
	public void draw(){
		double scaleFactor = Settings.getResizeFactor();
		//scaleFactor = 1;
		
		setBounds(getX(), getY(),
				(int)(641 * scaleFactor),
				(int)(385 * scaleFactor) + 60);
		
		contentPane.removeAll();
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds((int)(10 * scaleFactor),
				(int)(35 * scaleFactor),
				(int)(615 * scaleFactor),
				(int)(365 * scaleFactor) - (int)(30 * (scaleFactor - 1)));
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
		
		scrollPaneAllSpells.getHorizontalScrollBar().setUnitIncrement(25);
		
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
		
		panelAllSpells.setLayout(new GridLayout(1, 2, 15, 0));
		
		JLabel lblClickbrowseSpells = new JLabel("Click \"Browse Spells\" "
				+ "to add spells to your spellbook");
		lblClickbrowseSpells.setFont(new Font("Tahoma", Font.PLAIN, (int)(11 * scaleFactor)));
		lblClickbrowseSpells.setForeground(SystemColor.controlShadow);
		lblClickbrowseSpells.setHorizontalAlignment(SwingConstants.CENTER);
		panelAllSpells.add(lblClickbrowseSpells);
		
		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(100, 0, 0));
		menuBar.setForeground(new Color(255,255,255));
		menuBar.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		menuBar.setBorderPainted(false);
		menuBar.setBounds(0, 0, 
				(int)(635 * scaleFactor), 
				(int)(24 * scaleFactor));
		contentPane.add(menuBar);
		
		JMenu mnSpells = new JMenu("Spells");
		mnSpells.setBackground(new Color(128, 0, 0));
		mnSpells.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		menuBar.add(mnSpells);
		
		JMenuItem mntmBrowseSpells = new JMenuItem("Browse Spells");
		mntmBrowseSpells.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mntmBrowseSpells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int CWsetting = Settings.getCenterFrames();
				if (!activeBrowser.isVisible()) {
					//activeBrowser.reset();
					activeBrowser.setVisible(true);
					if (CWsetting == 1) {
						activeBrowser.setLocation(new Point(getX() + 30, getY() + 30));
					}
				}
				if (CWsetting == 2) {
					activeBrowser.setLocation(new Point(getX() + 30, getY() + 30));
				}
				activeBrowser.setState(Frame.NORMAL);
				activeBrowser.toFront();
			}
		});
		mntmBrowseSpells.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnSpells.add(mntmBrowseSpells);
		
		JMenuItem mntmCustomSpells = new JMenuItem("Custom Spells");
		mntmCustomSpells.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mntmCustomSpells.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mnSpells.add(mntmCustomSpells);
		mntmCustomSpells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int CWsetting = Settings.getCenterFrames();
				if (!customSpells.isVisible()) {
					customSpells.reset();
					customSpells.setVisible(true);
					if (CWsetting == 1) {
						customSpells.setLocation(new Point(getX() + 30, getY() + 30));
					}
				}
				if (CWsetting == 2) {
					customSpells.setLocation(new Point(getX() + 30, getY() + 30));
				}
				customSpells.setState(Frame.NORMAL);
				customSpells.toFront();
			}
		});
		
		JMenuItem mntmClearSpells = new JMenuItem("Clear All Spells");
		mntmClearSpells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int remove = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to remove all spells from "
						+ "your spellbook?\n\n(This only clears your learned "
						+ "and prepared spells. The master spell list and "
						+ "custom spell list will be unaffected)", 
						"Clear All Spells", JOptionPane.YES_NO_OPTION);
				if (remove == JOptionPane.YES_OPTION) {
					for (Component component : panelAllSpells.getComponents()) {
						SpellCard panel = (SpellCard) component;
						removeSpell(panel);
					}
				}
			}
		});
		mntmClearSpells.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mnSpells.add(mntmClearSpells);
		
		JMenu mnWindows = new JMenu("Windows");
		mnWindows.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		menuBar.add(mnWindows);
		
		JMenuItem mntmGatherWindows = new JMenuItem("Gather Windows");
		mntmGatherWindows.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_MASK));
		mntmGatherWindows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeBrowser.setLocation(getX()+((getWidth()-activeBrowser.getWidth())/2),
						getY()+((getHeight()-activeBrowser.getHeight())/2));
				activeBrowser.toFront();
				customSpells.setLocation(getX()+((getWidth()-customSpells.getWidth())/2),
						getY()+((getHeight()-customSpells.getHeight())/2));
				customSpells.toFront();	
				settings.setLocation(getX()+((getWidth()-settings.getWidth())/2),
						getY()+((getHeight()-settings.getHeight())/2));
				settings.toFront();
			}
		});
		mntmGatherWindows.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mnWindows.add(mntmGatherWindows);
		
		JMenuItem mntmShowAll = new JMenuItem("Show All");
		mntmShowAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_MASK));
		mntmShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeBrowser.setVisible(true);
				activeBrowser.toFront();
				activeBrowser.setState(Frame.NORMAL);
				customSpells.setVisible(true);
				customSpells.toFront();
				customSpells.setState(Frame.NORMAL);
				settings.setVisible(true);
				settings.toFront();
				settings.setState(Frame.NORMAL);
				
				if (Settings.getCenterFrames() > 0) {
					activeBrowser.setLocation(new Point(getX() + 30, getY() + 30));
					customSpells.setLocation(new Point(getX() + 30, getY() + 30));
					settings.setLocation(new Point(getX() + 30, getY() + 30));
				}
				
			}
		});
		
		JMenuItem mntmHideAll = new JMenuItem("Hide All");
		mntmHideAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mntmHideAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeBrowser.setVisible(false);
				customSpells.setVisible(false);
				settings.setVisible(false);
			}
		});
		mntmHideAll.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mnWindows.add(mntmHideAll);
		mntmShowAll.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mnWindows.add(mntmShowAll);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		menuBar.add(mnHelp);
		
		mntmPreferences = new JMenuItem("Settings");
		mnHelp.add(mntmPreferences);
		mntmPreferences.setFont(new Font("Segoe UI", Font.PLAIN, (int)(11 * scaleFactor)));
		mntmPreferences.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		mntmPreferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int CWsetting = Settings.getCenterFrames();
				if (!settings.isVisible()) {
					settings.refresh();
					settings.setVisible(true);
					if (CWsetting == 1) {
						settings.setLocation(new Point(getX() + 30, getY() + 30));
					}
				}
				if (CWsetting == 2) {
					settings.setLocation(new Point(getX() + 30, getY() + 30));
				}
				settings.setState(Frame.NORMAL);
				settings.toFront();
			}
		});
	
		for (JPanel panel : spellPanels) {
			panel.setLayout(new GridLayout(1, 2, 15, 0));
		}
		
		ArrayList<String> spellsLearned = new ArrayList<String>();
		if (Settings.getVersion() == gameVersion.FIFTH_EDITION)
		{
			spellsLearned = CharacterItems.getLearnedFESpells();
		} else if (Settings.getVersion() == gameVersion.PATHFINDER)
		{
			spellsLearned = CharacterItems.getLearnedPFSpells();
		}
		
		if (spellsLearned.size() > 0) {  //Removes the placeholder message if there are spells to add
			panelAllSpells.remove(0);
		}
		
		if (Settings.getVersion() == gameVersion.FIFTH_EDITION) {
			for (String spell : spellsLearned) {
				int level = Spell_List.getFESpellLevel(spell);
				if (level >= 0) {
					tabbedPane.setEnabledAt(level + 1, true);
					tabbedPane.getTabComponentAt(level + 1).setEnabled(true);
					panelAllSpells.add(new SpellCard(spell));
					spellPanels[level].add(new SpellCard(spell));
				} else {
					CharacterItems.unlearnFESpell(spell);
				}
			}
		} else if (Settings.getVersion() == gameVersion.PATHFINDER) {
			for (String spell : spellsLearned) {
				int level = Spell_List.getPFSpellLevel(spell);
				if (level >= 0) {
					tabbedPane.setEnabledAt(level + 1, true);
					tabbedPane.getTabComponentAt(level + 1).setEnabled(true);
					panelAllSpells.add(new SpellCard(spell));
					spellPanels[level].add(new SpellCard(spell));
				} else {
					CharacterItems.unlearnPFSpell(spell);
				}
			}
		}

		
		repaint();
	}
	
	/**
	 * Refreshes the cards in all panels
	 */
	public void refreshSpells()
	{
		panelAllSpells.removeAll();
		int i = 0;
		while(++i < 10)
		{
			tabbedPane.setEnabledAt(i, false);
			tabbedPane.getTabComponentAt(i).setEnabled(false);
		}
		for (JPanel panel : spellPanels) {
			panel.removeAll();
		}
		if (Settings.getVersion() == gameVersion.FIFTH_EDITION){
			ArrayList<String> spellsLearned = CharacterItems.getLearnedFESpells();
			for (String spell : spellsLearned) {
				int level = Spell_List.getFESpell(spell).getLevel();
				tabbedPane.setEnabledAt(level + 1, true);
				tabbedPane.getTabComponentAt(level + 1).setEnabled(true);
				panelAllSpells.add(new SpellCard(spell));
				spellPanels[level].add(new SpellCard(spell));
			}
		} else if (Settings.getVersion() == gameVersion.PATHFINDER){
			ArrayList<String> spellsLearned = CharacterItems.getLearnedPFSpells();
			for (String spell : spellsLearned) {
				int level = Spell_List.getPFSpellLevel(spell);
				tabbedPane.setEnabledAt(level + 1, true);
				tabbedPane.getTabComponentAt(level + 1).setEnabled(true);
				panelAllSpells.add(new SpellCard(spell));
				spellPanels[level].add(new SpellCard(spell));
			}
		}
	}
	
	
	
	/**
	 * Returns the location of the window
	 * @return {@code Point} UserSpellWindow's location
	 */
	public static Point getWindowLocation() {
		return window.getLocation(null);
	}
	
	/**
	 * Removes a SpellCard from the menu and the User's spellbook
	 * @param card
	 */
	public static void removeSpell(SpellCard card)
	{
		Spell spell = card.getSpell();
		int spellLevel = spell.getLevel();
		panelAllSpells.remove(card);
		spellPanels[card.getSpell().getLevel()].remove(card);

		CharacterItems.unlearnFESpell(spell.getName());
		CharacterItems.unprepareFESpell(spell.getName());
		
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
	
	/**
	 * Updates the version of the game that is being played
	 */
	public static void updateGameVersion()
	{
		activeBrowser.dispose();
		switch (Settings.getVersion()){
		case FIFTH_EDITION:
			activeBrowser = browserFE;
			window.refreshSpells();
			break;
		case PATHFINDER:
			activeBrowser = browserPF;
			window.refreshSpells();
			break;
		default:
			break;		
		}
		window.refreshSpells();
		activeBrowser.refresh();
	}
}