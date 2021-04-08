package twozerofoureight.frontend;

import twozerofoureight.init.GlobalConstants;

public class TextBox implements GlobalConstants {
	public float x;
	public float y;
	public float r = 128;
	public float g = 128;
	public float b = 128;
	public float transparency = 255;
	public float size = 12;
	public String text = "text";
	
	public TextBox(String text, float x, float y) {
		this.text = text;
		this.x = x;
		this.y = y;
	}
	
	public void textColor(float r, float g, float b) {
		this.r=r;
		this.g=g;
		this.b=b;
	}
	
	public void textTransparency(float t) {
		transparency = t;
	}
	
	public void setTextSize(float size) {
		this.size = size;
	}
	
	public void draw() {
		Graphics.setColor(r,g, b, transparency);
		Graphics.setTextAlign(CENTER, CENTER);
		Graphics.setTextSize(size);
		Graphics.text(text, x, y);
	}
}
