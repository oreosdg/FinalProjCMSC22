package finalproj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileHandler;

public class GamePanel extends JPanel implements Runnable
{
	final int orig_tile_size = 16; //16 x 16 tile
	final int scale = 3; //use this variable for scaling

	public final int tile_size = orig_tile_size * scale; // scaled 48 x 48 tiles
	public final int max_screen_row = 19;//want to display 12 x 16 (row x col)
	public final int max_screen_col = 21;
	final int screen_height = tile_size * max_screen_row; //to get the screen height, multiply the pixels by the dedsired row
	final int screen_width = tile_size * max_screen_col;//to get the screen width, multiply the pixels by the dedsired col

	TileHandler tile_handler = new TileHandler(this); //instantation of tile handler which mainly loads the tiles into our map
	KeyHandler key_handler = new KeyHandler(); //instantiation of key handler which stores which arrow keys are handled
	public CollisionChecker collision_checker = new CollisionChecker(this);
	public AssetSetter asset_setter = new AssetSetter(this);
	Thread gameThread; // thread instantiation so that the application knows it is running
	Player player1 = new Player(this, key_handler);
	//player2 here

	public SuperObject obj[] = new SuperObject[20];

	int player1_xPos = 100; //inital player1 position at x=100
	int player1_yPos = 100; //inital player1 position at y=100
	int player_speed = 4; // player movement speed

	int FPS = 60;

	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screen_width, screen_height)); //game panel will be set to the screen width and height
		this.setBackground(Color.BLACK); //set the screen to black
		this.setDoubleBuffered(true); //enables better rendering
		this.addKeyListener(key_handler); //listens for key input
		this.setFocusable(true);
	}

	public void startGameThread()
	{
		gameThread = new Thread(this); //we can treat threads as time
		gameThread.start();//will call the run method from the implemented runnable
	}

	public void setUpGame()
	{
		asset_setter.setObject();
	}

	@Override
	public void run()
	{
		double draw_interval = 1000000000/FPS; //used for restricting how much the application updates
		//in this case, every 0.01666666 sec, the game updates that fast
		double next_draw_time = System.nanoTime() + draw_interval;
		long startTime = System.currentTimeMillis();
		while(gameThread != null) //while the gamethread exists
		{
			long elapsedTime = System.currentTimeMillis() - startTime;
			long elapsedSeconds = elapsedTime / 1000;
			long secondsDisplay = elapsedSeconds % 60;
			//System.out.println(secondsDisplay);
			//update character position
			update();

			//draw the screen with updated position
			repaint();

			try //after updating and repainting the stage, we need to let the thread sleep
			{
				double remaining_time = next_draw_time - System.nanoTime(); //this calculates the remaining time the thread can sleep
				remaining_time = remaining_time/1000000; //as the sleep function only accepts miliseconds, we need to divide the nano seconds by a million

				if (remaining_time<0) //fail-safe case where if we the remaining time is all spent
				{
					remaining_time = 0;
				}

				Thread.sleep((long)remaining_time);
				next_draw_time += draw_interval;
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void update()
	{
		player1.update();
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		//Tiles
		tile_handler.draw(g2); //one important thing. you need to call the tiles first before the player

		//Object
		for(int i=0; i< obj.length; i++)
		{
			if(obj[i]!=null)
			{
				obj[i].draw(g2, this);
			}
		}

		//Players
		player1.draw(g2);//imagine it as layering a cake

		g2.dispose();
	}
}
