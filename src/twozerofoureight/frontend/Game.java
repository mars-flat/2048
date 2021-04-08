package twozerofoureight.frontend;

import java.util.*;
import twozerofoureight.init.*;
import twozerofoureight.backend.*;

@SuppressWarnings("unused")
public class Game implements RenderEvent, GlobalConstants {
	Button menuButton = new Button(50, 50, 100, 50);
	Map<String, Button> buttonMap = new HashMap<>();
	Map<String, ArrayList<Instruction>> toAnimate;
	List<TileCoordinate> tilesToAdd = new ArrayList<>();
	List<float[]> tilesToMove = new ArrayList<>(); //int array of length 2, storing from x and to x respectively
	Map<Integer, TileCoordinate> activeTiles = new HashMap<>();
	private boolean transitionDone;
	private boolean newTilesAdded;
	private boolean tilesMoved;
	private int transitionTransparency = 180; 
	public Board currentBoard = new Board();
	
	@Override
	public void Run() {}
	
	@Override
	public void Run(Map<String, Integer> UPositions) {
		this.background();
		int mouseX = UPositions.get("mouseX");
		int mouseY = UPositions.get("mouseY");
		boolean mousePressed = UPositions.get("mousePressed")==1?true:false;
		
		this.title();
		this.score();
		this.menuButtonSettings();
		this.drawBoardFrame();
		toAnimate = this.toAnimate();
		
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				int v = currentBoard.board[i][j];
				if (v!= 0) {
					int topLeftX = ((110 + 12) * j) + 162;
					int topLeftY = ((110 + 12) * i) + 212;
					
					TextBox tt = new TextBox(Integer.toString(v), (topLeftX+(topLeftX+110))/2, (topLeftY + (topLeftY+110))/2);
					tt.setTextSize(26);
					this.setColor(v, tt);
					Graphics.rectangle(topLeftX, topLeftY, 110, 110, 5);
					tt.draw();
				}
				
			}
		}
		

		//TODO make instructions for merging
		
		for (Map.Entry<String, Button> b: buttonMap.entrySet()) {
			if (b.getValue().mouseInButton(mouseX, mouseY)) {
				b.getValue().setColor(100, 100, 100);
				if (mousePressed == true) {
					switch(b.getKey()) {
						case "menu":
							Graphics.changeTicker(new Transition(new MainMenu(true)));
					}
				}
			}
		}
		
		menuButton.draw();
		if (transitionDone == false) {
			transitionDone = checkTransition();
		}
	}
	
	private void debug() {
		
		for (int[] r : currentBoard.board) {
			for (int el : r) {
				System.out.print(el + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		/*
		for (Map.Entry<Integer, TileCoordinate> ent : activeTiles.entrySet()) {
			System.out.println(ent.getValue().value+  ": (" + ent.getValue().gridX + ", " + ent.getValue().gridY + ")");
		}
		*/
	}
	
	private Map<String, ArrayList<Instruction>> toAnimate() {
		Map<String, ArrayList<Instruction>> idict = new HashMap<>();
		idict.put("NEW", new ArrayList<Instruction>());
		idict.put("MOVE", new ArrayList<Instruction>());
		idict.put("MERGE", new ArrayList<Instruction>());
		for (Instruction ins: currentBoard.currInstructions) {
			switch(ins.type) {
			case "NEW":
				idict.get("NEW").add(ins);
			case "MOVE":
				idict.get("MOVE").add(ins);
			case "MERGE":
				idict.get("MERGE").add(ins);
			}
		}
		currentBoard.currInstructions.clear();
		return idict;
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
	
	private void score() {
		TextBox title = new TextBox("score: 0", 400, 150);
		title.textColor(120, 66, 18);
		title.setTextSize(18);
		title.draw();
	}
	
	private void menuButtonSettings() {
		menuButton.setColor(150, 150, 150);
		menuButton.setStrokeWeight(3);
		buttonMap.put("menu", menuButton);
	}
	
	private void drawBoardFrame() {
		Graphics.setStrokeWeight(0);
		Graphics.setColor(150);
		Graphics.rectangle(150, 200, 500, 500, 5);
		//each tile is 110 pixels, each being 12 pixels apart. The first rect has x, y coords as 162, 212. 
		//To animate a tile, shift in the x/y direction by exactly 122 pixels
		Graphics.setColor(125);
		for (int i = 212; i < 579; i+=122) {
			for (int j = 162; j < 529; j+=122) {
				Graphics.rectangle(j, i, 110, 110, 5);
			}
		}
	}
	
	private void setColor(int value, TextBox tt) {
		switch(value) {
		case 2:
			Graphics.setColor(238, 228, 218);
			break;
		case 4:
			Graphics.setColor(237, 224, 200);
			break;
		case 8:
			Graphics.setColor(242, 177, 121);
			break;
		case 16:
			Graphics.setColor(245, 149, 99);
			break;
		case 32:
			Graphics.setColor(246, 124, 95);
			break;
		case 64:
			Graphics.setColor(246, 94, 59);
			break;
		case 128:
			Graphics.setColor(237, 207, 114);
			break;
		case 256:
			Graphics.setColor(237, 204, 97);
			break;
		case 512:
			Graphics.setColor(237, 200, 80);
			break;
		case 1024:
			Graphics.setColor(237, 197, 63);
			break;
		case 2048:
			Graphics.setColor(237, 194, 46);
			break;
		}
		
		if (value <= 4) {
			tt.textColor(50, 50, 50);
		} else {
			//white
			tt.textColor(25, 255, 255);
		}
	}
	
	

	@Override
	public void keyPressed(char key) {
		debug();
		System.out.println((int)key==37?"LEFT":(int)key==38?"UP":(int)key==39?"RIGHT":(int)key==40?"DOWN":(int)key);
		if (key==37) {
			currentBoard.moveLeft();
			newTilesAdded = false;
		} else if (key == 39) {
			currentBoard.moveRight();
			newTilesAdded = false;
		} else if (key==38) {
			currentBoard.moveUp();
			newTilesAdded = false;
		} else if (key == 40) {
			currentBoard.moveDown();
			newTilesAdded = false;
		}
		
		if (currentBoard.checkMovable() == false) {
			//make a game over screen
			
			
		}
		
		
		for (int[] r : currentBoard.board) {
			for (int el : r) {
				System.out.print(el + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		
	} 
}

/*
 * private void handleNewTiles() {
		//process new tile locations and remove them from the list
		while (!toAnimate.get("NEW").isEmpty()) {
			Instruction newTile = toAnimate.get("NEW").remove(0);
			//System.out.println(newTile.x + " " + newTile.y);
			int topLeftX = ((110 + 12) * newTile.y) + 162;
			int topRightX = topLeftX + 110;
			int topLeftY = ((110 + 12) * newTile.x) + 212;
			int bottomLeftY = topLeftY + 110;
			TileCoordinate temp = new TileCoordinate((topRightX+topLeftX)/2, (bottomLeftY+topLeftY)/2, 0, 0, newTile.x, newTile.y);
			temp.setValue(currentBoard.board[newTile.x][newTile.y]);
			tilesToAdd.add(temp);
		}
		
		//the first slot's top left point is 162 pixels from the left side of the screen.
		//to get the top left point of a slot, multiply (110+12) by the tile number (0 based) and add 162
		//to get the top right point, add 110 to that. 
		
		for (TileCoordinate tc : tilesToAdd) {
			tc.w+=11;
			tc.h+=11;
			if (tc.w >= 110 && tc.h >= 110) {
				newTilesAdded = true;
			}
			Graphics.setRectMode(CENTER);
			Graphics.setColor(238, 228, 218);
			Graphics.rectangle(tc.x, tc.y, tc.w, tc.h, 5);
		}
		
		if (newTilesAdded == true) {
			for (TileCoordinate el: tilesToAdd) {
				int topLeftX = ((110 + 12) * el.gridY) + 162;
				int topLeftY = ((110 + 12) * el.gridX) + 212;
				el.x = topLeftX;
				el.y = topLeftY;
				activeTiles.put(el.id, el);
			}
			tilesToAdd.clear();
			newTilesAdded = false;
		}			
	}
	
	private void moveTiles() {
		while (!toAnimate.get("MOVE").isEmpty()) {
			Instruction moveTile = toAnimate.get("MOVE").remove(0);
			//instructions are encoded here
			
			
			//in each instruction object what is important for this step:
			//turn oX and oY into proper pixel coords instead of array indicies
			//do the same for x and y
			//store these in a data structure
			//the tile to move id should be in activeTiles's keyset
			//that id is located using 'oid' in the instruction object
			
			if (activeTiles.containsKey(moveTile.oid)) {
				
				TileCoordinate temp = activeTiles.get(moveTile.oid);
				
				int oldTopLeftX = ((110 + 12) * temp.gridY) + 162;
				int newTopLeftX = ((110 + 12) * moveTile.y) + 162;
				float difference = newTopLeftX - oldTopLeftX;
				//System.out.println(oldTopLeftX + " " + newTopLeftX);
				
				tilesToMove.add(new float[] {newTopLeftX, difference, temp.id, moveTile.y, 0});
			}
		}
		
		while (!toAnimate.get("MERGE").isEmpty()) {
			Instruction mergeTile = toAnimate.get("MERGE").remove(0);
			//do the same thing as move pretty much
			if (activeTiles.containsKey(mergeTile.oid)) {
				TileCoordinate temp = activeTiles.get(mergeTile.oid);
				
				int oldTopLeftX = ((110 + 12) * temp.gridY) + 162;
				int newTopLeftX = ((110 + 12) * mergeTile.y) + 162;
				float difference = newTopLeftX - oldTopLeftX;
				
				tilesToMove.add(new float[] {newTopLeftX, difference, temp.id, mergeTile.y, 1});
			}
		}
		
		for (float[] tc : tilesToMove) {
			
			if (tc[4] == 0) {
				activeTiles.get((int) tc[2]).gridY = (int) tc[3];
				activeTiles.get((int) tc[2]).updateId();
				
				activeTiles.get((int) tc[2]).x += (tc[1]/8);
				//System.out.println(activeTiles.get(tc[2]).x);
				if (activeTiles.get((int) tc[2]).x <= tc[0]) { 
					
					tilesMoved = true;
				}
			} else {
				//handle merging tiles
				//activeTiles.get((int) tc[2]).gridY = (int) tc[3];
				//activeTiles.get((int) tc[2]).updateId();
				
				activeTiles.get((int) tc[2]).x += (tc[1]/8);
				//System.out.println(activeTiles.get(tc[2]).x);
				if (activeTiles.get((int) tc[2]).x <= tc[0]) { 
					
					//override previous id of the other merging tile with new one
					activeTiles.get((int) tc[2]).gridY = (int) tc[3];
					activeTiles.get((int) tc[2]).updateId();
					activeTiles.get((int) tc[2]).value*=2;
					tilesMoved = true;
				}
			}
		}
		
		if (tilesMoved == true) {
			tilesToMove.clear();
			tilesMoved = false;
		}
	}
	
	private void drawActiveTiles() {
		for (Map.Entry<Integer, TileCoordinate> tile : activeTiles.entrySet()) {
			TileCoordinate tc = tile.getValue();
			Graphics.setRectMode(CORNER);
			TextBox text = new TextBox(Integer.toString(tc.value), (tc.x+(tc.x+110))/2, (tc.y+(tc.y+110))/2);
			text.setTextSize(36);
			this.setColor(tc.value, text);
			Graphics.rectangle(tc.x, tc.y, tc.w, tc.h, 5);
			text.draw();
		}
		
	}
	*/

