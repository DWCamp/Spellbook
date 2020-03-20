package helperClasses;

import javax.swing.JFrame;

public abstract class SpellBrowser extends JFrame {

	private static final long serialVersionUID = 1L;
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
	public abstract void reset();

}
