
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
    private Image bottomLeftCorner;
    private Image bottomLeftCornerBehind;
    private Image bottomRightCorner;
    private Image bottonRightCornerBehind;
    private Image topRightCorner;
    private Image topRightCornerBehind;
    private Image topLeftCorner;
    private Image topLeftCornerBehind;
    private Image caveRock;
    private Image bridge;
    private Image bridgePost;
    private Image caveFlame;
    private Image caveFloor;
    private Image caveRoof;
    private Image outerLeftCave;
    private Image outerRightCave;
    private Image topLeftCave;
    private Image topRightCave;
    private int maxCols = 0;
    private int maxRows = 0;
    
    private int map[][] = new int[][] { 
    	{2 ,2 ,2 ,2 ,2 ,7 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,8 ,8 ,5 ,4 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,5 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,5 ,5 ,8 ,5 ,4 ,8 ,5 ,4 ,8 ,8 ,8 ,4 ,8 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{5 ,8 ,5 ,8 ,5 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,8 ,8 ,8 ,8 ,8 ,5 ,8 ,8 ,4 ,8 ,5 ,8 ,5 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{4 ,4 ,8 ,8 ,8 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,8 ,4 ,8 ,5 ,4 ,8 ,5 ,8 ,8 ,8 ,8 ,8 ,8 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,8 ,8 ,5 ,8 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,5 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,5 ,8 ,8 ,5 ,4 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,5 ,8 ,8 ,5 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,4 ,5 ,8 ,8 ,8 ,5 ,8 ,4 ,8 ,8 ,4 ,8 ,8 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,4 ,8 ,8 ,4 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,29,8 ,8 ,8 ,5 ,8 ,8 ,8 ,5 ,4 ,5 ,8 ,5 ,5 ,30,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,4 ,8 ,8 ,8 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,31,28,28,28,28,28,28,28,28,28,28,28,28,28,32,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,8 ,4 ,5 ,4 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,11,9 ,9 ,9 ,9 ,9 ,9 ,9 ,10,00,00,00,00,21,22,13,13,13,13,13,13,13,13,13,13,13,14,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{5 ,5 ,8 ,8 ,8 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,21,22,13,13,23,20,19,00,00,00,00,00,00,12,13,13,13,13,13,13,13,13,13,13,13,14,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,4 ,5 ,4 ,8 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,11,9 ,9 ,9 ,9 ,9 ,10,00,00,00,12,23,13,13,14,00,00,00,00,00,00,00,12,13,26,13,13,26,13,13,26,13,13,13,18,17,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
		{8 ,8 ,8 ,5 ,4 ,3 ,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,12,13,23,13,14,00,00,00,15,16,13,13,23,18,17,00,00,00,00,00,15,16,13,13,13,13,13,13,13,13,13,13,13,13,14,00,00,00,25,00,00,00,00,00,25,00,00,00,00,00,00},
		{4 ,8 ,8 ,8 ,8 ,3 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,2 ,27,27,27,27,27,27,27,27,27,27,27,27,27,27,27,2 ,2 ,2 ,7 ,24,24,24,24,24,6 ,2 ,2 ,2 ,2 ,2 ,2 },
		{4 ,8 ,4 ,4 ,8 ,8 ,5 ,8 ,8 ,8 ,4 ,8 ,8 ,5 ,8 ,5 ,8 ,4 ,8 ,8 ,4 ,8 ,8 ,8 ,4 ,5 ,5 ,8 ,8 ,8 ,5 ,4 ,8 ,8 ,8 ,5 ,8 ,8 ,8 ,8 ,4 ,8 ,8 ,4 ,8 ,8 ,8 ,8 ,5 ,8 ,4 ,8 ,8 ,8 ,4 ,8 ,5 ,8 ,8 ,3 ,00,00,00,00,00,1 ,8 ,5 ,8 ,5 ,8 ,4 },
		{8 ,8 ,8 ,8 ,4 ,8 ,8 ,5 ,8 ,8 ,8 ,8 ,4 ,8 ,4 ,8 ,8 ,8 ,5 ,8 ,8 ,5 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,4 ,8 ,8 ,8 ,8 ,4 ,8 ,5 ,8 ,5 ,8 ,8 ,8 ,5 ,8 ,8 ,8 ,5 ,8 ,8 ,8 ,8 ,5 ,8 ,8 ,8 ,8 ,4 ,8 ,4 ,3 ,00,00,00,00,00,1 ,4 ,8 ,8 ,4 ,8 ,8 },
		{8 ,5 ,8 ,8 ,5 ,8 ,5 ,8 ,8 ,8 ,4 ,8 ,8 ,4 ,8 ,8 ,5 ,8 ,8 ,8 ,4 ,8 ,8 ,4 ,8 ,8 ,4 ,8 ,8 ,8 ,5 ,8 ,5 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,4 ,8 ,8 ,8 ,8 ,8 ,8 ,4 ,8 ,8 ,5 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,1 ,8 ,8 ,8 ,8 ,5 ,5 },
		{8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,1 ,5 ,8 ,4 ,5 ,8 ,8 },
		{8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,1 ,8 ,8 ,8 ,8 ,8 ,8 },
		{8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,1 ,8 ,8 ,8 ,8 ,8 ,8 },
		{8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,1 ,8 ,8 ,8 ,8 ,8 ,8 },
		{8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,8 ,3 ,00,00,00,00,00,1 ,8 ,8 ,8 ,8 ,8 ,8 }
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
    	try {
    		this.bottomLeftCorner = ImageIO.read(new File("res/tiles/bottomleftcorner.png"));
    		this.bottomLeftCornerBehind = ImageIO.read(new File("res/tiles/bottomleftcornerbehind.png"));
    		this.bottomRightCorner = ImageIO.read(new File("res/tiles/bottomrightcorner.png"));
    	}
    	catch (IOException e) {
    		
    	}
    	try {
    		this.bottonRightCornerBehind = ImageIO.read(new File("res/tiles/bottomrightcornerbehind.png"));
    		this.topRightCorner = ImageIO.read(new File("res/tiles/toprightcorner.png"));
    		this.caveRock = ImageIO.read(new File("res/tiles/caverock.png"));
    	}
    	catch (IOException e) {
    		
    	}
    	try {
    		this.topRightCornerBehind = ImageIO.read(new File("res/tiles/toprightcornerbehind.png"));
    		this.topLeftCorner = ImageIO.read(new File("res/tiles/topleftcorner.png"));
    		this.topLeftCornerBehind = ImageIO.read(new File("res/tiles/topleftcornerbehind.png"));
    	}
    	catch (IOException e) {
    		
    	}
    	try {
    		this.bridge = ImageIO.read(new File("res/tiles/bridge.png"));
    		this.bridgePost = ImageIO.read(new File("res/tiles/bridgepost.png"));
    		this.caveFlame = ImageIO.read(new File("res/tiles/caveflame.png"));
    		this.caveFloor = ImageIO.read(new File("res/tiles/cavefloor.png"));
    		this.caveRoof = ImageIO.read(new File("res/tiles/caveroof.png"));
    	}
    	catch (IOException e) {
    		
    	}
    	try {
    		this.outerLeftCave = ImageIO.read(new File("res/tiles/outerleftcave.png"));
    		this.outerRightCave = ImageIO.read(new File("res/tiles/outerrightcave.png"));
    		this.topLeftCave = ImageIO.read(new File("res/tiles/topleftcave.png"));
    		this.topRightCave = ImageIO.read(new File("res/tiles/toprightcave.png"));
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
		else if (map[row][col] == 15) {
			image = bottomLeftCorner;
		}
		else if (map[row][col] == 16) {
			image = bottomLeftCornerBehind;
		}
		else if (map[row][col] == 17) {
			image = bottomRightCorner;
		}
		else if (map[row][col] == 18) {
			image = bottonRightCornerBehind;
		}
		else if (map[row][col] == 19) {
			image = topRightCorner;
		}
		else if (map[row][col] == 20) {
			image = topRightCornerBehind;
		}
		else if (map[row][col] == 21) {
			image = topLeftCorner;
		}
		else if (map[row][col] == 22) {
			image = topLeftCornerBehind;
		}
		else if (map[row][col] == 23) {
			image = caveRock;
		}
		else if (map[row][col] == 24) {
			image = bridge;
		}
		else if (map[row][col] == 25) {
			image = bridgePost;
		}
		else if (map[row][col] == 26) {
			image = caveFlame;
		}
		else if (map[row][col] == 27) {
			image = caveFloor;
		}
		else if (map[row][col] == 28) {
			image = caveRoof;
		}
		else if (map[row][col] == 29) {
			image = outerLeftCave;
		}
		else if (map[row][col] == 30) {
			image = outerRightCave;
		}
		else if (map[row][col] == 31) {
			image = topLeftCave;
		}
		else if (map[row][col] == 32) {
			image = topRightCave;
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
						map[row][col] == 10|| 
						map[row][col] == 11||
						map[row][col] == 24||
						map[row][col] == 27|| 
						map[row][col] == 29|| 
						map[row][col] == 30|| 
						map[row][col] == 31||
						map[row][col] == 32) {
					barriers.add(new BarrierSprite(col * TILE_WIDTH, row * TILE_HEIGHT, (col + 1) * TILE_WIDTH, (row + 1) * TILE_HEIGHT, false));
				}
			}
		}
		return barriers;
	}
	
}



