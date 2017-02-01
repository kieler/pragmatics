package de.cau.cs.kieler.overlapRemoval.helper

import java.awt.Color
import java.awt.Graphics
import org.eclipse.xtend.lib.annotations.Accessors

class Bounds {
    @Accessors private float xPos;
    @Accessors private float yPos;
    @Accessors private float width;
    @Accessors private float height;
    
    new(float xPos, float yPos, float width, float height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
    }
    
    def inBounds(Point point) {
        return (this.XPos <= point.XPos && (this.XPos + this.getWidth) >= point.XPos && this.YPos < point.YPos && (this.YPos + this.getHeight) >= point.YPos);
    }
    
    def void draw(Graphics gr) {
        gr.setColor(Color.red);
        gr.drawRect((this.XPos).intValue, (this.YPos).intValue, (this.width).intValue, (this.height).intValue);
//        println(this);
    }
    
    override String toString() {
        return this.XPos + " , " + this.YPos + " , " + this.width + " , " + this.height;
    }
}
