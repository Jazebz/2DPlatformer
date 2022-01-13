import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy1 implements DisplayableSprite, MovableSprite, CollidingSprite {
	private double ACCCELERATION_Y = 1000; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_X = 200;	//PIXELS PER SECOND
	private double maxVelocity = 400;

	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 500; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;
	
	private static final int VELOCITY = 200;
	Image walkingRightOne = null;
	Image walkingRightTwo = null;
	Image walkingRightThree = null;
	Image walkingRightFour = null;
	Image walkingRightFive = null;
	Image walkingRightSix = null;
	Image walkingLeftOne = null;
	Image walkingLeftTwo = null;
	Image walkingLeftThree = null;
	Image walkingLeftFour = null;
	Image walkingLeftFive = null;
	Image walkingLeftSix = null;
	private double centerX = 0.0;
	private double centerY = 0.0;
	private double height = 49.0;
	private double width = 49.0;
	private boolean dispose = false;
	private double velocityX = MAX_VELOCITY_X;
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
	
	
	public Enemy1() {
		this(0.0, 0.0);
	}
	public Enemy1(double centerX, double centerY) {
		this(centerX, centerY, 0, 0);
	}
	public Enemy1(double centerX, double centerY, double leftMostX, double rightMostX) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.rightMostX = rightMostX;
		this.leftMostX = leftMostX;
		//Assigns images to variables
		collisionDetection = new CollisionDetection();
		collisionDetection.setBounceFactorX(0.5);
		collisionDetection.setBounceFactorY(0);
		bounce = new TwoDimensionBounce();
		
		if (walkingRightOne == null) {
			try {
				walkingRightOne = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightTwo == null) {
			try {
				walkingRightTwo = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightThree == null) {
			try {
				walkingRightThree = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightFour == null) {
			try {
				walkingRightFour = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightFive == null) {
			try {
				walkingRightFive = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright5.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightSix == null) {
			try {
				walkingRightSix = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright6.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftOne == null) {
			try {
				walkingLeftOne = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingleft1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftTwo == null) {
			try {
				walkingLeftTwo = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingleft2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftThree == null) {
			try {
				walkingLeftThree = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingleft3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftFour == null) {
			try {
				walkingLeftFour = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingleft4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftFive == null) {
			try {
				walkingLeftFive = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingleft5.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftSix == null) {
			try {
				walkingLeftSix = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingleft6.png"));
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
		if((velocityX != 0) || (velocityY != 0)) {
			//Right
			if(currentAnimationX == 1) {
				if(currentAnimationX != 1) {
					currentFrame = 0;
				}
				if((int)currentFrame == 0) {
					return walkingRightOne;
				}
				else if((int)currentFrame == 1){
					return walkingRightTwo;
				}
				else if((int)currentFrame == 2){
					return walkingRightThree;
				}
				else if((int)currentFrame == 3){
					return walkingRightFour;
				}
				else if((int)currentFrame == 4){
					return walkingRightFive;
				}
				else if((int)currentFrame == 5){
					return walkingRightSix;
				}
				else {
					currentFrame = 0;
					return walkingRightOne;
				}
			}
			//Left
			else if(currentAnimationX == -1){
				if(currentAnimationX != -1) {
					currentFrame = 0;
				}
				if((int)currentFrame == 0) {
					return walkingLeftOne;
				}
				else if ((int)currentFrame == 1) {
					return walkingLeftTwo;
				}
				else if((int)currentFrame == 2) {
					return walkingLeftThree;
				}
				else if((int)currentFrame == 3){
					return walkingLeftFour;
				}
				else if((int)currentFrame == 4){
					return walkingLeftFive;
				}
				else if((int)currentFrame == 5){
					return walkingLeftSix;
				}
				else {
					currentFrame = 0;
					return walkingLeftOne; 
				}
			}
			else {
				return null;
			}
		}
		else {
			return null;
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
		boolean onGround = isOnGround(universe);
			// RIGHT
			if (centerX < leftMostX) {
				currentAnimationX = 1;
			}
			// LEFT
			if (centerX > rightMostX) {
				currentAnimationX = -1;
			}
			if(currentAnimationX == 1) {
				velocityX = MAX_VELOCITY_X * speed;
			}
			else {
				velocityX = -MAX_VELOCITY_X * speed;
			}
			
		collisionDetection.calculate2DBounce(bounce, this, universe.getSprites(), velocityX, velocityY, actual_delta_time);
		this.centerX = bounce.newX + (width / 2);
		this.centerY = bounce.newY + (width / 2);
		this.velocityX = bounce.newVelocityX;
		this.velocityY = bounce.newVelocityY;

		if (onGround == true) {
			this.velocityY = 0;
		} else {
			this.velocityY = this.velocityY + ACCCELERATION_Y * 0.001 * actual_delta_time;
		}
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
		for (DisplayableSprite sprite: universe.getSprites()) {
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
}














