package V1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	Color currentColor = new Color(0,0,0);
	int coefficient = 0;
	int espacement_X_min = 1, espacement_X_max = 5, espacement_Y_min = 1, espacement_Y_max = 5;
	int borne_X_min = -10, borne_X_max = 10, borne_Y_min = -10, borne_Y_max = 10;
	int typeCourbe = 0;
	
	BufferedImage image;
	WritableRaster raster;
	ColorModel model;
	
	public void paintComponent(Graphics g) {
		int largeur = getSize().width;
		int hauteur = getSize().height;
		
		image = new BufferedImage(largeur, hauteur, BufferedImage.TYPE_INT_RGB);
		raster = image.getRaster();
		model = image.getColorModel();
		
		setColor(Color.black);
		drawLine(1, hauteur/2, largeur-1, hauteur/2);
		drawLine(largeur/2, 1, largeur/2, hauteur-1);
		
		
		
	}
	
	
	
	GraphPanel(){
		this.setPreferredSize(new Dimension(800,800));
	}
	
	public void setColor(Color c) {
		currentColor = c;
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
		
	    if (startX == endX) {
	    	if (startY < endY) {
		    	for (int i = startY; i < endY + 1; i++) {
		    		colorPixel(startX,i);
		    	}
	    	} else {
	    		for (int i = endY; i < startY + 1; i++) {
		    		colorPixel(startX,i);
		    	}
	    	}
	    } else if (startY == endY) {
	    	if (startX < endX) {
	    		for (int i = startX; i < endX + 1; i++) {
	    			colorPixel(i,endY);
	    		}
	    	} else {
	    		for (int i = endX; i < startX + 1; i++) {
	    			colorPixel(i,endY);
	    		}
	    	}
	    	
	    }
	}
	
	public void colorPixel(int x, int y) {
		raster.setDataElements(x, y, model.getDataElements(currentColor.getRGB(), null));
	}

}
