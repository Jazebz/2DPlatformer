
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Platforms implements Background {

    protected static int TILE_WIDTH = 50;
    protected static int TILE_HEIGHT = 50;
    private Image rightEdgeDirt;
    private Image middleGrass;
    private Image dirtRock;
    private Image leftEdgeDirt;
    private Image rightEdge;
    private Image horizontalWood;
    private Image leftEdge;
    private Image middlePlatform;
    private Image rightPlatform;
    private Image leftPlatform;
    private Image threeRockDirt;
    private Image blankDirt;
    private Image rightWall;
    private Image leftWall;
    private Image middleBlank;
    private int maxCols = 0;
    private int maxRows = 0;
    
    private int map[][] = new int[][] { 
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,11,9 ,9 ,9 ,9 ,9 ,10,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,12,13,13,13,14,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{6,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,7},
		{1,8,4,8,8,8,4,8,8,8,4,8,8,8,8,5,8,4,8,8,4,8,8,8,4,5,5,8,8,8,5,4,8,8,8,5,8,8,8,8,4,8,8,4,8,8,8,8,5,8,4,8,8,8,4,8,5,8,8,3},
		{1,8,8,8,4,8,8,5,8,8,8,8,8,8,4,8,8,8,5,8,8,5,8,8,8,8,8,8,8,4,8,8,8,8,4,8,5,8,5,8,8,8,5,8,8,8,5,8,8,8,8,5,8,8,8,8,4,8,4,3},
		{1,5,8,8,4,8,5,8,8,8,4,8,8,4,8,8,5,8,8,8,4,8,8,4,8,8,4,8,8,8,5,8,5,8,8,8,8,8,8,8,4,8,8,8,8,8,8,4,8,8,5,8,8,8,8,8,8,8,8,3},
		{1,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3},
		{1,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3},
		{1,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3},
		{1,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3},
		{1,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,3}
	};
    
    public Platforms() {
    	try {
    		this.dirtRock = ImageIO.read(new File("res/tiles/dirtrock.png"));
    		this.middleGrass = ImageIO.read(new File("res/tiles/middlegrass.png"));
    		this.leftEdgeDirt = ImageIO.read(new File("res/tiles/leftedgedirt.png"));
    		this.horizontalWood = ImageIO.read(new File("res/tiles/horizontalwood.png"));
    		this.leftEdge = ImageIO.read(new File("res/tiles/leftedge.png"));
    	}
    	catch (IOException e){
    		
    	}
    	try {
    		this.leftPlatform = ImageIO.read(new File("res/tiles/leftplatform.png"));
    		this.rightEdgeDirt = ImageIO.read(new File("res/tiles/rightedgedirt.png"));
    		this.middlePlatform = ImageIO.read(new File("res/tiles/middleplatform.png"));
    		this.rightPlatform = ImageIO.read(new File("res/tiles/rightplatform.png"));
    		this.threeRockDirt = ImageIO.read(new File("res/tiles/threerockdirt.png"));
    	}
    	catch (IOException e) {
    		
    	}
    	try {
    		this.rightEdge = ImageIO.read(new File("res/tiles/rightedge.png"));
    		this.blankDirt = ImageIO.read(new File("res/tiles/blankdirt.png"));
    		this.rightWall = ImageIO.read(new File("res/tiles/rightwall.png"));
    		this.leftWall = ImageIO.read(new File("res/tiles/leftwall.png"));
    		this.middleBlank = ImageIO.read(new File("res/tiles/middleblank.png"));
    	}
    	catch (IOException e) {
    		
    	}
    	maxRows = map.length - 1;
    	maxCols = map[0].length - 1;
    	
    }
	@Override
	public Tile getTile(int col, int row) {
		Image image = null;
		
		if (row < 0 || row > maxRows || col < 0 || col > maxCols) {
			image = null;
		}
		else if (map[row][col] == 0) {
			image = null;
		}
		else if (map[row][col] == 1) {
			image = leftEdgeDirt;
		}
		else if (map[row][col] == 2) {
			image = middleGrass;
		}
		else if (map[row][col] == 3) {
			image = rightEdgeDirt;
		}
		else if (map[row][col] == 4) {
			image = dirtRock;
		}
		else if (map[row][col] == 5) {
			image = threeRockDirt;
		}
		else if (map[row][col] == 6) {
			image = leftEdge;
		}
		else if (map[row][col] == 7) {
			image = rightEdge;
		}
		else if (map[row][col] == 8) {
			image = blankDirt;
		}
		else if (map[row][col] == 9) {
			image = middlePlatform;
		}
		else if (map[row][col] == 10) {
			image = rightPlatform;
		}
		else if (map[row][col] == 11) {
			image = leftPlatform;
		}
		else if (map[row][col] == 12) {
			image = leftWall;
		}
		else if (map[row][col] == 13) {
			image = middleBlank;
		}
		else if (map[row][col] == 14) {
			image = rightWall;
		}
		else {
			image = rightEdge;
		}
			
		int x = (col * TILE_WIDTH);
		int y = (row * TILE_HEIGHT);
		
		Tile newTile = new Tile(image, x, y, TILE_WIDTH, TILE_HEIGHT, false);
		
		return newTile;
	}
	@Override
	public int getCol(int x) {
		int col = 0;
		if (TILE_WIDTH != 0) {
			col = (x / TILE_WIDTH);
			if (x < 0) {
				return col - 1;
			}
			else {
				return col;
			}
		}
		else {
			return 0;
		}
	}
	@Override
	public int getRow(int y) {
		int row = 0;
		
		if (TILE_HEIGHT != 0) {
			row = (y / TILE_HEIGHT);
			if (y < 0) {
				return row - 1;
			}
			else {
				return row;
			}
		}
		else {
			return 0;
		}
	}
	public ArrayList<DisplayableSprite> getBarriers() {
		ArrayList<DisplayableSprite> barriers = new ArrayList<DisplayableSprite>();
		for (int col = 0; col < map[0].length; col++) {
			for (int row = 0; row < map.length; row++) {
				if (map[row][col] == 2 || 
						map[row][col] == 6 || 
						map[row][col] == 1 || 
						map[row][col] == 7 || 
						map[row][col] == 2 || 
						map[row][col] == 3 || 
						map[row][col] == 9 || 
						map[row][col] == 10 || 
						map[row][col] == 11) {
					barriers.add(new BarrierSprite(col * TILE_WIDTH, row * TILE_HEIGHT, (col + 1) * TILE_WIDTH, (row + 1) * TILE_HEIGHT, false));
				}
			}
		}
		return barriers;
	}
	
}



