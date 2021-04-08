package twozerofoureight.frontend;
	
import twozerofoureight.init.*;

public class Button implements GlobalConstants {
	//button dimensions
	public float x;
	public float y;
	public float w;
	public float h;
	//button corner smooth radius
	public float rad1;
	public float rad2;
	public float rad3; 
	public float rad4;
	//rgb for button
	public float r=128;
	public float g=1;
	public float b=1;
	//button transparency
	public float transparency = 255;
	//rgb for button border
	public float sr = 0;
	public float sg = 1; 
	public float sb=1; 
	//stroke transparency of button border
	public float strokeTransparency=255; 
	//strokeweight of button border
	public float strokeWeight = 1;
	//for optional use 
	public boolean active = false;
	//integrated text box
	public TextBox text;
	
	public Button(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
	public void setRadii(float r) {
		rad1 = r;
		rad2 = r;
		rad3 = r;
		rad4 = r;
	}
	
	public void setRadii(float r1, float r2, float r3, float r4) {
		rad1 = r1;
		rad2 = r2;
		rad3 = r3;
		rad4 = r4;
	}
	
	public void setColor(float r, float g, float b) {
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	/*
	 * note that fully opaque is 255.
	 */
	public void setTransparency(float t) {
		transparency = t;
	}
	
	public void setStrokeColor(float r, float g, float b) {
		sr = r;
		sg = g;
		sb = b;
	}
	
	/*
	 * note that fully opaque is 255.
	 */
	public void setStrokeTransparency(float t) {
		strokeTransparency = t;
	}
	
	public void setStrokeWeight(float strokeWeight) {
		this.strokeWeight = strokeWeight;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public boolean mouseInButton(int mouseX, int mouseY) {
		if (mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h) {
			return true;
		}
		return false;
	}
	
	public void addText(String text) {
		this.text = new TextBox(text, x+w/2, y+h/2);
	}
	
	public void textColor(float r, float g, float b) {
		text.r=r;
		text.g=g;
		text.b=b;
	}
	
	public void textTransparency(float t) {
		text.transparency = t;
	}
	
	public void setTextSize(float size) {
		text.size = size;
	}
	
	public void draw() {
		Graphics.setColor(r, g, b, transparency);
		Graphics.setStroke(sr, sg, sb, strokeTransparency);
		Graphics.setStrokeWeight(strokeWeight);
		Graphics.setRectMode(CORNER);
		Graphics.rectangle(x, y, w, h, rad1, rad2, rad3, rad4);
		if (text!=null) {
			text.draw();
		}
	}
}

