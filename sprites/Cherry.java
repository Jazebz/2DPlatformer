import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cherry implements DisplayableSprite {

	Image cherryOne = null;
	Image cherryTwo = null;
	Image cherryThree = null;
	Image cherryFour = null;
	Image cherryFive = null;
	Image cherrySix = null;

	private double currentFrame = 0;
	private double frameRate = 0.1;
	private boolean visible = true;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 25;
	private double height = 25;
	private boolean dispose = false;
		
	public Cherry() {
		this(0.0, 0.0);
	}
	public Cherry(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		if (cherryOne == null) {
			try {
				cherryOne = ImageIO.read(new File("res/sprites/Cherry/cherry-1.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (cherryTwo == null) {
			try {
				cherryTwo = ImageIO.read(new File("res/sprites/Cherry/cherry-2.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (cherryThree == null) {
			try {
				cherryThree = ImageIO.read(new File("res/sprites/Cherry/cherry-3.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (cherryFour == null) {
			try {
				cherryFour = ImageIO.read(new File("res/sprites/Cherry/cherry-4.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (cherryFive == null) {
			try {
				cherryFive = ImageIO.read(new File("res/sprites/Cherry/cherry-5.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		if (cherrySix == null) {
			try {
				cherrySix = ImageIO.read(new File("res/sprites/Cherry/cherry-6.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
	}
	

	public Image getImage() {
		currentFrame += frameRate;
		if((int)currentFrame == 0) {
			return cherryOne;
		}
		else if((int)currentFrame == 1){
			return cherryTwo;
		}
		else if((int)currentFrame == 2){
			return cherryThree;
		}
		else if((int)currentFrame == 3){
			return cherryFour;
		}
		else if((int)currentFrame == 4){
			return cherryFive;
		}
		else if((int)currentFrame == 5){
			return cherrySix;
		}
		else {
			currentFrame = 0;
			return cherryOne;
		}
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return this.visible;
	}
	
	public double getMinX() {
		return centerX - (width / 2);
	}

	public double getMaxX() {
		return centerX + (width / 2);
	}

	public double getMinY() {
		return centerY - (height / 2);
	}

	public double getMaxY() {
		return centerY + (height / 2);
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public double getCenterX() {
		return centerX;
	};

	public double getCenterY() {
		return centerY;
	};
	
	
	public boolean getDispose() {
		return this.dispose;
	}
	
	public void setDispose(boolean dispose) {
		this.dispose = dispose;
	}

	public void update(Universe universe, KeyboardInput keyboard, long actual_delta_time) {
			
	}


	@Override
	public boolean getRespawn() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setRespawn(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
