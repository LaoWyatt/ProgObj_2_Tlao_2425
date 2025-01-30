package V1;

import java.awt.*;

public class Boule {
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

    public double getX() {
    	return x;
    }
    
    public double getY() {
    	return y;
    }
}
