package twozerofoureight.frontend;

import processing.core.*;
import twozerofoureight.init.GlobalConstants;

public class TextBox implements GlobalConstants {
	public float x,y,w=-1,h=-1;
	private float tr = 128, tg = -1, tb = -1, ttransparency = 255;
	public String text = "text";
	
	public TextBox(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public TextBox(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.x = w;
		this.y = h;
	}
	
	/**
	 * If you want a shade, set g and b as -1.
	 */
	public void textColor(float r, float g, float b) {
		tr=r;
		tg=g;
		tb=b;
	}
	
	public void textTransparency(float t) {
		ttransparency = t;
	}
	
	public void setText(String a) {
		text = a;
	}
	
	public void draw() {
		if (tg==-1||tb==-1) {
			Graphics.setColor(tr, ttransparency);
		} else {
			Graphics.setColor(tr,tg, tb, ttransparency);
		}
		Graphics.setTextAlign(CENTER, CENTER);
		if (w == -1 || h == -1) {
			Graphics.text(text, x, y);
		}
		Graphics.text(text, x, y, w, h);
	}
}
