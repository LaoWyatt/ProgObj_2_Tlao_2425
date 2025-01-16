package V1;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class WindowCloser extends WindowAdapter {
	public void windowClosing(WindowEvent e) {
	System.exit(0);
	}
}