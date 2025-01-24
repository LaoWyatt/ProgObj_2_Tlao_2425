package V1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class SpitUI extends JFrame {
	private static final long serialVersionUID = 1L;
	protected PanneauConfig aireGauche;
	protected PanneauBoules aireDroite;

	public SpitUI() {
		super("Des boules");
		setSize(900, 800);
		addWindowListener(new Fermeur());
		getContentPane().setLayout(new BorderLayout());

		aireGauche = new PanneauConfig();
		ImageIcon rouge = new ImageIcon("src/V1/gifs/rouge.gif");
		aireDroite = new PanneauBoules(150, rouge.getImage());
		JSplitPane sp = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, aireGauche, aireDroite);
		sp.setDividerSize(5);
		sp.setContinuousLayout(true);
		getContentPane().add(sp, BorderLayout.CENTER);

		JMenuBar mb = new JMenuBar();
		getRootPane().setJMenuBar(mb);
		JMenu menu = new JMenu("Threads");
		mb.add(menu);
		menu.add(new MenuAction(10));
		menu.add(new MenuAction(20));
		menu.add(new MenuAction(30));
		menu.add(new MenuAction(50));
		menu.add(new MenuAction(100));
		menu.add(new MenuAction(0));

		setVisible(true);

		new Thread(aireDroite).start();
	}

	public static void main(String argv[]) {
		new SpitUI();
	}

	class Fermeur extends WindowAdapter {
		public void windowClosing(WindowEvent e) { 
			System.exit(0);
		}
	}

	class MenuAction extends AbstractAction {
		int vitesse;
		public MenuAction(int vitesse) {
			super(Integer.toString(vitesse));
			this.vitesse = vitesse;
		}
		public void actionPerformed(ActionEvent e) {
			aireDroite.sommeil = vitesse;
		}
	}

}

class PanneauBoules extends JPanel implements Runnable, ComponentListener {

	private static final long serialVersionUID = 2652890975085217703L;
	protected Boule[] boules;
	protected Image img;
	protected Dimension dim;
	protected int sommeil;
	public PanneauBoules(int nBoules, Image img) {
		sommeil = 10;
		this.img = img;
		setBackground(Color.yellow);
		setPreferredSize(new Dimension(200,300));
		addComponentListener(this);
		boules = new Boule[nBoules];
		dim = getPreferredSize();
		for (int k=0; k<nBoules; k++)
			boules[k] = new Boule(dim);
	}
	public void run() {
		for(;;) {
			for (int k = 0; k < boules.length; k++)
				boules[k].move(dim);
			repaint();
			if (sommeil != 0) {	
				try { 
					Thread.sleep(sommeil); 
				} 
				catch(InterruptedException e) {}}
		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0,0, dim.width, dim.height);

		for (int k=0; k<boules.length; k++)
			g.drawImage(img, (int)boules[k].x, (int)boules[k].y, this);
	}

	public void componentHidden(ComponentEvent e){}
	public void componentShown(ComponentEvent e){}
	public void componentMoved(ComponentEvent e){}
	public void componentResized(ComponentEvent e){
		dim = getSize();
		for (int k = 0; k < boules.length; k++)
			boules[k].moveIntoRect(dim);
	}
}

class Boule {
	protected double x, y, vx, vy;
	public Boule(Dimension dim) {
		x = dim.width*Math.random();
		y = dim.height*Math.random();
		double angle = 2*Math.PI*Math.random();
		vx = 2*Math.cos(angle);
		vy = 2*Math.sin(angle);
	}

	public void move(Dimension dim) {
		double nx = x + vx;
		double ny = y + vy;
		if ((nx < 0)|| (nx > dim.width)) {
			vx = - vx;
			nx = x + vx;
		}
		if ((ny < 0)||(ny > dim.height)) {
			vy = - vy;
			ny = y + vy;
		}
		x = nx;
		y = ny;
	}

	public void moveIntoRect(Dimension dim) {
		x = Math.max(x, 0);
		x = Math.min(x, dim.width);
		y = Math.max(y, 0);
		y = Math.min(y, dim.height);
	}

}

class PanneauConfig extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public PanneauConfig() {
		setLayout(new GridLayout(3,0,0,0));
		
		
		JSlider curseur;
		
		JPanel backgroundPanel = new JPanel(new GridLayout(3,0,0,0));
		backgroundPanel.setBorder(new TitledBorder("Background Color"));
		
		curseur = new JSlider(0,255);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(50);
		addSlider(curseur, "R", backgroundPanel);
		
		curseur = new JSlider(0,255);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(50);
		addSlider(curseur, "G", backgroundPanel);
		
		curseur = new JSlider(0,255);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(50);
		addSlider(curseur, "B", backgroundPanel);
		
		JScrollPane scrollPane1 = new JScrollPane(backgroundPanel);
		
		this.add(scrollPane1);
		
	}
	
	public void addSlider(JSlider s, String description, JPanel pane) {
		final JLabel viewVar = new JLabel("0");

		s.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				viewVar.setText("" + source.getValue());
			}
		});
		viewVar.setText(String.valueOf(s.getValue()));
		
		pane.add(new JLabel(description));
		pane.add(s);
		pane.add(viewVar);

	}
}
