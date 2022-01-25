import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Message implements DisplayableSprite, CollidingSprite {

	Image message = null;

	private boolean visible = false;
	private double centerX = 0;
	private double centerY = 100;
	private double width = 150;
	private double height = 25;
	private boolean dispose = false;
	private int messageNumber = 0;
		
	public Message() {
		this(0.0, 0.0, 0, 150, 25);
	}
	public Message(double centerX, double centerY, int messageNumber, int width, int height) {
		this.centerX = centerX;
		this.centerY = centerY;
		this.messageNumber = messageNumber;
		this.height = height;
		this.width = width;
		
		if (message == null) {
			if(messageNumber == 1) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/welcome.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			if(messageNumber == 2) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/uparrow.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			if(messageNumber == 3) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/enemy.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			if(messageNumber == 4) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/cherryheal.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			if(messageNumber == 5) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/begin.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			if(messageNumber == 6) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/bossfight.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
			if(messageNumber == 7) {
				try {
					message = ImageIO.read(new File("res/sprites/Message/youwin.png"));
				}
				catch (IOException e) {
					System.out.println(e.toString());
				}
			}
		}
		
	}
	

	public Image getImage() {
		return message;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	//DISPLAYABLE
	
	public boolean getVisible() {
		return this.visible;
	}
	public int getMessageNumber() {
		return this.messageNumber;
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
