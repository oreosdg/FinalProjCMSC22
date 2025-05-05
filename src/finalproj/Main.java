package finalproj;

import javax.swing.JFrame;

public class Main
{
	public static void main(String[] args)
	{
		JFrame window = new JFrame(); //window to start
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Elbi Express");

		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);

		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);

		gamePanel.startGameThread();
	}
}
