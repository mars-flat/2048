package twozerofoureight.frontend;

import twozerofoureight.init.GlobalConstants;
import twozerofoureight.init.Main;

public class Graphics implements GlobalConstants {
	
	private static Main instance;
	
	private Graphics() {}
	public static void init(Main instance) {
	    Graphics.instance = instance;
	}
	
	public static void changeTicker(RenderEvent re) {
		instance.Ticker = re;
	}
	
	public static void setBackground(float shade) {
		instance.background(shade);
	}
	
	public static void setBackground(float r, float g, float b) {
		instance.background(r, g, b);
	}
	
	public static void setRectMode(int mode) {
		instance.rectMode(mode);
	}
	
	public static void setEllipseMode(int mode) {
		instance.ellipseMode(mode);
	}
	
	public static void setColor(float shade) {
		instance.fill(shade);
	}
	
	public static void setColor(float shade, float transparency) {
		instance.fill(shade);
	}
	
	public static void setColor(float r, float g, float b) {
		instance.fill(r, g, b);
	}

	public static void setColor(float r, float g, float b, float transparency) {
		instance.fill(r, g, b, transparency);
	}
	
	public static void setStroke(float shade) {
		instance.stroke(shade);
	}
	
	public static void setStroke(float shade, float transparency) {
		instance.stroke(shade);
	}
	
	public static void setStroke(float r, float g, float b) {
		instance.stroke(r, g, b);
	}

	public static void setStroke(float r, float g, float b, float transparency) {
		instance.stroke(r, g, b, transparency);
	}
	
	public static void setStrokeWeight(float strokeWeight) {
		instance.strokeWeight(strokeWeight);
	}
	
	public static void rectangle(float x, float y, float w, float h) {
		instance.rect(x, y, w, h);
	}
	
	public static void rectangle(float x, float y, float w, float h, float r) {
		instance.rect(x, y, w, h, r);
	}
	
	public static void rectangle(float x, float y, float w, float h, float r1, float r2, float r3, float r4) {
		instance.rect(x, y, w, h, r1, r2, r3, r4);
	}
	
	public static void square(float x, float y, float sidelength) {
		instance.circle(x, y, sidelength);
	}
	
	public static void ellipse(float x, float y, float w, float h) {
		instance.ellipse(x, y, w, h);
	}
	
	public static void circle(float x, float y, float r) {
		instance.circle(x, y, r);
	}
	
	public static void setTextSize(float size) {
		instance.textSize(size);
	}
	
	public static void setTextAlign(int pa, int pb) {
		instance.textAlign(pa, pb);
	}
	
	public static void text(String msg, int x, int y) {
		instance.text(msg, x, y);
	}
	
	public static void text(String msg, float x, float y, float w, float h) {
		instance.text(msg, x, y, w, h);
	}
	
	public static void exit() {
		instance.exit();
	}
	public static void text(String msg, float x, float y) {
		instance.text(msg, x, y);
	}
	
}

