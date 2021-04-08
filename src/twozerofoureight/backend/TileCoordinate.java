package twozerofoureight.backend;

public class TileCoordinate {
	//each tile is 110 pixels, each being 12 pixels apart. The first rect has x, y coords as 162, 212. 
	//To animate a tile, shift in the x/y direction by exactly 122 pixels
	//note that the y coord is always 50 more than the x coord
	
	//this is the current tile size
	public float x; 
	public float y;
	public float w;
	public float h;
	public int gridX;
	public int gridY;
	
	//color should auto set based on value ig
	public int value;
	
	//id = x*5 + y
	public int id;
	
	public TileCoordinate(float x, float y, float w, float h, int gridX, int gridY) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.gridX = gridX;
		this.gridY = gridY;
		id = ((this.gridX+1)*5000)+(this.gridY+1);
	}
	
	/**
	 * This should be a power of 2.
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	public void updateId() {
		id = ((this.gridX+1)*5000)+(this.gridY+1);
	}
}
