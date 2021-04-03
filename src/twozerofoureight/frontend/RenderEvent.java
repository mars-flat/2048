package twozerofoureight.frontend;

import java.util.*;

public interface RenderEvent {
	//basically creating a local draw() function
	public void Run();
	public void Run(Map<String, Integer> positions);
}
