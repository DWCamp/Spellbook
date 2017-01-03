package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class SpellBrowser extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPane;
	protected static SpellBrowser self;

	/**
	 * Create the frame.
	 */
	public SpellBrowser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Browse spells");
		self = this;
	}
	
	public abstract void refresh();
	
	public abstract void WindowRefresh();
	
	public abstract void reset();

}
