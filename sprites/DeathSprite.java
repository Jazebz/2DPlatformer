import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DeathSprite implements DisplayableSprite, MovableSprite, CollidingSprite {
	Image explosionOne = null;
	Image explosionTwo = null;
	Image explosionThree = null;
	Image explosionFour = null;
	Image explosionFive = null;
	Image explosionSix = null;
	private double centerX = 0.0;
	private double centerY = 0.0;
	private double height = 70.0;
	private double width = 70.0;
	private boolean dispose = false;
	private double velocityX = 200;
	private double velocityY = 0;
	private boolean message = false;
	private boolean isAtExit = false;
	private long score = 0;
	private double currentFrame;
	private double frameRate = 0.1;
	private boolean respawn = true;
	
	
	public DeathSprite() {
		this(0.0, 0.0);
	}
	public DeathSprite(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (explosionOne == null) {
			try {
				explosionOne = ImageIO.read(new File("res/sprites/Enemydeath/enemy-death-1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (explosionTwo == null) {
			try {
				explosionTwo = ImageIO.read(new File("res/sprites/Enemydeath/enemy-death-2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (explosionThree == null) {
			try {
				explosionThree = ImageIO.read(new File("res/sprites/Enemydeath/enemy-death-3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (explosionFour == null) {
			try {
				explosionFour = ImageIO.read(new File("res/sprites/Enemydeath/enemy-death-4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (explosionFive == null) {
			try {
				explosionFive = ImageIO.read(new File("res/sprites/Enemydeath/enemy-death-5.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (explosionSix == null) {
			try {
				explosionSix = ImageIO.read(new File("res/sprites/Enemydeath/enemy-death-6.png"));
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
			return explosionOne;
		}
		else if((int)currentFrame == 1){
			return explosionTwo;
		}
		else if((int)currentFrame == 2){
			return explosionThree;
		}
		else if((int)currentFrame == 3){
			return explosionFour;
		}
		else if((int)currentFrame == 4){
			return explosionFive;
		}
		else if((int)currentFrame == 5){
			return explosionSix;
		}
		else {
			setDispose(true);
			return explosionOne;
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














