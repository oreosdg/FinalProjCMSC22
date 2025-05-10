package entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import finalproj.GamePanel;
import finalproj.KeyHandler;

public class Player extends Entity
{
	GamePanel game_panel;
	KeyHandler key_handler;
	int has_order=0;
	int score=0;
	ArrayList<Integer> inv = new ArrayList<Integer>();

	public Player(GamePanel game_panel, KeyHandler key_handler)
	{
		this.game_panel = game_panel;
		this.key_handler = key_handler;
		solid_area = new Rectangle(8,16,32,30);
		x_solid_area_default = solid_area.x;
		y_solid_area_default = solid_area.y;

		setDefaultVal();
		getSprite();
	}

	public void setDefaultVal() //this method sets the default values that we put the player in
	{
		x_pos = 48;
		y_pos = 48;
		speed = 4;
		direction = "down";
	}

	public void getSprite() //this method gets the sprite from the resource folder in package player
	{
		try
		{
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_right_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_left_2.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void update() // the first important core of the game, this method updates the player's position according to
	{//the keys presented
		if(key_handler.up_pressed)//if up key is pressed
		{
			direction = "up";
			if(!collision_on)
			{
				y_pos -= speed;
			}
		}
		else if(key_handler.down_pressed)//if the down key is pressed
		{
			direction = "down";
			if(!collision_on)
			{
				y_pos += speed;
			}
		}
		else if(key_handler.right_pressed)//if the left key is pressed
		{
			direction = "right";
			if(!collision_on)
			{
				x_pos += speed;
			}
		}
		else if(key_handler.left_pressed)//if the right key is pressed
		{
			direction = "left";
			if(!collision_on)
			{
				x_pos -= speed;
			}
		}

		//tile collision
		collision_on = false;
		game_panel.collision_checker.checkTile(this); //this part checks collision whenever this is ran


		//object collision
		int obj_index = game_panel.collision_checker.checkObject(this, true);
		pickUpObject(obj_index);

		sprite_counter++; //sprite counter is added +1. though, to be precise, because this update method is called 60 times per sec,
		if(sprite_counter>15) //we can continuously update the sprite by 10 frames per second (we can change how much this change
		{//by changing the value above)
			if(sprite_num==1) //if the sprite number is 1 before this is called, we call the second sprite
			{
				sprite_num=2;
			}
			else if(sprite_num==2) //and vice versa
			{
				sprite_num=1;
			}
			sprite_counter=0; //we then reset the sprite counter
		}
	}

	public void pickUpObject(int i)
	{
		if(i != 999)
		{
			String obj_name = game_panel.obj[i].name;

			switch (obj_name)
			{
				case "notif":
					has_order++;
					inv.add(game_panel.obj[i].num_assigned);
					game_panel.obj[i]=null;
					//game_panel.asset_setter.replace();
					System.out.println("Order in Inventory!");
					break;
				case "door":
					if(has_order>0 && inv.contains(game_panel.obj[i].num_assigned))
					{
						has_order--;
						inv.remove(Integer.valueOf(game_panel.obj[i].num_assigned));
						score++;
						System.out.println("Order delivered!");
						System.out.println("Score: " + score);
					}
					break;
			}
		}
	}

	public void draw(Graphics g2)//the second important core of the game, this method then redraws the scene according
	{//to the updated positions and sprite of the player
		/*g2.setColor(Color.WHITE);
		g2.fillRect(x_pos, y_pos, game_panel.tile_size, game_panel.tile_size);*/

		BufferedImage sprite = null; //by using the buffered image pack,

		switch (direction) //we can use to direct how our sprite will be drawn/redrawn
		{
			case "up"://if the direction is up, then the sprite will use either up1 or up2
				if(sprite_num==1)
				{
					sprite = up1;
				}
				if(sprite_num==2)
				{
					sprite = up2;
				}
				break;
			case "down": //if the direction is down, then the sprite will use either down1 or down2
				if(sprite_num==1)
				{
					sprite = down1;;
				}
				if(sprite_num==2)
				{
					sprite = down2;
				}
				break;
			case "right"://if the direction is right, then the sprite will use either right1 or right2
				if(sprite_num==1)
				{
					sprite = right1;;
				}
				if(sprite_num==2)
				{
					sprite = right2;
				}
				break;
			case "left"://if the direction is left, then the sprite will use either left1 or left2
				if(sprite_num==1)
				{
					sprite = left1;;
				}
				if(sprite_num==2)
				{
					sprite = left2;
				}
				break;
		}//after updating the variables, we call on object to redraw scene
		g2.drawImage(sprite, x_pos, y_pos, game_panel.tile_size, game_panel.tile_size, null);
	}
}
