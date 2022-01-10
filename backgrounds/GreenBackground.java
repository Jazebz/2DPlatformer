import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GreenBackground implements Background{
	
	private Image greenBackground;
    private int backgroundWidth = 0;
    private int backgroundHeight = 0;
	
	 public GreenBackground() {
	    	try {
	    		this.greenBackground = ImageIO.read(new File("res/middle.png"));
	    		backgroundWidth = (int)(greenBackground.getWidth(null) * 3.5);
	    		backgroundHeight = (int)(greenBackground.getHeight(null) * 3.5);
	    		
	    	}
	    	catch (IOException e) {
	    		//System.out.println(e.toString());
	    	}		
	    }
	
	@Override
	public Tile getTile(int col, int row) {
		int x = (col * backgroundWidth);
		int y = (row * backgroundHeight);
		Tile newTile = null;
		
		newTile = new Tile(greenBackground, x, y, backgroundWidth, backgroundHeight, false);

		return newTile;
	}

	@Override
	public int getCol(int x) {
		int col = 0;
		if (backgroundWidth != 0) {
			col = (x / backgroundWidth);
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
		
		if (backgroundHeight != 0) {
			row = (y / backgroundHeight);
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
	public int getHeight() {
		return backgroundHeight;
	}
	public int getWidth() {
		return backgroundWidth;
	}

}
