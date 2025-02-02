package V1;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainAll extends JFrame {

	MainAll(){
		this.add(new JScrollPane(new ConfigPanel()));
		this.setSize(600, 600);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MainAll();
		
	}

}
