package V1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;



public class SplitUI extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static PanneauConfig aireGauche;
	protected static PanneauBoules aireDroite;
	protected static String[] coloredBall = {"src/V1/gifs/bleue.gif","src/V1/gifs/bleue2.gif","src/V1/gifs/gris.gif","src/V1/gifs/jaune.gif","src/V1/gifs/rose.gif","src/V1/gifs/rouge.gif","src/V1/gifs/rouge2.gif","src/V1/gifs/vert.gif","src/V1/gifs/vert2.gif","src/V1/gifs/violet.gif"};
	protected static Color background = new Color(255,255,0);
	
	
	public SplitUI() {
		super("Des boules");
		setSize(900, 800);
		addWindowListener(new Fermeur());
		getContentPane().setLayout(new BorderLayout());

		aireGauche = new PanneauConfig();
		ImageIcon rouge = new ImageIcon(coloredBall[5]);
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
		new SplitUI();
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
		setBackground(SplitUI.background);
		setPreferredSize(new Dimension(200,300));
		addComponentListener(this);
		boules = new Boule[nBoules];
		dim = getPreferredSize();
		for (int k=0; k<nBoules; k++)
			boules[k] = new Boule(dim);
	}
	
	public void setNumBoules(int nBoules) { 
        // Recreate the boules array with the new size
        boules = new Boule[nBoules]; 
        for (int k = 0; k < nBoules; k++) {
            boules[k] = new Boule(dim); 
        }
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
		setLayout(new GridLayout(4,0,0,0));
		
		
		JSlider curseur;
		
		JPanel backgroundPanel = new JPanel(new GridLayout(3,0,0,0));
		backgroundPanel.setBorder(new TitledBorder("Background Color"));
		
		curseur = new JSlider(0,255);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(50);
		curseur.setValue(255);
		
		curseur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				Color temp = new Color(source.getValue(), SplitUI.background.getGreen(), SplitUI.background.getBlue());
				SplitUI.background = temp;
				SplitUI.aireDroite.setBackground(SplitUI.background);
			}
		});
		
		addSlider(curseur, "R", backgroundPanel);
		
		
		
		curseur = new JSlider(0,255);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(50);
		curseur.setValue(255);
		
		curseur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				Color temp = new Color(SplitUI.background.getRed(), source.getValue(), SplitUI.background.getBlue());
				SplitUI.background = temp;
				SplitUI.aireDroite.setBackground(SplitUI.background);
			}
		});
		
		addSlider(curseur, "G", backgroundPanel);
		
		curseur = new JSlider(0,255);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(50);
		curseur.setValue(0);
		
		curseur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				Color temp = new Color(SplitUI.background.getRed(), SplitUI.background.getGreen(), source.getValue());
				SplitUI.background = temp;
				SplitUI.aireDroite.setBackground(SplitUI.background);
			}
		});
		
		addSlider(curseur, "B", backgroundPanel);
		
		JScrollPane scrollPane1 = new JScrollPane(backgroundPanel);
		
		this.add(scrollPane1);
		
		JPanel speedPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		speedPanel.setBorder(new TitledBorder("Ball's speed"));
		
		curseur = new JSlider();
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(20);
		curseur.setValue(10);
		
		curseur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				SplitUI.aireDroite.sommeil = source.getValue();
			}
		});
		
		addSlider(curseur, null, speedPanel);
		JScrollPane scrollPane2 = new JScrollPane(speedPanel);
		
		this.add(scrollPane2);
		
		JPanel numberPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		numberPanel.setBorder(new TitledBorder("Ball's Number"));
		
		curseur = new JSlider(0,1000);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(250);
		curseur.setValue(150);
		
		curseur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				SplitUI.aireDroite.setNumBoules(source.getValue()); 
			}
		});
		
		addSlider(curseur, null, numberPanel);
		JScrollPane scrollPane3 = new JScrollPane(numberPanel);
		
		this.add(scrollPane3);
		
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(Integer.valueOf(0), new JLabel(new ImageIcon(SplitUI.coloredBall[0])));
		labelTable.put(Integer.valueOf(10), new JLabel(new ImageIcon(SplitUI.coloredBall[1])));
		labelTable.put(Integer.valueOf(20), new JLabel(new ImageIcon(SplitUI.coloredBall[2])));
		labelTable.put(Integer.valueOf(30), new JLabel(new ImageIcon(SplitUI.coloredBall[3])));
		labelTable.put(Integer.valueOf(40), new JLabel(new ImageIcon(SplitUI.coloredBall[4])));
		labelTable.put(Integer.valueOf(50), new JLabel(new ImageIcon(SplitUI.coloredBall[5])));
		labelTable.put(Integer.valueOf(60), new JLabel(new ImageIcon(SplitUI.coloredBall[6])));
		labelTable.put(Integer.valueOf(70), new JLabel(new ImageIcon(SplitUI.coloredBall[7])));
		labelTable.put(Integer.valueOf(80), new JLabel(new ImageIcon(SplitUI.coloredBall[8])));
		labelTable.put(Integer.valueOf(90), new JLabel(new ImageIcon(SplitUI.coloredBall[9])));
		
		JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		colorPanel.setBorder(new TitledBorder("Ball's Image"));
		
		curseur = new JSlider(0,90);
		curseur.setPaintTicks(true);
		curseur.setPaintLabels(true);
		curseur.setMajorTickSpacing(10);
		curseur.setMinorTickSpacing(10);
		
		curseur.setLabelTable(labelTable);
		curseur.setValue(50);
		
		curseur.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider)event.getSource();
				SplitUI.aireDroite.img = new ImageIcon(SplitUI.coloredBall[source.getValue()/10]).getImage();
			}
		});
		
		colorPanel.add(curseur);
		JScrollPane scrollPane4 = new JScrollPane(colorPanel);
		
		this.add(scrollPane4);
		
		
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
		
		if (description != null) pane.add(new JLabel(description));
		pane.add(s);
		pane.add(viewVar);

	}
	
}
