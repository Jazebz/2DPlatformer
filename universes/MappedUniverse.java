import java.util.ArrayList;

public class MappedUniverse implements Universe {


	private boolean complete = false;	
	private Background platforms = null;	
	private Background background = null;
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> sprites = new ArrayList<DisplayableSprite>();
	private double xCenter = 0;
	private double yCenter = 0;

	public MappedUniverse () {

		platforms = new Platforms();
		background = new SkyBackground();
		
		ArrayList<DisplayableSprite> barriers = ((Platforms)platforms).getBarriers();
		
		player1 = new Player1(Platforms.TILE_HEIGHT * 2, Platforms.TILE_WIDTH * 2 + 280);
		
		sprites.add(player1);
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

}
