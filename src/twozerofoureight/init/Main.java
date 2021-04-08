package twozerofoureight.init;

import java.io.IOException;
import java.util.*;
//import twozerofoureight.backend.*;
import twozerofoureight.frontend.*;
import processing.core.*;

public class Main extends PApplet implements GlobalConstants {
	
	public RenderEvent Ticker;
    public static void main(String[] args) {
        PApplet.main(Thread.currentThread().getStackTrace()[1].getClassName());
    }
    
    @Override
    public void settings() {
        size(800,800);
    }
    
    @Override
    public void setup() {
    	surface.setVisible(false);
    	System.out.println("Launching... Press and enter any key to load window:");
    	try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	surface.setVisible(true);
    	Graphics.init(this);
    	Ticker = new MainMenu(false);
    }
    
    @Override
    public void draw() {
    	Map<String, Integer> positions = new HashMap<>();
    	positions.put("mouseX", mouseX);
    	positions.put("mouseY", mouseY);
    	positions.put("pmouseX", pmouseX);
    	positions.put("pmouseY", pmouseY);
    	positions.put("mousePressed", mousePressed==true?1:0);
    	Ticker.Run();
    	Ticker.Run(positions);
    }
    
    public void keyPressed() {
    	Ticker.keyPressed(key==GlobalConstants.CODED?(char)keyCode:key);
    }
}

/*
int CORNER = 0;
int CORNERS = 1;
int RADIUS = 2;
int CENTER = 3;
*/