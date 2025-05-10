package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import finalproj.GamePanel;

public class SuperObject
{
	public BufferedImage image;
	public String name;
	public int num_assigned;
	public boolean deleted = false;
	public boolean collision = false;
	public int x_pos, y_pos;
	public Rectangle solid_area = new Rectangle(0,0, 48, 48);
	public int x_solid_area_default = 0;
	public int y_solid_area_default = 0;

	public void draw(Graphics2D g2, GamePanel game_panel)
	{
		g2.drawImage(image, x_pos, y_pos, game_panel.tile_size, game_panel.tile_size, null);
	}
}
