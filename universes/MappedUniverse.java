import java.util.ArrayList;

public class MappedUniverse implements Universe {


	private boolean complete = false;	
	private Background platforms = null;	
	private Background background = null;
	private Background middleBackground = null;
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private double xCenter = 500;
	private double yCenter = 0;

	public MappedUniverse () {

		platforms = new Platforms();
		background = new SkyBackground();
		middleBackground = new GreenBackground();
		
		ArrayList<DisplayableSprite> barriers = ((Platforms)platforms).getBarriers();
		
		player1 = new Player1(Platforms.TILE_HEIGHT * 2 + 500, Platforms.TILE_WIDTH * 2 + 350);
		
		sprites.add(player1);
		sprites.add(new Enemy1(1000, Platforms.TILE_WIDTH * 2 + 350, 750, 1250));
		sprites.add(new Enemy1(2000, Platforms.TILE_WIDTH * 2 + 350, 1750, 2250));
		sprites.add(new Enemy1(3000, Platforms.TILE_WIDTH * 2 + 350, 2750, 3250));
		sprites.addAll(barriers);

	}

	public double getScale() {
		return 1;
	}	
	
	public double getXCenter() {
		return this.xCenter;
	}

	public double getYCenter() {
		return this.yCenter;
	}
	
	public void setXCenter(double xCenter) {
		this.xCenter = xCenter;
	}

	public void setYCenter(double yCenter) {
		this.yCenter = yCenter;
	}
	
	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		complete = true;
	}

	public Background getPlatforms() {
		return platforms;
	}
	public Background getBackground() {
		return background;
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

	public ArrayList<DisplayableSprite> getSprites() {
		return sprites;
	}
		
	public boolean centerOnPlayer() {
		return true;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < sprites.size(); i++) {
			DisplayableSprite sprite = sprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
	}

	public String toString() {
		return "MappedUniverse";
	}

	@Override
	public Background getBackgroundMiddle() {
		return middleBackground;
	}	

}
