package alerts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import files.Spell_List;
import gui.CustomSpellAdder;
import gui.Settings;
import helperClasses.SortedStringList;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.GridLayout;

public class ClassPicker extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelClassPanels;
	
	private ArrayList<ActionListener> aLList = new ArrayList<ActionListener>();
	private SortedStringList selectedClasses;
	private boolean mouseDown;

	/**
	 * Create the frame.
	 */
	public ClassPicker(String[] currentlySelected) {
		double scaleFactor = Settings.getResizeFactor();
		mouseDown = false;
		
		setResizable(false);
		setTitle("Select Classes");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 
				(int)(200*scaleFactor), 
				(int)(200*scaleFactor));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		CustomSpellAdder.newClassPicker(this);
		
		selectedClasses = new SortedStringList(currentlySelected);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.getVerticalScrollBar().setUnitIncrement((int)(5*scaleFactor));
		
		panelClassPanels = new JPanel();
		scrollPane.setViewportView(panelClassPanels);
		panelClassPanels.setLayout(new GridLayout(0, 1, 0, 1));
		
		for(String name : Spell_List.getFEClasses()){
			JLabel label = new JLabel(name);
			label.setFont(new Font("Tahoma", Font.BOLD, (int)(16 * scaleFactor)));
			if(selectedClasses.contains(name)) {
				label.setBackground(new Color(200,240,200));
			} else {
				label.setBackground(Color.LIGHT_GRAY);
			}
			label.setOpaque(true);
			panelClassPanels.add(label);
			label.addMouseListener(new MouseAdapter(){
				@Override
				public void mousePressed(MouseEvent e) {
					mouseDown = true;
				}
				@Override
				public void mouseExited(MouseEvent e) {
					mouseDown = false;
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					if(mouseDown)
					{
						if (selectedClasses.contains(label.getText())) {
							selectedClasses.remove(label.getText());
						}
						else {
							selectedClasses.add(label.getText());
						}
						if (label.getBackground().equals(Color.LIGHT_GRAY)) {
							label.setBackground(new Color(200,240,200));
						}
						else {
							label.setBackground(Color.LIGHT_GRAY);
						}
						notifyObservers();
					}
				}
			});
		}
	}
	
	/**
	 * Adds an actionListener which will be triggered 
	 * whenever a change has been made to the selected classes
	 * @param aL
	 */
	public void addActionListener(ActionListener aL)
	{
		aLList.add(aL);
	}
	
	/**
	 * Notifies listeners that the selected classes have changed
	 */
	private void notifyObservers()
	{
		for(ActionListener aL : aLList) {
			aL.actionPerformed(null);
		}
	}
	
	/**
	 * Returns the list of classes currently selected 
	 * @return SortedStringList
	 */
	public SortedStringList getSelectedClasses(){
		return selectedClasses;
	}
	
	/**
	 * refreshes the size of the window
	 */
	public void refresh(){
		double scaleFactor = Settings.getResizeFactor();
		
		setBounds(getX(), getY(), 
				(int)(200*scaleFactor), 
				(int)(200*scaleFactor));
		
		for(Component comp : panelClassPanels.getComponents()){
			comp.setFont(new Font("Tahoma", Font.BOLD, (int)(16 * scaleFactor)));
		}
	}
}
