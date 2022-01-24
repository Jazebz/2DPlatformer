import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MappedUniverse implements Universe {


	private boolean complete = false;	
	private Background platforms = null;	
	private Background background = null;
	private Background middleBackground = null;
	private DisplayableSprite player1 = null;
	private DisplayableSprite boss = null;
	private ArrayList<DisplayableSprite> barrierSprites = new ArrayList<DisplayableSprite>();
	private static ArrayList<DisplayableSprite> nonBarrierSprites = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> barrierDisposalList = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> nonBarrierDisposalList = new ArrayList<DisplayableSprite>();
	private ArrayList<DisplayableSprite> temporaryDisposalList = new ArrayList<DisplayableSprite>();
	private double xCenter = 500;
	private double yCenter = 0;
	Timer timer = new Timer();

	public MappedUniverse () {

		platforms = new Platforms();
		background = new SkyBackground();
		middleBackground = new GreenBackground();
		
		ArrayList<DisplayableSprite> barriers = ((Platforms)platforms).getBarriers();
		
		player1 = new Player1(Platforms.TILE_HEIGHT * 2 + 500, Platforms.TILE_WIDTH * 2 + 350);
		boss = new Boss(9500, Platforms.TILE_WIDTH * 2 + 350);
		barrierSprites.add(player1);
		//barrierSprites.add(new Enemy1(1000, Platforms.TILE_WIDTH * 2 + 350, 750, 1250));
		//barrierSprites.add(new Enemy1(2000, Platforms.TILE_WIDTH * 2 + 350, 1750, 2250));
		//barrierSprites.add(new Enemy1(3000, Platforms.TILE_WIDTH * 2 + 350, 2750, 3250));
		nonBarrierSprites.add(new Sign(875, Platforms.TILE_WIDTH * 2 + 490, 1));
		nonBarrierSprites.add(new Message(905, Platforms.TILE_WIDTH * 2 + 430, 1, 200, 25));
		
		nonBarrierSprites.add(new Sign(1350, Platforms.TILE_WIDTH * 2 + 490, 2));
		nonBarrierSprites.add(new Message(1370, Platforms.TILE_WIDTH * 2 + 430, 2, 150, 25));
		
		nonBarrierSprites.add(new Sign(2600, Platforms.TILE_WIDTH * 2 + 490, 3));
		nonBarrierSprites.add(new Message(2620, Platforms.TILE_WIDTH * 2 + 430, 3, 175, 25));

		nonBarrierSprites.add(new Sign(3500, Platforms.TILE_WIDTH * 2 + 490, 4));
		nonBarrierSprites.add(new Message(3520, Platforms.TILE_WIDTH * 2 + 430, 4, 175, 25));

		nonBarrierSprites.add(new Sign(4450, Platforms.TILE_WIDTH * 2 + 490, 5));
		nonBarrierSprites.add(new Message(4470, Platforms.TILE_WIDTH * 2 + 430, 5, 175, 25));
		
		nonBarrierSprites.add(new Sign(8500, Platforms.TILE_WIDTH * 2 + 490, 6));
		nonBarrierSprites.add(new Message(8520, Platforms.TILE_WIDTH * 2 + 430, 6, 175, 25));
		
		nonBarrierSprites.add(new House(550, Platforms.TILE_WIDTH * 2 + 350));
		nonBarrierSprites.add(new Hearts(75, 75, 0));
		nonBarrierSprites.add(new Hearts(75, 75, 1));
		nonBarrierSprites.add(new Hearts(75, 75, 2));
		barrierSprites.add(new Enemy1(3000, Platforms.TILE_WIDTH * 2 + 350, 2750, 3250));

		barrierSprites.add(new Enemy1(4750, Platforms.TILE_WIDTH * 2 + 350, 4700, 4930));
		barrierSprites.add(new Enemy1(4750, Platforms.TILE_WIDTH * 2 + 350, 4650, 4930));
		barrierSprites.add(new Enemy1(5000, Platforms.TILE_WIDTH * 2 + 350, 4935, 5400));
		barrierSprites.add(new Enemy1(5600, Platforms.TILE_WIDTH * 2 + 350, 5450, 5800));
		barrierSprites.add(new Enemy1(6000, Platforms.TILE_WIDTH * 2 + 350, 5850, 6200));
		barrierSprites.add(new Enemy1(6400, Platforms.TILE_WIDTH * 2 + 350, 6250, 6600));
		
		barrierSprites.add(boss);
		
		nonBarrierSprites.add(new Cherry(5575, Platforms.TILE_WIDTH * 2 + 150));
		nonBarrierSprites.add(new Cherry(5525, Platforms.TILE_WIDTH * 2 + 150));
		nonBarrierSprites.add(new Cherry(5625, Platforms.TILE_WIDTH * 2 + 150));
		
		nonBarrierSprites.add(new Cherry(3900, Platforms.TILE_WIDTH * 2 + 350));
		nonBarrierSprites.add(new Cherry(3975, Platforms.TILE_WIDTH * 2 + 350));
		nonBarrierSprites.add(new Cherry(4050, Platforms.TILE_WIDTH * 2 + 350));
		barrierSprites.addAll(barriers);
		
		timer.schedule(new TimerTask() {
			
			public void run() {
			//create random Y coords
				if(boss.getDispose() == false) {
					nonBarrierSprites.add(new Fireball(boss.getCenterX() - 50, boss.getCenterY()));
				}
			}
	        //Amount of Time to start generating Fire (ms), Amount of Time between spawns (ms) 
			}, 10000, 1000);

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
		if(player1.getRespawn()) {
			for (int i = 0; i < barrierDisposalList.size(); i++) {
				DisplayableSprite sprite = barrierDisposalList.get(i);
				sprite.setDispose(false);
	    	}
			for (int i = 0; i < nonBarrierDisposalList.size(); i++) {
				DisplayableSprite sprite = nonBarrierDisposalList.get(i);
				sprite.setDispose(false);
	    	}
			barrierSprites.addAll(barrierDisposalList);
			nonBarrierSprites.addAll(nonBarrierDisposalList);
			if (nonBarrierDisposalList.size() > 0) {
				nonBarrierDisposalList.clear();
		    }
			if (barrierDisposalList.size() > 0) {
				barrierDisposalList.clear();
			}
			player1.setRespawn(false);
		}
		disposeSprites();
		
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
    			barrierDisposalList.add(sprite);
    			temporaryDisposalList.add(sprite);
    		}
    	}
		for (int i = 0; i < nonBarrierSprites.size(); i++) {
			DisplayableSprite sprite = nonBarrierSprites.get(i);
    		if (sprite.getDispose() == true) {
    			nonBarrierDisposalList.add(sprite);
    			temporaryDisposalList.add(sprite);
    		}
    	}
		//go through the list of sprites to dispose
		//note that the sprites are being removed from the original list
		for (int i = 0; i < temporaryDisposalList.size(); i++) {
			DisplayableSprite sprite = temporaryDisposalList.get(i);
			barrierSprites.remove(sprite);
			System.out.println("Remove: " + sprite.toString());
    	}
		for (int i = 0; i < temporaryDisposalList.size(); i++) {
			DisplayableSprite sprite = temporaryDisposalList.get(i);
			nonBarrierSprites.remove(sprite);
			System.out.println("Remove: " + sprite.toString());
    	}
		
		//clear disposal list if necessary
    	if (temporaryDisposalList.size() > 0) {
    		temporaryDisposalList.clear();
    	}
    }

	public ArrayList<DisplayableSprite> getBarrierSprites() {
		return barrierSprites;
	}
	public ArrayList<DisplayableSprite> getNonBarrierSprites() {
		return nonBarrierSprites;
	}

}






