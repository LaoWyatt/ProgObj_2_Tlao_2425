package V1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Color courbe = new Color(255,0,0);
	int coefficient = 0;
	int espacement_X_min = 1, espacement_X_max = 5, espacement_Y_min = 1, espacement_Y_max = 5;
	int borne_X_min = -10, borne_X_max = 10, borne_Y_min = -10, borne_Y_max = 10;
	int typeCourbe = 0;
	
	public void paintComponent(Graphics g) {
		int largeur = getSize().width;
		int hauteur = getSize().height;
		
		g.setColor(Color.black);
		g.drawLine(0, hauteur/2, largeur, hauteur/2);
		g.drawLine(largeur/2, 0, largeur/2, hauteur);
		
		
		
	}
	
	
	
	GraphPanel(){
		this.setPreferredSize(new Dimension(800,800));
	}
	
	
	private int getPixelWidth() {
		int Xmin = borne_X_min, Xmax = borne_X_max;
		int largeur = getSize().width;
		
		if (Xmin < 0) Xmin = -Xmin;
		if (Xmax < 0) Xmax = -Xmax;
		
		return (Xmin + Xmax)/largeur;
	}
	
	private int getPixelHeight() {
		int Ymin = borne_Y_min, Ymax = borne_Y_max;
		int largeur = getSize().width;
		
		if (Ymin < 0) Ymin = -Ymin;
		if (Ymax < 0) Ymax = -Ymax;
		
		return (Ymin + Ymax)/largeur;
	}
	
	private int toZeroPixelWidth() {
		
		if (borne_X_min <= 0 && borne_X_max <= 0) return 0;
		return 0;
	}
	
	
	public void drawLine(int startX, int startY, int endX, int endY) {
	    double deltaX = endX - startX;
	    double deltaY = endY - startY;
	    double error = -1;
	    double deltaError = Math.abs(deltaY / deltaX);
	    int y = startY;

	    for (int x = startX; x < endX - 1; x++) {
	        error += deltaError;

	        if (error >= 0) {
	            y++;
	            error--;
	        }

	        
	    }
	}

}
