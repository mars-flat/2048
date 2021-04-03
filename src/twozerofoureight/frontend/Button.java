package twozerofoureight.frontend;
	
import twozerofoureight.init.*;

public class Button implements GlobalConstants {
	public float x, y, w, h;
	private float rad1, rad2, rad3, rad4;
	private float r=128, g=1, b=1, transparency=255;
	private float sr=0, sg=1, sb=1, strokeTransparency=255, strokeWeight;
	public boolean active = false;
	private TextBox text;
	
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
		this.text = new TextBox(x, y, w, h);
		this.text.setText(text);
	}
	
	public void draw() {
		Graphics.setColor(r, g, b, transparency);
		Graphics.setStroke(sr, sg, sb, strokeTransparency);
		Graphics.setStrokeWeight(strokeWeight);
		Graphics.setRectMode(CORNER);
		Graphics.rectangle(x, y, w, h, rad1, rad2, rad3, rad4);
		//text.draw();
	}
}

