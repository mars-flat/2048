package twozerofoureight.frontend;

import java.util.*;
import twozerofoureight.init.*;

@SuppressWarnings("unused")
public class MainMenu implements RenderEvent {
	Button newGameButton = new Button(260, 275, 125, 125);
	Button speedGameButton = new Button(400, 275, 125, 125);
	Button settingsButton = new Button(260, 415, 125, 125);
	Button creditsButton = new Button(400, 415, 125, 125);
	Button quitButton = new Button(50, 650, 100, 100);
	Map<String, Button> buttonMap = new HashMap<>();
	private boolean transitionDone = true;
	private int transitionTransparency = 180; 
	
	public MainMenu(boolean doTransition) {
		transitionDone = !doTransition;
	}
	
	@Override
	public void Run() {}
	
	@Override
	public void Run(Map<String, Integer> UProperties) {
		this.background();
		int mouseX = UProperties.get("mouseX");
		int mouseY = UProperties.get("mouseY");
		boolean mousePressed = UProperties.get("mousePressed")==1?true:false;
		
		this.title();
		this.help();
		this.score();
		this.newGameButtonSettings();
		this.speedGameSettings();
		this.settingsSettings();
		this.creditsSettings();
		this.quitButtonSettings();
		
		for (Map.Entry<String, Button> b: buttonMap.entrySet()) {
			if (b.getValue().mouseInButton(mouseX, mouseY)) {
				Button tmp = b.getValue();
				tmp.setColor(tmp.r-25, tmp.g-25, tmp.b-25);
				if (mousePressed == true) {
					switch(b.getKey()) {
						case "newGame":
							Graphics.changeTicker(new Transition(new Game()));
							break;
						case "newSpeedGame":
							Graphics.changeTicker(null);
							break;
						case "settings":
							Graphics.changeTicker(null);
							break;
						case "credits":
							Graphics.changeTicker(null);
							break;
						case "quit":
							Graphics.exit();
					}
				}
			}
		}
		
		newGameButton.draw();
		speedGameButton.draw();
		settingsButton.draw();
		creditsButton.draw();
		quitButton.draw();
		
		if (transitionDone == false) {
			transitionDone = checkTransition();
		}
	}
	
	private boolean checkTransition() {
		Graphics.setColor(0,0,0,transitionTransparency);
		Graphics.rectangle(-5, -5, 805, 805);
		transitionTransparency-=10;
		if (transitionTransparency <= 0) {
			return true;
		}
		return false;
	}
	
	private void background() {
		Graphics.setBackground(241, 237, 190);
	}
	
	private void title() {
		TextBox title = new TextBox("2048", 400, 100);
		title.textColor(120, 66, 18);
		title.setTextSize(56);
		title.draw();
	}
	
	private void help() {
		TextBox help = new TextBox("Merge the tiles, and try to reach 2048!", 400, 600);
		help.textColor(120, 66, 18);
		help.setTextSize(18);
		help.draw();
	}
	
	private void score() {
		TextBox title = new TextBox("Highscore: 0", 400, 240);
		title.textColor(120, 66, 18);
		title.setTextSize(18);
		title.draw();
	}
	
	private void newGameButtonSettings() {
		newGameButton.setRadii(5);
		newGameButton.setColor(237, 194, 46);
		newGameButton.setStrokeWeight(3);
		newGameButton.addText("New Game");
		newGameButton.setTextSize(20);
		newGameButton.textColor(255,255,255);
		buttonMap.put("newGame", newGameButton);
	}
	
	private void speedGameSettings() {
		speedGameButton.setRadii(5);
		speedGameButton.setColor(237, 197, 63);
		speedGameButton.setStrokeWeight(3);
		speedGameButton.addText("Speed Game");
		speedGameButton.setTextSize(18);
		speedGameButton.textColor(255,255,255);
		buttonMap.put("newSpeedGame", speedGameButton);
	}
	
	private void settingsSettings() {
		settingsButton.setRadii(5);
		settingsButton.setColor(237, 200, 80);
		settingsButton.setStrokeWeight(3);
		settingsButton.addText("Settings");
		settingsButton.setTextSize(20);
		settingsButton.textColor(255,255,255);
		buttonMap.put("settings", settingsButton);
	}
	
	private void creditsSettings() {
		creditsButton.setRadii(5);
		creditsButton.setColor(237, 204, 97);
		creditsButton.setStrokeWeight(3);
		creditsButton.addText("Credits");
		creditsButton.setTextSize(20);
		creditsButton.textColor(255,255,255);
		buttonMap.put("credits", creditsButton);
	}
	
	private void quitButtonSettings() {
		quitButton.setRadii(5);
		quitButton.setColor(150, 150, 150);
		quitButton.setStrokeWeight(3);
		quitButton.addText("Quit");
		quitButton.setTextSize(16);
		quitButton.textColor(255,255,255);
		buttonMap.put("quit", quitButton);
	}

	@Override
	public void keyPressed(char key) {}
}