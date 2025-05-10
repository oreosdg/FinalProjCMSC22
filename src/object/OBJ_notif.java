package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_notif extends SuperObject
{
	public OBJ_notif(int num)
	{
		num_assigned = num;
		name = "notif";
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream("/objects/notif.png"));
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
