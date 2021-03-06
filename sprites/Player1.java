import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player1 implements DisplayableSprite, MovableSprite, CollidingSprite {
	private double ACCCELERATION_Y = 1000; 	//PIXELS PER SECOND PER SECOND
	private double MAX_VELOCITY_X = 400;	//PIXELS PER SECOND
	private double maxVelocity = 400;

	private boolean isJumping = false;
	private final double INITIAL_JUMP_VELOCITY = 500; //pixels / second
	
	private CollisionDetection collisionDetection;
	TwoDimensionBounce bounce;
	
	private static final int VELOCITY = 200;
	Image idleRightOne = null;
	Image idleRightTwo = null;
	Image idleRightThree = null;
	Image idleRightFour = null;
	Image idleLeftOne = null;
	Image idleLeftTwo = null;
	Image idleLeftThree = null;
	Image idleLeftFour = null;
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
	Image jumpingRightUp = null;
	Image jumpingRightDown = null;
	Image jumpingLeftUp = null;
	Image jumpingLeftDown = null;
	Image hurtOne = null;
	Image hurtTwo = null;
	private double centerX = 0.0;
	private double centerY = 0.0;
	private double height = 49.0;
	private double width = 49.0;
	private boolean dispose = false;
	private double velocityX = 0;
	private double velocityY = 0;
	private boolean message = false;
	private boolean isAtExit = false;
	private long score = 0;
	private double currentFrame;
	private double frameRate = 0.075;
	private double speed = 1;
	private boolean dash = false;
	private int currentAnimationX = 1;
	private boolean idle = true;
	private double dashCoolDown = 0;
	private double respawnX = 500.0;
	private boolean respawn = false;
	private boolean hurt = false;
	private static int health = 3;
	
	
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
		
		if (idleRightOne == null) {
			try {
				idleRightOne = ImageIO.read(new File("res/sprites/Fox/Idleright/Idleright1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleRightTwo == null) {
			try {
				idleRightTwo = ImageIO.read(new File("res/sprites/Fox/Idleright/Idleright2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleRightThree == null) {
			try {
				idleRightThree = ImageIO.read(new File("res/sprites/Fox/Idleright/Idleright3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleRightFour == null) {
			try {
				idleRightFour = ImageIO.read(new File("res/sprites/Fox/Idleright/Idleright4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleLeftOne == null) {
			try {
				idleLeftOne = ImageIO.read(new File("res/sprites/Fox/Idleleft/Idleleft1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleLeftTwo == null) {
			try {
				idleLeftTwo = ImageIO.read(new File("res/sprites/Fox/Idleleft/Idleleft2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleLeftThree == null) {
			try {
				idleLeftThree = ImageIO.read(new File("res/sprites/Fox/Idleleft/Idleleft3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (idleLeftFour == null) {
			try {
				idleLeftFour = ImageIO.read(new File("res/sprites/Fox/Idleleft/Idleleft4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightOne == null) {
			try {
				walkingRightOne = ImageIO.read(new File("res/sprites/Fox/Walkingright/Walkingright1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightTwo == null) {
			try {
				walkingRightTwo = ImageIO.read(new File("res/sprites/Fox/Walkingright/Walkingright2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightThree == null) {
			try {
				walkingRightThree = ImageIO.read(new File("res/sprites/Fox/Walkingright/Walkingright3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightFour == null) {
			try {
				walkingRightFour = ImageIO.read(new File("res/sprites/Fox/Walkingright/Walkingright4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightFive == null) {
			try {
				walkingRightFive = ImageIO.read(new File("res/sprites/Fox/Walkingright/Walkingright5.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingRightSix == null) {
			try {
				walkingRightSix = ImageIO.read(new File("res/sprites/Fox/Walkingright/Walkingright6.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftOne == null) {
			try {
				walkingLeftOne = ImageIO.read(new File("res/sprites/Fox/Walkingleft/Walkingleft1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftTwo == null) {
			try {
				walkingLeftTwo = ImageIO.read(new File("res/sprites/Fox/Walkingleft/Walkingleft2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftThree == null) {
			try {
				walkingLeftThree = ImageIO.read(new File("res/sprites/Fox/Walkingleft/Walkingleft3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftFour == null) {
			try {
				walkingLeftFour = ImageIO.read(new File("res/sprites/Fox/Walkingleft/Walkingleft4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftFive == null) {
			try {
				walkingLeftFive = ImageIO.read(new File("res/sprites/Fox/Walkingleft/Walkingleft5.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (walkingLeftSix == null) {
			try {
				walkingLeftSix = ImageIO.read(new File("res/sprites/Fox/Walkingleft/Walkingleft6.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (jumpingRightUp == null) {
			try {
				jumpingRightUp = ImageIO.read(new File("res/sprites/Fox/Jumpingright/Up.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (jumpingRightDown == null) {
			try {
				jumpingRightDown = ImageIO.read(new File("res/sprites/Fox/Jumpingright/Down.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (jumpingLeftUp == null) {
			try {
				jumpingLeftUp = ImageIO.read(new File("res/sprites/Fox/Jumpingleft/Up.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (jumpingLeftDown == null) {
			try {
				jumpingLeftDown = ImageIO.read(new File("res/sprites/Fox/Jumpingleft/Down.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (hurtOne == null) {
			try {
				hurtOne = ImageIO.read(new File("res/sprites/Fox/Hurt/player-hurt-1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (hurtTwo == null) {
			try {
				hurtTwo = ImageIO.read(new File("res/sprites/Fox/Hurt/player-hurt-2.png"));
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
		if(idle == false || hurt == true) {
			currentFrame += frameRate * speed;
		}
		else {
			currentFrame += frameRate;
		}
		if(hurt == true) {
			if((int)currentFrame == 0) {
				return hurtOne;
			}
			else if((int)currentFrame == 1){
				return hurtTwo;
			}
			else if((int)currentFrame == 2){
				return hurtOne;
			}
			else if((int)currentFrame == 3){
				return hurtTwo;
			}
			else if((int)currentFrame == 4) {
				return hurtOne;
			}
			else if((int)currentFrame == 5){
				return hurtTwo;
			}
			else if((int)currentFrame == 6) {
				return hurtOne;
			}
			else if((int)currentFrame == 7){
				return hurtTwo;
			}
			else if((int)currentFrame == 8) {
				return hurtOne;
			}
			else if((int)currentFrame == 9){
				return hurtTwo;
			}
			else if((int)currentFrame == 10) {
				return hurtOne;
			}
			else if((int)currentFrame == 11){
				return hurtTwo;
			}
			else {
				currentFrame = 0;
				hurt = false;
				if(health == 0) {
					setRespawn(true);
					respawn();
				}
				return hurtTwo;
			}
		}
		if((velocityX != 0) || (velocityY != 0)) {
			
			//Up
			if(velocityY < 0) {
				if(currentAnimationX == 1) {
					return jumpingRightUp;
				}
				else if(currentAnimationX == -1){
					return jumpingLeftUp;
				}
				else {
					return null;
				}
				
			}
			//Down
			else if(velocityY > 0) {
				if(currentAnimationX == 1) {
					return jumpingRightDown;
				}
				else if(currentAnimationX == -1){
					return jumpingLeftDown;
				}
				else {
					return null;
				}
			}
			//Right
			else if(currentAnimationX == 1) {
				if(currentAnimationX != 1 || idle == true) {
					currentFrame = 0;
				}
				idle = false;
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
				if(currentAnimationX != -1 || idle == true) {
					currentFrame = 0;
				}
				idle = false;
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
			if(currentAnimationX == -1) {
				idle = true;
				if((int)currentFrame == 0) {
					return idleLeftOne;
				}
				else if ((int)currentFrame == 1) {
					return idleLeftTwo;
				}
				else if((int)currentFrame == 2) {
					return idleLeftThree;
				}
				else if((int)currentFrame == 3){
					return idleLeftFour;
				}
				else {
					currentFrame = 0;
					return idleLeftOne;
				}
			}
			else if(currentAnimationX == 1){
				idle = true;
				if((int)currentFrame == 0) {
					return idleRightOne;
				}
				else if ((int)currentFrame == 1) {
					return idleRightTwo;
				}
				else if((int)currentFrame == 2) {
					return idleRightThree;
				}
				else if((int)currentFrame == 3){
					return idleRightFour;
				}
				else {
					currentFrame = 0;
					return idleRightOne;
				}
			}
			else {
				return null;
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
				speed = 1.2;
			}
			else {
				speed = 1;
			}
			// RIGHT
			if (keyboard.keyDown(39)) {
				velocityX = MAX_VELOCITY_X * speed;
				currentAnimationX = 1;
			}
			// LEFT
			else if (keyboard.keyDown(37)) {
				velocityX = - MAX_VELOCITY_X * speed;
				currentAnimationX = -1;	
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
		if(centerX > 8500) {
			respawnX = 8550;
		}
		if(centerX > 4300 && respawnX != 8550) {
			respawnX = 4350;
		}
		
		collisionDetection.calculate2DBounce(bounce, this, universe.getBarrierSprites(), velocityX, velocityY, actual_delta_time);
		this.centerX = bounce.newX + (width / 2);
		this.centerY = bounce.newY + (width / 2);
		this.velocityX = bounce.newVelocityX;
		this.velocityY = bounce.newVelocityY;

		if (onGround == true) {
			this.velocityY = 0;
		} else {
			this.velocityY = this.velocityY + ACCCELERATION_Y * 0.001 * actual_delta_time;
		}
		for (DisplayableSprite sprite : universe.getBarrierSprites()) {
			if(sprite instanceof Enemy1) {
				if(CollisionDetection.overlaps(this.getMinX() - 1, this.getMinY() - 1,
						this.getMaxX() + 1, this.getMaxY(),
						sprite.getMinX(), sprite.getMinY(),
						sprite.getMaxX(), sprite.getMaxY())) {
					if(this.getMaxY() >= (sprite.getMinY()) && this.getMaxY() <= sprite.getMinY()){
						MappedUniverse.addNonBarrierSprite(new DeathSprite(((Enemy1) sprite).getCenterX(),((Enemy1) sprite).getCenterY()));
						((Enemy1) sprite).setDispose(true);
						if (keyboard.keyDown(32) || keyboard.keyDown(38)) {
							this.velocityY -= INITIAL_JUMP_VELOCITY;
						}
						else {
							this.velocityY -= 250;
						}
						break;
					}
					else {
						if(health >= 1 && hurt == false) {
							this.health -= 1;
							currentFrame = 0;
							hurt = true;
						}
					}
				}
			}
		}
		for (DisplayableSprite sprite : universe.getNonBarrierSprites()) {
			if(sprite instanceof Fireball) {
				if(CollisionDetection.overlaps(this.getMinX(), this.getMinY(),
						this.getMaxX(), this.getMaxY(),
						sprite.getMinX(), sprite.getMinY(),
						sprite.getMaxX(), sprite.getMaxY())) {
					if(health >= 1 && hurt == false) {
						this.health -= 1;
						currentFrame = 0;
						hurt = true;
					}
				}
			}
		}
		for (DisplayableSprite sprite : universe.getBarrierSprites()) {
			if(sprite instanceof Boss) {
				if(CollisionDetection.overlaps(this.getMinX() - 1, this.getMinY() - 1,
						this.getMaxX() + 1, this.getMaxY(),
						sprite.getMinX(), sprite.getMinY(),
						sprite.getMaxX(), sprite.getMaxY())) {
					if(this.getMaxY() >= (sprite.getMinY()) && this.getMaxY() <= sprite.getMinY()){
						this.score += 100;
						MappedUniverse.addNonBarrierSprite(new DeathSprite(((Boss) sprite).getCenterX(),((Boss) sprite).getCenterY()));
						((Boss) sprite).setDispose(true);
						MappedUniverse.addNonBarrierSprite(new Sign(9700, Platforms.TILE_WIDTH * 2 + 490, 7));
						MappedUniverse.addNonBarrierSprite(new Message(9730, Platforms.TILE_WIDTH * 2 + 430, 7, 225, 25));
						if (keyboard.keyDown(32) || keyboard.keyDown(38)) {
							this.velocityY -= INITIAL_JUMP_VELOCITY;
						}
						else {
							this.velocityY -= 250;
						}
						break;
					}
					else {
						if(health >= 1 && hurt == false) {
							this.health -= 1;
							currentFrame = 0;
							hurt = true;
						}
					}
				}
			}
		}
		for (DisplayableSprite sprite : universe.getNonBarrierSprites()) {
			if(sprite instanceof Cherry) {
				if(CollisionDetection.overlaps(this.getMinX(), this.getMinY(),
						this.getMaxX(), this.getMaxY(),
						sprite.getMinX(), sprite.getMinY(),
						sprite.getMaxX(), sprite.getMaxY())){
					MappedUniverse.addNonBarrierSprite(new DeathItem(((Cherry) sprite).getCenterX(),((Cherry) sprite).getCenterY()));
					((Cherry) sprite).setDispose(true);
					if(health < 3) {
						health += 1;
					}
					break;
				}
			}
		}
		for (DisplayableSprite signSprite : universe.getNonBarrierSprites()) {
			if(signSprite instanceof Sign) {
				double distanceX = this.centerX - signSprite.getCenterX();
				double distanceY = this.centerY - signSprite.getCenterY();
				double radialDistance = Math.sqrt((distanceY * distanceY) + (distanceX * distanceX));
				if (radialDistance <= 175) {
					for (DisplayableSprite messageSprite : universe.getNonBarrierSprites()) {
						if(messageSprite instanceof Message) {
							if (((Sign) signSprite).getSignNumber() == ((Message)messageSprite).getMessageNumber()) {
								((Message) messageSprite).setVisible(true);
							}
						}
					}
				}
				else {
					for (DisplayableSprite messageSprite : universe.getNonBarrierSprites()) {
						if(messageSprite instanceof Message) {
							if (((Sign) signSprite).getSignNumber() == ((Message)messageSprite).getMessageNumber()) {
								((Message) messageSprite).setVisible(false);
							}
						}
					}
				}
			}
		}
		//Respawn
		//if(this.centerY > 2500 || respawn == true) {
		//	setRespawn(true);
		//	respawn();
		//}
	}
	private void respawn() {
		this.centerY = 550;
		this.centerX = respawnX;
		this.velocityY = 0;
		this.health = 3;
		hurt = false;
	}
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
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
	public static int getHealth() {
		return health;
	}
}














