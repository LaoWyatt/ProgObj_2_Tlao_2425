import java.awt.*;
import javax.swing.*;

public class Type1 extends JPanel {
	private static final long serialVersionUID = 1L;
	
	RootPanel parent; 
	PanneauBoules pb;
	PanneauControles pc;

	JSplitPane sp1, sp2;
	
	public Type1(RootPanel parent) {
		this.parent = parent;
		pb = new PanneauBoules(150, new ImageIcon("gifs/rose.gif").getImage(), this);
		pc = new PanneauControles(this);

		
		this.setLayout(new BorderLayout());
		
		sp1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pc, pb);
		sp1.setContinuousLayout(true);
		sp1.setDividerSize(10);
		sp1.setResizeWeight(.5);
		
		add(sp1);
		
    	new Thread(pb).start();
	}


	//Renvoie le nombre de boules du panneau courant
	public int getNbBoulesCurr() {
		return pb.getNbBoules();
	}

	//Recupere les valeurs du panneau boule dont l'index est passe en parametre et appel les fonction de mise a jour
	public void updateBoules(int backgroundR, int backgroundG, int backgroundB, int sommeil, int nb, int img) {
		Color newColor = new Color((float) backgroundR/100, (float) backgroundG/100, (float) backgroundB/100);

		if(pb.getSommeil() != sommeil)
			pb.setSommeil(sommeil);
		
		else if(pb.getNbBoules() != nb) {
			pb.interrupt();
			pb.setNbBoules(nb);
			pb.resume();
		}
		
		else
			pb.setBackgroundColor(newColor);
		
		pb.setImg(img);
		
	}
	
	//Fonctions de mise en suspens
    public void interrupt() {
    	pb.interrupt();
    }
    
    public void resume() {
    	pb.resume();
    }

}
