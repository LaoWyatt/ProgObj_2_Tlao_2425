import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RootPanel extends JFrame implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	
	public RootPanel() {
		super("TP2");
    	addWindowListener(new Fermeur());
    	setSize(1000, 900);
    	add(new Type1(this));
    	setVisible(true);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}

class Fermeur extends WindowAdapter {
    public void windowClosing(WindowEvent e) { 
        System.exit(0);
    }
}
