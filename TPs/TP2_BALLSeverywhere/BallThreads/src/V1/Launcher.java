package V1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Launcher extends JFrame {
	
	Launcher() {
		
		RootPanel panneau = new RootPanel();
		setContentPane(panneau);
		addWindowListener(new WindowCloser());
		pack();
		setVisible(true);
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String args[]) {
		new Launcher();
    }
}

class WindowCloser extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
