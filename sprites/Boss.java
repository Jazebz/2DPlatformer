import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss implements DisplayableSprite, MovableSprite, CollidingSprite {
	private double ACCCELERATION_Y = 1000; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_Y = 200;	//PIXELS PER SECOND
	private double maxVelocity = 400;

	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 500; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;
	
	private static final int VELOCITY = 200;
	Image eagleOne = null;
	Image eagleTwo = null;
	Image eagleThree = null;
	Image eagleFour = null;
	private double centerX = 0.0;
	private double centerY = 0.0;
	private double height = 100.0;
	private double width = 100.0;
	private boolean dispose = false;
	private double velocityX = MAX_VELOCITY_Y;
	private double velocityY = 0;
	private boolean message = false;
	private boolean isAtExit = false;
	private long score = 0;
	private double currentFrame;
	private double frameRate = 0.075;
	private double speed = 1;
	private int currentAnimationX = 1;
	private boolean respawn = true;
	private double rightMostX = 0;
	private double leftMostX = 0;
	
	
	public Boss() {
		this(0.0, 0.0);
	}
	public Boss(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		//Assigns images to variables
		collisionDetection = new CollisionDetection();
		collisionDetection.setBounceFactorX(0);
		collisionDetection.setBounceFactorY(0);
		bounce = new TwoDimensionBounce();
		
		if (eagleOne == null) {
			try {
				eagleOne = ImageIO.read(new File("res/sprites/Boss/eagle-attack-1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (eagleTwo == null) {
			try {
				eagleTwo = ImageIO.read(new File("res/sprites/Boss/eagle-attack-2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (eagleThree == null) {
			try {
				eagleThree = ImageIO.read(new File("res/sprites/Boss/eagle-attack-3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (eagleFour == null) {
			try {
				eagleFour = ImageIO.read(new File("res/sprites/Boss/eagle-attack-4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
	}

	@Override
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}

	@Override
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	@Override
	public void moveX(double pixelsPerSecond) {
		this.velocityX = pixelsPerSecond;
	} 

	@Override
	public void moveY(double pixelsPerSecond) {
		this.velocityY = pixelsPerSecond;
	}

	@Override
	public void stop() {
		this.velocityX = 0;
		this.velocityY = 0;
	}

	@Override
	public Image getImage() {
		//Sprite Animation (Changes Image)
		currentFrame += frameRate;
			if((int)currentFrame == 0) {
				return eagleOne;
			}
			else if((int)currentFrame == 1){
				return eagleTwo;
			}
			else if((int)currentFrame == 2){
				return eagleThree;
			}
			else if((int)currentFrame == 3){
				return eagleFour;
			}
			else {
				currentFrame = 0;
				return eagleOne;
			}
	}
		
		


	@Override
	public boolean getVisible() {
		return true;
	}

	@Override
	public double getMinX() {
		return centerX - (width / 2);
	}

	@Override
	public double getMaxX() {
		return centerX + (width / 2);
	}

	@Override
	public double getMinY() {
		return centerY - (width / 2);
	}

	@Override
	public double getMaxY() {
		return centerY + (width / 2);
	}

	@Override
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public double getCenterX() {
		return centerX;
	}

	@Override
	public double getCenterY() {
		return centerY;
	}
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}

	@Override
	public boolean getDispose() {
		return dispose;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		// UP
		if (centerY > 560) {
			currentAnimationX = 1;
		}
		// DOWN
		if (centerY < 375) {
			currentAnimationX = -1;
		}
		if(currentAnimationX == 1) {
			velocityY = -150;
		}
		else {
			velocityY = 150;
		}
		
		double deltaY = actual_delta_time * 0.001 * velocityY;
		if (checkCollisionWithBarrier(universe, 0, deltaY) == false) {
			centerY += deltaY;
		}
		collisionDetection.calculate2DBounce(bounce, this, universe.getBarrierSprites(), velocityX, velocityY, actual_delta_time);
		this.centerX = bounce.newX + (width / 2);
		this.velocityX = bounce.newVelocityX;
	}
	
	@Override
	public long getScore() {
		return score;
	}
	@Override
	public String getProximityMessage() {
		if (message) {
			return "Hello, how are you.";
		}
		else {
			return "";
		}
	}
	@Override
	public boolean getIsAtExit() {
		return isAtExit;
	}
	//
	
	private boolean isOnGround(Universe universe) {
		boolean onGround = false;
		for (DisplayableSprite sprite: universe.getBarrierSprites()) {
			boolean bottomColiding = this.getMaxY() >= (sprite.getMinY()) && this.getMaxY() <= sprite.getMinY();
			boolean withinRange = this.getMaxX() > sprite.getMinX() && this.getMinX() < sprite.getMaxX();
			if (bottomColiding && withinRange) {
				onGround = true;
				break;
			}
		}
		return onGround;
	}
	@Override
	public boolean getRespawn() {
		return respawn;
	}
	@Override
	public void setRespawn(boolean respawn) {
		this.respawn = respawn;
		
	}
	private boolean checkCollisionWithBarrier(Universe sprites, double deltaX, double deltaY) {
		boolean colliding = false;
		
		for (DisplayableSprite sprite : sprites.getBarrierSprites()) {
			if(sprite instanceof Player1) {
				if(CollisionDetection.overlaps(this.getMinX() + deltaX, this.getMinY() + deltaY,
						this.getMaxX() + deltaX, this.getMaxY() + deltaY,
						sprite.getMinX(), sprite.getMinY(),
						sprite.getMaxX(), sprite.getMaxY())){
					colliding = true;
					break;
				}
			}
		}
		
		return colliding;
	}
}














