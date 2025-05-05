package finalproj;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	public boolean up_pressed, left_pressed, down_pressed, right_pressed;

	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W)
		{
			up_pressed = true;
		}
		if(code == KeyEvent.VK_A)
		{
			left_pressed = true;
		}
		if(code == KeyEvent.VK_S)
		{
			down_pressed = true;
		}
		if(code == KeyEvent.VK_D)
		{
			right_pressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{

		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W)
		{
			up_pressed = false;
		}
		if(code == KeyEvent.VK_A)
		{
			left_pressed = false;
		}
		if(code == KeyEvent.VK_S)
		{
			down_pressed = false;
		}
		if(code == KeyEvent.VK_D)
		{
			right_pressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub

	}

}
