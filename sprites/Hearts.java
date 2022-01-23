import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Hearts implements DisplayableSprite, CollidingSprite {

	Image heart = null;

	private double currentFrame = 0;
	private double frameRate = 0.075;
	private boolean visible = true;
	private double centerX = 0;
	private double centerY = 100;
	private double width = 60;
	private double height = 60;
	private boolean dispose = false;
	private int heartNumber = 0;
		
	public Hearts() {
		this(0.0, 0.0, 0);
	}
	public Hearts(double centerX, double centerY, int heartNumber) {
		this.centerX = centerX + heartNumber * 75;
		this.centerY = centerY;
		this.heartNumber = heartNumber;
		
		if (heart == null) {
			try {
				heart = ImageIO.read(new File("res/sprites/Heart/fancyheart.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
	}
	

	public Image getImage() {
		return heart;
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
		if(heartNumber == 0) {
			if(Player1.getHealth() >= 1) {
				setVisible(true);
			}
			else {
				setVisible(false);
			}
		}
		if(heartNumber == 1) {
			if(Player1.getHealth() >= 2) {
				setVisible(true);
			}
			else {
				setVisible(false);
			}
		}
		if(heartNumber == 2) {
			if(Player1.getHealth() == 3) {
				setVisible(true);
			}
			else {
				setVisible(false);
			}
		}
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
	@Override
	public long getScore() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getProximityMessage() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getIsAtExit() {
		// TODO Auto-generated method stub
		return false;
	}

}
