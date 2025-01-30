package V1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanneauBoules extends JPanel implements Runnable, ComponentListener {
	private static final long serialVersionUID = 1L;
	
	protected JPanel parent;
	protected Boule[] boules;
    protected Image img;
    protected Dimension dim;
    protected int imgNb;
    protected int sommeil;
    protected boolean interrupted;
    
    public PanneauBoules(int nBoules, Image img, JPanel parent) {
    	this.parent = parent;
    	interrupted = false;
		sommeil = 10;
		this.img = img;
		imgNb = 5;
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(200,300));
		addComponentListener(this);

		boules = new Boule[nBoules];
		dim = getPreferredSize();
		
		for (int k=0; k<nBoules; k++)
		    boules[k] = new Boule(dim);
    }
    
    public void run() {
		for(;;) {
			//Le programme ne tourne que si il n'est pas interrompu manuellement
			if(!interrupted) {
			    for (int k = 0; k < boules.length; k++)
			    	boules[k].move(dim);
			    
			    repaint(); 
			    //dataUpdater();
			}
		    
		    if (sommeil != 0) {	
		  	    try { 
		  	    	Thread.sleep(sommeil); 
		 	    } 
		  	    
		 	    catch(InterruptedException e) {}
		    }
		}
    }
    
    //Fonctions d'interruption et de reprise
    public void interrupt() {
    	interrupted = true;
    }
    
    public void resume() {
    	interrupted = false;
    }
    
    //Fonction qui envoie un int[][] a son parent pour mettre a jour le tableau de donnees
    public void dataUpdater() {
    	if(parent instanceof Type1) {
        	int[][] data = new int[boules.length][3];
        	
        	for(int k=0; k<boules.length; k++){
        		data[k][0] = k+1;
        		data[k][1] = ((int) boules[k].getX());
        		data[k][2] = ((int) boules[k].getY());
        	}
        	
    	}
    	
    }
    
    //Fonction graphique
    public void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0,0, dim.width, dim.height);

		for (int k=0; k<boules.length; k++)
		    g.drawImage(img, (int)boules[k].x, (int)boules[k].y, this);
	}
    
    //Getters & Setters
    public void setNbBoules(int nb) {
    	boules = new Boule[nb];
		
		for (int k=0; k<nb; k++)
		    boules[k] = new Boule(dim);
    }
    
    public int getNbBoules() {
    	return boules.length;
    }
    
    public void setImg(int img) {
    	imgNb = img;
    	
    	switch(img) {
    		case 1:
    			this.img = new ImageIcon("gifs/bleue.gif").getImage();
    			break;
    			
    		case 2:
    			this.img = new ImageIcon("gifs/bleue2.gif").getImage();
    			break;

    		case 3:
    			this.img = new ImageIcon("gifs/gris.gif").getImage();
    			break;
    			
       		case 4:
    			this.img = new ImageIcon("gifs/jaune.gif").getImage();
    			break;

    		case 5:
    			this.img = new ImageIcon("gifs/rose.gif").getImage();
    			break;

    		case 6:
    			this.img = new ImageIcon("gifs/rouge.gif").getImage();
    			break;

    		case 7:
    			this.img = new ImageIcon("gifs/rouge2.gif").getImage();
    			break;

    		case 8:
    			this.img = new ImageIcon("gifs/vert.gif").getImage();
    			break;

    		case 9:
    			this.img = new ImageIcon("gifs/vert2.gif").getImage();
    			break;

    		case 10:
    			this.img = new ImageIcon("gifs/violet.gif").getImage();
    			break;
    		
    		default:
    			break;
    			
    	}
    }
    
    public int getImg() {
    	return imgNb;
    }
    
    public void setSommeil(int sommeil) {
    	this.sommeil = sommeil;
    }
    
    public int getSommeil() {
    	return sommeil;
    }
    
    public void setBackgroundColor(Color newColor) {
    	setBackground(newColor);
    }
    
    public Color getBackgroundColor() {
    	return this.getBackground();
    }

    //Fonctions evenement
    public void componentHidden(ComponentEvent e){}
    public void componentShown(ComponentEvent e){}
    public void componentMoved(ComponentEvent e){}
    
    public void componentResized(ComponentEvent e){
		dim = getSize();
		
		for (int k = 0; k < boules.length; k++)
		    boules[k].moveIntoRect(dim);
    }
}
