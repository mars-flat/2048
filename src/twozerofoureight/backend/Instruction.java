package twozerofoureight.backend;

public class Instruction {
	public String type;
	//original x and y if applicable
	public int oX;
	public int oY;
	public int oid;
	public int id;
	
	//this is the amount you WANT it to become
	public int x;
	public int y;
	public Instruction(String type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.id = (x+1)*5000 + (y+1);
	} 
	
	public Instruction(String type, int oX, int oY, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.oX = oX;
		this.oY = oY;
		this.oid = (this.oX+1)*5000 + (this.oY+1);
		this.id = (this.x+1)*5000 + (this.y+1);
	} 
}
