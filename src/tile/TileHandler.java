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

			tile[3] = new Tile();
			tile[3].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/003.png"));
			tile[3].collision = true;

			tile[4] = new Tile();
			tile[4].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/004.png"));
			tile[4].collision = true;

			tile[5] = new Tile();
			tile[5].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/005.png"));
			tile[5].collision = true;

			tile[6] = new Tile();
			tile[6].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/006.png"));
			tile[6].collision = true;

			tile[7] = new Tile();
			tile[7].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/007.png"));
			tile[7].collision = true;

			tile[8] = new Tile();
			tile[8].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/008.png"));
			tile[8].collision = true;

			tile[9] = new Tile();
			tile[9].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/009.png"));
			tile[9].collision = true;

			tile[10] = new Tile();
			tile[10].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/010.png"));
			tile[10].collision = true;

			tile[11] = new Tile();
			tile[11].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/011.png"));
			tile[11].collision = true;

			tile[12] = new Tile();
			tile[12].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/012.png"));
			tile[12].collision = true;

			tile[13] = new Tile();
			tile[13].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/013.png"));
			tile[13].collision = true;

			tile[14] = new Tile();
			tile[14].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/014.png"));
			tile[14].collision = true;

			tile[15] = new Tile();
			tile[15].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/015.png"));
			tile[15].collision = true;

			tile[16] = new Tile();
			tile[16].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/016.png"));
			tile[16].collision = true;

			tile[17] = new Tile();
			tile[17].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/017.png"));
			tile[17].collision = true;

			tile[18] = new Tile();
			tile[18].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/018.png"));
			tile[18].collision = true;

			tile[19] = new Tile();
			tile[19].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/019.png"));
			tile[19].collision = true;

			tile[20] = new Tile();
			tile[20].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/020.png"));
			tile[20].collision = true;

			tile[21] = new Tile();
			tile[21].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/021.png"));
			tile[21].collision = true;

			tile[22] = new Tile();
			tile[22].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/022.png"));
			tile[22].collision = true;

			tile[23] = new Tile();
			tile[23].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/023.png"));
			tile[23].collision = true;

			tile[24] = new Tile();
			tile[24].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/024.png"));
			tile[24].collision = true;

			tile[25] = new Tile();
			tile[25].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/025.png"));
			tile[25].collision = true;

			tile[26] = new Tile();
			tile[26].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/026.png"));
			tile[26].collision = true;

			tile[27] = new Tile();
			tile[27].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/027.png"));
			tile[27].collision = true;

			tile[28] = new Tile();
			tile[28].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/028.png"));
			tile[28].collision = true;

			tile[29] = new Tile();
			tile[29].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/029.png"));
			tile[29].collision = true;

			tile[30] = new Tile();
			tile[30].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/030.png"));
			tile[30].collision = true;

			tile[31] = new Tile();
			tile[31].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/031.png"));
			tile[31].collision = true;

			tile[32] = new Tile();
			tile[32].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/032.png"));
			tile[32].collision = true;

			tile[33] = new Tile();
			tile[33].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/033.png"));
			tile[33].collision = true;

			tile[34] = new Tile();
			tile[34].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/034.png"));
			tile[34].collision = true;

			tile[35] = new Tile();
			tile[35].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/035.png"));
			tile[35].collision = true;

			tile[36] = new Tile();
			tile[36].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/036.png"));
			tile[36].collision = true;

			tile[37] = new Tile();
			tile[37].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/037.png"));
			tile[37].collision = true;

			tile[40] = new Tile();
			tile[40].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/040.png"));
			tile[40].collision = true;

			tile[41] = new Tile();
			tile[41].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/041.png"));
			tile[41].collision = true;

			tile[42] = new Tile();
			tile[42].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/042.png"));
			tile[42].collision = true;

			tile[43] = new Tile();
			tile[43].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/043.png"));
			tile[43].collision = true;

			tile[44] = new Tile();
			tile[44].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/044.png"));
			tile[44].collision = true;

			tile[45] = new Tile();
			tile[45].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/045.png"));
			tile[45].collision = true;

			tile[46] = new Tile();
			tile[46].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/046.png"));
			tile[46].collision = true;

			tile[47] = new Tile();
			tile[47].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/047.png"));
			tile[47].collision = true;

			tile[48] = new Tile();
			tile[48].tile_image = ImageIO.read(getClass().getResourceAsStream("/tile/048.png"));
			tile[48].collision = true;
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
