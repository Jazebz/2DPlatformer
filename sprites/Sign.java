import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sign implements DisplayableSprite, CollidingSprite {

	Image sign = null;

	private boolean visible = true;
	private double centerX = 0;
	private double centerY = 0;
	private double width = 20;
	private double height = 20;
	private boolean dispose = false;
	private int signNumber = 0;
		
	public Sign() {
		this(0.0, 0.0, 1);
	}
	public Sign(double centerX, double centerY, int signNumber) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.signNumber = signNumber;
		
		if (sign == null) {
			try {
				sign = ImageIO.read(new File("res/sprites/Sign/sign.png"));
			}
			catch (IOException e) {
				System.out.println(e.toString());
			}		
		}
		
	}
	

	public Image getImage() {
		return sign;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return this.visible;
	}
	public int getSignNumber() {
		return this.signNumber;
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
