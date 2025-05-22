package finalproj;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
	public boolean w_pressed, a_pressed, s_pressed, d_pressed;
	public boolean up_pressed, left_pressed, down_pressed, right_pressed;

	@Override
	public void keyPressed(KeyEvent e)
	{
		int code = e.getKeyCode();

		if(code == KeyEvent.VK_W)
		{
			w_pressed = true;
		}
		if(code == KeyEvent.VK_A)
		{
			a_pressed = true;
		}
		if(code == KeyEvent.VK_S)
		{
			s_pressed = true;
		}
		if(code == KeyEvent.VK_D)
		{
			d_pressed = true;
		}

		if(code == KeyEvent.VK_UP)
		{
			up_pressed = true;
		}
		if(code == KeyEvent.VK_LEFT)
		{
			left_pressed = true;
		}
		if(code == KeyEvent.VK_DOWN)
		{
			down_pressed = true;
		}
		if(code == KeyEvent.VK_RIGHT)
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
			w_pressed = false;
		}
		if(code == KeyEvent.VK_A)
		{
			a_pressed = false;
		}
		if(code == KeyEvent.VK_S)
		{
			s_pressed = false;
		}
		if(code == KeyEvent.VK_D)
		{
			d_pressed = false;
		}

		if(code == KeyEvent.VK_UP)
		{
			up_pressed = false;
		}
		if(code == KeyEvent.VK_LEFT)
		{
			left_pressed = false;
		}
		if(code == KeyEvent.VK_DOWN)
		{
			down_pressed = false;
		}
		if(code == KeyEvent.VK_RIGHT)
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
