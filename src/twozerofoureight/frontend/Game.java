package twozerofoureight.frontend;

import java.util.*;

public class Game implements RenderEvent {
	Button menuButton = new Button(500, 100, 100, 100);
	Map<String, Button> buttonMap = new HashMap<>();
	
	@Override
	public void Run() {}
	
	@Override
	public void Run(Map<String, Integer> positions) {
		//System.out.println("hi");
		int mouseX = positions.get("mouseX");
		int mouseY = positions.get("mouseY");
		boolean mousePressed = positions.get("mousePressed")==1?true:false;
		Graphics.setBackground(200);
		menuButton.setColor(150, 150, 150);
		menuButton.setStrokeWeight(3);
		
		buttonMap.put("menu", menuButton);
		for (Map.Entry<String, Button> b: buttonMap.entrySet()) {
			if (b.getValue().mouseInButton(mouseX, mouseY)) {
				b.getValue().setColor(100, 100, 100);
				if (mousePressed == true) {
					switch(b.getKey()) {
						case "menu":
							Graphics.changeTicker(new MainMenu());
					}
				}
			}
		}
		menuButton.draw();
	}
}
