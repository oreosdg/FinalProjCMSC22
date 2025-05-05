package tile;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import finalproj.GamePanel;

public class TileHandler
{
	GamePanel game_panel;
	public Tile[] tile;
	public int mapTileNum[][];

	public TileHandler(GamePanel game_panel)
	{
		this.game_panel = game_panel;//refers to the game panel we are using
		tile = new Tile[192];//size of how many tiles we are doing (you can change the value inside if we are adding more
		mapTileNum = new int[game_panel.max_screen_col][game_panel.max_screen_row];

		getTileImage(); // gets the tile images that we are doing
		loadMap("/maps/map01.txt"); //loads the map into the screen
	}

	public void getTileImage()
	{
		try
		{
			tile[0] = new Tile(); //in this method, we are creating tiles and putting the correspoinding image into the array
			tile[0].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/000.png")); //for example, the first tile is a grass tile

			tile[1] = new Tile();
			tile[1].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/001.png"));
			tile[1].collision = true;

			tile[2] = new Tile();
			tile[2].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/002.png"));
			tile[2].collision = true;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void loadMap(String path)
	{
		try
		{
			InputStream input_stream = getClass().getResourceAsStream(path); //the input stream is to import the text file
			BufferedReader reader = new BufferedReader(new InputStreamReader(input_stream)); //it then reads the values inside of the text file

			int row=0;
			int col=0;

			while(col<game_panel.max_screen_col && row<game_panel.max_screen_row)
			{
				String line = reader.readLine();

				while(col<game_panel.max_screen_col)
				{
					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if(col==game_panel.max_screen_col)
				{
					col=0;
					row++;
				}
			}
			reader.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void draw(Graphics g2)
	{
		/*g2.drawImage(tile[0].tile_image, 0, 0, game_panel.tile_size, game_panel.tile_size, null);
		g2.drawImage(tile[1].tile_image, 48, 48, game_panel.tile_size, game_panel.tile_size, null);
		g2.drawImage(tile[2].tile_image, 48, 0, game_panel.tile_size, game_panel.tile_size, null);*/

		int row=0;
		int col=0;
		int x = 0;
		int y = 0;

		while(col<game_panel.max_screen_col && row<game_panel.max_screen_row)
		{
			int tile_num = mapTileNum[col][row];

			g2.drawImage(tile[tile_num].tile_image, x, y, game_panel.tile_size, game_panel.tile_size, null);
			col++;
			x+=game_panel.tile_size;

			if (col == game_panel.max_screen_col)
			{
				col=0;
				x=0;
				row++;
				y+=game_panel.tile_size;
			}
		}
	}
}
