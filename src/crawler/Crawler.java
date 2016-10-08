package crawler;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.sun.glass.events.KeyEvent;
import com.sun.glass.events.WindowEvent;
public class Crawler extends JFrame{
	
	/**
	 * <h1>Constructor for Crawler class.</h1>
	 * - Set Title is "First application".<br>
	 * - Set Size is 300x300 px.
	 */
	public Crawler(){
		//Set Title is "First application".
		setTitle("First application");
		
		//Set Size is 300x300 px.
		setSize(300, 300);
		
		//Handle the closing event.
		addWindowListener(new WindowAdapter() {
			public void exit(WindowEvent e){
				ExitApp();
			}
		});
	}
	
	/**
	 * <h1>Exit the app</h1>
	 */
	public void ExitApp(){
		System.exit(0);
	}
	
	/**
	 * <h1>Setup file menu<h1>
	 */
	{
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		JMenuItem menuitem = new JMenuItem("Exit", KeyEvent.VK_X);
		menuitem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ExitApp();
			}
		});
		menu.add(menuitem);
		menubar.add(menu);
		setJMenuBar(menubar);
	}
	
	/**
	 * <h1>Main method</h1>
	 * Create a Crawler obj has name is "First"
	 * and show it on the screen.
	 * @param args Nothing
	 * @param first The only Crawler obj
	 */
	public static void main(String args[]){
		Crawler first = new Crawler();
		first.show();
	}
}
