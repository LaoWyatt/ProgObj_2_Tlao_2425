package V1;

import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;


public class PanneauControles extends JPanel implements ChangeListener, ItemListener {
	private static final long serialVersionUID = 1L;
	
	JPanel parent;
	CheckboxGroup rbGroup;
	JSlider curseurBackgroundR, curseurBackgroundG, curseurBackgroundB, curseurVitesse, curseurNb, curseurImg;

	//Creer un affichage graphique pour le panneau de controles en fonction du modele du parent
	public PanneauControles(JPanel parent) {
		this.parent = parent;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		if(!(parent instanceof Type1))
			this.add(panelButtons());
        this.add(backgroundSliders());
        this.add(vitesseSlider());
        this.add(nbSlider());
        this.add(imgSlider());
	}
	
	//Fonction qui renvoie le JPanel contenant les radio buttons
	private JPanel panelButtons() {
		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new FlowLayout());
		panelButtons.setBorder(new TitledBorder(new EtchedBorder(), "Panel"));
		rbGroup = new CheckboxGroup();
		return panelButtons;
	}
	
	//Fonction qui renvoie le JPanel contenant les sliders de couleurs d'arriere-plan
	private JPanel backgroundSliders() {
		JPanel panelBackground = new JPanel();
		panelBackground.setLayout(new BoxLayout(panelBackground, BoxLayout.Y_AXIS));
		panelBackground.setBorder(new TitledBorder(new EtchedBorder(), "Background color (RGB)"));
		
		curseurBackgroundR = new JSlider(0, 255, 255);
		curseurBackgroundR.setMajorTickSpacing(50);
		curseurBackgroundR.setPaintTicks(true);
		curseurBackgroundR.setPaintLabels(true);
		curseurBackgroundR.setPaintTrack(true);
		curseurBackgroundR.setBorder(new EmptyBorder(5, 5, 5, 5));
        curseurBackgroundR.addChangeListener(this);
        panelBackground.add(curseurBackgroundR);

        curseurBackgroundG = new JSlider(0, 255, 255);
        curseurBackgroundG.setMajorTickSpacing(50);
        curseurBackgroundG.setPaintTicks(true);
        curseurBackgroundG.setPaintLabels(true);
        curseurBackgroundG.setPaintTrack(true);
        curseurBackgroundG.setBorder(new EmptyBorder(5, 5, 5, 5));
        curseurBackgroundG.addChangeListener(this);
        panelBackground.add(curseurBackgroundG);        

        curseurBackgroundB = new JSlider(0, 255, 255);
        curseurBackgroundB.setMajorTickSpacing(50);
        curseurBackgroundB.setPaintTicks(true);
        curseurBackgroundB.setPaintLabels(true);
        curseurBackgroundB.setPaintTrack(true);
		curseurBackgroundB.setBorder(new EmptyBorder(5, 5, 5, 5));
		curseurBackgroundB.addChangeListener(this);
		panelBackground.add(curseurBackgroundB); 
		
		return panelBackground;
	}
	
	//Fonction qui renvoie le JPanel avec le slider de vitesse
	private JPanel vitesseSlider() {
		JPanel panelVitesse = new JPanel();
		panelVitesse.setLayout(new BoxLayout(panelVitesse, BoxLayout.Y_AXIS));
		panelVitesse.setBorder(new TitledBorder(new EtchedBorder(), "Ball speed"));
		
        curseurVitesse = new JSlider(0, 100, 10);
        curseurVitesse.setMajorTickSpacing(25);
        curseurVitesse.setPaintTicks(true);
        curseurVitesse.setPaintLabels(true);
        curseurVitesse.setPaintTrack(true);
		curseurVitesse.setBorder(new EmptyBorder(5, 5, 5, 5));
		curseurVitesse.addChangeListener(this);
        panelVitesse.add(curseurVitesse);   
        
    	return panelVitesse;
	}
    
	//Fonction qui renvoie le JPanel avec le slider de nombre de boules
	private JPanel nbSlider() {
		JPanel panelNb = new JPanel();
        panelNb.setLayout(new BoxLayout(panelNb, BoxLayout.Y_AXIS));
        panelNb.setBorder(new TitledBorder(new EtchedBorder(), "Ball amount"));
		
        curseurNb = new JSlider(0, 1000, 150);
        curseurNb.setMajorTickSpacing(250);
        curseurNb.setPaintTicks(true);
        curseurNb.setPaintLabels(true);
        curseurNb.setPaintTrack(true);
        curseurNb.setBorder(new EmptyBorder(5, 5, 5, 5));
        curseurNb.addChangeListener(this);
        panelNb.add(curseurNb);
        
        return panelNb;
	}
	
	//Fonction qui renvoie le JPanel avec le slider de selection d'image de boules
	private JPanel imgSlider() {
		JPanel panelImg = new JPanel();
        panelImg.setLayout(new BoxLayout(panelImg, BoxLayout.Y_AXIS));
        panelImg.setBorder(new TitledBorder(new EtchedBorder(), "Ball image"));
        
        Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
        labelTable.put(new Integer(1), new JLabel(new ImageIcon("gifs/bleue.gif")));
        labelTable.put(new Integer(2), new JLabel(new ImageIcon("gifs/bleue2.gif")));
        labelTable.put(new Integer(3), new JLabel(new ImageIcon("gifs/gris.gif")));
        labelTable.put(new Integer(4), new JLabel(new ImageIcon("gifs/jaune.gif")));
        labelTable.put(new Integer(5), new JLabel(new ImageIcon("gifs/rose.gif")));
        labelTable.put(new Integer(6), new JLabel(new ImageIcon("gifs/rouge.gif")));
        labelTable.put(new Integer(7), new JLabel(new ImageIcon("gifs/rouge2.gif")));
        labelTable.put(new Integer(8), new JLabel(new ImageIcon("gifs/vert.gif")));
        labelTable.put(new Integer(9), new JLabel(new ImageIcon("gifs/vert2.gif")));
        labelTable.put(new Integer(10), new JLabel(new ImageIcon("gifs/violet.gif")));
        		
        curseurImg = new JSlider(1, 10, 5);
        curseurImg.setMajorTickSpacing(1);
        curseurImg.setPaintTicks(true);
        curseurImg.setPaintLabels(true);
        curseurImg.setSnapToTicks(true);
        curseurImg.setBorder(new EmptyBorder(5, 5, 5, 5));
        curseurImg.addChangeListener(this);
        curseurImg.setLabelTable(labelTable);
        panelImg.add(curseurImg);
        
        return panelImg;
	}
	
	//Fonction qui met a jour les sliders aux valeurs passees en parametres (celles du panneau boule actuel)
	public void sliderUpdate(int r, int g, int b, int vitesse, int nb, int img) {
		curseurBackgroundR.setValue(r);
		curseurBackgroundG.setValue(g);
		curseurBackgroundB.setValue(b);
		
		curseurVitesse.setValue(vitesse);
		curseurNb.setValue(nb);
		curseurImg.setValue(img);
	}
	
	//Renvoie l'index du panneau boule courrant
	public int getCurrPanel() {
		return Integer.parseInt(rbGroup.getSelectedCheckbox().getLabel());
	}
	
	//Utilisation des sliders
	@Override
	public void stateChanged(ChangeEvent e) {
		
		if(parent instanceof Type1)
	    	((Type1) parent).updateBoules(
	    			curseurBackgroundR.getValue(), curseurBackgroundG.getValue(), curseurBackgroundB.getValue(), 
	    			curseurVitesse.getValue(), curseurNb.getValue(), curseurImg.getValue());
		
    }

	//Utilisation des radio buttons
	@Override
	public void itemStateChanged(ItemEvent e) {
		
	}
}
