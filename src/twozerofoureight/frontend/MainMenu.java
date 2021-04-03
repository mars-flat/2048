package twozerofoureight.frontend;

import java.util.*;

public class MainMenu implements RenderEvent {
	Button newGameButton = new Button(100, 100, 100, 100);
	Button quitButton = new Button(100, 250, 100, 100);
	Map<String, Button> buttonMap = new HashMap<>();
	
	@Override
	public void Run() {}
	
	@Override
	public void Run(Map<String, Integer> positions) {
		Graphics.setBackground(200);
		
		TextBox tt = new TextBox(400, 400, 200, 200);
		tt.setText("The quick brown fox jumps over the lazy dog.");
		tt.textColor(250, 150, 150);
		tt.draw();
		
		
		//System.out.println("hi");
		int mouseX = positions.get("mouseX");
		int mouseY = positions.get("mouseY");
		boolean mousePressed = positions.get("mousePressed")==1?true:false;
		
		newGameButton.setColor(150, 150, 150);
		newGameButton.setStrokeWeight(3);
		newGameButton.addText("New Game");
		buttonMap.put("newGame", newGameButton);
		quitButton.setColor(150, 150, 150);
		quitButton.setStrokeWeight(3);
		
		buttonMap.put("quit", quitButton);
		for (Map.Entry<String, Button> b: buttonMap.entrySet()) {
			if (b.getValue().mouseInButton(mouseX, mouseY)) {
				b.getValue().setColor(100, 100, 100);
				if (mousePressed == true) {
					switch(b.getKey()) {
						case "newGame":
							Graphics.changeTicker(new Game());
							break;
						case "quit":
							Graphics.exit();
					}
				}
			}
		}
		newGameButton.draw();
		quitButton.draw();
		
	}
}