import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player1 implements DisplayableSprite, MovableSprite, CollidingSprite {
	private double ACCCELERATION_Y = 1000; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_X = 300;	//PIXELS PER SECOND
	private double maxVelocity = 400;

	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 500; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;
	
	private static final int VELOCITY = 200;
	Image walkingUpOne = null;
	Image walkingUpTwo = null;
	Image walkingUpThree = null;
	Image walkingUpFour = null;
	Image walkingDownOne = null;
	Image walkingDownTwo = null;
	Image walkingDownThree = null;
	Image walkingDownFour = null;
	Image walkingRightOne = null;
	Image walkingRightTwo = null;
	Image walkingRightThree = null;
	Image walkingRightFour = null;
	Image walkingLeftOne = null;
	Image walkingLeftTwo = null;
	Image walkingLeftThree = null;
	Image walkingLeftFour = null;
	private double centerX = 0.0;
	private double centerY = 0.0;
	private double height = 65.0;
	private double width = 65.0;
	private boolean dispose = false;
	private double velocityX = 0;
	private double velocityY = 0;
	private boolean message = false;
	private boolean isAtExit = false;
	private long score = 0;
	private double currentFrame;
	private double frameRate = 0.05;
	private double speed = 1;
	private boolean dash = false;
	private String currentAnimation;
	private double dashCoolDown = 0;
	
	
	public Player1() {
		this(0.0, 0.0);
	}
	public Player1(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		//Assigns images to variables
		collisionDetection = new CollisionDetection();
		collisionDetection.setBounceFactorX(0.5);
		collisionDetection.setBounceFactorY(0);
		bounce = new TwoDimensionBounce();
		
		if (walkingUpOne == null) {
			try {
				walkingUpOne = ImageIO.read(new File("res/jaz/Marioman/Walkingup1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingUpTwo == null) {
			try {
				walkingUpTwo = ImageIO.read(new File("res/jaz/Marioman/Walkingup2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingUpThree == null) {
			try {
				walkingUpThree = ImageIO.read(new File("res/jaz/Marioman/Walkingup3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingUpFour == null) {
			try {
				walkingUpFour = ImageIO.read(new File("res/jaz/Marioman/Walkingup4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingDownOne == null) {
			try {
				walkingDownOne = ImageIO.read(new File("res/jaz/Marioman/Walkingdown1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingDownTwo == null) {
			try {
				walkingDownTwo = ImageIO.read(new File("res/jaz/Marioman/Walkingdown2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingDownThree == null) {
			try {
				walkingDownThree = ImageIO.read(new File("res/jaz/Marioman/Walkingdown3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingDownFour == null) {
			try {
				walkingDownFour = ImageIO.read(new File("res/jaz/Marioman/Walkingdown4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightOne == null) {
			try {
				walkingRightOne = ImageIO.read(new File("res/jaz/Marioman/Walkingright1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightTwo == null) {
			try {
				walkingRightTwo = ImageIO.read(new File("res/jaz/Marioman/Walkingright2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightThree == null) {
			try {
				walkingRightThree = ImageIO.read(new File("res/jaz/Marioman/Walkingright3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightFour == null) {
			try {
				walkingRightFour = ImageIO.read(new File("res/jaz/Marioman/Walkingright4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftOne == null) {
			try {
				walkingLeftOne = ImageIO.read(new File("res/jaz/Marioman/Walkingleft1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftTwo == null) {
			try {
				walkingLeftTwo = ImageIO.read(new File("res/jaz/Marioman/Walkingleft2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftThree == null) {
			try {
				walkingLeftThree = ImageIO.read(new File("res/jaz/Marioman/Walkingleft3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftFour == null) {
			try {
				walkingLeftFour = ImageIO.read(new File("res/jaz/Marioman/Walkingleft4.png"));
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
		currentFrame += frameRate * speed;
		if((velocityX != 0) || (velocityY != 0)) {
			//Up
			if(velocityY < 0) {
				if(currentAnimation != "up") {
					currentFrame = 0;
				}
				currentAnimation = "up";
				if((int)currentFrame == 0) {
					return walkingUpOne;
				}
				else if ((int)currentFrame == 1) {
					return walkingUpTwo;
				}
				else if((int)currentFrame == 2) {
					return walkingUpThree;
				}
				else if((int)currentFrame == 3){
					return walkingUpFour;
				}
				else {
					currentFrame = 0;
					return walkingUpOne;
				}
			}
			//Down
			else if(velocityY > 0) {
				if(currentAnimation != "down") {
					currentFrame = 0;
				}
				currentAnimation = "down";
				if((int)currentFrame == 0) {
					return walkingDownOne;
				}
				else if ((int)currentFrame == 1) {
					return walkingDownTwo;
				}
				else if((int)currentFrame == 2) {
					return walkingDownThree;
				}
				else if((int)currentFrame == 3){
					return walkingDownFour;
				}
				else {
					currentFrame = 0;
					return walkingDownOne;
				}
			}
			//Right
			else if(velocityX > 0) {
				if(currentAnimation != "right") {
					currentFrame = 0;
				}
				currentAnimation = "right";
				if((int)currentFrame == 0) {
					return walkingRightOne;
				}
				else if ((int)currentFrame == 1) {
					return walkingRightTwo;
				}
				else if((int)currentFrame == 2) {
					return walkingRightThree;
				}
				else if((int)currentFrame == 3){
					return walkingRightFour;
				}
				else {
					currentFrame = 0;
					return walkingRightOne;
				}
			}
			//Left
			else {
				if(currentAnimation != "left") {
					currentFrame = 0;
				}
				currentAnimation = "left";
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
				else {
					currentFrame = 0;
					return walkingLeftOne;
				}
			}
		}
		else {
			currentFrame = 0;
			if(currentAnimation == "right") {
				return walkingRightOne;
			}
			else if(currentAnimation == "left") {
				return walkingLeftOne;
			}
			else if(currentAnimation == "down") {
				return walkingDownOne;
			}
			else {
				return walkingUpOne;
			}
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

	@Override
	public boolean getDispose() {
		return dispose;
	}

	@Override
	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
		boolean onGround = isOnGround(universe);
			if(keyboard.keyDown(16)) {
				speed = 1.5;
			}
			else {
				speed = 1;
			}
			// RIGHT
			if (keyboard.keyDown(39)) {
				velocityX = MAX_VELOCITY_X * speed;
						
			}
			// LEFT
			else if (keyboard.keyDown(37)) {
					velocityX = - MAX_VELOCITY_X * speed;
			}
			else {
				this.velocityX = 0;
			}
		if (onGround) {

			if (keyboard.keyDown(32) || keyboard.keyDown(38)) {
				isJumping = true;
				this.velocityY -= INITIAL_JUMP_VELOCITY;
				onGround = false;
			}
			
		}
		else {
			
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
}














