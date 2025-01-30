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
	ButtonGroup rbGroup;
	Button ajout, supprimer;
	JTabbedPane tabbedPane;
	JPanel leftPanel;
	
	JRadioButton rb;
	JPanel rbPanel;
	
	
	public RootPanel() {

		JPanel buttonPanel = new JPanel();
		
		rbPanel = new JPanel();
		rbGroup = new ButtonGroup();
		
		rb = new JRadioButton("Type 1",true);
		rbPanel.add(rb); 
		rbGroup.add(rb);
		
		rb = new JRadioButton("Type 2",false);
		rbPanel.add(rb); 
		rbGroup.add(rb);
		
		
		buttonPanel.setLayout(new GridLayout(0,1));
		
		ajout = new Button("Ajouter");
		ajout.addActionListener(this);
		buttonPanel.add(ajout);
		
		supprimer = new Button("Supprimer");
		supprimer.addActionListener(this);
		buttonPanel.add(supprimer);
		
		
		leftPanel = new JPanel();
		leftPanel.add(rbPanel);
		leftPanel.add(buttonPanel);
		//lowerPanel.add(statut, BorderLayout.SOUTH);

		tabbedPane = new JTabbedPane(SwingConstants.TOP);
		createTab(1);


		setLayout(new BorderLayout()); 
		add(leftPanel, BorderLayout.WEST);
		add(tabbedPane, BorderLayout.CENTER);
	}

	
	public void createTab(int type) {
		int ong = tabbedPane.getTabCount();
		if (type == 1) tabbedPane.addTab("Id = " + ong + "  / Type : " + type , new Type1(this));
		else System.out.println("Type 2 not yet");
		
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
			if (rb.isSelected()) System.out.println("Type 2 not yet");
			else createTab(1);
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
