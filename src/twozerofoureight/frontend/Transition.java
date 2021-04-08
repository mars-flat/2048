package twozerofoureight.frontend;

import java.util.Map;

public class Transition implements RenderEvent {
	
	private int transparency = 0; 
	private RenderEvent to;
	
	public Transition(RenderEvent to) {
		this.to = to;
	}
	
	@Override
	public void Run() {}

	@Override
	public void Run(Map<String, Integer> positions) {
		Graphics.setColor(0, 0, 0, transparency);
		Graphics.rectangle(-5, -5, 805, 805);
		transparency+=7;
		if (transparency>=175) {
			Graphics.changeTicker(to);
		}
	}

	@Override
	public void keyPressed(char key) {}

}
