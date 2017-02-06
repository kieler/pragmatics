package de.cau.cs.kieler.overlapRemoval.helper;

import java.awt.Color;
import java.awt.Graphics;

public class Bounds {
    private double xPos;
    private double yPos;
    private double width;
    private double height;
    
    public Bounds(double xPos, double yPos, double width, double height) {
        this.setXPos(xPos);
        this.setYPos(yPos);
        this.width = width;
        this.height = height;
    }
    
    public boolean inBounds(Point point) {
        return (this.getXPos() <= point.getXPos() && (this.getXPos() + this.getWidth()) >= point.getXPos() && this.getYPos() < point.getYPos() && (this.getYPos() + this.getHeight()) >= point.getYPos());
    }
    
    public void draw(Graphics gr) {
        gr.setColor(Color.red);
        gr.drawRect((int) this.getXPos(), (int) this.getYPos(), (int) this.getWidth(), (int) this.getHeight());
    }
    
    @Override
    public String toString() {
        return this.getXPos() + " , " + this.getYPos() + " , " + this.width + " , " + this.height;
    }

	public double getXPos() {
		return xPos;
	}

	public void setXPos(double xPos) {
		this.xPos = xPos;
	}

	public double getYPos() {
		return yPos;
	}

	public void setYPos(double yPos) {
		this.yPos = yPos;
	}
	
	public double getWidth() {
		return this.width;
	}
	
	public void setWidth(double width) {
		this.width = width;
	}
	
	public double getHeight() {
		return this.width;
	}
	
	public void setHeight(double height) {
		this.height = height;
	}
}
