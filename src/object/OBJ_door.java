package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_door extends SuperObject
{
	public OBJ_door(int num)
	{
		name = "door";
		num_assigned = num;
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}