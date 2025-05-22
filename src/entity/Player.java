package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import finalproj.GamePanel;
import finalproj.KeyHandler;
import finalproj.Timer;

public class Player extends Entity {
    GamePanel game_panel;
    KeyHandler key_handler;
    int player_num;
    int has_order = 0;
    int score = 0;
    ArrayList<Integer> inv = new ArrayList<Integer>();

    Timer timer1;
	Timer timer2;
	Timer timer3;
	Timer timer4;

    public Player(GamePanel game_panel, KeyHandler key_handler, int player_num) {
        this.game_panel = game_panel;
        this.key_handler = key_handler;
        this.player_num = player_num;
        solid_area = new Rectangle(8, 16, 32, 32);
        x_solid_area_default = solid_area.x;
        y_solid_area_default = solid_area.y;

        setDefaultVal();
        getSprite();
    }

    public void setDefaultVal() {
        x_pos = 48;
        y_pos = 48;
        speed = 4; // This will be overridden by GamePanel's speed on update
        direction = "down";
    }

    public void getSprite() {
        try
        {
        	switch (this.player_num)
        	{
        		case 1:
		        	up1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_up_1.png"));
		            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_up_2.png"));
		            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_down_1.png"));
		            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_down_2.png"));
		            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_right_1.png"));
		            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_right_2.png"));
		            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_left_1.png"));
		            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_left_2.png"));
	            	break;
        		case 2:
        			up1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_up_1.png"));
		            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_up_2.png"));
		            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_down_1.png"));
		            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_down_2.png"));
		            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_right_1.png"));
		            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_right_2.png"));
		            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player1_left_1.png"));
		            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player1_left_2.png"));
        			break;
        	}
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update() {
        // Read current speed from GamePanel for dynamic speed changes during disasters
        speed = game_panel.getPlayerSpeed();

        int moveX = 0;
        int moveY = 0;

        // Handle disorientation on Earthquake disaster
        if (game_panel.isPlayerDisoriented()) {
            // Randomly invert controls for disorientation effect
            boolean invertX = Math.random() < 0.5;
            boolean invertY = Math.random() < 0.5;

            switch (this.player_num)
            {
            	case 1:
            		if (key_handler.w_pressed) moveY = invertY ? speed : -speed;
                    if (key_handler.s_pressed) moveY = invertY ? -speed : speed;
                    if (key_handler.a_pressed) moveX = invertX ? speed : -speed;
                    if (key_handler.d_pressed) moveX = invertX ? -speed : speed;
            		break;
            	case 2:
            		if (key_handler.up_pressed) moveY = invertY ? speed : -speed;
                    if (key_handler.down_pressed) moveY = invertY ? -speed : speed;
                    if (key_handler.left_pressed) moveX = invertX ? speed : -speed;
                    if (key_handler.right_pressed) moveX = invertX ? -speed : speed;
            		break;
            }
        }
        else
        {
            // Normal movement controls
        	switch (this.player_num)
            {
            	case 1:
            		if (key_handler.w_pressed) moveY = -speed;
                    else if (key_handler.s_pressed) moveY = speed;
                    else if (key_handler.a_pressed) moveX = -speed;
                    else if (key_handler.d_pressed) moveX = speed;
            		break;
            	case 2:
            		if (key_handler.up_pressed) moveY = -speed;
                    else if (key_handler.down_pressed) moveY = speed;
                    else if (key_handler.left_pressed) moveX = -speed;
                    else if (key_handler.right_pressed) moveX = speed;
            		break;
            }
        }

        // Set direction based on last key pressed for sprite facing
        if (moveY < 0) direction = "up";
        else if (moveY > 0) direction = "down";
        else if (moveX > 0) direction = "right";
        else if (moveX < 0) direction = "left";

        // Before moving, check collisions
        collision_on = false;
        game_panel.collision_checker.checkTile(this);

        if (!collision_on)
        { // Move only if no collision
            x_pos += moveX;
            y_pos += moveY;
        }

        // Check object collision and pickup
        int obj_index = game_panel.collision_checker.checkObject(this, true);
        pickUpObject(obj_index);

        // Sprite animation updating
        sprite_counter++;
        if (sprite_counter > 15) {
            if (sprite_num == 1) sprite_num = 2;
            else if (sprite_num == 2) sprite_num = 1;
            sprite_counter = 0;
        }

        if(timer1!=null)
		{
			if(timer1.getSecondsElapsed()<15 && !timer1.isStopped())
			{
				timer1.setSecondsElapsed(game_panel.seconds_display);
				//System.out.println(timer1.getSecondsElapsed());
			}
			else if(timer1.getSecondsElapsed()==15)
			{
				game_panel.asset_setter.replace1();
				timer1.reset();
			}
		}
		if(timer2!=null)
		{
			if(timer2.getSecondsElapsed()<15 && !timer2.isStopped())
			{
				timer2.setSecondsElapsed(game_panel.seconds_display);
				//System.out.println(timer2.getSecondsElapsed());
			}
			else if(timer2.getSecondsElapsed()==15)
			{
				game_panel.asset_setter.replace2();
				timer2.reset();
			}
		}
		if(timer3!=null)
		{
			if(timer3.getSecondsElapsed()<15 && !timer3.isStopped())
			{
				timer3.setSecondsElapsed(game_panel.seconds_display);
				//System.out.println(timer3.getSecondsElapsed());
			}
			else if(timer3.getSecondsElapsed()==15)
			{
				game_panel.asset_setter.replace3();
				timer3.reset();
			}
		}
		if(timer4!=null)
		{
			if(timer4.getSecondsElapsed()<15 && !timer4.isStopped())
			{
				timer4.setSecondsElapsed(game_panel.seconds_display);
				//System.out.println(timer4.getSecondsElapsed());
			}
			else if(timer4.getSecondsElapsed()==15)
			{
				game_panel.asset_setter.replace4();
				timer4.reset();
			}
		}
    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String obj_name = game_panel.obj[i].name;
            switch (obj_name) {
                case "notif":
                	game_panel.playSF(5);
                    has_order++;
                    inv.add(game_panel.obj[i].num_assigned);
                    if(game_panel.obj[i].num_link==1)
					{
						if(timer1==null)
						{
							timer1 = new Timer(game_panel);
						}
						else
						{
							timer1.go();
						}
					}
					else if(game_panel.obj[i].num_link==2)
					{
						if(timer2==null)
						{
							timer2 = new Timer(game_panel);
						}
						else
						{
							timer2.go();
						}
					}
					else if(game_panel.obj[i].num_link==3)
					{
						if(timer3==null)
						{
							timer3 = new Timer(game_panel);
						}
						else
						{
							timer3.go();
						}
					}
					else if(game_panel.obj[i].num_link==4)
					{
						if(timer4==null)
						{
							timer4 = new Timer(game_panel);
						}
						else
						{
							timer4.go();
						}
					}
                    game_panel.obj[i] = null;
                    System.out.println("Order in Inventory!");
                    break;
                case "door":
                    if (has_order > 0 && inv.contains(game_panel.obj[i].num_assigned))
                    {
                    	game_panel.playSF(6);
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

    public void draw(Graphics g2) {
        BufferedImage sprite = null;

        switch (direction)
        {
            case "up":
                sprite = (sprite_num == 1) ? up1 : up2;
                break;
            case "down":
                sprite = (sprite_num == 1) ? down1 : down2;
                break;
            case "right":
                sprite = (sprite_num == 1) ? right1 : right2;
                break;
            case "left":
                sprite = (sprite_num == 1) ? left1 : left2;
                break;
        }
        g2.drawImage(sprite, x_pos, y_pos, game_panel.tile_size, game_panel.tile_size, null);
    }
}
