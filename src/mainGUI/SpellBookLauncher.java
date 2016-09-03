package mainGUI;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import files.Class_List;
import files.Spell_List;
import userData.CharacterItems;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URI;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SpellBookLauncher extends JFrame {

	private static final long serialVersionUID = 1L;
	private static Dimension screenSize;
	private static double scaleFactor;
	private JPanel contentPane;
	private JTextArea textAreaError;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					System.out.println(screenSize.getWidth());
					// scaleFactor = screenSize.getWidth() / 1920.0;
					scaleFactor = 2;
					System.out.println(scaleFactor);
					
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					@SuppressWarnings("unused")
					SpellBookLauncher frame = new SpellBookLauncher();	
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SpellBookLauncher() {
		setTitle("Spellbook Launcher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int)(100 * scaleFactor), 
				(int)(100 * scaleFactor), 
				(int)(647 * scaleFactor), 
				(int)(380 * scaleFactor));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOhSnap = new JLabel("Oh snap!");
		lblOhSnap.setFont(new Font("Verdana", Font.BOLD, (int)(20 * scaleFactor)));
		lblOhSnap.setHorizontalAlignment(SwingConstants.CENTER);
		lblOhSnap.setBounds((int)(10 * scaleFactor), 
				(int)(11 * scaleFactor), 
				(int)(611 * scaleFactor), 
				(int)(36 * scaleFactor));
		contentPane.add(lblOhSnap);
		
		JTextPane txtMessage = new JTextPane();
		txtMessage.setEditable(false);
		txtMessage.setBackground(SystemColor.menu);
		txtMessage.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * scaleFactor)));
		txtMessage.setText("You really weren't supposed to see this. "
				+ "If you're seeing this window, that means some nasty bug "
				+ "has crawled into my code and is tearing it all apart, "
				+ "preventing the application from launching. Please copy "
				+ "the error message below and send it to me as a comment "
				+ "on my Reddit post.");
		txtMessage.setBounds((int)(10 * scaleFactor), 
				(int)(58 * scaleFactor), 
				(int)(611 * scaleFactor), 
				(int)(48 * scaleFactor));
		contentPane.add(txtMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds((int)(10 * scaleFactor), 
				(int)(152 * scaleFactor), 
				(int)(611 * scaleFactor), 
				(int)(141 * scaleFactor));
		contentPane.add(scrollPane);
		
		textAreaError = new JTextArea();
		textAreaError.setFont(new Font("Monospaced", Font.PLAIN, (int)(12 * scaleFactor)));
		textAreaError.setEditable(false);
		textAreaError.setLineWrap(true);
		textAreaError.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaError);
		
		JButton btnCopyMessage = new JButton("Copy error message");
		btnCopyMessage.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * scaleFactor)));
		btnCopyMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(new StringSelection(textAreaError.getText()), null);
				((JButton)arg0.getSource()).setText("Copied!");
			}
		});
		btnCopyMessage.setBounds((int)(470 * scaleFactor), 
				(int)(304 * scaleFactor), 
				(int)(151 * scaleFactor), 
				(int)(23 * scaleFactor));
		contentPane.add(btnCopyMessage);
		
		JButton btnGoTo = new JButton("Go To Reddit Post");
		btnGoTo.setFont(new Font("Tahoma", Font.PLAIN, (int)(12 * scaleFactor)));
		btnGoTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					open(new URI("https://www.reddit.com/r/DnD/comments/4x49di/i_made_a_digital_spell_book_if_anyone_wants_it/"));
				} catch (Exception e)
				{
					urlFailure();
				}
			}
		});
		btnGoTo.setBounds((int)(470 * scaleFactor), 
				(int)(118 * scaleFactor), 
				(int)(150 * scaleFactor), 
				(int)(23 * scaleFactor));
		contentPane.add(btnGoTo);
		
		try{
			Spell_List.load();
			Class_List.load();
			CharacterItems.loadItems();
			UserSpellWindow mainWindow = new UserSpellWindow();
			mainWindow.setVisible(true);
		} catch (Exception e)
		{
			setVisible(true);
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String exceptionAsString = sw.toString();
			printError(exceptionAsString);
		}
	}
	
	/**
	 * Prints the error message
	 * @param errorMessage
	 */
	public void printError(String errorMessage)
	{
		textAreaError.setText(errorMessage);
	}
	
	/**
	 * Fires if the button doesn't work for some reason
	 */
	private static void urlFailure()
	{
		JOptionPane.showMessageDialog(new JFrame(),"Wow. I REALLY "
				+ "fucked this one up. Even error reporting is broken. "
				+ "Well, here's the URL to copy-paste: \n"
				+ "https://www.reddit.com/r/DnD/comments/4x49di/i_made_a_digital_spell_book_if_anyone_wants_it/");
	}
	
	/**
	 * Opens the passed uri
	 * @param uri
	 */
	private static void open(URI uri)
	{
		if (Desktop.isDesktopSupported()) {
		      try {
		        Desktop.getDesktop().browse(uri);
		      } catch (IOException e) { urlFailure(); }
		    } else { urlFailure(); }
	}
	
	/**
	 * Returns the screen dimension of the user's monitor
	 * @return Dimension
	 */
	public static Dimension getScreenDimensions()
	{
		return screenSize;
	}
	
	/**
	 * Gets the 1 dimensional ratio between the default resolution (1080p)
	 * and the user's screen resolution <br>
	 * e.g. For a 4K monitor, this value would be 2.0
	 * @return double scale factor
	 */
	public static double getScaleFactor()
	{
		return scaleFactor;
	}
}
