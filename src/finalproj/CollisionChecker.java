package finalproj;

import entity.Entity;

public class CollisionChecker
{
	GamePanel game_panel;
	public CollisionChecker(GamePanel game_panel)
	{
		this.game_panel = game_panel;
	}

	public void checkTile(Entity entity) //this method accepts the entity and checks if that entity can pass through a certain tile
	{
		int entity_left_x = entity.x_pos + entity.solid_area.x; //this gets the entity's x position. to be precise, it gets the left side of the entity
		int entity_right_x = entity.x_pos + entity.solid_area.x + entity.solid_area.width;//this gets the right side of the entity
		int entity_top_y = entity.y_pos + entity.solid_area.y;//this gets the top side of the entity
		int entity_bot_y = entity.y_pos + entity.solid_area.y + entity.solid_area.height; //and this gets the bottom side of the entity

		int entity_left_col = entity_left_x/game_panel.tile_size;
		int entity_right_col = entity_right_x/game_panel.tile_size;
		int entity_top_row = entity_top_y/game_panel.tile_size;
		int entity_bot_row = entity_bot_y/game_panel.tile_size;

		int tile_num1, tile_num2;

		switch(entity.direction)
		{
			case "up":
				entity_top_row = (entity_top_y - entity.speed)/game_panel.tile_size; //this equation predicts where the player will be when w is pressed
				tile_num1 = game_panel.tile_handler.mapTileNum[entity_left_col][entity_top_row]; //this is equal to the top left corner of the invisible area
				tile_num2 = game_panel.tile_handler.mapTileNum[entity_right_col][entity_top_row];
				if(game_panel.tile_handler.tile[tile_num1].collision == true || game_panel.tile_handler.tile[tile_num2].collision == true)
				{
					entity.collision_on = true;
				}
				break;
			case "down":
				entity_bot_row = (entity_bot_y + entity.speed)/game_panel.tile_size; //this equation predicts where the player will be when w is pressed
				tile_num1 = game_panel.tile_handler.mapTileNum[entity_left_col][entity_bot_row]; //this is equal to the top left corner of the invisible area
				tile_num2 = game_panel.tile_handler.mapTileNum[entity_right_col][entity_bot_row];
				if(game_panel.tile_handler.tile[tile_num1].collision == true || game_panel.tile_handler.tile[tile_num2].collision == true)
				{
					entity.collision_on = true;
				}
				break;
			case "right":
				entity_right_col = (entity_right_x - entity.speed)/game_panel.tile_size; //this equation predicts where the player will be when w is pressed
				tile_num1 = game_panel.tile_handler.mapTileNum[entity_right_col][entity_top_row]; //this is equal to the top left corner of the invisible area
				tile_num2 = game_panel.tile_handler.mapTileNum[entity_right_col][entity_bot_row];
				if(game_panel.tile_handler.tile[tile_num1].collision == true || game_panel.tile_handler.tile[tile_num2].collision == true)
				{
					entity.collision_on = true;
				}
				break;
			case "left":
				entity_left_col = (entity_left_x + entity.speed)/game_panel.tile_size; //this equation predicts where the player will be when w is pressed
				tile_num1 = game_panel.tile_handler.mapTileNum[entity_left_col][entity_top_row]; //this is equal to the top left corner of the invisible area
				tile_num2 = game_panel.tile_handler.mapTileNum[entity_left_col][entity_bot_row];
				if(game_panel.tile_handler.tile[tile_num1].collision == true || game_panel.tile_handler.tile[tile_num2].collision == true)
				{
					entity.collision_on = true;
				}
				break;
		}
	}
}
