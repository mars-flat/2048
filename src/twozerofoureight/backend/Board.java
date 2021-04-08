package twozerofoureight.backend;

import twozerofoureight.init.*;
import twozerofoureight.frontend.*;
import java.util.*;

@SuppressWarnings("unused")
public class Board {

	public int[][] board;
	private Random rand = new Random();
	//"NEW" : [TileCoordinate x][TileCoordinate y]
	//"MOVE" : [curr x][curr y][new x][new y]
	//"MERGE" : [curr x][curr y][new x][new y]
	public ArrayList<Instruction> currInstructions = new ArrayList<>();
	public boolean moved = false;
	
	public Board() {
		moved = true;
		this.board = new int[4][4];
		
		int temp = 0;
		while (temp < 2) {
			int r = rand.nextInt(4);
			int c = rand.nextInt(4);
			if (board[r][c] == 0) {
				Instruction instruction = new Instruction("NEW", r, c);
				if (temp == 1) {
					board[r][c] = 2;
					
				} else {
					board[r][c] = rand.nextInt(10)==0?4:2;
				}
				currInstructions.add(instruction);
				temp++;
			}
		}
	}
	
	public void addTile() {
		if (this.checkMovable() == true) {
				
			if (moved == true) {
				while (true) {
					int r = rand.nextInt(4);
					int c = rand.nextInt(4);
					if (board[r][c] == 0) {
						currInstructions.add(new Instruction("NEW", r, c));
						board[r][c] = rand.nextInt(10)==0?4:2;
						break;
					}
				}
				moved = false;
			} else {
				System.out.println("Did not move");
			}
		} else {
			System.out.println("BAD!!!");
		}
	}
	
	public void moveLeft() {
		moved = false;
		for (int i = 0; i < 4; ++i) { 
			for (int j = 1; j < 4; ++j) {
				
				//current tile = board[i][j]
				
				//if this slot has a tile
				if (board[i][j] != 0) {
					//check all tiles to the left of it
					for (int k = j-1; k >= 0; --k) {
						//if this tile is NOT empty
						if (board[i][k] != 0) {
							//if this tile is NOT equal to current tile
							if (board[i][k] != board[i][j]) {
								
								//if this tile is NOT adjacent to the current tile
								if (k+1 != j) {
									//perform a move
									System.out.println("moving " + i + " " + j + " to " + i + " " + (k+1));
									//move current tile to this tile's right
									board[i][k+1] = board[i][j];
									board[i][j] = 0;
									
									//send a move command
									currInstructions.add(new Instruction("MOVE", i, j, i, (k+1) ));
									
									//set moved to true
									moved = true;
								}
							
							// if this tile IS equal to the current tile
							} else {
								if (k != j) {
									//perform a merge
									System.out.println("merging " + i + " " + j + " with " + i + " " + k);
									//multiply this tile by 2
									board[i][k]*=2;
									board[i][j] = 0;
									
									//the for loops iterate in a way such that:
									//it is guaranteed that this tile will be as left as it is because it likely has 
									//been moved or is unmovable to the left
									
									//send a merge command
									currInstructions.add(new Instruction("MERGE", i, j, i, k));
									
									//set moved to true
									moved = true;
								}
							}
							
						//after this the tile cannot be moved any further.
						break;
						//usually if it's equal to 0 we let it continue searching 
						//however when it gets to the end we must take care of that
						} else {
							
							//at this stage we're at the leftmost cell of the row
							//and we will move the current tile there
							if (k == 0) {
								board[i][0] = board[i][j];
								board[i][j] = 0;
								
								currInstructions.add(new Instruction("MOVE", i, j, i, 0));
								
								moved = true;
							}
						}
						
					}
				}
			}
		}
		addTile();
	}
	
	public void moveRight() {
		moved = false;
		for (int i = 0; i < 4; ++i) { 
			for (int j = 3; j >= 0; --j) {
				
				//current tile = board[i][j]
				
				//if this slot has a tile
				if (board[i][j] != 0) {
					//check all tiles to the right of it
					for (int k = j+1; k < 4; ++k) {
						//if this tile is NOT empty
						if (board[i][k] != 0) {
							//if this tile is NOT equal to current tile
							if (board[i][k] != board[i][j]) {
								
								//if this tile is NOT adjacent to the current tile
								if (k-1 != j) {
									//perform a move
									System.out.println("moving " + i + " " + j + " to " + i + " " + (k-1));
									//move current tile to this tile's left
									board[i][k-1] = board[i][j];
									board[i][j] = 0;
									
									//send a move command
									currInstructions.add(new Instruction("MOVE", i, j, i, (k-1) ));
									
									//set moved to true
									moved = true;
								}
							
							// if this tile IS equal to the current tile
							} else {
								if (k != j) {
									//perform a merge
									System.out.println("merging " + i + " " + j + " with " + i + " " + k);
									//multiply this tile by 2
									board[i][k]*=2;
									board[i][j] = 0;
									
									//the for loops iterate in a way such that:
									//it is guaranteed that this tile will be as right as it is because it likely has 
									//been moved or is unmovable to the right
									
									//send a merge command
									currInstructions.add(new Instruction("MERGE", i, j, i, k));
									
									//set moved to true
									moved = true;
								}
							}
							
						//after this the tile cannot be moved any further.
						break;
						//usually if it's equal to 0 we let it continue searching 
						//however when it gets to the end we must take care of that
						} else {
							
							//at this stage we're at the rightmost cell of the row
							//and we will move the current tile there
							if (k == 3) {
								board[i][3] = board[i][j];
								board[i][j] = 0;
								
								currInstructions.add(new Instruction("MOVE", i, j, i, 3));
								
								moved = true;
							}
						}
						
					}
				}
			}
		}
		addTile();
	}

	public void moveUp() {
		//this is different as we now need to iterate bottom to up from each column
		//column iteration
		moved = false;
		for (int j = 0; j < 4; ++j) {
			for (int i = 0; i < 4; ++i) {
				
				if (board[i][j] != 0) {
					
					//check from this tile down
					for (int k = i-1; k >= 0; --k) {
						//if tile is occupied
						if (board[k][j] != 0) {
							
							//merge check
							if (board[k][j] != board[i][j]) {
								
								//if curr tile is NOT right above this tile
								if (k+1 != i) {
									//perform a move
									System.out.println("moving " + i + " " + j + " to " + (k+1) + " " + j);
									//move current tile to this tile's left
									board[k+1][j] = board[i][j];
									board[i][j] = 0;
									
									//send a move command
									currInstructions.add(new Instruction("MOVE", i, j, (k+1), j));
									
									//set moved to true
									moved = true;
								}
							} else {
								if (k != i) {
									//perform a merge
									System.out.println("merging " + i + " " + j + " with " + k + " " + j);
									//multiply this tile by 2
									board[k][j]*=2;
									board[i][j] = 0;
									
									//the for loops iterate in a way such that:
									//it is guaranteed that this tile will be as left as it is because it likely has 
									//been moved or is unmovable to the left
									
									//send a merge command
									currInstructions.add(new Instruction("MERGE", i, j, k, j));
									
									//set moved to true
									moved = true;
								}
							}
							
							
							
							break;
						} else {
							if (k == 0) {
								board[0][j] = board[i][j];
								board[i][j] = 0;
								
								currInstructions.add(new Instruction("MOVE", i, j, 0, j));
								
								moved = true;
							}
						}
					}
				}
			}
		}
		addTile();			
	}
	
	public void moveDown() {
		//this is different as we now need to iterate bottom to up from each column
		//column iteration
		moved = false;
		for (int j = 0; j < 4; ++j) {
			for (int i = 3; i >= 0; --i) {
				
				if (board[i][j] != 0) {
					
					//check from this tile down
					for (int k = i+1; k < 4; ++k) {
						//if tile is occupied
						if (board[k][j] != 0) {
							
							//merge check
							if (board[k][j] != board[i][j]) {
								
								//if curr tile is NOT right above this tile
								if (k-1 != i) {
									//perform a move
									System.out.println("moving " + i + " " + j + " to " + (k-1) + " " + j);
									//move current tile to this tile's left
									board[k-1][j] = board[i][j];
									board[i][j] = 0;
									
									//send a move command
									currInstructions.add(new Instruction("MOVE", i, j, (k-1), j));
									
									//set moved to true
									moved = true;
								}
							} else {
								if (k != i) {
									//perform a merge
									System.out.println("merging " + i + " " + j + " with " + k + " " + j);
									//multiply this tile by 2
									board[k][j]*=2;
									board[i][j] = 0;
									
									//the for loops iterate in a way such that:
									//it is guaranteed that this tile will be as left as it is because it likely has 
									//been moved or is unmovable to the left
									
									//send a merge command
									currInstructions.add(new Instruction("MERGE", i, j, k, j));
									
									//set moved to true
									moved = true;
								}
							}
							
							
							
							break;
						} else {
							if (k == 3) {
								board[3][j] = board[i][j];
								board[i][j] = 0;
								
								currInstructions.add(new Instruction("MOVE", i, j, 3, j));
								
								moved = true;
							}
						}
					}
				}
			}
		}
		addTile();		
	}
	
	public boolean checkMovable() {
		boolean canMove = false;
		for (int i = 0; i < 4; ++i) { 
			for (int j = 1; j < 4; ++j) {
				if (board[i][j] != 0) {
					for (int k = j-1; k >= 0; --k) {
						if (board[i][k] != 0) {
							if (board[i][k] != board[i][j]) {
								if (k+1 != j) {canMove = true;}
							} else {
								if (k != j) {canMove = true;}
							}
						break;
						} else {
							if (k == 0) {canMove = true;}
						}
						
					}
				}
			}
		}
		for (int i = 0; i < 4; ++i) { 
			for (int j = 3; j >= 0; --j) {
				if (board[i][j] != 0) {
					for (int k = j+1; k < 4; ++k) {
						if (board[i][k] != 0) {
							if (board[i][k] != board[i][j]) {
								if (k-1 != j) {canMove = true;}
							} else {
								if (k != j) {canMove = true;}
							}
						break;
						} else {
							if (k == 3) {canMove = true;}
						}
						
					}
				}
			}
		}
		for (int j = 0; j < 4; ++j) {
			for (int i = 0; i < 4; ++i) {
				if (board[i][j] != 0) {
					for (int k = i-1; k >= 0; --k) {
						if (board[k][j] != 0) {
							if (board[k][j] != board[i][j]) {
								if (k+1 != i) {canMove = true;}
							} else {
								if (k != i) {canMove = true;}
							}
							break;
						} else {
							if (k == 0) {canMove = true;}
						}
					}
				}
			}
		}
		for (int j = 0; j < 4; ++j) {
			for (int i = 3; i >= 0; --i) {
				if (board[i][j] != 0) {
					for (int k = i+1; k < 4; ++k) {
						if (board[k][j] != 0) {
							if (board[k][j] != board[i][j]) {
								if (k-1 != i) {canMove = true;}
							} else {
								if (k != i) {canMove = true;}
							}
							break;
						} else {
							if (k == 3) {canMove = true;}
						}
					}
				}
			}
		}
		return canMove;
	}
}

