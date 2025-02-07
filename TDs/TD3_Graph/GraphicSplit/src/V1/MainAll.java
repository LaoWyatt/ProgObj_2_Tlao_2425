package V1;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class MainAll extends JFrame {
	private static final long serialVersionUID = 1L;

	ConfigPanel configLeft = new ConfigPanel();
	GraphPanel graphRight = new GraphPanel();
	JSplitPane split;
	
	MainAll(){
		
		
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(new ConfigPanel()),graphRight);
		
		
		
		this.add(split);
		this.setSize(600, 600);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MainAll();
		
	}

}
