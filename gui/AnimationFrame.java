import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class AnimationFrame extends JFrame {

	final public static int FRAMES_PER_SECOND = 60;
	final public static int SCREEN_HEIGHT = 750;
	final public static int SCREEN_WIDTH = 1000;

	private int xpCenter = SCREEN_WIDTH / 2;
	private int ypCenter = SCREEN_HEIGHT / 2;

	private double scale = 1;
	//point in universe on which the screen will center
	private static double xCenter = 0;		
	private static double yCenter = 0;

	private JPanel panel = null;
	private JButton btnPauseRun;
	private JLabel lblTimeLabel;
	private JLabel lblTime;
	private JLabel lblStatus;
	private JLabel lblXCenterNum;

	private static boolean stop = false;

	private long current_time = 0;								//MILLISECONDS
	private long next_refresh_time = 0;							//MILLISECONDS
	private long last_refresh_time = 0;
	private long minimum_delta_time = 1000 / FRAMES_PER_SECOND;	//MILLISECONDS
	private long actual_delta_time = 0;							//MILLISECONDS
	private long elapsed_time = 0;
	private boolean isPaused = false;

	private KeyboardInput keyboard = new KeyboardInput();
	private Universe universe = null;

	//local (and direct references to various objects in universe ... should reduce lag by avoiding dynamic lookup
	private Animation animation = null;
	private DisplayableSprite player1 = null;
	private ArrayList<DisplayableSprite> barrierSprites = null;
	private ArrayList<DisplayableSprite> nonBarrierSprites = null;
	private Background platforms = null;
	private Background skyBackground = null;
	private Background greenBackground = null;
	boolean centreOnPlayer = false;
	int universeLevel = 0;
	
	public AnimationFrame(Animation animation)
	{
		super("");
		
		this.animation = animation;
		this.setVisible(true);		
		this.setFocusable(true);
		this.setSize(SCREEN_WIDTH + 20, SCREEN_HEIGHT + 36);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				this_windowClosing(e);
			}
		});

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				keyboard.keyPressed(arg0);
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				keyboard.keyReleased(arg0);
			}
		});

		Container cp = getContentPane();
		cp.setBackground(Color.BLACK);
		cp.setLayout(null);

		panel = new DrawPanel();
		panel.setLayout(null);
		panel.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		getContentPane().add(panel, BorderLayout.CENTER);

		btnPauseRun = new JButton("||");
		btnPauseRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnPauseRun_mouseClicked(arg0);
			}
		});

		btnPauseRun.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnPauseRun.setBounds(20, 20, 48, 32);
		btnPauseRun.setFocusable(false);
		getContentPane().add(btnPauseRun);
		getContentPane().setComponentZOrder(btnPauseRun, 0);

		lblTimeLabel = new JLabel("Time: ");
		lblTimeLabel.setForeground(Color.YELLOW);
		lblTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTimeLabel.setBounds(80, 22, 96, 30);
		getContentPane().add(lblTimeLabel);
		getContentPane().setComponentZOrder(lblTimeLabel, 0);

		lblTime = new JLabel("000");
		lblTime.setForeground(Color.YELLOW);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTime.setBounds(192, 22, 320, 30);
		getContentPane().add(lblTime);
		getContentPane().setComponentZOrder(lblTime, 0);

		lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblStatus.setBounds(0, SCREEN_HEIGHT - 30 - 16, SCREEN_WIDTH, 36);
		getContentPane().add(lblStatus);
		getContentPane().setComponentZOrder(lblStatus, 0);
		

	}

	public void start()
	{
		Thread thread = new Thread()
		{
			public void run()
			{
				animationLoop();
				System.out.println("run() complete");
			}
		};

		thread.start();
		System.out.println("main() complete");

	}	
	private void animationLoop() {

		universe = animation.getNextUniverse();
		universeLevel++;

		while (stop == false && universe != null) {

			barrierSprites = universe.getBarrierSprites();
			nonBarrierSprites = universe.getNonBarrierSprites();
			player1 = universe.getPlayer1();
			platforms = universe.getPlatforms();
			skyBackground = universe.getBackground();
			greenBackground = universe.getBackgroundMiddle();
			centreOnPlayer = universe.centerOnPlayer();
			this.scale = universe.getScale();
			this.xCenter = universe.getXCenter();
			this.yCenter = universe.getYCenter();

			// main game loop
			while (stop == false && universe.isComplete() == false) {

				//adapted from http://www.java-gaming.org/index.php?topic=24220.0
				last_refresh_time = System.currentTimeMillis();
				next_refresh_time = current_time + minimum_delta_time;

				//sleep until the next refresh time
				while (current_time < next_refresh_time)
				{
					//allow other threads (i.e. the Swing thread) to do its work
					Thread.yield();

					try {
						Thread.sleep(1);
					}
					catch(Exception e) {    					
					} 

					//track current time
					current_time = System.currentTimeMillis();
				}

				//read input
				keyboard.poll();
				handleKeyboardInput();

				//UPDATE STATE
				updateTime();
				universe.update(keyboard, actual_delta_time);
				updateControls();

				//REFRESH
				this.scale = universe.getScale();
				this.repaint();
			}

			universe = animation.getNextUniverse();

		}

		System.out.println("animation complete");
		AudioPlayer.setStopAll(true);
		dispose();	

	}

	private void updateControls() {
		this.lblTime.setText(Long.toString(elapsed_time/1000));
		if (universe != null) {
			this.lblStatus.setText("Sunnyland");
		}

	}

	private void updateTime() {

		current_time = System.currentTimeMillis();
		actual_delta_time = (isPaused ? 0 : current_time - last_refresh_time);
		last_refresh_time = current_time;
		elapsed_time += actual_delta_time;

	}

	protected void btnPauseRun_mouseClicked(MouseEvent arg0) {
		if (isPaused) {
			isPaused = false;
			this.btnPauseRun.setText("||");
			((ExcitableSprite)this.player1).setIsEnergetic(true);
		}
		else {
			isPaused = true;
			this.btnPauseRun.setText(">");
			((ExcitableSprite)this.player1).setIsEnergetic(false);
		}
	}

	private void handleKeyboardInput() {
		if (keyboard.keyDown(80) && ! isPaused) {
			btnPauseRun_mouseClicked(null);	
		}
		if (keyboard.keyDown(79) && isPaused ) {
			btnPauseRun_mouseClicked(null);
		}
		if (keyboard.keyDown(112)) {
			scale *= 1.01;
			System.out.println(scale);
		}
		if (keyboard.keyDown(113)) {
			scale /= 1.01;
			System.out.println(scale);
		}
		if (keyboard.keyDown(65)) {
			xCenter -= 1;
			System.out.println("left");
		}
	}

	class DrawPanel extends JPanel {

		public void paintComponent(Graphics g)
		{	
			if (universe == null) {
				return;
			}
			if (player1 != null && centreOnPlayer) {
				//Move Right
				if ((xCenter - player1.getCenterX() < - 250)) {
					xCenter = player1.getCenterX() - 250;
				}
				//Move Left
				else if((xCenter - player1.getCenterX() > 250)) {
					xCenter = player1.getCenterX() + 250;
				}
				//Move Up
				if ((yCenter - player1.getCenterY() < - 200)) {
					yCenter = player1.getCenterY() - 200;
				}
				//Move Down
				else if((yCenter - player1.getCenterY() > 200)) {
					yCenter = player1.getCenterY() + 200;
				}
	   
			}
			if (player1 != null && centreOnPlayer) {
				if(((DisplayableSprite) player1).getRespawn() == true) {
					xCenter = 500;
					yCenter = 350;
					player1.setRespawn(false);
				}
			}
			
			paintBackground(g, skyBackground);
			paintBackground(g, greenBackground);
			paintBackground(g, platforms);

			for (DisplayableSprite activeSprite : nonBarrierSprites) {
				DisplayableSprite sprite = activeSprite;

				if(sprite.getClass() == Hearts.class) {
					if (sprite.getVisible()) {
						g.drawImage(sprite.getImage(), (int)sprite.getCenterX(), (int)sprite.getCenterY(), scaleX(sprite.getWidth()), scaleY(sprite.getHeight()), null);
					}
				}
				else {
					if (sprite.getVisible()) {
						if (sprite.getImage() != null) {
							g.drawImage(sprite.getImage(), translateX(sprite.getMinX()) - 20, translateY(sprite.getMinY()) - 40, scaleX(sprite.getWidth()) + 40, scaleY(sprite.getHeight()) + 40, null);
						}
						else {
							g.setColor(Color.BLUE);
							g.fillRect(translateX((sprite.getMinX())), translateY(sprite.getMinY()), scaleX(sprite.getWidth()), scaleY(sprite.getHeight()));					
						}
					}
				}

			}

			for (DisplayableSprite activeSprite : barrierSprites) {
				DisplayableSprite sprite = activeSprite;
				if (sprite.getVisible()) {
					if (sprite.getImage() != null) {
						g.drawImage(sprite.getImage(), translateX(sprite.getMinX()) - 20, translateY(sprite.getMinY()) - 40, scaleX(sprite.getWidth()) + 40, scaleY(sprite.getHeight()) + 40, null);
					}
					else {
						g.setColor(Color.BLUE);
						g.fillRect(translateX((sprite.getMinX())), translateY(sprite.getMinY()), scaleX(sprite.getWidth()), scaleY(sprite.getHeight()));					
					}
				}

			}
		}
		
		private int translateX(double x) {
			return xpCenter + scaleX(x - xCenter);
		}
		
		private int scaleX(double x) {
			return (int) Math.round(scale * x);
		}
		private int translateY(double y) {
			return ypCenter + scaleY(y - yCenter);
		}		
		private int scaleY(double y) {
			return (int) Math.round(scale * y);
		}

		private void paintBackground(Graphics g, Background background) {
			
			if ((g == null) || (background == null)) {
				return;
			}
			if(background == platforms) {
				//what tile covers the top-left corner?
				double xTopLeft =  (xCenter - (xpCenter / scale));
				double yTopLeft =  (yCenter - (ypCenter / scale));
				
				int row = background.getRow((int)yTopLeft);
				int col = background.getCol((int)xTopLeft);
				Tile tile = null;
			boolean rowDrawn = false;
			boolean screenDrawn = false;
			while (screenDrawn == false) {
				while (rowDrawn == false) {
					tile = background.getTile(col, row);
					if (tile.getWidth() <= 0 || tile.getHeight() <= 0) {
						//no increase in width; will cause an infinite loop, so consider this screen to be done
						g.setColor(Color.GRAY);
						g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);					
						rowDrawn = true;
						screenDrawn = true;						
					}
					else {
						Tile nextTile = background.getTile(col+1, row+1);
						int pwidth = translateX(nextTile.getMinX()) - translateX(tile.getMinX());
						int pheight = translateY(nextTile.getMinY()) - translateY(tile.getMinY());
						g.drawImage(tile.getImage(), translateX(tile.getMinX()), translateY(tile.getMinY()), pwidth, pheight, null);
					}					
					//does the RHE of this tile extend past the RHE of the visible area?
					if (translateX(tile.getMinX() + tile.getWidth()) > SCREEN_WIDTH || tile.isOutOfBounds()) {
						rowDrawn = true;
					}
					else {
						col++;
					}
				}
				//does the bottom edge of this tile extend past the bottom edge of the visible area?
				if (translateY(tile.getMinY() + tile.getHeight()) > SCREEN_HEIGHT || tile.isOutOfBounds()) {
					screenDrawn = true;
				}
				else {
					//TODO - should be passing in a double, as this represents a universe coordinate
					col = background.getCol((int)xTopLeft);
					row++;
					rowDrawn = false;
				}
			}
			}
			else if(background == skyBackground) {
				//what tile covers the top-left corner?
				double xTopLeft =  (xCenter * 0.5 - (xpCenter / scale));
				double yTopLeft =  (yCenter - (ypCenter / scale));
				
				int row = background.getRow((int)yTopLeft);
				int col = background.getCol((int)xTopLeft);
				Tile tile = null;
				boolean rowDrawn = false;
				tile = background.getTile(col, row);
				while (rowDrawn == false) {
					tile = background.getTile(col, row);
					if (tile.getWidth() <= 0 || tile.getHeight() <= 0) {
						//no increase in width; will cause an infinite loop, so consider this screen to be done
						g.setColor(Color.GRAY);
						g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);					
						rowDrawn = true;			
					}
					else {
						g.drawImage(tile.getImage(), xpCenter + scaleX(tile.getMinX() - xCenter * 0.5), 0, tile.getWidth(), tile.getHeight(), null);
					}					
					//does the RHE of this tile extend past the RHE of the visible area?
					if (((int)scaleX(tile.getMinX() - xCenter * 0.5) + tile.getWidth()) > SCREEN_WIDTH || tile.isOutOfBounds()) {
						rowDrawn = true;
					}
					else { 
						col++;
					}
				}
			}
			else if(background == greenBackground) {

				//what tile covers the top-left corner?
				double xTopLeft =  (xCenter * 0.65 - (xpCenter / scale));
				double yTopLeft =  (yCenter - (ypCenter / scale));
				
				int row = background.getRow((int)yTopLeft);
				int col = background.getCol((int)xTopLeft);
				Tile tile = null;
				boolean rowDrawn = false;
				tile = background.getTile(col, row);
				while (rowDrawn == false) {
					tile = background.getTile(col, row);
					if (tile.getWidth() <= 0 || tile.getHeight() <= 0) {
						//no increase in width; will cause an infinite loop, so consider this screen to be done
						g.setColor(Color.GRAY);
						g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);					
						rowDrawn = true;			
					}
					else {
						g.drawImage(tile.getImage(), scaleX(tile.getMinX() - xCenter * 0.65) + 250, 275, tile.getWidth(), tile.getHeight(), null);
					}					
					//does the RHE of this tile extend past the RHE of the visible area?
					if (translateX(tile.getMinX() + tile.getWidth()) > SCREEN_WIDTH || tile.isOutOfBounds()) {
						rowDrawn = true;
					}
					else {
						col++;
					}
				}
			}
		}				
	}
	protected void this_windowClosing(WindowEvent e) {
		System.out.println("windowClosing()");
		stop = true;
		dispose();	
	}
	public static double getXCenter() {
		return xCenter;
	}
	public static double getYCenter() {
		return yCenter;
	}
}
