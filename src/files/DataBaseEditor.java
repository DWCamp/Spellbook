package files;

import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import guiPanels.ClassPanel;
import guiPanels.SpellPanel;
import helperClasses.Feat;
import helperClasses.PClass;
import helperClasses.SortedObjectList;
import helperClasses.SortedStringList;
import helperClasses.SpellFE;

import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JTabbedPane;

/**
 * A utility I made to make editing the .txt resource files easier <br>
 * It displays all classes and spells and allows a user to add/delete and edit entries
 * @author Daniel Campman
 * @version September 3, 2016
 */
@SuppressWarnings("serial")
public class DataBaseEditor extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPaneClasses;
	private JTabbedPane tabbedPane;
	private JPanel[] panelArray;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class_List.load();
					Spell_List.loadFE();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					DataBaseEditor frame = new DataBaseEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame
	 */
	public DataBaseEditor() {
		setResizable(false);
		setTitle("Database Editor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 725, 677);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPaneClasses = new JScrollPane();
		scrollPaneClasses.setBounds(5, 5, 381, 150);
		contentPane.add(scrollPaneClasses);
		
		panelArray = new JPanel[10];
		
		JPanel panelClasses = new JPanel();
		panelClasses.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent arg0) {
				super.componentRemoved(arg0);
				getThis().repaint();
			}
		});
		scrollPaneClasses.setViewportView(panelClasses);
		panelClasses.setLayout(new GridLayout(0, 1, 0, 3));
		
		for(PClass element : Class_List.getClasses())
		{
			ClassPanel newPanel = new ClassPanel(element);
			panelClasses.add(newPanel);
		}

		JButton btnSaveClasses = new JButton("Save Classes");
		btnSaveClasses.setBounds(10, 160, 152, 23);
		contentPane.add(btnSaveClasses);
		
		JButton btnNewClass = new JButton("New Class");
		btnNewClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClassPanel newPanel = new ClassPanel(new PClass(
						"", 0, 0, new Feat[0][0], 0, new ArrayList<PClass>()));
				panelClasses.add(newPanel);
				getThis().repaint();
			}
		});
		btnNewClass.setBounds(285, 160, 89, 23);
		contentPane.add(btnNewClass);
		
		JButton btnNewSpell = new JButton("New Spell");
		btnNewSpell.setBounds(605, 614, 89, 23);
		contentPane.add(btnNewSpell);
		
		JButton btnSaveSpells = new JButton("Save Spells");
		btnSaveSpells.setBounds(5, 614, 152, 23);
		contentPane.add(btnSaveSpells);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(5, 206, 704, 397);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPaneCantrips = new JScrollPane();
		tabbedPane.addTab("Cantrips", null, scrollPaneCantrips, null);
		
		JPanel panelCantrips = new JPanel();
		panelCantrips.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneCantrips.setViewportView(panelCantrips);
		panelCantrips.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel1 = new JScrollPane();
		tabbedPane.addTab("Level 1", null, scrollPaneLevel1, null);
		
		JPanel panelLevel1 = new JPanel();
		panelLevel1.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel1.setViewportView(panelLevel1);
		panelLevel1.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel2 = new JScrollPane();
		tabbedPane.addTab("Level 2", null, scrollPaneLevel2, null);
		
		JPanel panelLevel2 = new JPanel();
		panelLevel2.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel2.setViewportView(panelLevel2);
		panelLevel2.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel3 = new JScrollPane();
		tabbedPane.addTab("Level 3", null, scrollPaneLevel3, null);
		
		JPanel panelLevel3 = new JPanel();
		panelLevel3.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel3.setViewportView(panelLevel3);
		panelLevel3.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel4 = new JScrollPane();
		tabbedPane.addTab("Level 4", null, scrollPaneLevel4, null);
		
		JPanel panelLevel4 = new JPanel();
		panelLevel4.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel4.setViewportView(panelLevel4);
		panelLevel4.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel5 = new JScrollPane();
		tabbedPane.addTab("Level 5", null, scrollPaneLevel5, null);
		
		JPanel panelLevel5 = new JPanel();
		panelLevel5.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel5.setViewportView(panelLevel5);
		panelLevel5.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel6 = new JScrollPane();
		tabbedPane.addTab("Level 6", null, scrollPaneLevel6, null);
		
		JPanel panelLevel6 = new JPanel();
		panelLevel6.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel6.setViewportView(panelLevel6);
		panelLevel6.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel7 = new JScrollPane();
		tabbedPane.addTab("Level 7", null, scrollPaneLevel7, null);
		
		JPanel panelLevel7 = new JPanel();
		panelLevel7.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel7.setViewportView(panelLevel7);
		panelLevel7.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel8 = new JScrollPane();
		tabbedPane.addTab("Level 8", null, scrollPaneLevel8, null);
		
		JPanel panelLevel8 = new JPanel();
		panelLevel8.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel8.setViewportView(panelLevel8);
		panelLevel8.setLayout(new GridLayout(0, 1, 0, 10));
		
		JScrollPane scrollPaneLevel9 = new JScrollPane();
		tabbedPane.addTab("Level 9", null, scrollPaneLevel9, null);
		
		JPanel panelLevel9 = new JPanel();
		panelLevel9.addContainerListener(new ContainerAdapter() {
			@Override
			public void componentRemoved(ContainerEvent e) {
				getThis().repaint();
			}
		});
		scrollPaneLevel9.setViewportView(panelLevel9);
		panelLevel9.setLayout(new GridLayout(0, 1, 0, 10));
		
		panelArray[0] = panelCantrips;
		panelArray[1] = panelLevel1;
		panelArray[2] = panelLevel2;
		panelArray[3] = panelLevel3;
		panelArray[4] = panelLevel4;
		panelArray[5] = panelLevel5;
		panelArray[6] = panelLevel6;
		panelArray[7] = panelLevel7;
		panelArray[8] = panelLevel8;
		panelArray[9] = panelLevel9;
		
		JButton btnSanitizeClassList = new JButton("Sanitize Class List");
		btnSanitizeClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (JPanel levelPanel : panelArray) {
					for (Component comp : levelPanel.getComponents()) {
						SpellPanel panel = (SpellPanel) comp;
						panel.sanitize();
					}
				}
			}
		});
		btnSanitizeClassList.setBounds(167, 614, 133, 23);
		contentPane.add(btnSanitizeClassList);
		
		JScrollPane scrollPaneClassAlt = new JScrollPane();
		scrollPaneClassAlt.setBounds(396, 5, 313, 150);
		contentPane.add(scrollPaneClassAlt);
		
		JPanel panelClassAlt = new JPanel();
		scrollPaneClassAlt.setViewportView(panelClassAlt);
		panelClassAlt.setLayout(new GridLayout(0, 1, 0, 3));
		
		JButton btnSomething = new JButton("Something");
		btnSomething.setBounds(605, 160, 89, 23);
		contentPane.add(btnSomething);
		
		for (int i = 0; i < 10; i++) {
			for (SpellFE spell : Spell_List.getFESpellsOfLevel(i)) {
				panelArray[i].add(new SpellPanel(spell));
			}
		}
																	//Action Listeners
		btnSaveClasses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SortedStringList sortedList = new SortedStringList();
				for (Component element : panelClasses.getComponents()) {
					ClassPanel classPanel = (ClassPanel) element;
					PClass panelClass = classPanel.getClassData();
					sortedList.add(classPanel.getName() 
							+ "," + panelClass.getHitDie()
							+ "," + panelClass.getMaxSpellLevel()
							+ "," + panelClass.printFeats()
							+ "," + panelClass.getSubClassAt()
							+ "," + panelClass.printSubClasses());
				}
				FileSystem.saveClassList(sortedList.toArray());
			}
		});
		
		btnSaveSpells.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveSpells();
			}
		});
		
		btnNewSpell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int index = tabbedPane.getSelectedIndex();
				panelArray[index].add(
						new SpellPanel(new SpellFE()));
				getThis().repaint();
			}
		});
	}

	private JFrame getThis()
	{
		return this;
	}
	
	/**
	 * Saves the spell list
	 */
	public void saveSpells() {
		ArrayList<ArrayList<SpellFE>> spellList = new ArrayList<ArrayList<SpellFE>>();
		SortedObjectList sortedSpells = new SortedObjectList();

		for (JPanel levelPanel : panelArray) {
			for (Component element : levelPanel.getComponents()) {
				SpellPanel spellPanel = (SpellPanel) element;
				sortedSpells.add(spellPanel.toSpell());
			}
			spellList.add(sortedSpells.toArrayList());
			sortedSpells.clear();
		}
		FileSystem.saveFESpellList(spellList); 
	}
}
