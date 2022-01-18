import java.util.ArrayList;

public class MappedUniverse implements Universe {


	private boolean complete = false;	
	private Background platforms = null;	
	private Background background = null;
	private Background middleBackground = null;
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> barrierSprites = new ArrayList<DisplayableSprite>();
	private static ArrayList<DisplayableSprite> nonBarrierSprites = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> disposalList = new ArrayList<DisplayableSprite>();
	private double xCenter = 500;
	private double yCenter = 0;

	public MappedUniverse () {

		platforms = new Platforms();
		background = new SkyBackground();
		middleBackground = new GreenBackground();
		
		ArrayList<DisplayableSprite> barriers = ((Platforms)platforms).getBarriers();
		
		player1 = new Player1(Platforms.TILE_HEIGHT * 2 + 500, Platforms.TILE_WIDTH * 2 + 350);
		
		barrierSprites.add(player1);
		//barrierSprites.add(new Enemy1(1000, Platforms.TILE_WIDTH * 2 + 350, 750, 1250));
		//barrierSprites.add(new Enemy1(2000, Platforms.TILE_WIDTH * 2 + 350, 1750, 2250));
		//barrierSprites.add(new Enemy1(3000, Platforms.TILE_WIDTH * 2 + 350, 2750, 3250));
		//nonBarrierSprites.add(new Cherry(3000, Platforms.TILE_WIDTH * 2 + 450));
		//nonBarrierSprites.add(new Cherry(1000, Platforms.TILE_WIDTH * 2 + 450));
		//nonBarrierSprites.add(new Cherry(2000, Platforms.TILE_WIDTH * 2 + 450))
		nonBarrierSprites.add(new Sign(875, Platforms.TILE_WIDTH * 2 + 490));
		nonBarrierSprites.add(new Sign(1350, Platforms.TILE_WIDTH * 2 + 490));
		nonBarrierSprites.add(new Sign(2600, Platforms.TILE_WIDTH * 2 + 490));
		nonBarrierSprites.add(new House(550, Platforms.TILE_WIDTH * 2 + 350));
		barrierSprites.add(new Enemy1(3250, Platforms.TILE_WIDTH * 2 + 350, 3000, 3500));
		barrierSprites.addAll(barriers);

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
	public static void addNonBarrierSprite(DisplayableSprite sprite) {
		nonBarrierSprites.add(sprite);
	}

	public DisplayableSprite getPlayer1() {
		return player1;
	}

		
	public boolean centerOnPlayer() {
		return true;
	}		
	
	public void update(KeyboardInput keyboard, long actual_delta_time) {

		if (keyboard.keyDownOnce(27)) {
			complete = true;
		}
		
		for (int i = 0; i < barrierSprites.size(); i++) {
			DisplayableSprite sprite = barrierSprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	}
		for (int i = 0; i < nonBarrierSprites.size(); i++) {
			DisplayableSprite sprite = nonBarrierSprites.get(i);
			sprite.update(this, keyboard, actual_delta_time);
    	} 
		disposeSprites();
		if(player1.getRespawn()) {
		}
	}

	public String toString() {
		return "MappedUniverse";
	}

	@Override
	public Background getBackgroundMiddle() {
		return middleBackground;
	}	
	protected void disposeSprites() {
        
    	//collect a list of sprites to dispose
    	//this is done in a temporary list to avoid a concurrent modification exception
		for (int i = 0; i < barrierSprites.size(); i++) {
			DisplayableSprite sprite = barrierSprites.get(i);
    		if (sprite.getDispose() == true) {
    			disposalList.add(sprite);
    		}
    	}
		for (int i = 0; i < nonBarrierSprites.size(); i++) {
			DisplayableSprite sprite = nonBarrierSprites.get(i);
    		if (sprite.getDispose() == true) {
    			disposalList.add(sprite);
    		}
    	}
		//go through the list of sprites to dispose
		//note that the sprites are being removed from the original list
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			barrierSprites.remove(sprite);
			System.out.println("Remove: " + sprite.toString());
    	}
		for (int i = 0; i < disposalList.size(); i++) {
			DisplayableSprite sprite = disposalList.get(i);
			nonBarrierSprites.remove(sprite);
			System.out.println("Remove: " + sprite.toString());
    	}
		
		//clear disposal list if necessary
    	if (disposalList.size() > 0) {
    		disposalList.clear();
    	}
    }

	public ArrayList<DisplayableSprite> getBarrierSprites() {
		return barrierSprites;
	}
	public ArrayList<DisplayableSprite> getNonBarrierSprites() {
		return nonBarrierSprites;
	}

}
