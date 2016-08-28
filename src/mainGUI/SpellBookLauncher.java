package mainGUI;

import java.awt.Desktop;
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
	private JPanel contentPane;
	private JTextArea textAreaError;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		setBounds(100, 100, 647, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOhSnap = new JLabel("Oh snap!");
		lblOhSnap.setFont(new Font("Verdana", Font.BOLD, 20));
		lblOhSnap.setHorizontalAlignment(SwingConstants.CENTER);
		lblOhSnap.setBounds(10, 11, 611, 36);
		contentPane.add(lblOhSnap);
		
		JTextPane txtMessage = new JTextPane();
		txtMessage.setEditable(false);
		txtMessage.setBackground(SystemColor.menu);
		txtMessage.setText("You really weren't supposed to see this. "
				+ "If you're seeing this window, that means some nasty bug "
				+ "has crawled into my code and is tearing it all apart, "
				+ "preventing the application from launching. Please copy "
				+ "the error message below and send it to me as a comment "
				+ "on my Reddit post.");
		txtMessage.setBounds(10, 58, 611, 48);
		contentPane.add(txtMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 152, 611, 141);
		contentPane.add(scrollPane);
		
		textAreaError = new JTextArea();
		textAreaError.setFont(new Font("Monospaced", Font.PLAIN, 11));
		textAreaError.setEditable(false);
		textAreaError.setLineWrap(true);
		textAreaError.setWrapStyleWord(true);
		scrollPane.setViewportView(textAreaError);
		
		JButton btnNewButton = new JButton("Copy error message");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(new StringSelection(textAreaError.getText()), null);
				((JButton)arg0.getSource()).setText("Copied!");
			}
		});
		btnNewButton.setBounds(470, 304, 151, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Go To Reddit Post");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					open(new URI("https://www.reddit.com/r/DnD/comments/4x49di/i_made_a_digital_spell_book_if_anyone_wants_it/"));
				} catch (Exception e)
				{
					urlFailure();
				}
			}
		});
		btnNewButton_1.setBounds(470, 118, 151, 23);
		contentPane.add(btnNewButton_1);
		
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
}
