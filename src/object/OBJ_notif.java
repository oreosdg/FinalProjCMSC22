package object;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class OBJ_notif extends SuperObject
{
	public OBJ_notif(int num)
	{
		num_assigned = randNum();
		num_link = num;
		name = "notif";

		getObjImage();
	}

	private void getObjImage()
	{
		try
		{
			switch(num_assigned)
			{
				case 1:
					image = ImageIO.read(getClass().getResourceAsStream("/objects/red.png"));
					break;
				case 2:
					image = ImageIO.read(getClass().getResourceAsStream("/objects/blue.png"));
					break;
				case 3:
					image = ImageIO.read(getClass().getResourceAsStream("/objects/yellow.png"));
					break;
				case 4:
					image = ImageIO.read(getClass().getResourceAsStream("/objects/purple.png"));
					break;
				case 5:
					image = ImageIO.read(getClass().getResourceAsStream("/objects/orange.png"));
					break;
				case 6:
					image = ImageIO.read(getClass().getResourceAsStream("/objects/lblue.png"));
					break;
			}
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
