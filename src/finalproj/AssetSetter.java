package finalproj;

import object.OBJ_door;
import object.OBJ_notif;

public class AssetSetter
{
	GamePanel game_panel;

	public AssetSetter(GamePanel game_panel)
	{
		this.game_panel = game_panel;
	}

	public void setObject()
	{
		game_panel.obj[0] = new OBJ_notif(1);
		game_panel.obj[0].x_pos = 3 * game_panel.tile_size;
		game_panel.obj[0].y_pos = 4 * game_panel.tile_size;

		game_panel.obj[1] = new OBJ_notif(2);
		game_panel.obj[1].x_pos = 7 * game_panel.tile_size;
		game_panel.obj[1].y_pos = 4 * game_panel.tile_size;

		game_panel.obj[2] = new OBJ_notif(3);
		game_panel.obj[2].x_pos = 13 * game_panel.tile_size;
		game_panel.obj[2].y_pos = 4 * game_panel.tile_size;

		game_panel.obj[3] = new OBJ_notif(4);
		game_panel.obj[3].x_pos = 17 * game_panel.tile_size;
		game_panel.obj[3].y_pos = 4 * game_panel.tile_size;

		game_panel.obj[4] = new OBJ_door(1);
		game_panel.obj[4].x_pos = 5 * game_panel.tile_size;
		game_panel.obj[4].y_pos = 8 * game_panel.tile_size;

		game_panel.obj[5] = new OBJ_door(2);
		game_panel.obj[5].x_pos = 5 * game_panel.tile_size;
		game_panel.obj[5].y_pos = 12 * game_panel.tile_size;

		game_panel.obj[6] = new OBJ_door(3);
		game_panel.obj[6].x_pos = 15 * game_panel.tile_size;
		game_panel.obj[6].y_pos = 8 * game_panel.tile_size;

		game_panel.obj[7] = new OBJ_door(4);
		game_panel.obj[7].x_pos = 15 * game_panel.tile_size;
		game_panel.obj[7].y_pos = 12 * game_panel.tile_size;
	}

	public void replace()
	{
		for(int i=0; i<4; i++)
		{
			if(game_panel.obj[i]==null)
			{
				System.out.println("Notif " + (i+1) + " has been deleted");
				switch (i)
				{
					case 0:
						game_panel.obj[0] = new OBJ_notif(1);
						game_panel.obj[i].x_pos = 3 * game_panel.tile_size;
						game_panel.obj[i].y_pos = 4 * game_panel.tile_size;
						break;
				}
			}
		}
	}
}
