package object;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class OBJ_notif extends SuperObject
{
	public OBJ_notif(int num)
	{
		num_assigned = randNum();
		num_link=num;
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

	public int randNum()
	{
		int random_number;
		Random rand = new Random();
		return random_number = rand.nextInt(6)+1;
	}
}
