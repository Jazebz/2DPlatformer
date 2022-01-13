import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DeathSprite implements DisplayableSprite, MovableSprite, CollidingSprite {
	private double ACCCELERATION_Y = 1000; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_X = 200;	//PIXELS PER SECOND
	private double maxVelocity = 400;

	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 500; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;
	
	private static final int VELOCITY = 200;
	Image explosionOne = null;
	Image explosionTwo = null;
	Image walkingRightThree = null;
	Image walkingRightFour = null;
	Image walkingRightFive = null;
	Image walkingRightSix = null;
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
	
	
	public DeathSprite() {
		this(0.0, 0.0);
	}
	public DeathSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (explosionOne == null) {
			try {
				explosionOne = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (explosionTwo == null) {
			try {
				explosionTwo = ImageIO.read(new File("res/sprites/Enemyopossum/Walkingright2.png"));
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
		return explosionOne;
		
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
	@Override
	public boolean getRespawn() {
		return respawn;
	}
	@Override
	public void setRespawn(boolean respawn) {
		this.respawn = respawn;
		
	}
}














