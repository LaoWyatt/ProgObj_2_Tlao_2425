package V1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

		/*setSize(1000, 900);
    	add(new Type1(this));
    	setVisible(true);*/
    	
public class RootPanel extends JPanel implements ActionListener, ChangeListener {
	private static final long serialVersionUID = 1L;
	JButton newTab, deleteTab,saveDrawing;
	JTabbedPane tabbedPane;
	
	
	public RootPanel() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,0));
		
		newTab = new JButton("Nouveau");
		newTab.addActionListener(this);
		buttonPanel.add(newTab);
		
		deleteTab = new JButton("Supprimer");
		deleteTab.addActionListener(this);
		buttonPanel.add(deleteTab);
		
		saveDrawing = new JButton("Sauvegarder");
		saveDrawing.addActionListener(this);
		buttonPanel.add(saveDrawing);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		createTab(1);


		setLayout(new BorderLayout()); 
		add(buttonPanel, BorderLayout.NORTH);
		add(tabbedPane, BorderLayout.CENTER);
	}

	
	public void createTab(int type) {
		int ong = tabbedPane.getTabCount();
		tabbedPane.addTab("Id = " + ong + "  / Type : " + type , new SplitMain());
		
		tabbedPane.setSelectedIndex(ong);
	}
	
	public void killTab() {
		if (tabbedPane.getTabCount() > 0) {
			tabbedPane.removeTabAt(tabbedPane.getTabCount()-1);
		}
	}
	
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String lib = ((Button) e.getSource()).getActionCommand();
		if (lib.equals("Ajouter")) {
			//nothing
		}
		else {
			killTab();
		}
		
		
	}
	
	
}

class Fermeur extends WindowAdapter {
    public void windowClosing(WindowEvent e) { 
        System.exit(0);
    }
}
